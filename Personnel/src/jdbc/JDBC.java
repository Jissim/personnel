package jdbc;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import serialisation.*;
import personnel.*;

public class JDBC implements Passerelle 
{
	Connection connection;

	public JDBC()
	{
		try
		{
			Class.forName(Credentials.getDriverClassName());
			connection = DriverManager.getConnection(Credentials.getUrl(), Credentials.getUser(), Credentials.getPassword());
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Pilote JDBC non installé.");
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}
	

	@Override
	public GestionPersonnel getGestionPersonnel()
	{
		GestionPersonnel gestionPersonnel = new GestionPersonnel();
		try 
		{
			String requete = "select * from ligue";
			Statement instruction = connection.createStatement();
			ResultSet ligues = instruction.executeQuery(requete);
			
			while (ligues.next())
			{
				gestionPersonnel.addLigue(ligues.getInt("num_ligue"), ligues.getString("nom_ligue"));
		        PreparedStatement response = connection.prepareStatement("SELECT * FROM employe WHERE num_ligue = ?");
		        response.setInt(1, ligues.getInt("num_ligue"));
		        ResultSet employe = response.executeQuery();
		        Ligue ligue = gestionPersonnel.getLigues().last();
			
			while (employe.next()) {
				
				int id = employe.getInt("id_employe");
		        String  nom = employe.getString("nom_employe");
			    String  prenom = employe.getString("prenom_employe");
				String	mail = employe.getString("mail_employe");
	            String	password = employe.getString("password_employe");
				
		        LocalDate dateArrivee = employe.getString("dateArrivee_employe") != null ?
		        		                LocalDate.parse(employe.getString("dateArrivee_employe")) : null;
			    LocalDate dateDepart =  employe.getString("dateDepart_employe") != null ? 
			    		                LocalDate.parse(employe.getString("dateDepart_employe")) : null;
				
			    Employe employes = ligue.addEmploye(nom, prenom, mail, password, dateArrivee, dateDepart);
			    
			    if(employe.getBoolean("admin")) {
			    	ligue.setAdministrateur(employes);
			    }

			} }
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return gestionPersonnel;
	}

	@Override
	public void sauvegarderGestionPersonnel(GestionPersonnel gestionPersonnel) throws SauvegardeImpossible 
	{
		close();
	}
	
	public void close() throws SauvegardeImpossible
	{
		try
		{
			if (connection != null)
				connection.close();
		}
		catch (SQLException e)
		{
			throw new SauvegardeImpossible(e);
		}
	}
	
	@Override
	public int Insert(Ligue ligue) throws SauvegardeImpossible 
	{
		try 
		{
			PreparedStatement instruction;
			instruction = connection.prepareStatement("insert into ligue (nom_ligue) values(?)", Statement.RETURN_GENERATED_KEYS);
			instruction.setString(1, ligue.getNom());		
			instruction.executeUpdate();
			ResultSet id = instruction.getGeneratedKeys();
			id.next();
			return id.getInt(1);
		} 
		catch (SQLException exception) 
		{
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}		
	}
	
	@Override
	public int Insert(Employe employe) throws SauvegardeImpossible 
	{
		try {
			
			PreparedStatement instruction2;
			instruction2 = connection.prepareStatement("insert into employe (nom_employe, prenom_employe, mail_employe, password_employe, dateArrivee_employe, num_ligue) values(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			instruction2.setString(1, employe.getNom());		
			instruction2.setString(2, employe.getPrenom());	
			instruction2.setString(3, employe.getMail());
			instruction2.setString(4, employe.getPassword());
			instruction2.setString(5, employe.getDateArrivee() == null ? null :  String.valueOf(employe.getDateArrivee()));
			instruction2.setInt(6, employe.getLigue().getId());
			instruction2.executeUpdate();
			ResultSet id = instruction2.getGeneratedKeys();
			id.next();
			return id.getInt(1);
		}
		catch (SQLException exception)
		{
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}
	}
	
	@Override
	public void UpdateLigue(Ligue ligue) throws SauvegardeImpossible 
	{
		try
		{
			PreparedStatement instruction;
			instruction = connection.prepareStatement("UPDATE ligue SET nom_ligue = ? WHERE num_ligue = ?");
			instruction.setString(1, ligue.getNom());
			instruction.setInt(2, ligue.getId());
			instruction.executeUpdate();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new SauvegardeImpossible(e);
		}
	}
	
//	@Override
//	public void UpdateEmploye(Employe employe) throws SauvegardeImpossible 
//	{
//		try
//		{
//			PreparedStatement instruction;
//			instruction = connection.prepareStatement("UPDATE employe SET nom_employe = ?, prenom_employe = ?, mail_employe = ? , password_employe = ? WHERE id_employe = ?");
//			instruction.setString(1, employe.getNom());
//			instruction.setString(2, employe.getPrenom());
//			instruction.setString(3, employe.getMail());
//			instruction.setString(4, employe.getPassword());
//			instruction.setInt(5, employe.getId());
//			instruction.executeUpdate();
//		}
//		catch (SQLException e) 
//		{
//			e.printStackTrace();
//			throw new SauvegardeImpossible(e);
//		}
//	}
	
	public void UpdateEmploye(Employe employe, String dataList) throws SauvegardeImpossible 
	{
		try 
		{
			PreparedStatement instruction;
	        instruction = connection.prepareStatement("UPDATE employe SET " + dataList + "= ? WHERE id_employe = ?");
	
			Map <String, String> map = new HashMap<>();
					map.put("nom_employe", employe.getNom());
					map.put("prenom_employe", employe.getPrenom());
					map.put("mail_employe", employe.getMail());
					map.put("password_employe", employe.getPassword());
					map.put("dateArrivee_employe", String.valueOf(employe.getDateArrivee()).isEmpty() ? null : String.valueOf(employe.getDateArrivee()));
					map.put("dateDepart_employe", String.valueOf(employe.getDateDepart()).isEmpty() ? null : String.valueOf(employe.getDateDepart()));
		instruction.setString(1, map.get(dataList));
	    instruction.setInt(2, employe.getId());
			instruction.executeUpdate();
		}
		catch (SQLException e) 
		{
			
			throw new SauvegardeImpossible(e);
	}
	}
	
	@Override
	public void DeleteLigue(Ligue ligue) throws SauvegardeImpossible 
	{	
		try
		{
			PreparedStatement listLigue;
			listLigue = connection.prepareStatement("DELETE FROM ligue WHERE num_ligue = ?");
			listLigue.setInt(1, ligue.getId());
			listLigue.executeUpdate();
			System.out.println("Ligue " + ligue.getNom() + " supprim�");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new SauvegardeImpossible(e);
		}
		
	}	

	@Override
	public void DeleteEmploye(Employe employe) throws SauvegardeImpossible 
	{	
		try
		{
			PreparedStatement listEmploye;
			listEmploye = connection.prepareStatement("DELETE FROM employe WHERE id_employe = ?");
			listEmploye.setInt(1, employe.getId());
			listEmploye.executeUpdate();
			System.out.println("Employe " + employe.getNom() + " supprim�");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new SauvegardeImpossible(e);
		}
		
	}
    public static void main(String[] args) throws SauvegardeImpossible {
        JDBC jdbc = new JDBC();
        
        // Appel de la méthode getGestionPersonnel pour récupérer les ligues
        GestionPersonnel gestionPersonnel = jdbc.getGestionPersonnel();
        
        // Affichage des ligues récupérées
        System.out.println(gestionPersonnel);
        
        // Création d'une nouvelle ligue et insertion dans la base de données
        Ligue ligue = new Ligue(gestionPersonnel, "Ligue de test");
        int id_ligue = jdbc.Insert(ligue);
        System.out.println("Ligue insérée avec l'id : " + id_ligue);
        
        Ligue ligue2 = new Ligue(gestionPersonnel, "Ligue de Foot");
        int id_ligue2 = jdbc.Insert(ligue2);
        System.out.println("Ligue insérée avec l'id : " + id_ligue2);
        
        
        // Fermeture de la connexion JDBC
        try {
            jdbc.close();
        } catch (SauvegardeImpossible e) {
            e.printStackTrace();
        }
    }

}



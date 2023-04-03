 package testsUnitaires;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import personnel.*;

class TestLigue 
{
	
	GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();
	
	@Test
	void createLigue() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		assertEquals("Fléchettes", ligue.getNom());
	}

	@Test
	void addEmploye() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty",LocalDate.now(),(LocalDate.of(2023, 12, 20))); 
		assertEquals(employe, ligue.getEmployes().first());
	}

	@Test
	void GetNom ()throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		assertEquals("Fléchettes", ligue.getNom());
	}
	
	@Test
	void SetNom() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		ligue.setNom("VolleyBall");
		assertEquals("VolleyBall", ligue.getNom());
	}
	
	@Test
	void GetAdministrateur () throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty",LocalDate.now(),(LocalDate.of(2023, 12, 20)));
		ligue.setAdministrateur (employe);
		assertEquals(employe, ligue.getAdministrateur());
		
	}
	
	@Test
	void SetAdministrateur () throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty",LocalDate.now(),(LocalDate.of(2023, 12, 20)));
		ligue.setAdministrateur (employe);
		assertEquals(employe, ligue.getAdministrateur());
		
	}
	
	@Test
	void GetEmployes () throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe1 = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty",LocalDate.now(),(LocalDate.of(2023, 12, 20)));
		Employe employe2 = ligue.addEmploye("Dupont", "Pierre", "p.dupont@gmail.com", "azerty",LocalDate.now(),(LocalDate.of(2023, 12, 20)));
		assertEquals(employe1, ligue.getEmployes().first());
		assertEquals(employe2, ligue.getEmployes().last());
		
	}
	
	@Test
	void Remove () throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		ligue.remove ();
		assertEquals(false, gestionPersonnel.getLigues().equals(ligue));	
	}
	
	@Test
	void CompareTo() throws SauvegardeImpossible
	{
		Ligue ligue1 = gestionPersonnel.addLigue("Fléchettes");
		Ligue ligue2 = gestionPersonnel.addLigue("Equitation");
		assertEquals(0, ligue1.compareTo(ligue1));
		assertEquals(1, ligue1.compareTo(ligue2));
	}
	
	@Test
	void ToString () throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		assertEquals("Fléchettes", ligue.toString());
	}
	
	
	
}	
package testsUnitaires;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import personnel.*;

class TestEmploye {
	
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
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty"); 
		assertEquals(employe, ligue.getEmployes().first());
	}

	
	@Test
	void estAdmin () throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe1 = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty");
		Employe employe2 = ligue.addEmploye("Dupont", "Pierre", "p.dupont@gmail.com", "azerty");
		ligue.setAdministrateur (employe1);
		assertEquals(true, employe1.estAdmin(ligue));
		assertEquals(false, employe2.estAdmin(ligue));
//		assertTrue(employe1.estAdmin(ligue));
//		AssertTrue affirme qu'une condition est vraie,
//		mais vous devez encore coder cette condition
//		pour qu'elle soit évaluée au moment de l'exécution.
	}
	
	@Test
	void estRoot () throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe1 = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty");
		Employe employe2 = ligue.addEmploye("Dupont", "Pierre", "p.dupont@gmail.com", "azerty");
		 employe1 = gestionPersonnel.getRoot();
		 assertEquals(true, employe1.estRoot());
		 assertEquals(false, employe2.estRoot()); 
	}
	
	@Test
	void GetNom () throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty");
		assertEquals("Bouchard", employe.getNom());
	}
	
	@Test
	void SetNom () throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty");
		employe.setNom("Boulanger");
		assertEquals("Boulanger", employe.getNom());
	}
	
	@Test
	void GetPrenom () throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty");
		assertEquals("Gérard", employe.getPrenom());
	}
	
	@Test
	void SetPrenomNom () throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty");
		employe.setPrenom("Patrick");
		assertEquals("Patrick", employe.getPrenom());
	}
	@Test
	void GetMail () throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty");
		assertEquals("g.bouchard@gmail.com", employe.getMail());
	}
	
	@Test
	void SetMail () throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty");
		employe.setMail("gerard.bouchard@gmail.com");
		assertEquals("gerard.bouchard@gmail.com", employe.getMail());
	}
	
	@Test
	void CheckPassword () throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty");
		assertEquals(true, employe.checkPassword("azerty"));
		assertEquals(false, employe.checkPassword("qwerty"));
	}
	
	@Test
	void SetPasswordl () throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty");
		employe.setPassword("qwerty");
		assertEquals(true, employe.checkPassword("qwerty"));
		assertEquals(false, employe.checkPassword("azerty"));
	}
	
	@Test
	void GetLigue () throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty");
		assertEquals(ligue, employe.getLigue());
	}
	
	@Test
	void CompareTo() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe1 = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty");
		Employe employe2 = ligue.addEmploye("Dupont", "Pierre", "p.dupontd@gmail.com", "azerty");
		assertEquals(0, employe1.compareTo(employe1));
		assertEquals(-2, employe1.compareTo(employe2));
	}
	
//	@Test
//	void ToString () throws SauvegardeImpossible
//	{
//		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
//		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty");
//		assertEquals("Bouchard Gérard g.bouchard@gmail.com (Fléchettes)", employe.toString());
//		employe = gestionPersonnel.getRoot();
//		assertEquals("Bouchard Gérard g.bouchard@gmail.com (super-utilisateur)", employe.toString());
//	}
//	
	
	
}

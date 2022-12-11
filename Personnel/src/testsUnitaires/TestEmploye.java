package testsUnitaires;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
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
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty",(LocalDate.of(2023, 12, 20))); 
		assertEquals(employe, ligue.getEmployes().first());
	}
	
	@Test
	void GetDateArrivee ()throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty",(LocalDate.of(2023, 12, 20)));
		assertEquals(LocalDate.now(), employe.getDateArrivee());
		
	}
	
	
	@Test
	void GetDateDepart () throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty",(LocalDate.of(2023, 12, 20)));
		assertEquals((LocalDate.of(2023, 12, 20)), employe.getDateDepart());
		
	}
	
	@Test
	void SetDateDepart () throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty",(LocalDate.of(2023, 12, 20)));
		employe.setDateDepart((LocalDate.of(2023, 10, 10)));
		assertEquals((LocalDate.of(2023, 10, 10)), employe.getDateDepart());
	}
	
	
	
	@Test
	void estAdmin () throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe1 = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty",(LocalDate.of(2023, 12, 20)));
		Employe employe2 = ligue.addEmploye("Dupont", "Pierre", "p.dupont@gmail.com", "azerty",(LocalDate.of(2023, 04, 20)));
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
		Employe employe1 = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty",(LocalDate.of(2023, 12, 20)));
		Employe employe2 = ligue.addEmploye("Dupont", "Pierre", "p.dupont@gmail.com", "azerty",(LocalDate.of(2023, 12, 20)));
		 employe1 = gestionPersonnel.getRoot();
		 assertEquals(true, employe1.estRoot());
		 assertEquals(false, employe2.estRoot()); 
	}
	
	@Test
	void GetNom () throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty",(LocalDate.of(2023, 12, 20)));
		assertEquals("Bouchard", employe.getNom());
	}
	
	@Test
	void SetNom () throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty",(LocalDate.of(2023, 12, 20)));
		employe.setNom("Boulanger");
		assertEquals("Boulanger", employe.getNom());
	}
	
	@Test
	void GetPrenom () throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty",(LocalDate.of(2023, 12, 20)));
		assertEquals("Gérard", employe.getPrenom());
	}
	
	@Test
	void SetPrenomNom () throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty",(LocalDate.of(2023, 12, 20)));
		employe.setPrenom("Patrick");
		assertEquals("Patrick", employe.getPrenom());
	}
	@Test
	void GetMail () throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty",(LocalDate.of(2023, 12, 20)));
		assertEquals("g.bouchard@gmail.com", employe.getMail());
	}
	
	@Test
	void SetMail () throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty",(LocalDate.of(2023, 12, 20)));
		employe.setMail("gerard.bouchard@gmail.com");
		assertEquals("gerard.bouchard@gmail.com", employe.getMail());
	}
	
	@Test
	void CheckPassword () throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty",(LocalDate.of(2023, 12, 20)));
		assertEquals(true, employe.checkPassword("azerty"));
		assertEquals(false, employe.checkPassword("qwerty"));
	}
	
	@Test
	void SetPasswordl () throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty",(LocalDate.of(2023, 12, 20)));
		employe.setPassword("qwerty");
		assertEquals(true, employe.checkPassword("qwerty"));
		assertEquals(false, employe.checkPassword("azerty"));
	}
	
	@Test
	void GetLigue () throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty",(LocalDate.of(2023, 12, 20)));
		assertEquals(ligue, employe.getLigue());
	}
	
	@Test
	void CompareTo() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe1 = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty",(LocalDate.of(2023, 12, 20)));
		Employe employe2 = ligue.addEmploye("Dupont", "Pierre", "p.dupontd@gmail.com", "azerty",(LocalDate.of(2023, 04, 20)));
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

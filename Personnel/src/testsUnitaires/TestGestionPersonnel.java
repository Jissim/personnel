package testsUnitaires;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import personnel.*;

class TestGestionPersonnel {
	
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
	void getLigue() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye ("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty");
		ligue.setAdministrateur(employe);
		assertEquals(ligue, employe.getLigue());
	}
	
	@Test
	void GetLigues () throws SauvegardeImpossible
	{
		Ligue ligue1 = gestionPersonnel.addLigue("Fléchettes");
		Ligue ligue2 = gestionPersonnel.addLigue("VolleyBall");
		assertEquals(ligue1, gestionPersonnel.getLigues().first());
		assertEquals(ligue2, gestionPersonnel.getLigues().last());
		
	}
	
//	@Test
//	void addLigue() throws SauvegardeImpossible
//	{
//		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
//		assertEquals(ligue, gestionPersonnel.addLigue("Fléchettes"));
//	}
//	
	
}

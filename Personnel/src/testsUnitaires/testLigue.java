package testsUnitaires;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import personnel.*;

class testLigue 
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
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty"); 
		assertEquals(employe, ligue.getEmployes().first());
	}
	@Test
	void testGetNom() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("");
		ligue.setNom("Fléchettes");
		assertEquals("Fléchettes", ligue.getNom());
	}	
	@Test
	void testSetNom() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		assertEquals("Fléchettes", ligue.getNom());

	}
	@Test
	void testGetAdministrateur() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe admin = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty");
		ligue.setAdministrateur(admin);
		assertEquals(admin, ligue.getAdministrateur());

	}
	@Test
	
	void testSetAdministrateur() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe admin = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty");
		ligue.setAdministrateur(admin);
		assertEquals(admin, ligue.getAdministrateur());
		
	}
	
	@Test
	void testGetEmploye () throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty");
		Employe admin = ligue.addEmploye("Hadrien", "Bernard", "B.rongier@gmail.com", "qwerty");
		assertEquals(employe, ligue.getEmployes().first());
        assertEquals(admin, ligue.getEmployes().last());
	}
	@Test
	void TestRemove() throws SauvegardeImpossible
    {
        Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
        Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty");
        employe.remove();
        assertEquals(0, ligue.getEmployes().size());
    }
	@Test
    void TestCompareTo() throws SauvegardeImpossible
    {
        Ligue flechettes = gestionPersonnel.addLigue("Basketball");
        Ligue basketball = gestionPersonnel.addLigue("Basketball");
        assertEquals(0, basketball.compareTo(flechettes));
    }
	@Test
    void TestToString() throws SauvegardeImpossible
    {
        Ligue basketball = gestionPersonnel.addLigue("Basketball");
        assertEquals("Basketball", basketball.toString());
    }
}

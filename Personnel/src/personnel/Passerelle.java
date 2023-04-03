package personnel;

public interface Passerelle 
{
	public GestionPersonnel getGestionPersonnel();
	public void sauvegarderGestionPersonnel(GestionPersonnel gestionPersonnel)  throws SauvegardeImpossible;
	public int Insert(Ligue ligue) throws SauvegardeImpossible;
	public int Insert(Employe employe) throws SauvegardeImpossible;
	public void UpdateLigue(Ligue ligue) throws SauvegardeImpossible;
	public void UpdateEmploye(Employe employe, String string) throws SauvegardeImpossible;
	public void DeleteLigue(Ligue ligue) throws SauvegardeImpossible ;
	public void DeleteEmploye(Employe employe) throws SauvegardeImpossible;
}

public class Joueur
{
	// Attributs
	private int iScore;
	private String sCouleur;
	private Tuile[] ensBatiment;
	private Ouvrier[] ensOuvrier;


	public Joueur(String sCouleur)
	{
		this.iScore=0;
		this.sCouleur = sCouleur;
		this.ensBatiment = new Tuile[5]; // Pour le futur c'est forcement le nombre de maison dispo  a la base
		this.ensOuvrier = new Ouvrier[3]; // Depend toujours du nbJoueurs
	}

	public void majScore(int i)
	{
		this.iScore+=i;
	}
}
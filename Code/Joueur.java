public class Joueur extends Banque
{
	// Attributs
	private static int nbJoueur = 0;

	private String sCouleur;
	private Tuile[] ensBatiment;
	private Ouvrier[] ensOuvrier;
	private int iNumJoueur;
	private boolean bAJouer;


	public Joueur(String sCouleur)
	{
		super(3);
		this.sCouleur    = sCouleur;
		this.ensBatiment = new Tuile[5]; // Pour le futur c'est forcement le nombre de maison dispo  a la base
		this.ensOuvrier  = new Ouvrier[3]; // Depend toujours du nbJoueurs
		this.iNumJoueur  = ++ Joueur.nbJoueur;
		this.bAJouer     = false;
	}
	
	public int getNumJoueur()
	{
		return this.iNumJoueur;
	}

	public void setAction(boolean bJouer)
	{
		this.bAJouer = bJouer;
	}

	public boolean aJouer()
	{
		return this.bAJouer;
	}

	public String toString()
	{
		String sRet = "Joueur n°" + this.iNumJoueur + ", " +  " couleur : " + this.sCouleur+ "\n";
		sRet+=super.toString();
		return sRet;	

	}
}
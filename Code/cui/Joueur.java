package littletown.cui;



public class Joueur extends Banque
{
	// Attributs
	private static int iNbJoueur = 0;

	private int iScore;
	private String sCouleur;
	private String sNom;


	private Tuile[] ensBatiment;
	private Ouvrier[] ensOuvrier;
	private int iNumJoueur;
	private boolean bAJouer;

	public Joueur(String sCouleur, String sNom)
	{
		super(3);
		this.iScore      = 0;
		this.sCouleur    = sCouleur;
		this.sNom        = sNom;
		this.ensBatiment = new Tuile[5];   // Pour le futur c'est forcement le nombre de maison dispo  a la base
		this.ensOuvrier  = new Ouvrier[3]; // Depend toujours du nbJoueurs
		this.iNumJoueur  = ++ Joueur.iNbJoueur;
		this.bAJouer     = false;
	}

	public void masterClass(int attribut)
	{
		char[] rsc = {'P','A','E','C','M','S'};   

		for(int i=0;i<rsc.length;i++)
			super.ajouterEnlever(rsc[i],999*attribut);
	}

	public void echangerRscJoueurVBanque( Banque b, char rsc, int iNb )
	{
		b.ajouterEnlever   ( rsc, iNb );
		this.ajouterEnlever( rsc,-iNb );
	}

	public void changeScore( int iScore ) { this.iScore += iScore; }
	
	public String getCouleur() { return this.sCouleur;   }
	public int getScore     () { return this.iScore;     }
	public String getNom    () { return this.sNom;       }
	public int getNumJoueur () { return this.iNumJoueur; }
	public boolean aJouer   () { return this.bAJouer;    }

	public void setAction(boolean bJouer) 
	{
		this.bAJouer = bJouer;
	}

	public void majScore(int i)
	{
		this.iScore += i;
	}

	public String toString()
	{
		String sRet = "Joueur n°" + this.iNumJoueur + ", ( " + this.sNom + " ), " +  " couleur : " + this.sCouleur+ "\n";
		
		sRet += super.toString();
		
		sRet += "\n" +"Nombre de Point  : " + this.iScore;
		
		return sRet;

	}
}

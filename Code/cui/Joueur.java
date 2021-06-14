package littletown.cui;


/**
* Gère les informations des joueurs.
*/
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

	/**
	* Constructeur de la classe Joueur.
	*
	* @param sCouleur
	*			Couleur du joueur.
	* @param sNom
	*			Nom du joueur.
	*/
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

	/**
	* Méthode spécial.
	*
	* @param attribut
	*			Nombre de ressources du joueur.
	*/
	public void masterClass(int attribut)
	{
		char[] rsc = {'P','A','E','C','M','S'};   

		for(int i=0;i<rsc.length;i++)
			super.ajouterEnlever(rsc[i],999*attribut);
	}

	/**
	* Permet d'échanger les ressources entre le joueur et la banque
	* @param b
	*		Change de ressource avec un autre banque de joueur.
	* @param rsc
	*		Type de ressource.
	* @param iNb
	*		Nombre de ressource a échanger.
	*/
	public void echangerRscJoueurVBanque( Banque b, char rsc, int iNb )
	{
		b.ajouterEnlever   ( rsc, iNb );
		this.ajouterEnlever( rsc,-iNb );
	}

	/**
	* Change le score du joueur. Le changement du score peut être négatif ou positif.
	*
	* @param iScore
	*			Score à changer.
	*/
	public void changeScore( int iScore ) { this.iScore += iScore; }
	
	/**
	* Retourne la couleur du joueur.
	* @return Couleur du joueur.
	*/
	public String getCouleur() { return this.sCouleur;   }

	/**
	* Retoune le score du joueur.
	*
	* @return Score du joueur.
	*/
	public int getScore     () { return this.iScore;     }


	/**
	* Retourne le nom du joueur.
	*
	* @return Nom du joueur.
	*/
	public String getNom    () { return this.sNom;       }

	/**
	* Retourne le numéro du joueur.
	*
	* @return Numéro du joueur.
	*/
	public int getNumJoueur () { return this.iNumJoueur; }

	/**
	* Détermine si le joueur à déjà jouer.
	*
	* @return Retourne un boolean pour détermine si le joueur à déjà jouer.
	*/
	public boolean aJouer   () { return this.bAJouer;    }

	/**
	* Savoir si le joueur à jouer ou pas.
	*
	* @param bJouer
	*			Vérifie si le joueur peut effectuer une action.
	*/
	public void setAction(boolean bJouer) 
	{
		this.bAJouer = bJouer;
	}

	/**
	* Met à jour le score du joueur et l'incrémente.
	*
	* @param i
	*		Score.
	*/
	public void majScore(int i)
	{
		this.iScore += i;
	}

	/**
	* Retourne les informations de la partie du joueur actuel (nom, couleur et points).
	* 
	* @return Retourne le numéro du joueur, sa couleur et son nombre de point(s).
	*/
	public String toString()
	{
		String sRet = "Joueur n°" + this.iNumJoueur + ", ( " + this.sNom + " ), " +  " couleur : " + this.sCouleur+ "\n";
		
		sRet += super.toString();
		
		sRet += "\n" +"Nombre de Point  : " + this.iScore;
		
		return sRet;

	}
}

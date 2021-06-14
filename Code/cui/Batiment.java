package littletown.cui;


import java.util.ArrayList;

/**
* Classe Batiment (gère les informations des bâtiments).
*/
public class Batiment extends Tuile
{

	private String sCout;
	private String sCoutProduction;
	private String sRevientProduction;

	private Joueur jProprietaire;
	
	private int iScore;

	/**
	* Constructeur de la classe
	* @param sNom
	*		Nom du bâtiment
	* @param sCout
	*		Cout du bâtiment
	* @param sCoutProduction
	*		Cout de production du bâtiment
	* @param sRevientProduction
	*		Production du bâtiment
	* @param iScore
	*		Score(s) obtenu du bâtiment
	*/
	public Batiment ( String sNom, String sCout, String sCoutProduction, String sRevientProduction, int iScore )
	{		
		super ( sNom );
		this.sCout              = sCout;
		this.sCoutProduction    = sCoutProduction;
		this.sRevientProduction = sRevientProduction;

		this.jProprietaire      = null;

		
		this.iScore = iScore;
	}

	/**
	* Constructeur des tuiles déjà présentes sur le plateau
	* @param sNom
	*		Nom du bâtiment
	*/
	public Batiment(String sNom)
	{
		this( sNom, "", "", sNom.charAt(0) + "1" , 0 );
	}

	/**
	* Retourne le coût du bâtiment
	* @return Coût du bâtiment.
	*/
	public String getCout()        { return this.sCout;              }

	/**
	* Retourne le coût de production du bâtiment.
	* @return Coût de production du bâtiment.
	*/
	public String getCoutProd()    { return this.sCoutProduction;    }

	/**
	* Retourne la production obtenu du bâtiment.
	* @return Production du bâtiment.
	*/
	public String getRevientProd() { return this.sRevientProduction; }

	/**
	* Retourne le score du bâtiment.
	* @return Score obtenu du bâtiment.
	*/
	public int    getScore()       { return this.iScore;             }

	/**
	* Retourne le propriétaire du bâtiment.
	* @return Joueur
	*/
	public Joueur getProprietaire()
	{
		return this.jProprietaire;
	}
	
	
	/**
	* Obliger de déclarer cette méthode car elle est abstraite dans Tuile
	*/
	public Ouvrier getOuvrier()
	{
		return null;
	}

	/**
	* Permet de d'attribuer un bâtiment à un joueur.
	* @param jProprietaire
	*					Joueur propriétaire du bâtiment.
	*/
	public void setProprietaire( Joueur jProprietaire )
	{
		this.jProprietaire = jProprietaire;
	}

	/**
	* Retourne l'ensemble des infos du bâtiment.
	* @return Informations du bâtiment sous forme de chaîne de caractères.
	*/
	public String infoBatiment()
	{
		String sRet="\n";

		sRet += "Nom du batiment    : " + super.getNom()          + "\n" +
		        "Cout               : " + this.sCout              + "\n" +
		        "Cout de Production : " + this.sCoutProduction    + "\n" +
		        "Revient Production : " + this.sRevientProduction + "\n" +
		        "Gain de score      : " + this.iScore;

		return sRet;
	}


	/**
	* Affiche les les infos des bâtiments et/ou leurs propriétaires s'il y a.
	* @return Retourne une chaine de caractères (String) des infos des bâtiments et/ou leurs propriétaires
	*/
	public String toString()
	{
		if(this.jProprietaire != null)
			return super.toString() + this.jProprietaire.getNumJoueur();
		else
			return super.toString();
	}
}

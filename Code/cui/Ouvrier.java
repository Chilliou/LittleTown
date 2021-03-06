package littletown.cui;


/**
* Gère les informations des ouvriers.
*/
public class Ouvrier
{
	private int    iPosLigne;
	private int    iPosColonne;
	private Joueur jProprietaire;

	/**
	* Constructeur de la classe
	* @param iPosLigne
	*		Position de l'ouvrier sur une ligne du plateau
	* @param iPosColonne
	*		Position de l'ouvrier sur une colonne du plateau
	* @param jProprietaire
	*		Proprietaire de l'ouvrier
	*/
	public Ouvrier ( int iPosLigne, int iPosColonne, Joueur jProprietaire )
	{
		this.iPosLigne     = iPosLigne;
		this.iPosColonne   = iPosColonne;
		this.jProprietaire = jProprietaire;
	}

	/**
	* Retourne le proprietaire de l'ouvrier.
	*
	* @return Propriétaire de l'ouvrier.
	*
	* @see Joueur
	*/
	public Joueur getProprietaire()
	{
		return this.jProprietaire;
	}

	/**
	* Permets de replacer l'ouvrier à la fin du tour.
	*/
	public void resetOuvrier()
	{
		this.iPosLigne   = -1; 
		this.iPosColonne = -1;
	}
}

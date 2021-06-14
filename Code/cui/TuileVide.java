package littletown.cui;

/**
* Gère les tuiles vides des plateaux. 
*/
public class TuileVide extends Tuile
{
	private Ouvrier ouvrier;

	/**
	* Constructeur de la classe.
	*/
	public TuileVide ()
	{
		super( "Vide" );
		this.ouvrier = null;
	}

	/**
	* Permets de placer un ouvrier sur une tuile vide.
	*
	* @param ovr
	*		Information de l'ouvrier.
	*
	* @see Ouvrier
	*/
	public void setOuvrier ( Ouvrier ovr )
	{
		this.ouvrier = ovr;
	}

	/**
	* Retourne les informations d'un ouvrier sur une tuile.
	*
	* @return Informations d'un ouvrier sur une tuile.
	*
	* @see Ouvrier
	*/
	public Ouvrier getOuvrier ()
	{
		return this.ouvrier;
	}

	/**
	* Retourne le propriétaire de la tuile.
	*
	* @return Propriétaire de la tuile.
	*
	* @see Joueur
	*/
	public Joueur getProprietaire()
	{ 
		if ( this.ouvrier == null )
			return null;

		return this.ouvrier.getProprietaire();
	}

	/**
	* Retourne le propriétaire et le numéro du joueur à qui appartient la tuile sous forme de chaine de caractères.
	*
	* @return Propriétaire et numéro du joueur de la tuile.
	*/
	public String toString ()
	{
		if ( this.ouvrier != null )
			return "O"+this.getProprietaire().getNumJoueur();
		else
			return " ";
	}

}

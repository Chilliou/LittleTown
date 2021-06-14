package littletown.cui;


import java.util.ArrayList;

/**
* Gère les tuiles des plateaux.
*/
public abstract class Tuile
{
	private boolean bActive;
	private String  nom;

	/**
	* Constructeur de la classe.
	* @param nom
	*			Nom de la tuile
	*/
	public Tuile ( String nom )  
	{
		this.nom          = nom;
		this.bActive      = true;
	}

	/**
	* Permets d'obtenir les informations d'un ouvrier placé sur une tuile. (Méthode abstraite)
	*
	* @return Information(s) d'un ouvrier sur une tuile.
	*
	* @see Ouvrier
	*/
	public abstract Ouvrier getOuvrier();

	/**
	* Permets d'obtenir le propriétaire d'une tuile. (Méthode abstraite)
	*
	* @return Proprietaire de la tuile.
	*
	* @see Joueur
	*/
	public abstract Joueur  getProprietaire();

	/**
	* Active/désactive une tuile.
	* @param status
	*			Active/désactive une tuile (boolean).
	*/
	public void setActivation(boolean status)
	{
		this.bActive = status;
	}

	/**
	* Indique si la tuile est activable
	* @return Tuile activable ? (boolean)
	*/
	public boolean isActivable()
	{
		return this.bActive;
	}

	/**
	* Retourne le nom de la tuile.
	* @return Nom de la tuile.
	*/
	public String getNom()
	{
		return this.nom;
	}

	/**
	* Retourne le nom de la tuile sous forme de chaîne de caractère.
	* @return Nom de la tuile.
	*/
	public String toString()
	{
		return this.nom.charAt(0)+"";
	}
}

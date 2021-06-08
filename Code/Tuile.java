import java.util.ArrayList;


public abstract class Tuile
{
	private boolean bActive;
	private String nom;

	public Tuile ( String nom)  
	{
		this.nom     = nom;
		this.bActive = true;
	}

	public abstract Ouvrier getOuvrier();
	public abstract Joueur getProprietaire();

	public void setActivation(boolean status)
	{
		this.bActive = status;
	}

	public boolean isActivable()
	{
		return this.bActive;
	}

	public String getNom()
	{
		return this.nom;
	}

	public String toString()
	{
		return this.nom.charAt(0)+"";
	}
}

import java.util.ArrayList;


public abstract class Tuile
{
	private boolean bActive;
	private String  sNom;

	public Tuile ( String sNom)  
	{
		this.sNom     = sNom;
		this.bActive = true;
	}

	public abstract Ouvrier getOuvrier();
	public abstract Joueur getProprietaire();

	public void setActivation(boolean bStatus)
	{
		this.bActive = bStatus;
	}

	public boolean isActivable()
	{
		return this.bActive;
	}

	public String getNom()
	{
		return this.sNom;
	}

	public String toString()
	{
		return this.sNom.charAt(0) + "";
	}
}

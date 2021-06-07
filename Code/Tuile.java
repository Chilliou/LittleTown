import java.util.ArrayList;


public abstract class Tuile
{
	
	private String nom;

	public Tuile ( String nom)  
	{
		this.nom = nom;
	}

  public abstract Ouvrier getOuvrier();
  public abstract Joueur getProprietaire();

  public String getNom()
  {
    return this.nom;
  }

	public String toString()
	{
		return this.nom.charAt(0)+"";
	}
}

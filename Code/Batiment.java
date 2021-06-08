import java.util.ArrayList;

public class Batiment extends Tuile
{
  private Joueur proprietaire;
	
	private String cout;
	private String coutProduction;
	private String revientProduction;
	
	private int score;

  public Batiment ( String nom,String cout,String coutProduction,String revientProduction, int score)
	{
		super(nom);
		
		this.cout              = cout;
		this.coutProduction    = coutProduction;
		this.revientProduction = revientProduction;

		this.proprietaire = null;
		
		this.score = score;
	}

  public Batiment(String nom)
  {

    this(nom,"","","",0);
  }

  public String getCout(){ return this.cout;}
  public String getCoutProd(){ return this.coutProduction;}
  public String getRevientProd(){ return this.revientProduction;}
  public int   getScore(){ return this.score;}

	public Joueur getProprietaire()
	{
		return this.proprietaire;
	}
  public Ouvrier getOuvrier()
  {
    return null;
  }

	public void setProprietaire(Joueur proprietaire)
	{
		this.proprietaire = proprietaire;
	}

	public String infoBatiment()
	{
		String sRet="\n";

		sRet += "Nom du batiment    : " + super.getNom()   + "\n" +
            "Cout               : " + this.cout    + "\n" +
            "Cout de Production : " + this.coutProduction    + "\n" +
            "Revient Production : " + this.revientProduction + "\n" +
            "Gain de score      : " + this.score  ;



		return sRet;
	}


  public String toString()
  {
    if(this.proprietaire != null)
      return super.toString()+this.proprietaire.getNumJoueur();
    else
      return super.toString();
  }
}
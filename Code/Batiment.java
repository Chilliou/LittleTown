import java.util.ArrayList;

public class Batiment extends Tuile
{
  private Joueur proprietaire;
	
	private ArrayList<Ressource> cout;
	private ArrayList<Ressource> coutProduction;
	private ArrayList<Ressource> revientProduction;
	
	private int score;

  public Batiment ( String nom, int score)
	{
		super(nom);
		
		this.cout              = new ArrayList <Ressource>();
		this.coutProduction    = new ArrayList <Ressource>();
		this.revientProduction = new ArrayList <Ressource>();

		this.proprietaire = null;
		
		this.score = score;
	}
  public Batiment(String nom)
  {
    this(nom,0);
  }

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

	
	public boolean ajouterCout ( Ressource r )
	{
		this.cout.add ( r );
		return true;
	}
	
	public boolean ajouterCoutProd ( Ressource r )
	{
		this.coutProduction.add ( r );
		return true;
	}
	
	public boolean ajouterRevientProd ( Ressource r )
	{
		this.revientProduction.add ( r );
		return true;
	}

  public String toString()
  {
    if(this.proprietaire != null)
      return super.toString()+this.proprietaire.getNumJoueur();
    else
      return super.toString();
  }
}
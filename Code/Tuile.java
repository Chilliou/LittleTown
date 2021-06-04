import java.util.ArrayList;

public class Tuile
{
	
	private String nom;
	
	private ArrayList<Ressource> cout;
	private ArrayList<Ressource> coutProduction;
	private ArrayList<Ressource> revientProduction;
	
	private int score;
	
	public Tuile ( String nom, int score)
	{
		this.nom   = nom;
		
		this.cout              = new ArrayList <Ressource>();
		this.coutProduction    = new ArrayList <Ressource>();
		this.revientProduction = new ArrayList <Ressource>();
		
		this.score = score;
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
}

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
		
		this.cout              = new ArrayList ();
		this.coutProduction    = new ArrayList ();
		this.revientProduction = new ArrayList ();
		
		this.score = score;
	}
	
	public boolean ajouterCout ( Ressource r )
	{
		this.cout.add ( r );
	}
	
	public boolean ajouterCoutProd ( Ressource r )
	{
		this.coutProduction.add ( r );
	}
	
	public boolean ajouterRevientProd ( Ressource r )
	{
		this.revientProduction.add ( r );
	}
}

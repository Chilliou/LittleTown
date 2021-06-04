import java.util.ArrayList;

public class Tuile
{
	
	private String nom;
	
	private ArrayList<Ressource> cout;
	private ArrayList<Ressource> coutProduction;
	private ArrayList<Ressource> revientProduction;
	
	private int score;

	private boolean revientPassif; //On changera tkt donc tg <3
	
	public Tuile ( String nom, int score,boolean passif)
	{
		this.nom   = nom;
		
		this.cout              = new ArrayList <Ressource>();
		this.coutProduction    = new ArrayList <Ressource>();
		this.revientProduction = new ArrayList <Ressource>();
		
		this.score = score;
		this.revientPassif = false;
	}

	public Tuile ( String nom)  // Le temps que l'on fasse une classe TuilePlateau pour les tuilles de plateau
	{
		this(nom,0,true);
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
		return this.nom.charAt(0)+"";
	}
}

package littletown;


import java.util.ArrayList;

public class Batiment extends Tuile
{

	private String sCout;
	private String sCoutProduction;
	private String sRevientProduction;

	private Joueur jProprietaire;
	
	private int iScore;

	public Batiment ( String sNom,String sCout,String sCoutProduction,String sRevientProduction, int iScore )
	{		
		super ( sNom );
		this.sCout              = sCout;
		this.sCoutProduction    = sCoutProduction;
		this.sRevientProduction = sRevientProduction;

		this.jProprietaire      = null;

		
		this.iScore = iScore;
	}
	
	//Constructeur des tuiles déjà présentes sur le plateau
	public Batiment(String sNom)
	{
		this( sNom, "", "", sNom.charAt(0) + "1" , 0 );
	}

	public String getCout()        { return this.sCout;              }
	public String getCoutProd()    { return this.sCoutProduction;    }
	public String getRevientProd() { return this.sRevientProduction; }
	public int    getScore()       { return this.iScore;             }

	public Joueur getProprietaire()
	{
		return this.jProprietaire;
	}
	
	//Obliger de déclarer cette méthode car elle est abstraite dans Tuile
	public Ouvrier getOuvrier()
	{
		return null;
	}

	public void setProprietaire( Joueur jProprietaire )
	{
		this.jProprietaire = jProprietaire;
	}

	public String infoBatiment()
	{
		String sRet="\n";

		sRet += "Nom du batiment    : " + super.getNom()          + "\n" +
		        "Cout               : " + this.sCout              + "\n" +
		        "Cout de Production : " + this.sCoutProduction    + "\n" +
		        "Revient Production : " + this.sRevientProduction + "\n" +
		        "Gain de score      : " + this.iScore;

		return sRet;
	}


	public String toString()
	{
		if(this.jProprietaire != null)
			return super.toString() + this.jProprietaire.getNumJoueur();
		else
			return super.toString();
	}
}

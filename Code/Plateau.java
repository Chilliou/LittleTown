import java.util.Scanner;
import java.io.FileInputStream;

public class Plateau 
{
	private Tuile[][] terrainDeJeu;
	private Joueur[]   joueurActif ; 

	public Plateau(int nbJoueur,int choixPlateau) // possiblement passer par un factory
	{											  // Pour check le nbJoueur et choixPlateau
		this.terrainDeJeu = new Tuile[6][9];
		this.joueurActif  = new Joueur[nbJoueur];

		this.initTerrain(choixPlateau);
	}

	private void initTerrain(int choixPlateau)
	{
		try
		{
			Scanner sc = new Scanner ( new FileInputStream ( "plateau"+choixPlateau+".data" ) );

			int ligne = 0;
			while ( sc.hasNextLine() )
			{
				int colone = 0;
				for(Character c : sc.nextLine().toCharArray())
				{
					if(c == 'P')  // Pierre
						this.terrainDeJeu[ligne][colone] = new Batiment("Pierre");
					if(c == 'V')  // Vide
						this.terrainDeJeu[ligne][colone] = new TuileVide();
					if(c == 'E')  // Eau
						this.terrainDeJeu[ligne][colone] = new Batiment("Eau");
					if(c == 'A')  // Arbre
						this.terrainDeJeu[ligne][colone] = new Batiment("Arbre");

					colone++;
				}

				ligne++;	
			}

			sc.close();
		}
		catch (Exception e)
		{ e.printStackTrace(); }
		
	}

    public Tuile getTuile(int x, int y)
    {
        return this.terrainDeJeu[x][y];
    }

    public TuileVide getTuileVide ( int x, int y )
    {
      return (TuileVide) this.terrainDeJeu[x][y];
    }

    public Batiment getBatiment(int x, int y)
    {
    	return (Batiment) this.terrainDeJeu[x][y];
    }

    public void setTuile(int x, int y, Tuile tuile)
    {
      this.terrainDeJeu[x][y] = tuile;
    }

    public void setTuileVide (int x, int y, TuileVide tuile)
    {
      this.terrainDeJeu[x][y] = tuile;
    }


	public String toString()
	{
		String sRet = "";

        // LIGNE HAUTE

        for (int cpt = 0; (cpt < this.terrainDeJeu[0].length); cpt++)
		{
			sRet+="  " + (char)('A'+cpt) + "  ";
		}

		sRet+="\n";

		for (int cpt = 0; (cpt < this.terrainDeJeu[0].length -1); cpt++) sRet += "+----";
		sRet+="+----+\n";
		
		for(int i=0; i < this.terrainDeJeu.length;i++)
		{
			for(int y=0; y < this.terrainDeJeu[0].length;y++)
			{
				if (! this.terrainDeJeu[i][y].toString().equals(" "))
				{
					if ( this.terrainDeJeu[i][y].getProprietaire() == null)
					{
						sRet+= "| " + this.terrainDeJeu[i][y].toString() + "- ";
					}
					else
					{
						sRet+= "| " + this.terrainDeJeu[i][y].toString() + " ";
					}
					
				}	
				else
				{
					sRet+= "|    ";
				}

				if ( y == this.terrainDeJeu[0].length -1) sRet += "|";
			}
			sRet +=  " " + (i+1) ;
			sRet+="\n";

			for (int cpt = 0; (cpt < this.terrainDeJeu[0].length -1 ); cpt++) sRet += "+----";
			sRet+="+----+\n";

		}

		return sRet;
	}

    public static void main(String[] args)
    {
		System.out.println();
		Plateau p  = new Plateau(4,1);
		System.out.println(p);
    }
}
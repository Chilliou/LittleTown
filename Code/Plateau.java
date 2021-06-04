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
			Scanner sc = new Scanner ( new FileInputStream ( "Plateau"+choixPlateau+".data" ) );

			int ligne = 0;
			while ( sc.hasNextLine() )
			{
				int colone=0;
				for(Character c : sc.nextLine().toCharArray())
				{
					if(c == 'P')  // pierre
						this.terrainDeJeu[ligne][colone] = new Tuile("Pierre");
					if(c == 'V')  // Vide
						this.terrainDeJeu[ligne][colone] = new Tuile("Vide");
					if(c == 'E')  // eau
						this.terrainDeJeu[ligne][colone] = new Tuile("Eau");
					if(c == 'A')  // Arbre
						this.terrainDeJeu[ligne][colone] = new Tuile("Arbre");

					colone++;
				}

				ligne++;	
			}

			sc.close();
		}
		catch (Exception e)
		{ e.printStackTrace(); }
		
	}

	public String toString()
	{
		String sRet="";
		for(int i=0;i<this.terrainDeJeu.length;i++)
		{
			for(int y=0;y<this.terrainDeJeu[0].length;y++)
			{
				sRet+=this.terrainDeJeu[i][y].toString();
			}
			sRet+="\n";
		}

		return sRet;
	}

	public static void main(String[] args)
	{
		Plateau p  = new Plateau(4,1);
		System.out.println(p);
	}
}


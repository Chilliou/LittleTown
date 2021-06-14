package littletown.cui;


import java.util.Scanner;
import java.io.FileInputStream;

/**
* Classe Plateau (gère les plateaux du jeu).
*/
public class Plateau 
{
	private Tuile[][] terrainDeJeu;
	private Joueur[]   joueurActif; 

	/**
	* Constructeur du Plateau
	* @param nbJoueur
	*		Nombres de joueurs de la partie.
	* @param choixPlateau
	*		Choix du plateau (1 ou 2)
	*/
	public Plateau(int nbJoueur,int choixPlateau) // possiblement passer par un factory
	{                                             // Pour tester le nbJoueur et choixPlateau
		this.terrainDeJeu = new Tuile[6][9];
		this.joueurActif  = new Joueur[nbJoueur];

		this.initTerrain(choixPlateau);
	}

	/**
	* Initialise les plateaux
	* @param nbJoueur
	*		Choix du plateau (1 ou 2)
	*/
	private void initTerrain(int choixPlateau)
	{
		try
		{
			Scanner sc = new Scanner ( new FileInputStream ( "../data/plateau" + choixPlateau + ".data" ) );

			int ligne = 0;
			while ( sc.hasNextLine() )
			{
				int colone = 0;
				for(Character c : sc.nextLine().toCharArray())
				{
					if(c == 'P')  // Pierre
						this.terrainDeJeu[ligne][colone] = new Batiment( "Pierre" );
					if(c == 'V')  // Vide
						this.terrainDeJeu[ligne][colone] = new TuileVide();
					if(c == 'E')  // Eau
						this.terrainDeJeu[ligne][colone] = new Batiment( "Eau" );
					if(c == 'A')  // Arbre
						this.terrainDeJeu[ligne][colone] = new Batiment( "Arbre" );

					colone++;
				}

				ligne++;	
			}

			sc.close();
		}
		catch (Exception e)
		{ e.printStackTrace(); }
		
	}

	/**
	* Retourne l'ensemble des tuiles selon sa position.
	* @param x
	*		Position de la tuile selon l'axe x du plateau.
	* @param y
	*		Position de la tuile selon l'axe y du plateau.
	* @return Ensemble des tuiles du plateau.
	*/
    public Tuile getTuile(int x, int y)
    {
        return this.terrainDeJeu[x][y];
    }

	/**
	* Retourne l'ensemble des tuiles vides selon sa position.
	* @param x
	*		Position de la tuile selon l'axe x du plateau.
	* @param y
	*		Position de la tuile selon l'axe y du plateau.
	* @return Tuile vide
	*/
    public TuileVide getTuileVide ( int x, int y )
    {
      return (TuileVide) this.terrainDeJeu[x][y];
    }

	/**
	* Retourne l'ensemble des bâtiments sur le plateau selon sa position.
	* @param x
	*		Position du bâtiment selon l'axe x du plateau.
	* @param y
	*		Position du bâtiment selon l'axe y du plateau.
	* @return Bâtiment sur la tuile
	*/
    public Batiment getBatiment(int x, int y)
    {
    	return (Batiment) this.terrainDeJeu[x][y];
    }

	/**
	* Permets de mettre un élément sur une tuile (ouvrier ou bâtiment).
	* @param x
	*		Position de la tuile selon l'axe x du plateau.
	* @param y
	*		Position de la tuile selon l'axe y du plateau.
	* @param tuile
	*		Élément que l'ont met sur la tuile
	*/
    public void setTuile(int x, int y, Tuile tuile)
    {
      this.terrainDeJeu[x][y] = tuile;
    }

	/**
	* Permets de mettre un élément sur une tuile vide (ouvrier ou bâtiment).
	* @param x
	*		Position de la tuile vide selon l'axe x du plateau.
	* @param y
	*		Position de la tuile vide selon l'axe y du plateau.
	* @param tuile
	*		Élément que l'ont met sur la tuile vide
	*/
    public void setTuileVide (int x, int y, TuileVide tuile)
    {
      this.terrainDeJeu[x][y] = tuile;
    }

	/**
	* Supprime les ouvriers du plateau.
	*/
	public void clearOuvrier ()
	{

		for ( int x = 0; x < this.terrainDeJeu.length; x++ )
		{
			for ( int y = 0; y < this.terrainDeJeu[x].length; y++ )
			{
				Tuile t = terrainDeJeu[x][y];

				if ( t.getClass() == new TuileVide().getClass() )
					if ( t.getOuvrier() != null )
					{
						TuileVide tOuvrier = this.getTuileVide( x, y );
						tOuvrier.setOuvrier( null );
					}
			}
		}
	}

	/**
	* Retourne le plateau du jeu.
	* @return Plateau du jeu sous forme de chaine de caractère.
	*/
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

}

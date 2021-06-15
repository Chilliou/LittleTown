package littletown.cui;


import java.util.ArrayList;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.Collections;

/**
* Gère le plateau bas du jeu.
*/
public class PlateauBas
{
	private Tuile[][] plateauBas;
	private int       iNbBle;
	private int       iNbConstru1;
	private int       iNbConstru2;
	private int       iNbConstru3;
	private int       iNbConstru4;

	private Controleur ctrl;

	private ArrayList<Batiment> ensBatiment;

	/**
    * Constructeur de la classe.
    *
    * @param ctrl
    *           Controleur
    */
	public PlateauBas(Controleur ctrl)
	{
		this.ctrl         = ctrl;

		this.plateauBas   = new Tuile[2][6];
		this.iNbBle       = 5;
		this.iNbConstru1  = 0;
		this.iNbConstru2  = 0;
		this.iNbConstru3  = 0;
		this.iNbConstru4  = 0;
		this.initTuile();
		this.initPlateauBas();
		
	}

	/**
    * Détermine si le placement du blé est possible.
    *
    * @return Le placement du blé est possible.
    */
	public boolean enleverBle ()
	{
		if ( this.iNbBle < 1 )
		    return false;

		this.iNbBle--;
		return true;
	}

	/**
    * Initialise le plateau bas.
    */
	private void initPlateauBas()
	{

		int iNbTuile = 0;
		for ( int i = 0; i < this.plateauBas.length; i++ )
		{
			for ( int y = 0; y < this.plateauBas[0].length; y++ )
			{
				this.plateauBas[i][y] = this.ensBatiment.get( iNbTuile );
				iNbTuile++;
			}
		}

	}
	
	/**
    * Initialise les tuiles du plateau.
    */
	private void initTuile()
	{
		this.ensBatiment = new ArrayList<Batiment>();

		try
		{	

			Scanner sc = new Scanner ( new FileInputStream ( "../data/tuile.data" ) );

			while ( sc.hasNextLine() )
			{
				String[] parts = sc.nextLine().split("/");
				for(int i=0;i<parts.length;i++)
					if(parts[i].equals("null")) parts[i] = "";
					
				ensBatiment.add( new Batiment(parts[0], parts[1], parts[2], parts[3], Integer.parseInt( parts[4] ) ) );
			}

			sc.close();
		}
		catch (Exception e) { e.printStackTrace(); }

		// On mélange l'ArrayList
		Collections.shuffle(this.ensBatiment);
		
	}


	/**
	* Retourne la tuile selon sa position.
	*
	* @param x
	*		Position de la tuile selon l'axe x du plateau.
	* @param y
	*		Position de la tuile selon l'axe y du plateau.
	*
	* @return Ensemble des tuiles du plateau.
	*/
	public Tuile getTuile( int x, int y )
	{
		return this.plateauBas[x-6][y-2];
	}

	/**
    * Retourne les tuiles vides du plateau.
	* 
	* @param x
	*	Position de la tuile selon l'axe x du plateau.
	* @param y
	*	Position de la tuile selon l'axe y du plateau.
	*
	* @return Tuiles vides du plateau.
    */
	public TuileVide getTuileVide ( int x, int y )
	{
		return (TuileVide) this.plateauBas[x-6][y-2];
	}

	/**
	* Retourne la tuile vide selon sa position.
	*
	* @param x
	*		Position de la tuile selon l'axe x du plateau.
	* @param y
	*		Position de la tuile selon l'axe y du plateau.
	*
	* @return Tuile vide
	*
	* @see Batiment
	*/
	public Batiment getBatiment(int x, int y)
	{
		return (Batiment) this.plateauBas[x-6][y-2];
	}

	/**
	* Ajoute une tuile sur le plateau selon sa position.
	*
	* @param x
	*		Position de la tuile selon l'axe x du plateau.
	* @param y
	*		Position de la tuile selon l'axe y du plateau.
	* @param tuile
	*		Élément que l'ont met sur la tuile
	*/
	public void setTuile(int x, int y, Tuile tuile)
	{
		this.plateauBas[x-6][y-2] = tuile;
	}

	/**
	* Ajoute un ouvrier sur le plateau bas.
	*
	* @param iNumJoueur
	*		Numéro du joueur.
	*/
	public void addOuvrier( int iNumJoueur)
	{
		int iNumOuvrierJoueur;

		iNumOuvrierJoueur = iNumJoueur + 1;
		switch( iNumOuvrierJoueur )
		{
			case 1 -> this.iNbConstru1++;
			case 2 -> this.iNbConstru2++;
			case 3 -> this.iNbConstru3++;
			case 4 -> this.iNbConstru4++;
		}

	}

	/**
	* Supprimer les ouvriers du plateau bas.
	*/
	public void clearOuvrier()
	{
		this.iNbConstru1 = 0;
		this.iNbConstru2 = 0;
		this.iNbConstru3 = 0;
		this.iNbConstru4 = 0;

	}

	/**
	* Initialisation du plateau bas de l'IHM
	* @return Ensemble des tuiles
	*
	* @see Tuile
	*/
	public Tuile[] getTuileIHM()
	{
		Tuile[] ensTuiles = new Tuile[12];

		for(int i = 0; i < ensTuiles.length; i++)
			ensTuiles[i] = this.ensBatiment.get(i);

		return ensTuiles;
	}


	/**
	* Retourne le plateau bas du jeu.
	*
	* @return Plateau bas du jeu sous forme de chaine de caractère.
	*/
	public String toString()
	{
		String sRet="";
		int iNumLigne = 7;

		for ( int i = 0; i < this.plateauBas[0].length + 1; i++ )
			sRet+= "+----";

		sRet += "+----+\n";

		for( int i = 0; i < this.plateauBas.length; i++ )
		{
			for( int y = 0; y < this.plateauBas[0].length; y++ )
			{
				if (i == 0 && y == 0)
					sRet += "| " + this.iNbBle + "  | B  ";

				if (i == 1 && y == 0)
					sRet += "| " + this.iNbConstru1 + "  | " + this.iNbConstru2 + "  ";

				sRet += "| " + this.plateauBas[i][y].toString() + "  ";

			}
			
			sRet+= "|      " + iNumLigne + "\n";
			iNumLigne++;
			
			for (int cpt = 0; cpt < this.plateauBas[0].length + 1; cpt++)
				sRet+= "+----";

			sRet += "+----+\n";

		}


		if (this.ctrl.getNbJoueur() == 3 )
		{
			sRet += "| " + this.iNbConstru3 + "  |";
			sRet += "\n+----+";
		}


		if (this.ctrl.getNbJoueur() == 4 )
		{
			sRet += "| " + this.iNbConstru3 + "  | " + this.iNbConstru4 + "  |";
			sRet += "\n+----+----+";
		}

		sRet += "\n Le tour actuel est " + this.ctrl.getTour() + " / 4.";


		return sRet;
	}

}

public class Plateau 
{
	private Tuille[][] terrainDeJeu;
	private Joueur[]   joueurActif ; 

	public Plateau(int nbJoueur,int choixPlateau) // possiblement passer par un factory
	{											  // Pour check le nbJoueur et choixPlateau
		this.terrainDeJeu = new Tuille[6][9];
		this.joueurActif  = new Joueur[nbJoueur];

		this.initTerrain(choixPlateau);
	}

	private void initTerrain(int choixPlateau)
	{
		try
		{
			Scanner sc = new Scanner ( new FileInputStream ( "Plateau"+choixPlateau+".txt" ) );

			while ( sc.hasNextLine() )
				System.out.println ( sc.nextLine() );

			sc.close();
		}
		catch (Exception e)
		{ e.printStackTrace(); }
		
	}
}


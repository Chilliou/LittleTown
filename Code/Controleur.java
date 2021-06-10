public class Controleur
{
	private Metier metier;

	private IhmCUI ihm;

	public Controleur()
	{
		this.metier = new Metier(this);

		this.ihm    = new IhmCUI();

		this.lancerJeu();
	}

	private void lancerJeu()
	{
		int iAction;

		while ( this.metier.getTour() <= 4 )
		{

			for( int iTour = 0; iTour < this.metier.getNbOuvrier() * 2; iTour++ )
			{
				boolean bTour = false;

				do
				{
					this.ihm.afficher();

					System.out.println( this.metier.getJoueurActif().toString() );
					
					iAction = this.ihm.getChoix();
					
					switch ( iAction )
					{
						case 1 -> this.metier.placerOuvrier      ();
						case 2 -> this.metier.construireBatiment ();
						case 3 -> this.metier.activerTuile       ();
						case 4 -> this.metier.getInfoBatiment    ();
						case 5 -> this.metier.echangerPiece      ();
						case 6 ->
						{
							if (this.metier.getJoueurActif().aJouer()) 
								bTour = true;
							else
								System.out.println( "Merci d'effectuer une action !" );
						}
						
	
					}
	
				}
				while ( ! ( this.metier.getJoueurActif().aJouer()) || bTour == false );
	
				//Rénitialisation de l'action du joueur et changement du joueur
				this.metier.getJoueurActif().setAction(false);
				this.metier.changementJoueur();
	
			}

			// NOURIR OUVRIER
			this.metier.nourrirOuvrier();
			this.metier.plus1Tour();

			// Clear Ouvrier
			this.ihm.clearOuvrier();
		}
	}

	public Tuile getTuile( int x, int y )
	{
		return this.ihm.getTuile( x, y );
	} 

	public TuileVide getTuileVide ( int x, int y )
	{
		return this.ihm.getTuileVide( x, y );
	}

	public Batiment getBatiment( int x, int y )
	{
		return this.ihm.getBatiment( x, y );
	}

	public void setTuile( int x, int y, Tuile tuile )
	{
		this.ihm.setTuile( x, y, tuile );
	}

	public void setTuileVide ( int x, int y, TuileVide tuile )
	{
		this.ihm.setTuile( x, y, tuile );
	}

	public void addOuvrier(int iNumJoueur)
	{
		this.ihm.addOuvrier( iNumJoueur );
	}

	public void nourrirOuvrierInfo(Joueur j)
	{
		this.ihm.nourrirOuvrierInfo(j);
	}

	public int nourrirOuvrier(char ressource, int iOuvrierNourri, int nbOuvrierTotint, Joueur j)
	{
		return this.ihm.nourrirOuvrier (ressource, iOuvrierNourri, nbOuvrierTotint, j);
	}

	public void finNourrir(int iOuvrierNourri, int nbOuvrierTot, Joueur j)
	{
		this.ihm.finNourrir(iOuvrierNourri, nbOuvrierTot, j);
	}

	public boolean enleverBle ()
	{
		return this.ihm.enleverBle();
	}

	public String getSaisiePos()
	{
		return this.ihm.getSaisiePos();
	}

	public static void main(String[] a)
	{
		new Controleur();
	}
}

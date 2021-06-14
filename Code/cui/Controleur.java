package littletown.cui;


public class Controleur
{
	private Metier metier;

	private IhmCUI ihm;

	public Controleur()
	{
		this.ihm     = new IhmCUI(this);
		this.metier  = new Metier(this);
		this.lancerJeu();
	}

	private void lancerJeu()
	{
		int iAction;

		while ( this.metier.getTour() <= 4 ) 
		{

			for( int iTour = 0; iTour < this.metier.getNbOuvrier() * this.getNbJoueur(); iTour++ ) 
			{
				boolean bTour = false;

				do
				{
					this.ihm.afficher();

					System.out.println( "Banque" +"\n" + this.metier.getBanque()+"\n");
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
				this.metier.resetDernierOuvrier();
	
			}

			// NOURIR OUVRIER
			this.metier.nourrirOuvrier();
			this.metier.plus1Tour();

			// Clear Ouvrier
			this.ihm.clearOuvrier();
		}

		this.metier.finDePartie();
	}

	public Tuile getTuile         ( int x, int y ) { return this.ihm.getTuile( x, y );     }
	public TuileVide getTuileVide ( int x, int y ) { return this.ihm.getTuileVide( x, y ); }
	public Batiment getBatiment   ( int x, int y ) { return this.ihm.getBatiment( x, y );  }


	public void setTuile     ( int x, int y, Tuile tuile )     { this.ihm.setTuile( x, y, tuile ); }
	public void setTuileVide ( int x, int y, TuileVide tuile ) { this.ihm.setTuile( x, y, tuile ); }

	public int getInitNbJoueurs()                { return this.ihm.saisienbJoueur();           }
	public String saisieNomJoueur(int numJoueur) { return this.ihm.saisieNomJoueur(numJoueur); }
	public int getNbJoueur()                     { return this.metier.getNbJoueur() ; }

	public void addOuvrier(int iNumJoueur) { this.ihm.addOuvrier( iNumJoueur ); }

	public void nourrirOuvrierInfo(Joueur j) { this.ihm.nourrirOuvrierInfo(j); }

	public int nourrirOuvrier(char ressource, int iOuvrierNourri, int iNbOuvrierTotint, Joueur j)
	{
		return this.ihm.nourrirOuvrier (ressource, iOuvrierNourri, iNbOuvrierTotint, j);
	}

	public void finNourrir( int iOuvrierNourri, int iNbOuvrierTot, Joueur j )
	{
		this.ihm.finNourrir(iOuvrierNourri, iNbOuvrierTot, j);
	}

	public boolean enleverBle ()
	{
		return this.ihm.enleverBle();
	}

	public String getSaisiePosVide()
	{
		return this.ihm.getSaisiePosVide	();
	}

	public String getSaisiePosBtm()
	{
		return this.ihm.getSaisiePosBtm	();
	}

	public void getInfoBatiment()
	{
		this.ihm.getInfoBatiment();
	}

	public void afficherFinDePartie( Joueur[] tab )
	{
		this.ihm.afficherFinDePartie(tab);
	}


	public static void main(String[] a)
	{
		new Controleur();
	}
}

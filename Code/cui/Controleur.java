package littletown.cui;


/**
* Controleur du programme (permet de lancer l'application).
*/
public class Controleur
{
	private Metier metier;

	private IhmCUI ihm;

	/**
	* Constructeur de la classe.
	* @param mode
	*			Mode du jeu selectionné.
	*
	*/
	public Controleur(String mode)
	{
		this.ihm     = new IhmCUI(this);
		this.metier  = new Metier(this);
		this.lancerJeu(mode);
	}

	/**
	* Permets de lancer le jeu.
	*/
	private void lancerJeu(String mode)
	{
		int iAction;

		// Gestion des modes
		if (mode.equals("mp")) this.scenarioMillieuDePartie();
		if (mode.equals("fp")) this.scenarioFinDePartie    ();
		if (mode.equals("no")) this.scenarioNourrirOuvrier ();

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

			// On change de premier joueur
			this.metier.changementDuPremierJoueur();
		}

		this.metier.finDePartie();
	}

	/**
	* Informations d'une tuile sur le plateau selon sa position.
	*
	* @param x 
	*		Position x de la tuile.
	* @param y
	*		Position y de la tuile.
	*
	* @return Informations de la tuile.
	*
	* @see Tuile
	*/
	public Tuile getTuile         ( int x, int y ) { return this.ihm.getTuile( x, y );     }

	/**
	* Informations d'une tuile vide sur le plateau selon sa position.
	*
	* @param x 
	*		Position x de la tuile.
	* @param y
	*		Position y de la tuile.
	*
	* @return Informations de la tuile vide.
	*
	* @see TuileVide
	*/
	public TuileVide getTuileVide ( int x, int y ) { return this.ihm.getTuileVide( x, y ); }

	/**
	* Informations d'un bâtiment selon sa position sur le plateau.
	*
	* @param x 
	*		Position x du bâtiment.
	* @param y
	*		Position y du bâtiment.
	*
	* @return Informations du bâtiment.
	*
	* @see TuileVide
	*/
	public Batiment getBatiment   ( int x, int y ) { return this.ihm.getBatiment( x, y );  }

	/**
	* Retourne le tour.
	*
	* @return Tour de la partie.
	*/
	public int getTour () { return this.metier.getTour() ; }

	/**
	* Permets de placer une tuile.
	*
	* @param x 
	*		Position x du bâtiment.
	* @param y
	*		Position y du bâtiment.
	* @param tuile
	*		Tuile à placer.
	*/
	public void setTuile     ( int x, int y, Tuile tuile )     { this.ihm.setTuile( x, y, tuile ); }

	/**
	* Mer tuile à vide.
	*
	* @param x 
	*		Position x de la tuile vide.
	* @param y
	*		Position y de la tuile vide.
	* @param tuile
	*		Tuile à mettre à vide.
	*/
	public void setTuileVide ( int x, int y, TuileVide tuile ) { this.ihm.setTuile( x, y, tuile ); }

	/**
	* Retourne la saisi du nombre de joueurs.
	*
	* @return Nombre de joueurs.
	*/
	public int getInitNbJoueurs()                { return this.ihm.saisienbJoueur();           }

	/**
	* Saisie du nom du joueur.
	*	
	* @param numJoueur
	*				Numéro du joueur.
	*
	* @return Nom du joueur.
	*/
	public String saisieNomJoueur(int numJoueur) { return this.ihm.saisieNomJoueur(numJoueur); }

	/**
	* Retourne le nombre de joueurs de la partie.
	*
	* @return Nombre de joueurs.
	*/
	public int getNbJoueur()                     { return this.metier.getNbJoueur() ; }

	/**
	* Ajout d'un ouvrier.
	*
	* @param iNumJoueur
	*			Numéro du joueur.
	*/
	public void addOuvrier(int iNumJoueur) { this.ihm.addOuvrier( iNumJoueur ); }

	/**
	* Informations sur la nourritures des ouvriers.
	*
	* @param j
	*		Joueur
	*/
	public void nourrirOuvrierInfo(Joueur j) { this.ihm.nourrirOuvrierInfo(j); }
	
	/**
	* Retourne les ouvriers à nourrir.
	*
	* @param ressource
	*			Type de ressource
	* @param iOuvrierNourri
	*			Numéro de l'ouvrier à nourrir.
	* @param iNbOuvrierTotint
	*			Nombre des ouvriers à nourri
	* @param j
	*			Joueur
	*
	* @return Nourriture des ouvriers.
	*/
	public int nourrirOuvrier(char ressource, int iOuvrierNourri, int iNbOuvrierTotint, Joueur j)
	{
		return this.ihm.nourrirOuvrier (ressource, iOuvrierNourri, iNbOuvrierTotint, j);
	}

	/**
	* Arrête de nourrir les ouvriers.
	*
	* @param iOuvrierNourri
	*			Numéro de l'ouvrier à nourrir.
	* @param iNbOuvrierTot
	*			Nombre des ouvriers à nourri
	* @param j
	*			Joueur
	*/
	public void finNourrir( int iOuvrierNourri, int iNbOuvrierTot, Joueur j )
	{
		this.ihm.finNourrir(iOuvrierNourri, iNbOuvrierTot, j);
	}

	/**
	* Vérifie si l'on peut enlever le blé.
	*
	* @return Le blé peut-il être enlevé ? (boolean).
	*/
	public boolean enleverBle ()
	{
		return this.ihm.enleverBle();
	}

	/**
	* Evite les répétitions de demande de positions
	*
	* @return Saisie de la position.
	*/
	public String getSaisiePosVide()
	{
		return this.ihm.getSaisiePosVide	();
	}

	/**
	* Retourne la position du bâtiment.
	*
	* @return Position du bâtiment.
	*/
	public String getSaisiePosBtm()
	{
		return this.ihm.getSaisiePosBtm	();
	}

	/**
	* Informations du bâtiment.
	*/
	public void getInfoBatiment()
	{
		this.ihm.getInfoBatiment();
	}

	/**
	* Affiche la fin de la partie (tableau des scores et gagnant).
	*
	* @param tab
	*			Tableau des joueurs.
	*/
	public void afficherFinDePartie( Joueur[] tab )
	{
		this.ihm.afficherFinDePartie(tab);
	}

	/**
	* Initialise le scénario de fin de partie.
	*
	*/
	public void scenarioFinDePartie()
	{
		this.metier.plus1Tour();
		this.metier.plus1Tour();
		this.metier.plus1Tour();

		this.metier.changementDuPremierJoueur();
		this.metier.changementDuPremierJoueur();
		this.metier.changementDuPremierJoueur();
	}


	/**
	* Initialise le scénario pour nourir les ouvriers.
	*
	*/
	public void scenarioNourrirOuvrier()
	{
		this.metier.plus1Tour();
		this.metier.changementDuPremierJoueur();

		// On donne 
		for(Joueur j : this.metier.getTabJoueurs())
		{
			j.ajouterEnlever('C', 2);
			j.ajouterEnlever('E', 1);
			j.ajouterEnlever('M', 3);
		}


		this.metier.nourrirOuvrier();
	}

	/**
	* Initialise le scénario de milieu de partie.
	*
	*/
	public void scenarioMillieuDePartie()
	{
		this.metier.plus1Tour();
		this.metier.changementDuPremierJoueur();

		int[][] iCoordDebut = {
			              {6, 5},
					      {6, 2},
						  {7, 3},
						  {7, 4},
						  };
		int[][] iCoordFin = {
			              {2, 5},
					      {4, 2},
						  {2, 2},
						  {1, 7},
						  };
		int cpt = 0;
		for(Joueur j : this.metier.getTabJoueurs())
		{
			Batiment bat;

			bat = this.getBatiment( iCoordDebut[cpt][0], iCoordDebut[cpt][1] );
			TuileVide terrain = this.getTuileVide( iCoordFin[cpt][0], iCoordFin[cpt][1] );
	
			this.setTuile( iCoordFin[cpt][0],iCoordFin[cpt][1],     bat    );
			this.setTuile( iCoordDebut[cpt][0],iCoordDebut[cpt][1], terrain);


			bat.setProprietaire(j);

			// On donne des ressources au joueur.
			j.ajouterEnlever('A', 1);
			j.ajouterEnlever('C', 2);
			j.ajouterEnlever('E', 1);
			j.ajouterEnlever('M', 3);

			cpt++;
		}



	}

	/**
	* Classe main du controleur.
	*	
	* @param a
	*		Argument du main.
	*/
	public static void main(String[] a)
	{
		if (a.length != 0)
			new Controleur(a[0]);
		else
			new Controleur("");
	}
}

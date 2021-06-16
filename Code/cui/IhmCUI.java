package littletown.cui;


import java.util.Scanner;

/**
* Gère l'IHM du mode CUI.
*/
public class IhmCUI
{

	private Plateau    plateau;
	private PlateauBas plateauBas;

	/**
	* Constructeur de la classe.
	*
	* @param ctrl
	*		Controleur
	*/
	public IhmCUI(Controleur ctrl)
	{
		this.plateau    = new Plateau(2,this.getNumeroPlateau());
		this.plateauBas = new PlateauBas(ctrl);
	}

	/**
	* Affichage des deux plateaux.
	*/
	public void afficher()
	{
		System.out.println("> Plateau actuel : ");
		System.out.println( this.plateau);
		System.out.println( this.plateauBas);

	}

	/**
	* Saisi du nombre de joueur de la partie (min : 2 et max 4).
	*
	* @return Nombre de joueur.
	*/
	public int saisienbJoueur()
	{
		Scanner sc = new Scanner( System.in );
		String  iSaisieNbJoueurs = "";
		int     iNbJoueurs = 0;

        try
        {
            do
            {
                System.out.print( "Combien de joueurs voulez vous choisir : " );
                iSaisieNbJoueurs = sc.nextLine();

            } while(! iSaisieNbJoueurs.matches("^([2-4]{1})") );
        }
		catch(Exception e) { System.out.println(e);}

        iNbJoueurs = Integer.parseInt(iSaisieNbJoueurs);

        return iNbJoueurs;
	}

	public int getNumeroObjectif()
	{
		Scanner sc = new Scanner( System.in );
		String  iSaisieNumObjectif = "";
		int     iNumObjectif = 0;

        try
        {
            do
            {
                System.out.print( "Quel est le numéro de l'objectif ? [1 ou 2] : " );
                iSaisieNumObjectif = sc.nextLine();

            } while(! iSaisieNumObjectif.matches("^([1-2]{1})") );
        }
		catch(Exception e) { System.out.println(e);}

        iNumObjectif = Integer.parseInt(iSaisieNumObjectif);
        return iNumObjectif;
	}


	/**
	* Saisi du numéro du plateau.
	*
	* @return Numéro du plateau.
	*/
	public int getNumeroPlateau()
	{
		Scanner sc = new Scanner( System.in );
		String  iSaisieNumPlateau = "";
		int     iNumPlateau = 0;

        try
        {
            do
            {
                System.out.print( "Sur quel plateau voulez vous jouer ? [1 ou 2] : " );
                iSaisieNumPlateau = sc.nextLine();

            } while(! iSaisieNumPlateau.matches("^([1-2]{1})") );
        }
		catch(Exception e) { System.out.println(e);}

        iNumPlateau = Integer.parseInt(iSaisieNumPlateau);
        return iNumPlateau;
	}


	/**
	* Saisie du nom du joueur.
	*	
	* @param numJoueur
	*				Numéro du joueur.
	*
	* @return Nom du joueur.
	*/
	public String saisieNomJoueur(int numJoueur)
	{
		Scanner sc = new Scanner( System.in );
		String  sNomJoueur = "";
        try
        {
                System.out.print( "Joueur n°" + (numJoueur+1) + " quel est votre prenom ? : " );
                sNomJoueur = sc.nextLine();
        }
		catch(Exception e) { System.out.println(e);}

        return sNomJoueur;
	}

	/**
	* Vérifie si l'on peut enlever le blé du plateau bas.
	*
	* @return Le blé peut-il être enlevé ? (boolean).
	*/
	public boolean enleverBle ()
	{
		return this.plateauBas.enleverBle();
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
	public Tuile getTuile( int x, int y )
	{
		if( x <= 5 )
			return this.plateau.getTuile( x, y );
		else
			return this.plateauBas.getTuile( x, y );
	}

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
	public TuileVide getTuileVide ( int x, int y )
	{
		if( x <= 5 )
			return this.plateau.getTuileVide( x, y );
		else
			return this.plateauBas.getTuileVide( x, y );
	}

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
	public Batiment getBatiment( int x, int y )
	{
		if( x <= 5 )
			return this.plateau.getBatiment( x, y );
		else
			return this.plateauBas.getBatiment( x, y );
	}

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
	public void setTuile( int x, int y, Tuile tuile )
	{
		if( x <= 5 )
			this.plateau.setTuile( x, y, tuile );
		else
			this.plateauBas.setTuile( x, y, tuile );
	}

	/**
	* Remplace une tuile par une autre passé en paramètre.
	*
	* @param x 
	*		Position x de la tuile vide.
	* @param y
	*		Position y de la tuile vide.
	* @param tuile
	*		Nouvelle tuile.
	*/
	public void setTuileVide ( int x, int y, TuileVide tuile )
	{
		this.plateau.setTuile( x, y, tuile );
	}
	
	/**
	* Rénitialisation des ouvriers sur le plateau.
	*/
	public void clearOuvrier()
	{
		this.plateauBas.clearOuvrier();
		this.plateau   .clearOuvrier();
	}

	/**
	* Ajout d'un ouvrier sur le plateau bas.
	*
	* @param iNumJoueur
	*				Numéro du joueur.
	*/
	public void addOuvrier(int iNumJoueur)
	{
		this.plateauBas.addOuvrier( iNumJoueur );
	}
	

	/**
	* Affichage du choix de l'action.
	*
	* @return Choix du joueur.
	*/
	public int getChoix()
	{
		String sChoix;
		int    iChoix;
		String saisieChoix = "";

		sChoix  = "+-----------------------------------------------+\n";
		sChoix += "| > Action possibles:                           |\n";
		sChoix += "| 1 - Placer Ouvrier                            |\n";
		sChoix += "| 2 - Construire batiment                       |\n";
		sChoix += "| 3 - Activer tuile                             |\n";
		sChoix += "| 4 - Info Batiment                             |\n";
		sChoix += "| 5 - Echanger pièce -> ressources              |\n";
		sChoix += "| 6 - Valider un objectif                       |\n";
		sChoix += "| 7 - Fin de tour                               |\n";
		sChoix += "+-----------------------------------------------+\n";

		System.out.println( sChoix);

		Scanner sc = new Scanner( System.in );
		try
		{
			do
			{
				System.out.print( "Faites votre choix : " );
				saisieChoix = sc.nextLine();

			}while(!saisieChoix.matches("^([1-7]{1})"));
		}catch(Exception e)
		{
		}
		
		iChoix = Integer.parseInt(saisieChoix);

		return iChoix;
	}


	/**
	* Donne les informations pour nourir un ouvrier au joueur.
	*
	* @param j
	*		Joueur qui doit nourir ses ouvriers.
	*/
	public void nourrirOuvrierInfo(Joueur j)
	{
		System.out.println("<=======> A table !!!! <=======>\n");
		System.out.println(j.toString());
		System.out.println("Vous devez maintenant nourrir vos ouvriers...");
		System.out.println("Vous pouvez utiliser votre eau, votre blé ou 3 pièces par nourriture manquante.");
		System.out.println("En cas de nourriture manquante, vous recevez -3 de score par ouvrier non nourri.");
	}

	/**
	* Récupère une tuile du plateau bas.
	*
	* @return Une tuile
	*
	* @see PlateauBas
	*/
	public Tuile[] getTuileIHM() { return this.plateauBas.getTuileIHM(); }

	/**
	* Permets de nourrir les ouvriers selon les ressources du joueur.
	*
	* @param ressource
	*				Type de ressource.
	* @param iOuvrierNourri
	*				Ouvrier(s) à nourrir.
	* @param nbOuvrierTotint
	*				Nombre d'ouvrier(s) à nourrir.
	* @param j
	*				Ouvrier du joueur.
	*
	* @return Nombre d'ouvriers nourris. 
	*/
	public int nourrirOuvrier(char ressource, int iOuvrierNourri, int nbOuvrierTotint, Joueur j)
	{
		int iSaisi;
		int iNouveauNbOuvrierNourri = 0;
		Scanner kb = new Scanner(System.in);

		if (nbOuvrierTotint - iOuvrierNourri > 0)
		{
			switch (ressource)
			{
				case 'C' ->
				{

					System.out.println( "Il vous reste " +  ( nbOuvrierTotint - iOuvrierNourri ) + " ouvrier(s) à nourrir." );
					System.out.println( "Combien de votre blé voulez vous utiliser ? : " );
					
					iSaisi = kb.nextInt();
					
					if ( j.getRsc('C') >= iSaisi )
					{
						if ( iSaisi <= nbOuvrierTotint-iOuvrierNourri ) 
							iNouveauNbOuvrierNourri += iSaisi;
						else 
							iNouveauNbOuvrierNourri = nbOuvrierTotint;
					}
					else
					{
						System.out.println( "Ressource insufisante" );
					}

				}


				case 'E' ->
				{
					System.out.println( "Il vous reste " +  ( nbOuvrierTotint - iOuvrierNourri ) + " ouvrier(s) à nourrir." );
					System.out.println( "Combien de votre eau voulez vous utiliser ? : " );
					
					iSaisi = kb.nextInt();

					if ( j.getRsc('E') >= iSaisi )
					{
						if ( iSaisi <= nbOuvrierTotint-iOuvrierNourri ) 
							iNouveauNbOuvrierNourri += iSaisi;
						else 
							iNouveauNbOuvrierNourri = nbOuvrierTotint;
					}
					else
					{
						System.out.println( "Ressource insufisante" );
					}
				}
	
	
				case 'M' ->
				{

					System.out.println( "Il vous reste " +  ( nbOuvrierTotint - iOuvrierNourri ) + " ouvrier(s) à nourrir." );
					System.out.println( "Combien de vos pièces voulez vous utiliser ? (3 pièces / ouvrier): " );

					iSaisi = kb.nextInt();

					if ( j.getRsc('M') >= iSaisi )
					{
						while ( iSaisi >= 3 )
						{
							iSaisi                  -= 3;
							iNouveauNbOuvrierNourri += 1;
						}
					}
					else
					{
						System.out.println( "Ressource insufisante" );
					}

				}
			}
		}

		return iNouveauNbOuvrierNourri;

	}

	/**
	* Met fin à la l'alimentation des ouvriers.
	*
	* @param iOuvrierNourri
	*				Numéro de l'ouvrier à nourrir.
	* @param nbOuvrierTot
	*				Nombre d'ouvrier(s) à nourrir.
	* @param j
	*				Ouvrier du joueur.
	*/
	public void finNourrir(int iOuvrierNourri, int nbOuvrierTot, Joueur j)
	{
	
		if ( iOuvrierNourri < nbOuvrierTot )
		{
			System.out.println( "Vous n'avez pas nourri " +  ( nbOuvrierTot- iOuvrierNourri ) + " ouvrier(s)" );
			System.out.println( "Vous allez recevoir -" + ( nbOuvrierTot - iOuvrierNourri ) * 3 + " de score." );
			
			j.changeScore( -( nbOuvrierTot - iOuvrierNourri ) * 3 );
			
			System.out.println( j.toString() );
		}
		else
		{
			System.out.println( "Féliciation joueur n°" + j.getNumJoueur() + ", vous avez nourri tous vos ouvriers" );
			System.out.println( j.toString() );
		}
	}

	/**
	* Evite les répétitions de demande de positions
	*
	* @return Position.
	*/
	public String getSaisiePosVide()
	{
		String saisie="";
		Scanner sc = new Scanner( System.in );

		try
		{
			do
			{
				do
				{
					System.out.println( "Une position est constituer d'une lettre  entre 'A' et 'I' et un chiffre entre 1 et 6" );
					System.out.print( "Entrez la position de la tuile vide : " );

					saisie = sc.next();
					saisie = saisie.toUpperCase();

					
				}while(!saisie.matches("^([A-I])+([1-6])$") );
			}while(!this.testTuileVide(saisie));

		} catch (Exception e) 
		{
			System.out.println( e );

		}
		return saisie;
		
	}

	/**
	* Retourne la position du bâtiment.
	*
	* @return Position du bâtiment.
	*/
	public String getSaisiePosBtm()
	{
		String saisie = "";
		Scanner sc = new Scanner( System.in );

		try
		{
			do
			{
				do
				{
					System.out.println( "Une position est constituer d'une lettre  entre 'A' et 'I' et un chiffre entre 1 et 8" );
					System.out.print( "Entrez la position du batiment : " );

					saisie = sc.nextLine();
					saisie = saisie.toUpperCase();

				}while(!saisie.matches("((?=^[AI]|[B])([AI])+([1-6])|([B])+([1-7])|^([C-H])+([1-8]))") );
			}while(!this.testBatiment(saisie));
		} catch (Exception e) 
		{
			System.out.println( "Saisie de position invalide" );

		}
		return saisie;
		
	}

	/**
	* Test d'un placement de bâtiment.
	*
	* @param pos
	*		Position du bâtiment.
	*
	* @return Vérifie si le bâtiment peut être placé.
	*/
	public boolean testBatiment(String pos)
	{
		int y =  pos.charAt(0) - (int) ('A');
		int x = (Integer.parseInt(pos.charAt(1) + "")-1) ;

		if(pos.equals("B7")) return true;

		Tuile tester = this.getTuile(x,y);
		
		return tester.getClass() == new Batiment("Useless").getClass();
	}

	
	/**
	* Test d'un placement de tuile.
	*
	* @param pos
	*		Position de la tuile.
	*
	* @return Vérifie si la tuile peut être placé.
	*/
	public boolean testTuileVide(String pos)
	{
		int y =  pos.charAt(0) - (int) ('A');
		int x = (Integer.parseInt(pos.charAt(1) + "")-1) ; 

		Tuile tester = this.getTuile(x,y);

		return tester.getClass() == new TuileVide().getClass();		
	}

	/**
	* Informations du bâtiment.
	*/
	public void getInfoBatiment()
	{
		String saisie = this.getSaisiePosBtm();

		int y =  saisie.charAt(0) - (int) ('A');
		int x = (Integer.parseInt(saisie.charAt(1) + "")-1);

		if(saisie.equals("B7")) 
			System.out.println(new Batiment ( "Blé", "A1", "", "C1", 3 ).infoBatiment());
		else
			System.out.println(this.getBatiment(x,y).infoBatiment()); 
	}

	/**
	* Affiche la fin de la partie (tableau des scores et gagnant).
	*
	* @param tab
	*			Tableau des joueurs.
	*/
	public void afficherFinDePartie( Joueur[] tab )
	{
		System.out.println( "\nPartie Terminé !\n");
		System.out.println( "Tableau des scores : ");

		int place = 1;
		for ( int cpt = tab.length - 1; cpt >= 0; cpt-- )
		{
			System.out.println( place + ": Joueur n°" + tab[cpt].getNumJoueur() + ", ( " + tab[cpt].getNom() + " )");
			place++;
		}
	}

}
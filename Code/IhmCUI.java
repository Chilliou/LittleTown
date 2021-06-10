import java.util.Scanner;

public class IhmCUI
{

	private Plateau    plateau;
	private PlateauBas plateauBas;

	public IhmCUI()
	{
		this.plateau    = new Plateau(2,1);
		this.plateauBas = new PlateauBas();
	}
	
	//Affichage des deux plateau
	public void afficher()
	{
		System.out.println("Plateau actuel : ");
		System.out.println( this.plateau);
		System.out.println( this.plateauBas);

	}

	public boolean enleverBle ()
	{
		return this.plateauBas.enleverBle();
	}

	public Tuile getTuile( int x, int y )
	{
		if( x <= 5 )
			return this.plateau.getTuile( x, y );
		else
			return this.plateauBas.getTuile( x, y );
	}

	public TuileVide getTuileVide ( int x, int y )
	{
		if( x <= 5 )
			return this.plateau.getTuileVide( x, y );
		else
			return this.plateauBas.getTuileVide( x, y );
	}

	public Batiment getBatiment( int x, int y )
	{
		if( x <= 5 )
			return this.plateau.getBatiment( x, y );
		else
			return this.plateauBas.getBatiment( x, y );
	}

	public void setTuile( int x, int y, Tuile tuile )
	{
		if( x <= 5 )
			this.plateau.setTuile( x, y, tuile );
		else
			this.plateauBas.setTuile( x, y, tuile );
	}

	public void setTuileVide ( int x, int y, TuileVide tuile )
	{
		this.plateau.setTuile( x, y, tuile );
	}
	
	//Rénitialisation des ouvriers sur le plateau
	public void clearOuvrier()
	{
		this.plateauBas.clearOuvrier();
	}

	public void addOuvrier(int iNumJoueur)
	{
		this.plateauBas.addOuvrier( iNumJoueur );
	}
	
	//Affichage du choix de l'action
	public int getChoix()
	{
		String sChoix;
		int iChoix;
		String saisieChoix="";

		sChoix  = "+-----------------------------------------------+\n";
		sChoix += "| > Action possibles:                           |\n";
		sChoix += "| 1 - Placer Ouvrier                            |\n";
		sChoix += "| 2 - Construire batiment                       |\n";
		sChoix += "| 3 - Activer tuile                             |\n";
		sChoix += "| 4 - Info Batiment                             |\n";
		sChoix += "| 5 - Echanger pièce -> ressources              |\n";
		sChoix += "| 6 - Fin de tour                               |\n";
		sChoix += "+-----------------------------------------------+\n";

		System.out.println( sChoix);

		Scanner sc = new Scanner( System.in );
		try
		{
			do
			{
				System.out.print( "Faites votre choix : " );
				saisieChoix = sc.nextLine();

			}while(!saisieChoix.matches("^[1-6])"));
		}catch(Exception e)
		{
		}
		
		iChoix = Integer.parseInt(saisieChoix);

		return iChoix;
	}


	// Méthode  pour nourir les ouvriers
	public void nourrirOuvrierInfo(Joueur j)
	{
		System.out.println("<=======> A table !!!! <=======>\n");
		System.out.println(j.toString());
		System.out.println("Vous devez maintenant nourrir vos ouvriers...");
		System.out.println("Vous pouvez utiliser votre eau, votre blé ou 3 pièces par nourriture manquante.");
		System.out.println("En cas de nourriture manquante, vous recevez -3 de score par ouvrier non nourri.");
	}

	// Méthode  pour nourir les ouvriers
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
							System.out.println( "Je passe :" );
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

	// Méthode  pour nourir les ouvriers
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
	
	//Méthode pour éviter les répétitions de demande de pos
	public String getSaisiePosVide()
	{
		String saisie="";
		boolean saisieEstVide = true;
		Scanner sc = new Scanner( System.in );

		try
		{
			do
			{
				do
				{
					if(!saisieEstVide) 	System.out.println( "\n"+"Cette tuile n'est pas vide" );

					System.out.println( "Une position est constituer d'une lettre  entre 'A' et 'I' et un chiffre entre 1 et 6" );
					System.out.print( "Entrez la position : " );

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

	public String getSaisiePosBtm()
	{
		String saisie="";
		boolean saisieEstVide = true;
		boolean regex;
		Scanner sc = new Scanner( System.in );

		try
		{
			do
			{
				do
				{
					if(!saisieEstVide) 	System.out.println( "\n"+"Cette tuile n'est pas un batiment" );

					System.out.println( "Une position est constituer d'une lettre  entre 'A' et 'I' et un chiffre entre 1 et 8" );
					System.out.print( "Entrez la position : " );

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

	public boolean testBatiment(String pos)
	{
		int y =  pos.charAt(0) - (int) ('A');
        int x = (Integer.parseInt(pos.charAt(1)+"")-1) ;

		Tuile tester = this.getTuile(x,y);
		
		return tester.getClass() == new Batiment("Useless").getClass();
	}

	
	public boolean testTuileVide(String pos)
	{
		int y =  pos.charAt(0) - (int) ('A');
        int x = (Integer.parseInt(pos.charAt(1)+"")-1) ; 

		Tuile tester = this.getTuile(x,y);

		return tester.getClass() == new TuileVide().getClass();		
	}

	
}
package littletown.cui;

import java.util.Scanner;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileOutputStream;


public class Metier
{

	private int iNbMaxOuvriers;
	private int iNbMaxBatiment;

	private Controleur ctrl;

	private Banque banque;

	private int  iNbJoueurs;
	private int  iTourActuel;

	private int      iNumJoueurActif;
	private Joueur[] tabJoueurs;

	private String sDernierOuvrierPos;

	private ArrayList<Batiment> ensBatimentActiver;

	public Metier( Controleur ctrl )
	{
		this.banque = new Banque();

		this.ctrl               = ctrl;
		this.sDernierOuvrierPos = null;
		this.iTourActuel        = 1;

		this.ensBatimentActiver = new ArrayList<Batiment>();

		this.initJoueur();
	}

	public Joueur getJoueurActif()
	{
		return this.tabJoueurs[iNumJoueurActif];
	}


	public int getNbJoueur () { return this.iNbJoueurs;     }
	public int getNbOuvrier() { return this.iNbMaxOuvriers; }


	private void initJoueur()
	{
		int iNbJoueursInit;
		String[] sCoulJoueurs = new String[] {"Rouge", "Bleu", "Vert", "Orange"};

		iNbJoueursInit       = this.ctrl.getInitNbJoueurs();
		this.iNbJoueurs      = iNbJoueursInit;
		this.tabJoueurs      = new Joueur[this.iNbJoueurs];
		this.iNumJoueurActif = 0;

		switch ( this.iNbJoueurs )
		{
			case 2 ->
			{
				this.iNbMaxOuvriers = 5;
				this.iNbMaxBatiment = 7;
			}

			case 3 ->
			{
				this.iNbMaxOuvriers = 4;
				this.iNbMaxBatiment = 6;
			}

			case 4 ->
			{
				this.iNbMaxOuvriers = 3;
				this.iNbMaxBatiment = 6;
			}
		}

		for (int i = 0; i < this.tabJoueurs.length; i++)
			this.tabJoueurs[i] = new Joueur( sCoulJoueurs[i], this.ctrl.saisieNomJoueur(i) );


	}

	public void changementJoueur()
	{
		this.resetBatimentActivation();

		if ( this.iNumJoueurActif == this.tabJoueurs.length - 1 )
			this.iNumJoueurActif = 0;
		else
			this.iNumJoueurActif++;
	}

	public void placerOuvrier()
	{
		if ( ! this.tabJoueurs[iNumJoueurActif].aJouer() )
		{
			String sCoordOuvrier;

			sCoordOuvrier = this.ctrl.getSaisiePosVide();
			int y =  sCoordOuvrier.charAt(0) - (int) ('A');
			int x = (Integer.parseInt(sCoordOuvrier.charAt(1)+"")-1) ;

			try
			{

				TuileVide terrain = this.ctrl.getTuileVide( x, y );

				if ( terrain.getNom().equals( "Vide" ) && terrain.getOuvrier() == null )
				{
					terrain.setOuvrier( new Ouvrier ( x, y, this.getJoueurActif() ) );
					this.ctrl.setTuileVide ( x, y, terrain );
					this.sDernierOuvrierPos = sCoordOuvrier;

					// Le joueur a fait son action
					this.tabJoueurs[iNumJoueurActif].setAction(true);
				}

			}
			catch (Exception e){ System.out.println("Tu ne peut pas le poser ici"); }
		}
		else
		{
		System.out.println("Vous avez déja réalisé votre action.");
		}


	}


	public boolean construireBatiment()
	{

		if( ! this.tabJoueurs[iNumJoueurActif].aJouer() ) 
		{

			String sCordBatiment ;
			sCordBatiment = this.ctrl.getSaisiePosBtm();

			int col = sCordBatiment.charAt(0) - (int) ('A');
			int lig = ( Integer.parseInt( sCordBatiment.charAt(1) + "" ) - 1 );

			if(lig <= 5 )
				return false;

			String sCoordPlacement ;
			sCoordPlacement = this.ctrl.getSaisiePosVide();

			int y = sCoordPlacement.charAt(0) - (int) ('A');
			int x = ( Integer.parseInt( sCoordPlacement.charAt(1) + "" ) - 1 );

			try
			{
				Batiment bat;

				if ( lig == 6 && col == 1 )
				bat = new Batiment ( "Blé", "A1", "", "C1", 3 );
				else
				bat = this.ctrl.getBatiment( lig, col ) ;

				TuileVide terrain = this.ctrl.getTuileVide( x, y );

				System.out.println( terrain.getOuvrier() );

				if ( terrain.getOuvrier() != null )
					return false;

				for ( int i = 0; i < bat.getCout().length(); i += 2 )
					if ( this.tabJoueurs[iNumJoueurActif].getRsc( bat.getCout().charAt(i) ) < Integer.parseInt( bat.getCout().charAt(i+1) + "" ) )
						return false;

				// On peut ajouter le batiment 
				for(int i=0;i<bat.getCout().length();i+=2)
					this.tabJoueurs[iNumJoueurActif].echangerRscJoueurVBanque( this.banque,bat.getCout().charAt(i), Integer.parseInt( bat.getCout().charAt(i+1) + "" ) );

				this.ctrl.setTuile( x, y, bat);
				
				if ( ! bat.getNom().equals( "Blé" ) )
					this.ctrl.setTuile(lig,col,terrain);
				else
					this.ctrl.enleverBle();

				this.ctrl.addOuvrier( this.iNumJoueurActif );
				this.tabJoueurs[iNumJoueurActif].majScore( bat.getScore() );
				bat.setProprietaire( this.tabJoueurs[iNumJoueurActif] );
				this.tabJoueurs[iNumJoueurActif].setAction(true);

				return true;

			}
			catch (Exception e){ System.out.println("Tu ne peut pas le poser ici"); }
		}
		else
		{
			System.out.println("Vous avez déja réalisé votre action.");
			return false;
		}
		
		return false;

	}


	public void activerTuile()
	{

		if (this.sDernierOuvrierPos != null)
		{

			String coorTuile = "";
			coorTuile = this.ctrl.getSaisiePosBtm();
			int yOuvrier =  this.sDernierOuvrierPos.charAt(0) - (int) ('A');
			int xOuvrier = (Integer.parseInt(this.sDernierOuvrierPos.charAt(1)+"")-1);

			int yTuile   =  coorTuile.charAt(0) - (int) ('A');
			int xTuile   = (Integer.parseInt(coorTuile.charAt(1)+"")-1);

			// Vérifiaction des coordonnées de la tuile
			if ((xTuile == xOuvrier - 1 || xTuile == xOuvrier + 1 || xTuile == xOuvrier)
			&& (yTuile == yOuvrier - 1 || yTuile == yOuvrier + 1 || yTuile == yOuvrier))
			{

				// Vérification que la tuile n'a pas deja était activée.
				if (this.ctrl.getTuile(xTuile, yTuile).isActivable())
				{
					Batiment bat = this.ctrl.getBatiment(xTuile, yTuile);
					System.out.println("> Activation de la tuile : " + this.ctrl.getTuile(xTuile, yTuile).getNom());

					//Si le batiment n'appartient aucun jouer 
					if ( bat.getProprietaire() != null  )
					{
					
						//Verif si il a les ressources néccésaire
						for(int i=0;i<bat.getCoutProd().length();i+=2)
						if(this.tabJoueurs[iNumJoueurActif].getRsc(bat.getCoutProd().charAt(i))<Integer.parseInt(bat.getCoutProd().charAt(i+1)+""))
						return ;

						//si le Proprietaire est différence du genre actuel
						if(bat.getProprietaire() == this.tabJoueurs[this.iNumJoueurActif])
						{
							for(int i=0;i<bat.getCoutProd().length();i+=2)
							{
								this.tabJoueurs[iNumJoueurActif].echangerRscJoueurVBanque(this.banque,bat.getCoutProd().charAt(i),Integer.parseInt(bat.getCoutProd().charAt(i+1)+""));
							}
							
							for(int i=0;i<bat.getRevientProd().length();i+=2)
							{
								this.banque.echangerRscBanqueVJoueur(this.tabJoueurs[iNumJoueurActif],
								bat.getRevientProd().charAt(i),
								Integer.parseInt( bat.getRevientProd().charAt(i+1) + "" ) );
							}

						}
						else
						{
							if ( this.tabJoueurs[this.iNumJoueurActif].getRsc( 'M' ) > 0  )
							{
								this.tabJoueurs[this.iNumJoueurActif].echangerRscJoueurVBanque ( bat.getProprietaire(), 'M', 1 );
								
								for(int i=0;i<bat.getCoutProd().length();i+=2)
								{
									this.tabJoueurs[iNumJoueurActif].echangerRscJoueurVBanque(this.banque,bat.getCoutProd().charAt(i),Integer.parseInt(bat.getCoutProd().charAt(i+1)+""));
								}
								
								for(int i=0;i<bat.getRevientProd().length();i+=2)
								{
									this.banque.echangerRscBanqueVJoueur(this.tabJoueurs[iNumJoueurActif],
									bat.getRevientProd().charAt(i),
									Integer.parseInt( bat.getRevientProd().charAt(i+1) + "" ) );
								}

							}
						}
						//Si le jouer actif a moins d'1 coins


					}
					else
					{
						this.banque.echangerRscBanqueVJoueur(this.tabJoueurs[iNumJoueurActif],
						bat.getRevientProd().charAt(0),
						Integer.parseInt( bat.getRevientProd().charAt(1) + "" ) );
					}


					bat.setActivation(false);
					this.ensBatimentActiver.add(bat);
				}
				else { System.out.println("Vous avez deja était activé cette tuile !"); }
			}
			else { System.out.println("/!\\ Aucun ouvrié se trouve dans le autour de cette tuile !"); }

		}
		else { System.out.println("/!\\ Aucun ouvrié n'as encore été placé !"); }

	}

	public void nourrirOuvrier()
	{
		int iOuvrierNourri      = 0;
		int iNbRessourceConsomme = 0;

		for ( Joueur j : this.tabJoueurs )
		{
			// Affichage des infos
			this.ctrl.nourrirOuvrierInfo(j);

			// Blé
			iOuvrierNourri       = this.ctrl.nourrirOuvrier ( 'C', iOuvrierNourri, this.iNbMaxOuvriers, j );
			iNbRessourceConsomme = iOuvrierNourri;

			j.echangerRscJoueurVBanque(this.banque,'C',iNbRessourceConsomme);

			// Eau
			iNbRessourceConsomme  = this.ctrl.nourrirOuvrier( 'E', iOuvrierNourri, this.iNbMaxOuvriers, j );
			iOuvrierNourri       += iNbRessourceConsomme;

			j.echangerRscJoueurVBanque(this.banque,'E',iNbRessourceConsomme);

			// Pièces
			iNbRessourceConsomme = this.ctrl.nourrirOuvrier( 'M', iOuvrierNourri, this.iNbMaxOuvriers, j );

			if ( iNbRessourceConsomme != 0 )
				iNbRessourceConsomme += 2 * iNbRessourceConsomme;


			if ( iNbRessourceConsomme >= 3 )
				iOuvrierNourri += iNbRessourceConsomme -2;
			else
				iOuvrierNourri += iNbRessourceConsomme;

			j.echangerRscJoueurVBanque( this.banque, 'M', iNbRessourceConsomme );

			// On affiche si le joueurs perd du score ou non.
			this.ctrl.finNourrir( iOuvrierNourri, this.iNbMaxOuvriers, j );

			// On recommence à 0 pour les autres joueurs
			iOuvrierNourri = 0;

		}

	}

	public void getInfoBatiment( )
	{
		this.ctrl.getInfoBatiment();
	}

	public boolean echangerPiece()
	{
		if ( this.tabJoueurs[iNumJoueurActif].getRsc('M') < 3 )
			return false;

		Scanner sc = new Scanner( System.in );
		System.out.print( "Entrez Ressources : " );

		String sSaisie = sc.nextLine();

		char rsc = sSaisie.charAt(0);

		if ( rsc != 'A' && rsc != 'C' && rsc != 'E' && rsc != 'P' )
			return false;

		try
		{
			this.tabJoueurs[iNumJoueurActif].echangerRscJoueurVBanque( this.banque, 'M', 3 );

			this.banque.echangerRscBanqueVJoueur( this.tabJoueurs[iNumJoueurActif], rsc, 1 );


			return true;
		}
		catch (Exception e){ System.out.println( "Ressource Inconnu" ); }

		return false;
	}

	public void resetBatimentActivation()
	{
		for(Batiment b : this.ensBatimentActiver)
			b.setActivation(true);

		this.ensBatimentActiver.clear();
	}

	public void resetDernierOuvrier ()
	{
		this.sDernierOuvrierPos = null;
	}

	public int getTour()
	{
		return this.iTourActuel;
	}

	public Banque getBanque()
	{
		return this.banque;
	}

	public void plus1Tour()
	{
		this.iTourActuel++;
	}

	public void finDePartie ()
	{
		Joueur[] tabTmp = new Joueur[this.iNbJoueurs];

		tabTmp = this.tabJoueurs;

		int cpt = 0;
		while ( ! this.estTrier(tabTmp) && cpt < tabTmp.length )
		{

			int taille = tabTmp.length - 1 - cpt;

			for ( int i = 0; i < taille; i++ )
			{

				if ( tabTmp[i].getScore() > tabTmp[i+1].getScore() )
				{
					Joueur tmp;

					tmp         = tabTmp[i];
					tabTmp[i]   = tabTmp[i+1];
					tabTmp[i+1] = tmp;

					System.out.println(tabTmp[i]);
				}
			}

			cpt++;
		}

		this.sauvergardeScore(tabTmp);
		this.ctrl.afficherFinDePartie(tabTmp);
	}

	public boolean estTrier ( Joueur[] tab )
	{
		for ( int cpt = 0; cpt < tab.length - 1; cpt++ )
			if ( tab[cpt].getScore() > tab[cpt+1].getScore() )
				return false;

		return true;
	}

	public void sauvergardeScore(Joueur[] tab)
	{
		GregorianCalendar date = new GregorianCalendar();

		String mois[] = { "Janv", "Fevr", "Mars", "Avr",
		"Mai", "Juin", "Juill", "Aout",
		"Sept", "Oct", "Nov", "Dec" };

		String classement[] = { "Premier", "Deuxieme", "Troisieme", "Quatrieme" };

		try
		{
			PrintWriter pw = new PrintWriter( new FileOutputStream("Score.txt",true) );


			pw.println ( "\n" +"Parti du "+ mois[date.get(Calendar.MONTH)] + " "
			                              + date.get(Calendar.DATE) + ", "
			                              + date.get(Calendar.YEAR) + "\n"        );

			for(int i=0;i<tab.length;i++)
				pw.println(classement[i] + " : "+ tab[i].getNom()+"\n");

			pw.close();
		}
		catch (Exception e){ e.printStackTrace(); }
	}

}

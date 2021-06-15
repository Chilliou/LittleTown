package littletown.cui;



import java.util.Scanner;

import java.io.PrintWriter;
import java.io.FileOutputStream;

import java.util.GregorianCalendar;
import java.util.Calendar;


import java.util.ArrayList;

/**
* Classe Metier du programme (couche logique de l'application).
*/
public class Metier
{

    private int nbMaxOuvriers;
    private int nbMaxBatiment;
    
    private Controleur ctrl;

    private Banque banque;
  
    private int  nbJoueurs;
    private int  tourActuel;

    private int  numJoueurActif;
    private Joueur[] tabJoueurs;

    private String dernierOuvrierPos;

    private ArrayList<Batiment> ensBatimentActiver;

    /**
    * Constructeur de la classe.
    *
    * @param ctrl
    *           Controleur
    */
    public Metier(Controleur ctrl)
    {
        this.banque = new Banque();

        this.ctrl              = ctrl;
        this.dernierOuvrierPos = null;
        this.tourActuel        = 1;

        this.ensBatimentActiver = new ArrayList<Batiment>();

        this.initJoueur();
    }

    /**
    * Retourne le joueur actif qui joue.
    *
    * @return joueur actif.
    *
	* @see Joueur
    */
    public Joueur getJoueurActif()
    {
        return this.tabJoueurs[numJoueurActif];
    }
    
    /**
    * Retourne le nombres de joueurs de la partie.
    *
    * @return nombres de joueurs de la partie.
    */
    public int getNbJoueur() { return this.nbJoueurs; }

    /**
    * Retourne le nombres d'ouvriers de la partie.
    *
    * @return nombres d'ouvriers de la partie.
    */
    public int getNbOuvrier() { return this.nbMaxOuvriers; }

    /**
    * Retourne le tableau de joueur de la partie.
    *
    * @return Tableau de joueur.
    */
    public Joueur[] getTabJoueurs() { return this.tabJoueurs; }


    /**
    * Initialise les joueurs du plateau.
    */
    private void initJoueur()
    {
        int nbJoueursInit;
        String[] sCoulJoueurs = new String[] {"Rouge", "Bleu", "Vert", "Orange"};

        
        nbJoueursInit          = this.ctrl.getInitNbJoueurs();
        this.nbJoueurs         = nbJoueursInit;
        this.tabJoueurs        = new Joueur[this.nbJoueurs];
        this.numJoueurActif    = 0;

        switch ( this.nbJoueurs )
        {
            case 2 ->
            {
                this.nbMaxOuvriers = 5;
                this.nbMaxBatiment = 7;
            }

            case 3 ->
            {
                this.nbMaxOuvriers = 4;
                this.nbMaxBatiment = 6;
            }

            case 4 ->
            {
                this.nbMaxOuvriers = 3;
                this.nbMaxBatiment = 6;
            }
        }

        for (int i = 0; i < this.tabJoueurs.length; i++)
        {
            this.tabJoueurs[i] = new Joueur(sCoulJoueurs[i], this.ctrl.saisieNomJoueur(i) );
            if(this.tabJoueurs[i].getNom().equals("Philippe")) this.tabJoueurs[i].masterClass(1);
            if(this.tabJoueurs[i].getNom().equals("Enzo")) this.tabJoueurs[i].masterClass(-1);

        }


        

    }

    /**
    * Changement du joueur.
    */
    public void changementJoueur()
    {
        this.resetBatimentActivation();

        if (this.numJoueurActif == this.tabJoueurs.length -1)
            this.numJoueurActif = 0;
        else
            this.numJoueurActif++;
    }

    /**
    * Placement d'un ouvrier sur le plateau.
    */
    public void placerOuvrier()
    {
        if (! this.tabJoueurs[numJoueurActif].aJouer())
        {
            String sCoordOuvrier ;
            sCoordOuvrier = this.ctrl.getSaisiePosVide();
            int y =  sCoordOuvrier.charAt(0) - (int) ('A');
            int x = (Integer.parseInt(sCoordOuvrier.charAt(1)+"")-1) ;
            
            try
            {
              TuileVide terrain = this.ctrl.getTuileVide( x, y );
    
              if ( terrain.getNom().equals( "Vide" ) && terrain.getOuvrier() == null )
              {
                terrain.setOuvrier( new Ouvrier ( x, y, this.getJoueurActif()) );
                this.ctrl.setTuileVide ( x, y, terrain );
                this.dernierOuvrierPos = sCoordOuvrier;
    
                // Le joueur a fait son action
                this.tabJoueurs[numJoueurActif].setAction(true);
              }
            }
            catch (Exception e){ System.out.println("Tu ne peut pas le poser ici"); }
        }
        else
        {
            System.out.println("Vous avez déja réalisé votre action.");
        }

    
    }

    /**
    * Permets de placer un bâtiment sur une tuile vide.
    * @return Retourne un boolean qui vérifie si la tuile est vide pour construire le bâtiment.
    */
    public boolean construireBatiment()
    {

        if(!this.tabJoueurs[numJoueurActif].aJouer() ) 
        {

            String sCordBatiment ;
            sCordBatiment = this.ctrl.getSaisiePosBtm();
            int col =  sCordBatiment.charAt(0) - (int) ('A');
            int lig = (Integer.parseInt(sCordBatiment.charAt(1)+"")-1) ;

            if(lig <= 5 ) return false;

            String sCoordPlacement ;
            sCoordPlacement = this.ctrl.getSaisiePosVide();
            int y =  sCoordPlacement.charAt(0) - (int) ('A');
            int x = (Integer.parseInt(sCoordPlacement.charAt(1)+"")-1) ;
            
            try
            {
                Batiment bat;
                if ( lig == 6 && col == 1)
                {
                    bat = new Batiment ( "Blé", "A1", "", "C1", 3 );
                }
                else
                    bat = this.ctrl.getBatiment( lig, col ) ;

                TuileVide terrain = this.ctrl.getTuileVide( x, y );
				
				System.out.println( terrain.getOuvrier() );
				
                if( terrain.getOuvrier() != null )
                    return false;

                for(int i=0;i<bat.getCout().length();i+=2)
                    if(this.tabJoueurs[numJoueurActif].getRsc(bat.getCout().charAt(i))<Integer.parseInt(bat.getCout().charAt(i+1)+""))
                        return false;

                // Du coup on peut ajouter le batiment 
                for(int i=0;i<bat.getCout().length();i+=2)
                    this.tabJoueurs[numJoueurActif].echangerRscJoueurVBanque(this.banque,bat.getCout().charAt(i),Integer.parseInt(bat.getCout().charAt(i+1)+""));

                this.ctrl.setTuile(x,y,bat);
                if ( ! bat.getNom().equals( "Blé" ) )
                    this.ctrl.setTuile(lig,col,terrain);
                else
                    this.ctrl.enleverBle();
                this.ctrl.addOuvrier(this.numJoueurActif);
                this.tabJoueurs[numJoueurActif].majScore(bat.getScore());
                bat.setProprietaire(this.tabJoueurs[numJoueurActif]);
                this.tabJoueurs[numJoueurActif].setAction(true);

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

    /**
    * Permets d'activer une tuile.
    */
    public void activerTuile()
    {

        if (this.dernierOuvrierPos != null)
        {
            String coorTuile = "";
            coorTuile = this.ctrl.getSaisiePosBtm();
            int yOuvrier =  this.dernierOuvrierPos.charAt(0) - (int) ('A');
            int xOuvrier = (Integer.parseInt(this.dernierOuvrierPos.charAt(1)+"")-1);

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
			
			for(int i=0;i<bat.getRevientProd().length();i+=2)
				if(this.banque.getRsc(bat.getRevientProd().charAt(i))<Integer.parseInt(bat.getRevientProd().charAt(i+1)+""))
					return ;

                    //Si  le batiment n'appartient aucun jouer 
                    if ( bat.getProprietaire() != null  )
                    {
                        //Verif si il a les ressources néccésaire
                        for(int i=0;i<bat.getCoutProd().length();i+=2)
                             if(this.tabJoueurs[numJoueurActif].getRsc(bat.getCoutProd().charAt(i))<Integer.parseInt(bat.getCoutProd().charAt(i+1)+""))
                                return ;

                        //si le Proprietaire est différence du genre actuel
                        if(bat.getProprietaire() == this.tabJoueurs[this.numJoueurActif])
                        {
                            for(int i=0;i<bat.getCoutProd().length();i+=2)
                            {
                                 this.tabJoueurs[numJoueurActif].echangerRscJoueurVBanque(this.banque,bat.getCoutProd().charAt(i),Integer.parseInt(bat.getCoutProd().charAt(i+1)+""));
                            }
                            for(int i=0;i<bat.getRevientProd().length();i+=2)
                            {
                                this.banque.echangerRscBanqueVJoueur(this.tabJoueurs[numJoueurActif],
                                                        bat.getRevientProd().charAt(i),
                                                         Integer.parseInt( bat.getRevientProd().charAt(i+1) + "" ) );
                            }

                        }
                        else
                        {
                             if ( this.tabJoueurs[this.numJoueurActif].getRsc( 'M' ) > 0  )
                             {
                                this.tabJoueurs[this.numJoueurActif].echangerRscJoueurVBanque ( bat.getProprietaire(), 'M', 1 );
                                for(int i=0;i<bat.getCoutProd().length();i+=2)
                                {
                                     this.tabJoueurs[numJoueurActif].echangerRscJoueurVBanque(this.banque,bat.getCoutProd().charAt(i),Integer.parseInt(bat.getCoutProd().charAt(i+1)+""));
                                }
                                for(int i=0;i<bat.getRevientProd().length();i+=2)
                                {
                                    this.banque.echangerRscBanqueVJoueur(this.tabJoueurs[numJoueurActif],
                                                            bat.getRevientProd().charAt(i),
                                                             Integer.parseInt( bat.getRevientProd().charAt(i+1) + "" ) );
                                }

                             }
                        }
                        //Si le jouer actif a moins d'1 coin
                       

                    }
                    else
                    {
                        this.banque.echangerRscBanqueVJoueur(this.tabJoueurs[numJoueurActif],
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

    /**
    * Permets de nourrir les ouvriers à chaque fin de tour.
    */
    public void nourrirOuvrier()
    {
        int iOuvrierNourri      = 0;
        int nbRessourceConsomme = 0;

        for (Joueur j : this.tabJoueurs)
        {
            // Affichage des infos
            this.ctrl.nourrirOuvrierInfo(j);

            // Blé
            iOuvrierNourri      = this.ctrl.nourrirOuvrier ('C', iOuvrierNourri, this.nbMaxOuvriers, j);
            nbRessourceConsomme = iOuvrierNourri;
            j.echangerRscJoueurVBanque(this.banque,'C',nbRessourceConsomme);
            
            // Eau
            nbRessourceConsomme = this.ctrl.nourrirOuvrier('E', iOuvrierNourri, this.nbMaxOuvriers, j);
            iOuvrierNourri      += nbRessourceConsomme;
            j.echangerRscJoueurVBanque(this.banque,'E',nbRessourceConsomme);

            // Pièces
            nbRessourceConsomme = this.ctrl.nourrirOuvrier('M', iOuvrierNourri, this.nbMaxOuvriers, j);

            if (nbRessourceConsomme != 0 ) nbRessourceConsomme+= 2 * nbRessourceConsomme;

            if (nbRessourceConsomme >= 3) iOuvrierNourri      += nbRessourceConsomme -2;
            else iOuvrierNourri += nbRessourceConsomme;

            j.echangerRscJoueurVBanque(this.banque,'M',nbRessourceConsomme);
            
            // On affiche si le joueurs perd du score ou non.
            this.ctrl.finNourrir(iOuvrierNourri, this.nbMaxOuvriers, j);
            
            // On recommence à 0 pour les autres joueurs
            iOuvrierNourri = 0;
            
        }

    }

    /**
    * Permets d'obtenir les informations d'un bâtiment.
    */
    public void getInfoBatiment( )
    {
        this.ctrl.getInfoBatiment();
    }

    /**
    * Permets d'échanger les pièces d'un joueur.
    *
    * @return Détermine s'il a assez de pièces pour échanger (boolean).
    */
    public boolean echangerPiece()
    {
        if ( this.tabJoueurs[numJoueurActif].getRsc('M') < 3 )
            return false;

        Scanner sc = new Scanner( System.in );
        System.out.print( "Entrez Ressources : " );

        String sSaisie = sc.nextLine();

        char rsc = sSaisie.charAt(0);

        if ( rsc != 'A' && rsc != 'C' && rsc != 'E' && rsc != 'P' )
            return false;

        try
        {
            this.tabJoueurs[numJoueurActif].echangerRscJoueurVBanque( this.banque,
                                                                      'M',
                                                                      3            );

            this.banque.echangerRscBanqueVJoueur( this.tabJoueurs[numJoueurActif],
                                                  rsc                            ,
                                                  1                               );


            return true;
        }
        catch (Exception e){ System.out.println( "Ressource Inconnu" ); }

        return false;
    }

    /**
    * Remet à zéro les bâtiments activés.
    */
    public void resetBatimentActivation()
    {
        for(Batiment b: this.ensBatimentActiver)
            b.setActivation(true);
        this.ensBatimentActiver.clear();
    }

    /**
    * Déclaration obligatoire (héritage)
    */
    public void resetDernierOuvrier ()
    {
        this.dernierOuvrierPos = null;
    }

    /**
    * Retourne le tour actuel de la partie.
    *
    * @return Tour actuel de la partie
    */
    public int getTour()
    {
        return this.tourActuel;
    }

    /**
    * Retourne les ressources de la banque.
    *
    * @return Ressources de la banque.
    */
    public Banque getBanque()
    {
        return this.banque;
    }

    /**
    * Passe au tour suivant.
    */
    public void plus1Tour()
    {
        this.tourActuel++;
    }

    /**
    * Met fin à la partie.
    */
    public void finDePartie ()
    {
        Joueur[] tabTmp = new Joueur[this.nbJoueurs];

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

                    System.out.println( tabTmp[i] ) ;
                }
            }

            cpt++;
        }

        this.sauvergardeScore(tabTmp);
        this.ctrl.afficherFinDePartie(tabTmp);
    }

    /**
    * Retourne le tri des scores des joueurs.
    *
    * @param tab
    *       Tableau des joueurs.
    * 
    * @return Boolean qui passe les scores de chaque joueur et retourne true.
    */
    public boolean estTrier ( Joueur[] tab )
    {
        for ( int cpt = 0; cpt < tab.length - 1; cpt++ )
            if ( tab[cpt].getScore() > tab[cpt+1].getScore() )
                return false;

        return true;
    }

    /**
    * Sauvegarde le score des joueurs.
    *
    * @param tab
    *       Tableau des joueurs.
    */
    public void sauvergardeScore(Joueur[] tab)
    {
        GregorianCalendar date = new GregorianCalendar();

        String mois[] = { "Janv", "Fevr", "Mars", "Avr",
                           "Mai", "Juin", "Juill", "Aout",
                           "Sept", "Oct", "Nov", "Dec" };

        String classement[] = { "Premier", "Deuxieme", "Troisieme", "Quatrieme"};       

        try
        {
            PrintWriter pw = new PrintWriter( new FileOutputStream("../data/Score.txt", true) );


            pw.println ("\n" +"Parti du "+ mois[date.get(Calendar.MONTH)] + " "
                                    + date.get(Calendar.DATE) + ", "
                                    + date.get(Calendar.HOUR) + "h ," + date.get(Calendar.MINUTE) + " ( "
                                    + date.get(Calendar.YEAR) + " )\n");

            for(int i=0;i < tab.length;i++)
            {
                 pw.println(String.format("%-10s", classement[i]) + " : "+ tab[i].getNom()+"\n");    
            }

            pw.close();
        }
        catch (Exception e){ e.printStackTrace(); }
    }

}

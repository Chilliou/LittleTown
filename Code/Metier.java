import java.util.Scanner;

public class Metier
{

    private final int NB_OUVRIERS = 5;
    
    private Controleur ctrl;

    private Banque banque;
  
    private int  nbJoueurs;
    private int  tourActuel;

    private int  numJoueurActif;
    private Joueur[] tabJoueurs;

    private String dernierOuvrierPos;

    public Metier(Controleur ctrl)
    {
        this.banque = new Banque();

        this.ctrl              = ctrl;
        this.nbJoueurs         = 2;
        this.numJoueurActif    = 0;
        this.tourActuel        = 1;
        this.tabJoueurs        = new Joueur[2];
        this.dernierOuvrierPos = null;

        this.initJoueur();
    }

    public Joueur getJoueurActif()
    {
        return this.tabJoueurs[numJoueurActif];
    }
    

    public int getNbJoueur() { return this.nbJoueurs; }

    public int getNbOuvrier() { return this.NB_OUVRIERS; }


    private void initJoueur()
    {
        this.tabJoueurs[0] = new Joueur("Rouge");
        this.tabJoueurs[1] = new Joueur("Bleu");
    }

    public void changementJoueur()
    {
        if (this.numJoueurActif == this.tabJoueurs.length -1)
            this.numJoueurActif = 0;
        else
            this.numJoueurActif++;
    }

    public void placerOuvrier()
    {
        if (! this.tabJoueurs[numJoueurActif].aJouer())
        {
            String sCoordOuvrier ;
            sCoordOuvrier = this.ctrl.getSaisiePos();
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


    public boolean construireBatiment()
    {

        if(!this.tabJoueurs[numJoueurActif].aJouer() ) 
        {

            String sCordBatiment ;
            sCordBatiment = this.ctrl.getSaisiePos();
            int col =  sCordBatiment.charAt(0) - (int) ('A');
            int lig = (Integer.parseInt(sCordBatiment.charAt(1)+"")-1) ;

            String sCoordPlacement ;
            sCoordPlacement = this.ctrl.getSaisiePos();
            int y =  sCoordPlacement.charAt(0) - (int) ('A');
            int x = (Integer.parseInt(sCoordPlacement.charAt(1)+"")-1) ;

            

            try
            {
                Batiment bat;
                if ( lig == 6 && col == 1)
                    bat = new Batiment ( "Blé", "A1", "", "C1", 3 );
                else
                    bat = this.ctrl.getBatiment( lig, col ) ;

                TuileVide terrain = this.ctrl.getTuileVide( x, y );

                if(!terrain.getNom().equals( "Vide" ) )
                    return false;

                for(int i=0;i<bat.getCout().length();i+=2)
                    if(this.tabJoueurs[numJoueurActif].getRsc(bat.getCout().charAt(i))<Integer.parseInt(bat.getCout().charAt(i+1)+""))
                        return false;

                if ( bat.getNom().equals( "Blé" ) && this.ctrl.enleverBle() )


                //DU coup on peut ajouter le batiment 
                for(int i=0;i<bat.getCout().length();i+=2)
                    this.tabJoueurs[numJoueurActif].echangerRscJoueurVBanque(this.banque,bat.getCout().charAt(i),Integer.parseInt(bat.getCout().charAt(i+1)+""));

                this.ctrl.setTuile(x,y,bat);
                if ( ! bat.getNom().equals( "Blé" ) )
                    this.ctrl.setTuile(lig,col,terrain);
                this.ctrl.addOuvrier(this.numJoueurActif);
                this.tabJoueurs[numJoueurActif].changeScore(bat.getScore());
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

    public void activerTuile()
    {

        if (this.dernierOuvrierPos != null)
        {
            String coorTuile = "";
            coorTuile = this.ctrl.getSaisiePos();
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

                    System.out.println("> Activation de la tuile : " + this.ctrl.getTuile(xTuile, yTuile).getNom());

                    if ( this.ctrl.getBatiment(xTuile, yTuile).getProprietaire() != this.tabJoueurs[this.numJoueurActif] )
                    {
                        if ( this.tabJoueurs[this.numJoueurActif].getRsc( 'M' ) > 0 )
                            this.tabJoueurs[this.numJoueurActif].echangerRscJoueurVBanque ( this.ctrl.getBatiment(xTuile, yTuile).getProprietaire(), 'M', 1 );
                        else
                            return;

                    }

                    this.banque.echangerRscBanqueVJoueur(this.tabJoueurs[numJoueurActif],
                                                         this.ctrl.getBatiment(xTuile, yTuile).getRevientProd().charAt(0),
                                                         Integer.parseInt( this.ctrl.getBatiment(xTuile, yTuile).getRevientProd().charAt(1) + "" ) );
                    this.ctrl.getTuile(xTuile, yTuile).setActivation(false);
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
        int nbRessourceConsomme = 0;

        for (Joueur j : this.tabJoueurs)
        {
            // Affichage des infos
            this.ctrl.nourrirOuvrierInfo(j);

            // Blé
            iOuvrierNourri      = this.ctrl.nourrirOuvrier ('C', iOuvrierNourri, this.NB_OUVRIERS, j);
            nbRessourceConsomme = iOuvrierNourri;
            j.echangerRscJoueurVBanque(this.banque,'C',nbRessourceConsomme);
            
            // Eau
            nbRessourceConsomme = this.ctrl.nourrirOuvrier('E', iOuvrierNourri, this.NB_OUVRIERS, j);
            iOuvrierNourri      += nbRessourceConsomme;
            j.echangerRscJoueurVBanque(this.banque,'E',nbRessourceConsomme);

            // Pièces
            nbRessourceConsomme = this.ctrl.nourrirOuvrier('M', iOuvrierNourri, this.NB_OUVRIERS, j);
            if (nbRessourceConsomme != 0 ) nbRessourceConsomme+= 2;

            iOuvrierNourri      += nbRessourceConsomme;
            j.echangerRscJoueurVBanque(this.banque,'M',nbRessourceConsomme);
            
            // On affiche si le joueurs perd du score ou non.
            this.ctrl.finNourrir(iOuvrierNourri, this.NB_OUVRIERS, j);
            
            // On recommence à 0 pour les autres joueurs
            iOuvrierNourri = 0;
            
        }

    }

    public void getInfoBatiment( )
    {
      
       String saisie = this.ctrl.getSaisiePos();
        int y =  saisie.charAt(0) - (int) ('A');
        int x = (Integer.parseInt(saisie.charAt(1)+"")-1) ;

      System.out.println(this.ctrl.getBatiment(x,y).infoBatiment()); 
    }

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

    public int getTour()
    {
        return this.tourActuel;
    }

    public void plus1Tour()
    {
        this.tourActuel++;
    }


}

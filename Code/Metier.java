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
            sCoordOuvrier = this.getSaisie();
            int y =  sCoordOuvrier.charAt(0) - (int) ('A');
            int x = (Integer.parseInt(sCoordOuvrier.charAt(1)+"")-1) ;
            
            try
            {
              TuileVide terrain = this.ctrl.getIhm().getTuileVide( x, y );
    
              if ( terrain.getNom().equals( "Vide" ) && terrain.getOuvrier() == null )
              {
                terrain.setOuvrier( new Ouvrier ( x, y ) );
                this.ctrl.getIhm().setTuileVide ( x, y, terrain );
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
            sCordBatiment = this.getSaisie();
            int col =  sCordBatiment.charAt(0) - (int) ('A');
            int lig = (Integer.parseInt(sCordBatiment.charAt(1)+"")-1) ;

            String sCoordPlacement ;
            sCoordPlacement = this.getSaisie();
            int y =  sCoordPlacement.charAt(0) - (int) ('A');
            int x = (Integer.parseInt(sCoordPlacement.charAt(1)+"")-1) ;

            

            try
            {
                Batiment bat = this.ctrl.getIhm().getBatiment( lig, col ) ;
                TuileVide terrain = this.ctrl.getIhm().getTuileVide( x, y );

                if(!terrain.getNom().equals( "Vide" ) )
                    return false;

                for(int i=0;i<bat.getCout().length();i+=2)
                    if(this.tabJoueurs[numJoueurActif].getRsc(bat.getCout().charAt(i))<Integer.parseInt(bat.getCout().charAt(i+1)+""))
                        return false;

                //DU coup on peut ajouter le batiment 
                for(int i=0;i<bat.getCout().length();i+=2)
                    this.tabJoueurs[numJoueurActif].echangerRscJoueurVBanque(this.banque,bat.getCout().charAt(i),Integer.parseInt(bat.getCout().charAt(i+1)+""));

                this.ctrl.getIhm().setTuile(x,y,bat);
                this.ctrl.getIhm().setTuile(lig,col,terrain);
                this.ctrl.getIhm().addOuvrier(this.numJoueurActif);
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



    public String getSaisie()
    {
      String saisie;
      saisie = this.ctrl.getIhm().getSaisiePos();
      return saisie;
    }


    public void activerTuile()
    {

        if (this.dernierOuvrierPos != null)
        {
            String coorTuile = "";
            coorTuile = this.getSaisie();
            int yOuvrier =  this.dernierOuvrierPos.charAt(0) - (int) ('A');
            int xOuvrier = (Integer.parseInt(this.dernierOuvrierPos.charAt(1)+"")-1);

            int yTuile   =  coorTuile.charAt(0) - (int) ('A');
            int xTuile   = (Integer.parseInt(coorTuile.charAt(1)+"")-1);


            // Vérifiaction des coordonnées de la tuile
            if ((xTuile == xOuvrier - 1 || xTuile == xOuvrier + 1 || xTuile == xOuvrier)
             && (yTuile == yOuvrier - 1 || yTuile == yOuvrier + 1 || yTuile == yOuvrier))
            {
                
                // Vérification que la tuile n'a pas deja était activée.
                if (this.ctrl.getIhm().getTuile(xTuile, yTuile).isActivable())
                {
                    System.out.println("> Activation de la tuile : " + this.ctrl.getIhm().getTuile(xTuile, yTuile).getNom());
                    this.banque.echangerRscBanqueVJoueur(this.tabJoueurs[numJoueurActif],
                                                         this.ctrl.getIhm().getTuile(xTuile, yTuile).toString().charAt(0),
                                                         Integer.parseInt( this.ctrl.getIhm().getBatiment(xTuile, yTuile).getRevientProd().charAt(1) + "" ) );
                    this.ctrl.getIhm().getTuile(xTuile, yTuile).setActivation(false);
                }
                else { System.out.println("Vous avez deja était activé cette tuile !"); }
            }
            else { System.out.println("/!\\ Aucun ouvrié se trouve dans le autour de cette tuile !"); }

        }
        else { System.out.println("/!\\ Aucun ouvrié n'as encore été placé !"); }

    }


    public void nourrirOuvrier()
    {
        int iOuvrierNourri = 0;
        int iSaisi;
        Scanner kb = new Scanner(System.in);

        System.out.println("<=======> A table !!!! <=======>\n");
        for (Joueur j : this.tabJoueurs)
        {
            System.out.println(j.toString());
            System.out.println("Vous devez maintenant nourrir vos ouvriers...");
            System.out.println("Vous pouvez utiliser votre eau, votre blé ou 3 pièces par nourriture manquante.");
            System.out.println("En cas de nourriture manquante, vous recevez -3 de score par ouvrier non nourri.");

            System.out.println();
            if (j.getRsc('C') != 0  && iOuvrierNourri < this.NB_OUVRIERS)
            {
                System.out.println("Il vous reste " +  (this.NB_OUVRIERS - iOuvrierNourri) + " ouvrier(s) à nourrir.");
                System.out.println("Combien de votre blé voulez vous utiliser ? : ");
                iSaisi = kb.nextInt();
                iOuvrierNourri += iSaisi;

                
            }
            if (j.getRsc('E') != 0 && iOuvrierNourri < this.NB_OUVRIERS)
            {
                System.out.println("Il vous reste " +  (this.NB_OUVRIERS - iOuvrierNourri) + " ouvrier(s) à nourrir.");
                System.out.println("Combien de votre eau voulez vous utiliser ? : ");
                iSaisi = kb.nextInt();
                iOuvrierNourri += iSaisi;

            }
            if (j.getRsc('M') != 0 && iOuvrierNourri < this.NB_OUVRIERS)
            {
                System.out.println("Il vous reste " +  (this.NB_OUVRIERS - iOuvrierNourri) + " ouvrier(s) à nourrir.");
                System.out.println("Combien de vos pièces voulez vous utiliser ? (3 pièces / ouvrier): ");
                iSaisi = kb.nextInt();

                while (iSaisi >= 3)
                {
                    iSaisi -= 3;
                    iOuvrierNourri+=1;
                }

            }

            if (iOuvrierNourri < this.NB_OUVRIERS)
            {
                System.out.println("Vous n'avez pas nourri " +  (this.NB_OUVRIERS - iOuvrierNourri) + " ouvrier(s)");
                System.out.println("Vous allez recevoir -" + (this.NB_OUVRIERS - iOuvrierNourri)*3 + " de score.");
                j.changeScore(-(this.NB_OUVRIERS - iOuvrierNourri)*3);
                System.out.println("Joueur n°" + j.getNumJoueur() + ", votre score est maintenant de : " + j.getScore());
            }
            else
            {
                System.out.println("Féliciation joueur n°" + j.getNumJoueur() + ", vous avez nourri tous vos ouvriers");
            }

            System.out.println("\n");

        }
    }

    public void getInfoBatiment( )
    {
      
       String saisie = this.ctrl.getIhm().getSaisiePos();
        int y =  saisie.charAt(0) - (int) ('A');
        int x = (Integer.parseInt(saisie.charAt(1)+"")-1) ;

      System.out.println(this.ctrl.getIhm().getBatiment(x,y).infoBatiment()); 
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

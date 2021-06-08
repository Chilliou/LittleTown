

public class Metier
{

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


    public boolean construireBatiment(char col, int lig, Tuile tuile)
    {
        if (! this.tabJoueurs[numJoueurActif].aJouer())
        {
            int x, y;
            x = (int) col - (int) ('A');
    
    
            if (this.ctrl.getIhm().getTuile(x,lig) == null ) return false;
    
            this.ctrl.getIhm().setTuile(x,lig,tuile);
    
            // Le joueur a fait son action
            this.tabJoueurs[numJoueurActif].setAction(true);
    
            return true;
        }
        {
            System.out.println("Vous avez déja réalisé votre action.");
            return false;
        }



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
            if ((xTuile == xOuvrier - 1 || xTuile == yOuvrier + 1 || xTuile == xOuvrier)
             && (yTuile == yOuvrier - 1 || yTuile == yOuvrier + 1 || yTuile == yOuvrier))
            {
                
                // Vérification que la tuile n'a pas deja était activée.
                if (this.ctrl.getIhm().getTuile(xTuile, yTuile).isActivable())
                {
                    System.out.println("> Activation de la tuile : " + this.ctrl.getIhm().getTuile(xTuile, yTuile).getNom());
                    this.banque.echangerRscBanqueVJoueur(this.tabJoueurs[numJoueurActif],this.ctrl.getIhm().getTuile(xTuile, yTuile).toString().charAt(0),1);
                    this.ctrl.getIhm().getTuile(xTuile, yTuile).setActivation(false);
                }
                else { System.out.println("Vous avez deja était activé cette tuile !"); }
            }
            else { System.out.println("/!\\ Aucun ouvrié se trouve dans le autour de cette tuile !"); }

        }
        else { System.out.println("/!\\ Aucun ouvrié n'as encore été placé !"); }

    }


    public String getInfoBatiment( )
    {
      
       String saisie = this.ctrl.getIhm().getSaisiePos();
        int y =  saisie.charAt(0) - (int) ('A');
        int x = (Integer.parseInt(saisie.charAt(1)+"")-1) ;

      return this.ctrl.getIhm().getTuile(x,y).toString();
    }

    public void finTour()
    {
        // Reset des actions du joueurs
        this.tabJoueurs[numJoueurActif].setAction(false);
    }

    public void echangerPiece()
    {
        
    }

    public int getTour()
    {
        return this.tourActuel;
    }

    public void changerJoueurActif()
    {
    	
    }
}



public class Metier
{

    private Controleur ctrl;
  
    private int  nbJoueurs;
    private int  tourActuel;

    private int  numJoueurActif;
    private Joueur[] tabJoueurs;

    public Metier(Controleur ctrl)
    {
        this.ctrl           = ctrl;
        this.nbJoueurs      = 2;
        this.numJoueurActif = 0;
        this.tourActuel     = 1;
        this.tabJoueurs     = new Joueur[2];

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
        String zinzon ;
       zinzon = this.getSaisie();
        int y =  zinzon.charAt(0) - (int) ('A');
        int x = (Integer.parseInt(zinzon.charAt(1)+"")-1) ;
        
        try
        {
          TuileVide terrain = this.ctrl.getIhm().getTuileVide( x, y );

          if ( terrain.getNom().equals( "Vide" ) && terrain.getOuvrier() == null )
          {
            terrain.setOuvrier( new Ouvrier ( x, y ) );
            this.ctrl.getIhm().setTuileVide ( x, y, terrain );
          }
        }
        catch (Exception e){ System.out.println("Tu ne peut pas le poser ici"); }
    
    }


    public boolean construireBatiment(char col, int lig, Tuile tuile)
    {
        int x, y;
        x = (int) col - (int) ('A');


        if (this.ctrl.getIhm().getTuile(x,lig) == null ) return false;

        this.ctrl.getIhm().setTuile(x,lig,tuile);
        return true;


    }



    public String getSaisie()
    {
      String saisie="je sais pas";
      saisie = this.ctrl.getIhm().getSaisiePos();
      return saisie;
    }


    public void activerTuile()
    {
        
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

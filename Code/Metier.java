public class Metier
{

    Controleur ctrl;
    Plateau    plateau;

    private int nbJoueurs;
    private Joueur jActif;
    private int tourActuel;

    public Metier(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.nbJoueurs = 2;
        this.jActif = null;
        this.tourActuel = 1;
    }

    public Joueur getJoueurActif()
    {
        return this.jActif;
    }

    public void placerOuvrier(int x, int y)
    {
        
    }

    public void construireBatiment()
    {
        
    }

    public void activerTuile()
    {
        
    }

    public void getInfoBatiment()
    {
       
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
}
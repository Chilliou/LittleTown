package littletown.ihm;


import littletown.IhmCUI;
import littletown.Tuile;


import java.awt.event.*;
import java.awt.Point;


public class Controleur implements ComponentListener
{

    private Metier metier;

	private FrameMenu               menu;
    private FrameResultat           resultat;
    private FrameSelectNbJoueurs    selectNbJoueurs;

    private FrameBanque             frameBanque;
	private FramePlateau            framePlateau;
	private FrameJoueur             frameJoueur;

    private FrameHelp               frameHelp;

    //private IhmCUI ihm;

	public  Controleur ()
	{
        this.metier  = new Metier();
		this.menu    = new FrameMenu  (this);

        //this.ihm    = new IhmCUI();
	}

    public void appelFrameResultat()
    {
        this.resultat = new FrameResultat(this);
    }

    public void appelFrameSelectNbJoueur()
    {
        this.selectNbJoueurs = new FrameSelectNbJoueurs(this);
    }

    public void appelFrameHelp()
    {
        this.frameHelp = new FrameHelp(this);
    }

	public void appelFramePlateau()
    {
        this.framePlateau = new FramePlateau(this);
        this.frameBanque  = new FrameBanque (this);
		this.frameJoueur  = new FrameJoueur (this);

        this.selectNbJoueurs.closeFrame();

        this.framePlateau.addComponentListener(this);
        this.frameBanque.addComponentListener (this);
        this.frameJoueur.addComponentListener (this);
    }

    public void action ( int numAction )
	{
		if ( numAction == 1 ) this.metier.augmenterNbJoueur();
		if ( numAction == 2 ) this.metier.diminuerNbJoueur();

		this.selectNbJoueurs.majIHM();
	}

	public int getNbJoueur() { return metier.getNbJoueur();}

	public void majFramePlateau(int x, int y)
    {
        this.framePlateau.setLocation(x, y);
    }

    public void majFrameJoueur(int x, int y)
    {
        this.frameJoueur.setLocation(x, y);
    }

    public void majFrameBanque(int x, int y)
    {
        this.frameBanque.setLocation(x, y);
    }

	// Utilisé pour initialiser le plateau bas de l'IHM.
	//public Tuile[] getTuileIHM() { return this.ihm.getTuileIHM(); }


	public void componentMoved(ComponentEvent e) 
    {
        if (e.getSource() == this.framePlateau )
        {
            Point p, p2;
            
            p2 = this.framePlateau.getLocation();
            p2.setLocation(p2.getX()+1010, p2.getY());

            p = this.framePlateau.getLocation();
            p.setLocation(p.getX()-210, p.getY());
            
            this.frameJoueur.setLocation(p);
            this.frameBanque.setLocation(p2);
        }
        /*
        if (e.getSource() == this.frameJoueur )
        {
            Point p;
            p = this.frameJoueur.getLocation();
            p.setLocation(p.getX()+210, p.getY());
            this.framePlateau.setLocation(p);
        }
        if (e.getSource() == this.frameBanque )
        {
            Point p;
            p = this.frameBanque.getLocation();
            p.setLocation(p.getX()-1010, p.getY());
            this.framePlateau.setLocation(p);
        }
        */
    }

	public void componentShown(ComponentEvent e) {}
	public void componentResized(ComponentEvent e) {}
	public void componentHidden(ComponentEvent e) {}

	public static void main(String[] a)
	{
		new Controleur();
	}

}
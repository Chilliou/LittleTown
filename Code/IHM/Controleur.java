import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.Point;

public class Controleur implements ComponentListener
{

    private Metier metier;

	private FrameMenu   menu;
    private FrameResultat   resultat;
    private FrameSelectNbJoueurs selectNbJoueurs;

	private FrameSelectNbJoueurs frameSelectNbJoueurs;

	private FramePlateau framePlateau;
	private FrameJoueurUn frameJoueurUn;

	public  Controleur ()
	{
        this.metier = new Metier();
		this.menu    = new FrameMenu  (this);
	}

    public void appelFrameResultat()
    {
        this.resultat = new FrameResultat(this);
    }

    public void appelFrameSelectNbJoueur()
    {
        this.selectNbJoueurs = new FrameSelectNbJoueurs(this);
    }

	public void appelFramePlateau()
    {
		this.frameJoueurUn = new FrameJoueurUn(this);
        this.framePlateau = new FramePlateau(this);
    }

    public void action ( int numAction )
	{
		if ( numAction == 1 ) this.metier.augmenter();
		if ( numAction == 2 ) this.metier.diminuer ();

		this.selectNbJoueurs.majIHM();
	}

	public int getVal()
	{
		return metier.getVal();
	}

	public void majFramePlateau(int x, int y)
    {
        this.framePlateau.setLocation(x, y);
    }

    public void majFrameJoueur(int x, int y)
    {
        this.frameJoueurUn.setLocation(x, y);
    }

	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void componentMoved(ComponentEvent e) 
    { 
        if (e.getSource() == this.framePlateau )
        {
            System.out.println("Frame Plateau se déplace --->" + "(" + this.framePlateau.getX() + ": " + this.framePlateau.getY() + ")");
            Point p;
            p = this.framePlateau.getLocation();
            p.setLocation(p.getX(), p.getY()+310);
            this.frameJoueurUn.setLocation(p);
        }
        if (e.getSource() == this.frameJoueurUn )
        {
            System.out.println("Frame Joueur se déplace --->" + "(" + this.frameJoueurUn.getX() + ": " + this.frameJoueurUn.getY() + ")");
            Point p;
            p = this.frameJoueurUn.getLocation();
            p.setLocation(p.getX(), p.getY()-310);
            this.framePlateau.setLocation(p);
        }
    }

	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] a)
	{
		new Controleur();
	}

}
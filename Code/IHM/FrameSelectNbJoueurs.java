import javax.swing.*;
import java.awt.BorderLayout;

public class FrameSelectNbJoueurs extends JFrame
{
    
    Controleur ctrl;

    PanelSelectNbJoueurs panelSelectNbJoueurs;

    public FrameSelectNbJoueurs(Controleur ctrl)
    {

        this.ctrl = ctrl;

        this.setTitle("Joueurs");

        this.setSize(250,150);
        this.setResizable(false);

        this.panelSelectNbJoueurs = new PanelSelectNbJoueurs(ctrl);

        this.add(this.panelSelectNbJoueurs);


        this.setVisible(true);
    }

    public void majIHM()
	{
		this.panelSelectNbJoueurs.repaint();
	}

    public void closeFrame()
    {
        this.dispose();
    }

}

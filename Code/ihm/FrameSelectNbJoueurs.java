package littletown.ihm;


import javax.swing.*;

public class FrameSelectNbJoueurs extends JFrame
{
    
    private Controleur ctrl;

    private PanelSelectNbJoueurs panelSelectNbJoueurs;

    public FrameSelectNbJoueurs(Controleur ctrl)
    {

        this.ctrl = ctrl;

        this.setTitle("Joueurs");
        //this.setSize(250,150);
        this.setResizable(false);

        // Création des panels
        this.panelSelectNbJoueurs = new PanelSelectNbJoueurs(ctrl);

        // Positionnement des Panels
        this.add(this.panelSelectNbJoueurs);

        // On centre la fenêtre au millieu de l'écran
		this.setLocationRelativeTo(null);

        // Activation de la fenêtre
        this.pack();
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

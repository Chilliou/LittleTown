import javax.swing.*;

import java.awt.Dimension;
import java.awt.Toolkit;

public class FrameJoueur extends JFrame
{
	PanelJoueur   panelJoueur;

	public FrameJoueur ( Controleur ctrl)
	{

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;

		this.setTitle    ( "Joueur 1" );
		this.setSize(200,height-100);
        this.setResizable(false);

		// Organisation du LayoutManager

		// Création des Panels
		this.panelJoueur   = new PanelJoueur   ( ctrl );

		// Positionnement des Panels
		this.add ( this.panelJoueur   );

		// Fenetre en premier plan
		this.toFront();
		this.setAlwaysOnTop(true);

		// Activation de la fenetre
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setVisible ( true );
	}

}
import javax.swing.*;

import java.awt.Dimension;
import java.awt.Toolkit;

import java.awt.BorderLayout;

public class FrameJoueur extends JFrame
{
	PanelHautJoueur    panelHautJoueur;
	PanelCentreJoueur  panelCentreJoueur;
	PanelBasJoueurs     panelBasJoueur;

	public FrameJoueur ( Controleur ctrl)
	{
		// Positionnement et dimensionnement
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		// Infos de la frame
		this.setTitle    ( "Joueur 1" );
		this.setSize(200,screenHeight-100);
        this.setResizable(false);
		this.setLocation(screenWidth / 2 - 500 - 210, 50);


		/**************************/
		/*  Création des panels   */
		/**************************/
		this.panelHautJoueur     = new PanelHautJoueur  (ctrl);
		this.panelCentreJoueur   = new PanelCentreJoueur(ctrl);
		this.panelBasJoueur      = new PanelBasJoueurs   (ctrl);


		/********************************/
		/*  Positionnement des panels   */
		/********************************/
		this.add ( this.panelHautJoueur,   BorderLayout.NORTH   );
		this.add ( this.panelCentreJoueur, BorderLayout.CENTER  );
		this.add ( this.panelBasJoueur,    BorderLayout.SOUTH   );



		// Fenetre en premier plan
		this.toFront();
		this.setAlwaysOnTop(true);

		// Activation de la fenetre
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setVisible ( true );
	}

}
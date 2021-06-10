import javax.swing.*;

import java.awt.Dimension;
import java.awt.Toolkit;

import java.awt.BorderLayout;

public class FrameBanque extends JFrame
{

	private PanelBanqueNorth panelBanqueNorth;
	private PanelBanqueSouth panelBanqueSouth;

	private Controleur ctrl;

	public FrameBanque ( Controleur ctrl )
	{
		this.ctrl = ctrl;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;


		this.setTitle    ( "Banque" );
		this.setSize(200, screenHeight-100);
		this.setLocation(screenWidth / 2 + 500 + 10, 50);
        this.setResizable(false);

		// Organisation du LayoutManager

		// Création des Panels
		this.panelBanqueNorth  = new PanelBanqueNorth ( ctrl );
		this.panelBanqueSouth   = new PanelBanqueSouth( ctrl );

		// Positionnement des Panels
		this.add ( this.panelBanqueNorth, BorderLayout.NORTH );
		this.add ( this.panelBanqueSouth, BorderLayout.SOUTH );

		// Fenetre en premier plan
		this.toFront();
		this.setAlwaysOnTop(true);

		// Activation de la fenetre
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setVisible ( true );
	}
	


}
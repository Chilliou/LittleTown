package littletown.ihm;


import javax.swing.*;

import java.awt.BorderLayout;

public class FrameHelp extends JFrame
{

	private PanelHelp panelHelp;

    Controleur ctrl;

	public FrameHelp ( Controleur ctrl )
	{
		this.ctrl = ctrl;

		this.setTitle    ( "Aide" );
		this.setSize(200, 200);
        this.setResizable(false);

		// Organisation du LayoutManager

		// Création des Panels
		this.panelHelp  = new PanelHelp ( ctrl );

		// Positionnement des Panels
		this.add ( this.panelHelp, BorderLayout.NORTH );

		// Activation de la fenetre
        this.setLocationRelativeTo(null);
		this.setVisible ( true );
	}
	
	public void majIHM()
	{
		this.panelHelp.repaint();
	}


}
import javax.swing.*;

import java.awt.Dimension;
import java.awt.Toolkit;

public class FrameBanque extends JFrame
{
	PanelBanque   panelBanque;

	public FrameBanque ( Controleur ctrl)
	{

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;

		this.setTitle    ( "Banque" );
		this.setSize(200, height-100);
        this.setResizable(false);

		// Organisation du LayoutManager
		

		// CrÃ©ation des Panels
		this.panelBanque   = new PanelBanque   ( ctrl );

		// Positionnement des Panels
		this.add ( this.panelBanque   );

		// Fenetre en premier plan
		this.toFront();
		this.setAlwaysOnTop(true);

		// Activation de la fenetre
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setVisible ( true );
	}
	


}
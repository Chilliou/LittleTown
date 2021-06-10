import javax.swing.*;
import java.awt.*;

public class FrameResultat extends JFrame
{
	PanelResultat   panelResultat;

	public FrameResultat ( Controleur ctrl)
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		this.setTitle    ( "LittleTown | Résultats" );

		int x = (screenSize.width - this.getWidth()) / 5;
        int y = (screenSize.height - this.getHeight()) / 10;
        this.setLocation(x, y);
        
		this.setResizable(false);
		this.setSize(820,150);

		// Organisation du LayoutManager

		// Création des Panels
		this.panelResultat   = new PanelResultat   ( ctrl );

		// Positionnement des Panels
		this.add ( this.panelResultat   );

		// Activation de la fenetre
		this.setVisible ( true );
	}

}
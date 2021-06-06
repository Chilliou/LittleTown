import javax.swing.*;
import java.awt.*;

public class FrameResultat extends JFrame
{
	PanelResultat   panelResultat;

	public FrameResultat ( Controleur ctrl)
	{
		this.setTitle    ( "LittleTown | Résultats" );
		this.setLocation ( 10, 10     );
        this.setResizable(false);
		this.setSize(820,150);
		// Organisation du LayoutManager
        


		// CrÃ©ation des Panels
		this.panelResultat   = new PanelResultat   ( ctrl );

		// Positionnement des Panels
		this.add ( this.panelResultat   );

		// Activation de la fenÃªtre
		this.setVisible ( true );
	}

}
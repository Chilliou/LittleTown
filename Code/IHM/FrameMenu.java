import javax.swing.*;
import java.awt.*;

public class FrameMenu extends JFrame
{
	PanelMenu   panelMenu;

	public FrameMenu ( Controleur ctrl)
	{
		this.setTitle    ( "LittleTown | Menu" );
		this.setLocation ( 10, 10     );
		this.setSize     ( 400, 340  );
        this.setResizable(false);

		// Organisation du LayoutManager
        


		// CrÃ©ation des Panels
		this.panelMenu   = new PanelMenu   ( ctrl );

		// Positionnement des Panels
		this.add ( this.panelMenu   );

		// Activation de la fenÃªtre
		this.setVisible ( true );
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}


}
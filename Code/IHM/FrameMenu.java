import javax.swing.*;
import java.awt.*;

public class FrameMenu extends JFrame
{
	PanelMenu   panelMenu;

	public FrameMenu ( Controleur ctrl)
	{
		this.setTitle    ( "LittleTown | Menu" );
		this.setSize     ( 400, 340  );
        this.setResizable(false);

		// Organisation du LayoutManager
        


		// CrÃ©ation des Panels
		this.panelMenu   = new PanelMenu   ( ctrl );

		// Positionnement des Panels
		this.add ( this.panelMenu   );

		// On centre la fenêtre au millieu de l'écran
		this.setLocationRelativeTo(null);

		// Activation de la fenÃªtre
		this.setVisible ( true );
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}


}
import javax.swing.*;
import java.awt.*;

public class FramePlateau extends JFrame
{
	PanelPlateau   panelPlateau;

	public FramePlateau ( Controleur ctrl)
	{

		this.setTitle    ( "Plateau de jeu" );
		this.setSize     ( 710, 720  );
        this.setResizable(false);

		// Organisation du LayoutManager
		


		// CrÃ©ation des Panels
		this.panelPlateau   = new PanelPlateau   ( ctrl );

		// Positionnement des Panels
		this.add ( this.panelPlateau   );

		// Activation de la fenÃªtre
		this.setVisible ( true );
	}
	


}
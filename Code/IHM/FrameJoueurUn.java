import javax.swing.*;
import java.awt.*;

public class FrameJoueurUn extends JFrame
{
	PanelJoueurUn   panelPlateau;


	public FrameJoueurUn ( Controleur ctrl)
	{

		this.setTitle    ( "Plateau de jeu" );
		this.setLocation ( 10, 10     );
		this.setSize     ( 710, 100  );
        this.setResizable(false);

		// Organisation du LayoutManager
		


		// CrÃ©ation des Panels
		this.panelPlateau   = new PanelJoueurUn   ( ctrl );

		// Positionnement des Panels
		this.add ( this.panelPlateau   );

		// Activation de la fenÃªtre
		this.setVisible ( true );
	}
	


}
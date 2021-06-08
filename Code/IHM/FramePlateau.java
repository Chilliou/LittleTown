import javax.swing.*;

public class FramePlateau extends JFrame
{
	PanelPlateau   panelPlateau;

	Controleur ctrl;

	public FramePlateau ( Controleur ctrl)
	{

		this.ctrl = ctrl;

		this.setTitle    ( "Plateau de jeu" );
		this.setSize(1000,580);
        this.setResizable(false);

		// CrÃ©ation des Panels
		this.panelPlateau   = new PanelPlateau   ( ctrl );

		// Positionnement des Panels
		this.add ( this.panelPlateau   );

		// On centre le plateau
		this.setLocationRelativeTo(null);

		// Activation de la fenetre
		this.setVisible ( true );
	}

	public void fermerFrame()
	{
		this.dispose();
	}
}
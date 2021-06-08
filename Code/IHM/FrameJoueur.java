import javax.swing.*;

public class FrameJoueur extends JFrame
{
	PanelJoueur   panelJoueur;

	public FrameJoueur ( Controleur ctrl)
	{

		this.setTitle    ( "Joueur 1" );
		this.setSize(1000,100);
        this.setResizable(false);

		// Organisation du LayoutManager
	

		// CrÃ©ation des Panels
		this.panelJoueur   = new PanelJoueur   ( ctrl );


		// Positionnement des Panels
		this.add ( this.panelJoueur   );

		// Activation de la fenÃªtre
		this.setVisible ( true );
	}
	


}
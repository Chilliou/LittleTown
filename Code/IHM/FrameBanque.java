import javax.swing.*;

public class FrameBanque extends JFrame
{
	PanelBanque   panelBanque;

	public FrameBanque ( Controleur ctrl)
	{

		this.setTitle    ( "Banque" );
		this.setSize(1000,100);
        this.setResizable(false);

		// Organisation du LayoutManager
		

		// CrÃ©ation des Panels
		this.panelBanque   = new PanelBanque   ( ctrl );

		// Positionnement des Panels
		this.add ( this.panelBanque   );

		// Activation de la fenÃªtre
		this.setVisible ( true );
	}
	


}
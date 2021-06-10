import javax.swing.*;
import java.awt.*;

import java.awt.event.*;

public class PanelMenu extends JPanel implements ActionListener
{
	Controleur ctrl;

    JLabel lblLogo;
    ImageIcon imgLogo;

    JPanel panelTemp;
    JFrame panelJoueurs;

    JButton btnNouvellePartie;
    JButton btnResultats;
    JButton btnQuitter;

	public PanelMenu( Controleur ctrl)
	{
		this.ctrl = ctrl;

        
		// CrÃ©ation des ContrÃ´les
        this.imgLogo = new ImageIcon("../../img/logo.png");
        this.lblLogo = new JLabel(this.imgLogo);

        this.btnNouvellePartie = new JButton("Nouvelle partie");
        this.btnResultats = new JButton("Résultats");
        this.btnQuitter = new JButton("Quitter");

        this.panelTemp = new JPanel();

		// Positionnement des ContrÃ´les
        this.add(this.lblLogo);

        this.add(this.panelTemp);

        panelTemp.setLayout(new GridLayout(3,1,5,5));

        panelTemp.add(this.btnNouvellePartie);
        panelTemp.add(this.btnResultats);
        panelTemp.add(this.btnQuitter);

        // Activation des composants
        
        this.btnNouvellePartie.addActionListener(this);
        this.btnResultats.addActionListener(this);
        this.btnQuitter.addActionListener(this);

	}

    public void actionPerformed(ActionEvent e) 
    {

        if(e.getSource() == this.btnNouvellePartie)
        {
          this.ctrl.appelFrameSelectNbJoueur();
        }

        if(e.getSource() == this.btnResultats)
        {
            this.ctrl.appelFrameResultat();
        }

        if(e.getSource() == this.btnQuitter)
        {
            System.exit(0);
        }

    }

}
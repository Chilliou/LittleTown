import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;

public class PanelSelectNbJoueurs extends JPanel implements ActionListener
{
	Controleur ctrl;

    FrameSelectNbJoueurs frameSelectNbJoueurs;

    private JButton btnMoins;
    private JButton btnPlus;
    private JButton btnCommencer;

    private JComboBox cbPlateau;
    
    private JTextField txtNbJoueurs;

    private JPanel panelTemp;

    private String plateaux[] = { "Plateau 1", "Plateau 2" }; 

	public PanelSelectNbJoueurs( Controleur ctrl )
	{
		this.ctrl = ctrl;

        this.setLayout(new BorderLayout());

        // Création des composants
        this.btnMoins = new JButton("-");
        this.btnPlus = new JButton("+");
        this.btnCommencer = new JButton("Commencer la partie");
        
        this.panelTemp = new JPanel();
        panelTemp.setLayout(new GridLayout(2,1));

        this.txtNbJoueurs = new JTextField();
        this.txtNbJoueurs.setEditable(false);
        this.txtNbJoueurs.setHorizontalAlignment(JTextField.CENTER);
        this.txtNbJoueurs.setText("" + this.ctrl.getVal());

        this.cbPlateau = new JComboBox<>(this.plateaux);

        // Placement des composants

        this.add(panelTemp, BorderLayout.NORTH);
        
        panelTemp.setLayout(new GridLayout(2,1));
        panelTemp.add(new JLabel("Paramètres de la partie :", JLabel.CENTER));
        panelTemp.add(this.cbPlateau);
        
        this.add(btnMoins, BorderLayout.WEST);
        this.add(txtNbJoueurs, BorderLayout.CENTER);
        this.add(btnPlus, BorderLayout.EAST);

        this.add(btnCommencer, BorderLayout.SOUTH);

        // Activation des composants

        this.btnPlus.addActionListener(this);
        this.btnMoins.addActionListener(this);

        this.btnCommencer.addActionListener(this);
    }

    public void paintComponent (Graphics g)
	{
		super.paintComponent(g);

		this.txtNbJoueurs.setText ( "" + this.ctrl.getVal() );
	}

    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == this.btnPlus)
        {
            this.ctrl.action(1);
        }

        if(e.getSource() == this.btnMoins)
        {
            this.ctrl.action(2);
        }

        if(e.getSource() == this.btnCommencer)
        {
            this.ctrl.appelFramePlateau();
        }
        
    }

}
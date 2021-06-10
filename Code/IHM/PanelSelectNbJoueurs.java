import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class PanelSelectNbJoueurs extends JPanel implements ActionListener, FocusListener
{
	Controleur ctrl;

    FrameSelectNbJoueurs frameSelectNbJoueurs;

    private JButton btnMoins;
    private JButton btnPlus;
    private JButton btnCommencer;

    private JComboBox<String> cbPlateau;
    
    private JTextField txtNbJoueurs;

    private JPanel panelTemp;
    private JPanel panelTempNomsJoueurs;
    private JPanel panelTempNbJoueurs;

    private String plateaux[] = { "Plateau 1", "Plateau 2" };

    private JTextField txtNomJoueurs[];

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
        this.txtNbJoueurs.setText("" + this.ctrl.getNbJoueur());

        this.panelTempNbJoueurs = new JPanel(new GridLayout(1, 3));

        this.cbPlateau = new JComboBox<>(this.plateaux);

        this.txtNomJoueurs = new JTextField[4];

        for (int i=0; i < this.txtNomJoueurs.length; i++)
        {
            this.txtNomJoueurs[i] = new JTextField(20);
            this.txtNomJoueurs[i].setText("Nom du joueur " + (i+1));
        }

        this.panelTempNomsJoueurs = new JPanel(new GridLayout(5,0));
            
        // Placement des composants

        this.add(panelTemp, BorderLayout.NORTH);
        
        panelTemp.setLayout(new GridLayout(2,1));
        panelTemp.add(new JLabel("Paramètres de la partie :", JLabel.CENTER));
        panelTemp.add(this.cbPlateau);

        this.add(this.panelTempNbJoueurs, BorderLayout.CENTER);

        this.panelTempNbJoueurs.add(btnMoins);
        this.panelTempNbJoueurs.add(txtNbJoueurs);
        this.panelTempNbJoueurs.add(btnPlus);

        this.add(this.panelTempNomsJoueurs, BorderLayout.SOUTH);


        for ( JTextField txtJ : this.txtNomJoueurs ) 
        {
            this.panelTempNomsJoueurs.add(txtJ);
        }

        panelTempNomsJoueurs.add(btnCommencer);

        // Activation des composants

        this.btnPlus.addActionListener(this);
        this.btnMoins.addActionListener(this);

        for (int i = 0; i < this.txtNomJoueurs.length; i++) {
            this.txtNomJoueurs[i].addFocusListener(this);   
        }

        this.btnCommencer.addActionListener(this);
    }

    public void paintComponent (Graphics g)
	{
		super.paintComponent(g);

		this.txtNbJoueurs.setText ( "" + this.ctrl.getNbJoueur() );

        if (this.ctrl.getNbJoueur() == 2)
        {
            this.txtNomJoueurs[2].setVisible(false);
            this.txtNomJoueurs[3].setVisible(false);
        }

        if (this.ctrl.getNbJoueur() == 3)
        {
            this.txtNomJoueurs[2].setVisible(true);
            this.txtNomJoueurs[3].setVisible(false);
        }

        if (this.ctrl.getNbJoueur() == 4)
        {
            this.txtNomJoueurs[2].setVisible(true);
            this.txtNomJoueurs[3].setVisible(true);
        }


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

    public void focusGained(FocusEvent e) {  
        for (int i = 0; i < this.txtNomJoueurs.length; i++) {
            if(e.getSource() == this.txtNomJoueurs[i])
            {
                this.txtNomJoueurs[i].setText("");  
            }
        }
    }  

    public void focusLost(FocusEvent e) { 

        for (int i = 0; i < this.txtNomJoueurs.length; i++) {
            if (this.txtNomJoueurs[i].getText().length() == 0) {  
                this.txtNomJoueurs[i].setText("Nom du joueur " + (i+1));
            }
        }
 
    }
}
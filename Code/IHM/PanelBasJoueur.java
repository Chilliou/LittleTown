package littletown.ihm;


import javax.swing.*;

import java.awt.GridLayout;

import java.awt.event.*;

public class PanelBasJoueur extends JPanel implements ActionListener
{
    // Attributs
    Controleur ctrl;

    private JButton    btnAide;
    private JButton    btnEchangerPiece;
    private JLabel     lblAction;


    public PanelBasJoueur(Controleur ctrl)
    {

        this.ctrl = ctrl;

        this.setLayout( new GridLayout(3, 1));

        /************************************/
        /*  Création des composants         */
        /************************************/

        this.lblAction        = new JLabel("A vous de jouer.");
        this.btnEchangerPiece = new JButton("Echanger pièce");
        this.btnAide          = new JButton("Aide");


        /************************************/
        /*  Positionnement des composants   */
        /************************************/

        this.add(lblAction);
        this.add(btnEchangerPiece);
        this.add(btnAide);

        // Activation des composants

        this.btnAide.addActionListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent e) 
    {

        if(e.getSource() == this.btnAide)
        {
            this.ctrl.appelFrameHelp();
        }
        
    }

    
}

import javax.swing.*;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

public class PanelBasJoueurs extends JPanel
{
    // Attributs
    private Controleur ctrl;

    private JButton    btnAide;
    private JButton    btnEchangerPiece;
    private JLabel     lblAction;


    public PanelBasJoueurs(Controleur ctrl)
    {
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

    }
}

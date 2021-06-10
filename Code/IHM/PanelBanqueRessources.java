import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.Image;

import java.awt.Font;

public class PanelBanqueRessources extends JPanel
{

    private Controleur ctrl;

    private ImageIcon[] imgRessources;
    private String[] imgFichier = new String[] {"eau.png", "ble.png", "pierre.png", "bois.png", "piece_1.png"};

    private JLabel[]  lblRessources;
    private JLabel[]  lblNbRessources;

    public PanelBanqueRessources(Controleur ctrl) 
    {
        this.ctrl = ctrl;

        this.setLayout(new GridLayout(6,2,5,5));
        this.setBorder(new EmptyBorder(10, 0, 0, 0));

        // Création des composants

        this.imgRessources = new ImageIcon[this.imgFichier.length];
        this.lblRessources = new JLabel[this.imgFichier.length];
        this.lblNbRessources = new JLabel[this.imgFichier.length];

        for (int i = 0; i < this.imgRessources.length; i++) {
            this.imgRessources[i] = new ImageIcon(new ImageIcon("../../img/" + this.imgFichier[i]).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
            this.lblRessources[i] = new JLabel(this.imgRessources[i]);
            this.lblNbRessources[i] = new JLabel("" + i);
            this.lblNbRessources[i].setFont(new Font ("Arial", Font.PLAIN, 20));
        }

        // Posionnement des composants 

        for (int i = 0; i < this.imgFichier.length; i++) {
            this.add(this.lblRessources[i]);
            this.add(this.lblNbRessources[i]);
        }

}

}
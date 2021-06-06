import java.awt.Graphics;

import javax.swing.*;

import java.awt.Image;

public class PanelJoueurUn extends JPanel
{

    Controleur ctrl;

    public PanelJoueurUn(Controleur ctrl) 
    {
        this.ctrl = ctrl;

        this.add(new JLabel("Ensemble des ressources du joueur 1"));

    }

}

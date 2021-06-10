import java.awt.Graphics;

import javax.swing.*;

import java.awt.Image;

public class PanelJoueur extends JPanel
{

    Controleur ctrl;

    public PanelJoueur(Controleur ctrl) 
    {
        this.ctrl = ctrl;

        this.add(new JLabel("Ensemble des ressources du joueur 1"));

    }

}

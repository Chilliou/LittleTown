package littletown.ihm;


import java.awt.Graphics;

import javax.swing.*;

import java.awt.Image;

public class PanelBanque extends JPanel
{

    Controleur ctrl;

    public PanelBanque(Controleur ctrl) 
    {
        this.ctrl = ctrl;

        this.add(new JLabel("Ensemble des ressources de la banque"));

    }

}

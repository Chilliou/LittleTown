import java.awt.Color;

import javax.swing.*;

public class PanelBanqueHeader extends JPanel
{

    private Controleur ctrl;


    public PanelBanqueHeader(Controleur ctrl) 
    {
        this.ctrl = ctrl;

        this.setBackground(Color.GRAY);

        // Création des composants


        // Posionnement des composants 

        this.add(new JLabel("Ressources :"), JLabel.CENTER);


    }

}

import javax.swing.*;

import java.awt.Graphics;
import java.awt.Image;

public class PanelPlateau extends JPanel
{

    Controleur ctrl;
    Image fond;

    public PanelPlateau(Controleur ctrl) 
    {
        this.ctrl = ctrl;

        // Image de fond
        ImageIcon imgFond = new ImageIcon("../../img/plateau_1.png");
        this.fond = imgFond.getImage().getScaledInstance(700, 700, Image.SCALE_DEFAULT);

        this.add(new JLabel("Plateau de jeu", JLabel.CENTER));

    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(this.fond, 0, 0, null);
    }
    
}

import javax.swing.*;

import java.awt.GridLayout;
import java.awt.Image;

public class PanelBanque extends JPanel
{

    Controleur ctrl;

    private JPanel panelTemp;

    private ImageIcon imgEau;
    private ImageIcon imgBle;
    private ImageIcon imgPierre;
    private ImageIcon imgBois;

    private JLabel    lblEau;
    private JLabel    lblBle;
    private JLabel    lblPierre;
    private JLabel    lblBois;

    public PanelBanque(Controleur ctrl) 
    {
        this.ctrl = ctrl;


        // Création des composants

        this.imgEau = new ImageIcon(new ImageIcon("../../img/eau.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        this.imgBle = new ImageIcon(new ImageIcon("../../img/ble.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        this.imgPierre = new ImageIcon(new ImageIcon("../../img/pierre.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        this.imgBois = new ImageIcon(new ImageIcon("../../img/bois.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));

        this.lblEau = new JLabel(this.imgEau, JLabel.CENTER);
        this.lblBle = new JLabel(this.imgBle, JLabel.CENTER);
        this.lblPierre = new JLabel(this.imgPierre, JLabel.CENTER);
        this.lblBois = new JLabel(this.imgBois, JLabel.CENTER);

        this.panelTemp = new JPanel(new GridLayout(4,2));

        // Posionnement des composants 

        this.add(new JLabel("Ressources :"));

        this.add(this.panelTemp);

        this.panelTemp.add(this.lblEau);
        this.panelTemp.add(new JLabel("2"));

        this.panelTemp.add(this.lblBle);
        this.panelTemp.add(new JLabel("3"));

        this.panelTemp.add(this.lblPierre);
        this.panelTemp.add(new JLabel("5"));

        this.panelTemp.add(this.lblBois);
        this.panelTemp.add(new JLabel("6"));

    }

}

import javax.swing.*;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.ResourceBundle.Control;


public class PanelCentreJoueur extends JPanel
{

    private JLabel lblNbEau;
    private JLabel lblNbBois;
    private JLabel lblNbBle;
    private JLabel lblNbPierre;
    private JLabel[] lblNbRessource;

    private Controleur ctrl;

    public PanelCentreJoueur(Controleur ctrl)
    {
        this.ctrl = ctrl;

        // Layout
        this.setLayout( new BorderLayout() );

        /************************************/
        /*  Création des composants         */
        /************************************/

        // Panels
        JPanel panelHaut;
        JPanel panelRessources;
        JLabel lblRessource;

        panelHaut           = new JPanel();
        panelRessources     = new JPanel( new GridLayout(5, 2));

        // Composants
        lblRessource        = new JLabel("Ressources");
        this.lblNbRessource = new JLabel[5];
        JLabel[] lblImgRessources = new JLabel[5];

        this.lblNbRessource[0] = new JLabel("5");
        this.lblNbRessource[1] = new JLabel("5");
        this.lblNbRessource[2] = new JLabel("5");
        this.lblNbRessource[3] = new JLabel("5");
        this.lblNbRessource[4] = new JLabel("5");

        ImageIcon imgEau    = new ImageIcon(new ImageIcon("../../img/eau.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        lblImgRessources[0] = new JLabel(imgEau);

        ImageIcon imgBois   = new ImageIcon(new ImageIcon("../../img/ble.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        lblImgRessources[1] = new JLabel(imgBois);

        ImageIcon imgBle    = new ImageIcon(new ImageIcon("../../img/bois.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        lblImgRessources[2] = new JLabel(imgBle);

        ImageIcon imgPierre = new ImageIcon(new ImageIcon("../../img/pierre.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        lblImgRessources[3] = new JLabel(imgPierre);

        ImageIcon imgPiece  = new ImageIcon(new ImageIcon("../../img/piece_1.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        lblImgRessources[4] = new JLabel(imgPiece);

        /************************************/
        /*  Positionnement des composants   */
        /************************************/

        panelHaut.add(lblRessource);

        for(int cpt = 0; cpt < 5; cpt ++)
        {
            panelRessources.add(lblImgRessources[cpt]);
            panelRessources.add(this.lblNbRessource[cpt]);
        }

        this.add(panelHaut, BorderLayout.NORTH);
        this.add(panelRessources, BorderLayout.CENTER);
    }
}

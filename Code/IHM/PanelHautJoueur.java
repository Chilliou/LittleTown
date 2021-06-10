import javax.swing.*;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

public class PanelHautJoueur extends JPanel
{
        // Attributs
        JPanel panelPseudo;
        JPanel panelScoreOuvrier;

        private JLabel lblJoueurPlacement;
        private JLabel lblJoueurScore;
        private JLabel lblNBouvrier;
        
        private Controleur ctrl;

        public PanelHautJoueur(Controleur ctrl)
        {

            this.ctrl = ctrl;
            
            // Layout
            this.setLayout( new BorderLayout() );

            /************************************/
            /*  Création des composants         */
            /************************************/

            // Panels
            JPanel panelGridOuvrier = new JPanel( new GridLayout(1, 2) );
            this.panelPseudo        = new JPanel( new BorderLayout() );
            this.panelScoreOuvrier  = new JPanel( new BorderLayout() );

            // Composants
            JLabel lblPseudo        = new JLabel("Pseudo");
            lblPseudo.setForeground(Color.BLUE);
            lblPseudo.setFont( new Font ("Arial", Font.PLAIN, 20));
            this.lblJoueurPlacement = new JLabel("#~   ");
            this.lblJoueurPlacement.setFont( new Font ("Arial", Font.PLAIN, 20));
            this.lblJoueurScore     = new JLabel("Score : 5", JLabel.CENTER);
            this.lblJoueurScore.setFont( new Font ("Arial", Font.PLAIN, 20));
            ImageIcon  imgTest = new ImageIcon(new ImageIcon("../../img/ouvrier_bleu.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
            JLabel lblImgOuvrier    = new JLabel(imgTest);
            this.lblNBouvrier       = new JLabel("5");
            this.lblNBouvrier.setFont( new Font ("Arial", Font.PLAIN, 20));

            /************************************/
            /*  Positionnement des composants   */
            /************************************/

            panelGridOuvrier.add(lblImgOuvrier);
            panelGridOuvrier.add(this.lblNBouvrier);

            this.panelPseudo.add(lblJoueurPlacement, BorderLayout.WEST);
            this.panelPseudo.add(lblPseudo         , BorderLayout.CENTER);

            this.panelScoreOuvrier.add(this.lblJoueurScore, BorderLayout.NORTH);
            this.panelScoreOuvrier.add(panelGridOuvrier, BorderLayout.CENTER);

            this.add( this.panelPseudo, BorderLayout.NORTH);
            this.add( this.panelScoreOuvrier, BorderLayout.CENTER);

            // Colors
            this.panelPseudo.setBackground(Color.RED);
        }
}

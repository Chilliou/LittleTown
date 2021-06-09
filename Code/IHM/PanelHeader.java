import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;

public class PanelHeader extends JPanel
{
    private Controleur ctrl;

    public PanelHeader(Controleur ctrl)
    {
        this.ctrl = ctrl;
        /********************************/
        /*Création des composants       */
        /********************************/
        this.setLayout( new BorderLayout() );

        // Image
        JLabel lblLogoJoueur = new JLabel();
        // A COMPLETER

        // Label
        JLabel lblJoueur = new JLabel("Joueur n°1");

        /********************************/
        /*Positionnement des composants */
        /********************************/
        this.add( lblJoueur, BorderLayout.CENTER );
        

        /********************************/
        /* Couleurs                     */
        /********************************/
        this.setBackground(Color.RED);


    }
}

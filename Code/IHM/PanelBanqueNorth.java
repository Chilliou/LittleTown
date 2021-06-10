import javax.swing.*;

import java.awt.BorderLayout;

public class PanelBanqueNorth extends JPanel
{

    private Controleur ctrl;

    private PanelBanqueHeader panelBanqueHeader;
    private PanelBanqueRessources panelBanqueRessources;


    public PanelBanqueNorth(Controleur ctrl) 
    {
        this.ctrl = ctrl;

        this.setLayout(new BorderLayout());

        this.panelBanqueHeader = new PanelBanqueHeader(ctrl);
        this.panelBanqueRessources = new PanelBanqueRessources(ctrl);

        // Création des composants

        // Posionnement des composants 

        this.add(this.panelBanqueHeader, BorderLayout.NORTH);
        this.add(this.panelBanqueRessources, BorderLayout.CENTER);


    }

}

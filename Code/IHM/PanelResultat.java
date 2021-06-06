import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import java.awt.*;

public class PanelResultat extends JPanel
{
	Controleur ctrl;

	public PanelResultat( Controleur ctrl)
	{
		this.ctrl = ctrl;

        String donnees[][]={ {"05/06/2021", "32 (Quentin)","22 (Dorian)","28 (Théo)","42 (Mathéo)","Mathéo","124"} };
                          
                          
        String colonnes[]={"Date", "Joueur 1", "Joueur 2", "Joueur 3", "Joueur 4", "Gagnant", "Total"};         
        JTable jt=new JTable(donnees,colonnes);
        jt.setEnabled(false);
        JScrollPane sp=new JScrollPane(jt);

        TableColumnModel columnModel = jt.getColumnModel();

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );

        for(int i = 0; i <= 6; i++)
        {
            columnModel.getColumn(i).setPreferredWidth(150);
            jt.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
        }

        sp.setPreferredSize(new Dimension(800, 100));

        this.add(sp);
    }

}
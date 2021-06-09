import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Toolkit;

public class FramePlateau extends JFrame {
	PanelPlateau panelPlateau;

	Controleur ctrl;

	JMenu menu, submenu;  
	
	JMenuItem i1, i2, i3, i4, i5;  

	public FramePlateau(Controleur ctrl) {

		this.ctrl = ctrl;

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		this.setTitle("Plateau de jeu");
		this.setSize(screenWidth-440, screenHeight - 100);
		this.setResizable(false);

		// Création des Panels
		this.panelPlateau = new PanelPlateau(ctrl);

		// Positionnement des Panels
		this.add(this.panelPlateau);

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("../../img/plateau_1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Image dimg = img.getScaledInstance(1000, screenHeight - 100, Image.SCALE_DEFAULT);
		ImageIcon imageIcon = new ImageIcon(dimg);
		setContentPane(new JLabel(imageIcon));

		JMenuBar mb = new JMenuBar();
		menu = new JMenu("Partie");
		i1 = new JMenuItem("Nouvelle partie");
		i2 = new JMenuItem("Quitter");
		menu.add(i1);
		menu.add(i2);
		mb.add(menu);
		this.setJMenuBar(mb);

		// On centre le plateau
		this.setLocationRelativeTo(null);

		// Activation de la fenetre
		this.setVisible(true);
	}

	public void fermerFrame() {
		this.dispose();
	}
}
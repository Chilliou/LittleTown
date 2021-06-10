import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Toolkit;

import java.awt.MediaTracker;


import java.awt.Graphics;

import java.awt.image.*;
import java.awt.Insets;

import java.awt.event.*;

public class FramePlateau extends JFrame
{
	PanelPlateau panelPlateau;
	private Image imgGrille;

	Controleur ctrl;


	public FramePlateau(Controleur ctrl)
	{

		this.ctrl = ctrl;

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		this.setTitle("Plateau de jeu");
		this.setSize(1000, screenHeight-100);
		this.setResizable(false);
		this.setLocation( screenWidth / 2 - 500, 50);

		/******************************/
		/* Créations des panels       */
		/******************************/

		this.panelPlateau = new PanelPlateau(ctrl);

		/***********************************/
		/* Positionnement des panels       */
		/***********************************/

		this.add(this.panelPlateau);

		BufferedImage img = null;

		try
		{
			img = ImageIO.read(new File("../../img/plateau_1.png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		Image dimg = img.getScaledInstance(1000, screenHeight - 100, Image.SCALE_DEFAULT);
		ImageIcon imageIcon = new ImageIcon(dimg);
		setContentPane(new JLabel(imageIcon));

		try {
			img = ImageIO.read(new File("../../img/grille.png"));
		} catch (Exception e) {
			//TODO: handle exception
		}
		imgGrille = img.getScaledInstance(1000, screenHeight - 100, Image.SCALE_DEFAULT);
		MediaTracker track = new MediaTracker(this);

		// 0 est l'identifiant de l'image a charger
		track.addImage(imgGrille, 0);

		// Rendre la fenetre visible
		this.setVisible(true);

		this.addMouseListener ( new GereSouris() );
	}

	public void fermerFrame()
	{
		this.dispose();
	}

	public class GereSouris extends MouseAdapter
	{
		public void mousePressed ( MouseEvent e )
		{
			System.out.println ( e.getX() + "," + e.getY() );
		}
	}

    public void paint (Graphics g)
    {
        super.paint(g);
		g.drawImage( imgGrille, 0, 11, null);

    }
}
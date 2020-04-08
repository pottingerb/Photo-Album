/**
 * ---------------------------------------------------------------------------
 * File name: AboutDialog.java
 * Project name: Photo Album
 * ---------------------------------------------------------------------------
 * Creator's name and email: Benjamin Pottinger, pottingerb@etsu.edu
 * Course:  CSCI 1260
 * Creation Date: Nov 27, 2019
 * ---------------------------------------------------------------------------
 */
package photoAlbum;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Handles all data and behavior for the About Dialog box, that shows when player clicks the "About" option in the main window
 *
 * Date created: Dec 3, 2019
 *
 * @author Benjamin Pottinger
 */
public class AboutDialog extends JDialog {

	private static final long serialVersionUID = 1;
	private JLabel title;
	private JLabel author;
	private JLabel copyright;
	private JPanel grid;
	
	/**
	 * AboutDialog parameterized constructor, initializes and sets all components
	 *
	 * Date created: Nov 28, 2019
	 */
	public AboutDialog(JFrame parent) {
		super(parent, "About this library ... ", true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		grid = new JPanel();
		grid.setLayout(new GridLayout(1,2));
		
		JPanel leftPanel = new JPanel();
		ImageIcon img = new ImageIcon("Photos//book.jpg");
		
		int desiredHeight = 200;
		
		Image pic = img.getImage();
		double ratio = (double) pic.getWidth(null) / pic.getHeight(null);
		
		BufferedImage b1 = new BufferedImage((int) (desiredHeight * ratio), desiredHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics g1 = b1.getGraphics();
		g1.drawImage(pic, 0, 0, (int) (desiredHeight * ratio), desiredHeight, null);
		
		ImageIcon previewImageSized = new ImageIcon(b1);
		
		JLabel imgLabel = new JLabel(previewImageSized);
		leftPanel.add(imgLabel);
		
		JPanel rightPanel = new JPanel(); 
		title = new JLabel("World's Best Digital Photo Album");
		title.setFont(new Font("Serif", Font.BOLD, 16));
		title.setForeground(Color.BLUE);
		author = new JLabel("Created for CSCI1260-Project");
		copyright = new JLabel("Copyright: Benjamin Pottinger, Friday, November 3, 2019");
		//JTextArea info = new JTextArea("World's Best Digital Photo Album \n Created for CSCI1260-Project 6 \n Copyright: Benjamin Pottinger, Friday, November 3, 2019");
		
		JPanel textPanel = new JPanel(new GridLayout(3,1));
		
		textPanel.add(title);
		textPanel.add(author);
		textPanel.add(copyright);
		rightPanel.add(textPanel);
		
		grid.add(leftPanel);
		grid.add(rightPanel);
		add(grid);
		
		ImageIcon windowImage = new ImageIcon("Photos//MenuPhotos//about.png");
		setIconImage(windowImage.getImage());
		
		setResizable(false);
		pack();
		setLocationRelativeTo(parent);
		setVisible(true);
	}
	
}

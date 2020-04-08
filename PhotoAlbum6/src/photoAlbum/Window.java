/**
 * ---------------------------------------------------------------------------
 * File name: Window.java
 * Project name: Photo Album
 * ---------------------------------------------------------------------------
 * Creator's name and email: Benjamin Pottinger, pottingerb@etsu.edu
 * Course:  CSCI 1260
 * Creation Date: Nov 27, 2019
 * ---------------------------------------------------------------------------
 */
package photoAlbum;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * The Window class of the program, handles GUI behavior and the behavior of all GUI components.
 *
 * Date created: Dec 3, 2019
 *
 * @author Benjamin Pottinger
 */
public class Window {

	//panels
	private JPanel masterPanel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel bottomPanel;
	
	//rightPanel
	private PhotoAlbum pa;
	private ArrayList<Photo> photoAlbum;
	private JScrollPane jcb;
	
	//leftPanel
	private JList<String> photoSelect;
	private JTextField photoNameInput;
	private JComboBox<String> fileTypeInput;
	private JTextField fileSizeInput;
	private JTextField takenByInput;
	
	private ImageIcon previewImage;
	private String[] photoFilePathValues = new String[20];
	private JLabel labelImage;
	private JFrame window;
	
	private Photo derivedPhoto;
	private String sFileName = "PhotoData\\FileData.txt";
	
	/**
	 * Window constructor with no parameters, initializes JFrame specifications, adds components to JPanels, adds JPanels to JFrame, and sets the JFrame as visible
	 *
	 * Date created: Nov 28, 2019
	 */
	public Window() {	
		window = new JFrame();
		
		window.setTitle("PhotoAlbum");
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(500, 500);
		ImageIcon windowImage = new ImageIcon("Photos//MenuPhotos//photoAlbum.png");
		window.setIconImage(windowImage.getImage());
		
		masterPanel = new JPanel();
		leftPanel = new JPanel();
		rightPanel = new JPanel();
		bottomPanel = new JPanel();
		
		//leftPanel
		photoSelect = new JList<String>();
		
		pa = new PhotoAlbum("PhotoData\\FileData.txt");
		photoAlbum = pa.getPhotoAlbum();
		
		for(int i = 0; i < photoAlbum.size(); i++) {
			photoFilePathValues[i] = photoAlbum.get(i).getFileName();
		}
		
		photoSelect.setListData(photoFilePathValues);
		photoSelect.setVisibleRowCount(6);
		photoSelect.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//photoSelect.setFixedCellWidth(125);
		photoSelect.addListSelectionListener(new ListListener());
		jcb = new JScrollPane(photoSelect);
		leftPanel.add(jcb);
		
		//rightPanel
		JLabel photoNameText = new JLabel("Photo Name");
		JLabel fileTypeText = new JLabel("File Type");
		JLabel fileSizeText = new JLabel("File Size (MB)");
		JLabel takenByText = new JLabel("Taken By");
		
		photoNameInput = new JTextField();
		String[] options = {"JPG","GIF","PNG","BMP","OTHER"};
		fileTypeInput = new JComboBox<String>(options);
		fileSizeInput = new JTextField();
		takenByInput = new JTextField();
		
		GridLayout gl = new GridLayout(2,4);
		rightPanel.setLayout(gl);
		rightPanel.add(photoNameText);
		rightPanel.add(photoNameInput);
		rightPanel.add(fileTypeText);
		rightPanel.add(fileTypeInput);
		rightPanel.add(fileSizeText);
		rightPanel.add(fileSizeInput);
		rightPanel.add(takenByText);
		rightPanel.add(takenByInput);	
		
		//menu
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem openItem = new JMenuItem("Open ...");
		JMenuItem saveItem = new JMenuItem("Save ...");
		JMenuItem exitItem = new JMenuItem("Exit ...");
		ImageIcon openImage = new ImageIcon("Photos//MenuPhotos//open.png");
		ImageIcon saveImage = new ImageIcon("Photos//MenuPhotos//save.png");
		ImageIcon exitImage = new ImageIcon("Photos//MenuPhotos//exit.jpg");
		openItem.setIcon(openImage);
		saveItem.setIcon(saveImage);
		exitItem.setIcon(exitImage);
		
		openItem.addActionListener(new OpenItemListener());
		saveItem.addActionListener(new SaveItemListener());
		exitItem.addActionListener(new ExitItemListener());
		
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(exitItem);
		
		JMenu editMenu = new JMenu("Edit");
		JMenuItem addItem = new JMenuItem("Add");
		JMenuItem modifyItem = new JMenuItem("Modify");
		JMenuItem deleteItem = new JMenuItem("Delete");
		ImageIcon addImage = new ImageIcon("Photos//MenuPhotos//add.png");
		ImageIcon modifyImage = new ImageIcon("Photos//MenuPhotos//modify.png");
		ImageIcon deleteImage = new ImageIcon("Photos//MenuPhotos//delete.png");
		addItem.setIcon(addImage);
		modifyItem.setIcon(modifyImage);
		deleteItem.setIcon(deleteImage);
		
		addItem.addActionListener(new AddItemListener());
		modifyItem.addActionListener(new ModifyItemListener());
		deleteItem.addActionListener(new DeleteItemListener());
		
		editMenu.add(addItem);
		editMenu.add(modifyItem);
		editMenu.add(deleteItem);
		
		JMenu helpMenu = new JMenu("Help");
		JMenuItem aboutItem = new JMenuItem("About ...");
		
		ImageIcon aboutImage = new ImageIcon("Photos//MenuPhotos//about.png");
		aboutItem.setIcon(aboutImage);
		
		aboutItem.addActionListener(new AboutItemListener());
		helpMenu.add(aboutItem);
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(helpMenu);
		window.setJMenuBar(menuBar);
		
		//bottomPanel
		previewImage = new ImageIcon(photoFilePathValues[5]);
		labelImage = new JLabel("No Photo Selected.");
		bottomPanel.add(labelImage);
		
		//masterPanel
		masterPanel.add(leftPanel, BorderLayout.WEST);
		masterPanel.add(rightPanel, BorderLayout.EAST);
		masterPanel.add(bottomPanel, BorderLayout.SOUTH);
		window.add(masterPanel);
		
		window.setVisible(true);
	}
	
	/**
	 * Listener class for the JList, displays the image corresponding to the selected item 
	 *
	 * Date created: Nov 28, 2019
	 *
	 * @author Benjamin Pottinger
	 */
	private class ListListener implements ListSelectionListener {
		
		/**
		 * When a List Select Event occurs, display the image corresponding to the selected item 
		 *
		 * Date created: Nov 28, 2019
		 * @param e - An ListSelectionEvent instance pertaining to photoSelect
		 */
		@Override
		public void valueChanged(ListSelectionEvent e) {
			
			//System.out.println("First index: " + e.getFirstIndex());
			//System.out.println("Last index: " + e.getLastIndex());
			
			bottomPanel.remove(labelImage);
			try {
			previewImage = new ImageIcon(photoFilePathValues[photoSelect.getSelectedIndex()]);
			} catch(Exception ex) {
				previewImage = new ImageIcon(photoFilePathValues[photoSelect.getSelectedIndex()+1]);
			}
			
			int desiredHeight = 275;
			
			Image pic = previewImage.getImage();
			double ratio = (double) pic.getWidth(null) / pic.getHeight(null);
			
			BufferedImage b1 = new BufferedImage((int) (desiredHeight * ratio), desiredHeight, BufferedImage.TYPE_INT_ARGB);
			Graphics g1 = b1.getGraphics();
			g1.drawImage(pic, 0, 0, (int) (desiredHeight * ratio), desiredHeight, null);
			
			ImageIcon previewImageSized = new ImageIcon(b1);
			
			labelImage = new JLabel(previewImageSized);
			bottomPanel.add(labelImage);
			window.revalidate(); window.repaint();
		}
	}
	
	/**
	 * Listener class for the Open item option, opens a JFileChooser and requests the user to select a file
	 *
	 * Date created: Nov 28, 2019
	 *
	 * @author Benjamin Pottinger
	 */
	private class OpenItemListener implements ActionListener {

		/**
		 * When action performed, open a JFileChooser and requests the user to select a file
		 *
		 * Date created: Nov 28, 2019
		 * @param e - An action event
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			  			
			File selectedFile = null;
			JFileChooser jc = new JFileChooser("TextFiles");
		
			jc.setDialogTitle("Select Existing File");
			jc.setApproveButtonToolTipText("Select the file you want to open; then click me");
			int iButton = jc.showOpenDialog(null);
			if(iButton == JFileChooser.APPROVE_OPTION) {
				sFileName = jc.getSelectedFile().getPath();
				selectedFile = jc.getSelectedFile();
				pa = new PhotoAlbum(sFileName);
			} else {
				JOptionPane.showMessageDialog(null, "No file selected - cannot continue");
				System.exit(-12);
			}
			
			Scanner sInputFile = null;
			try {
				sInputFile = new Scanner(selectedFile);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//ArrayList<Photo> updatedPhotoAlbum = new ArrayList<>();
			
			photoAlbum.clear();
			System.out.println("pa" + photoAlbum.size());
			
			while(sInputFile.hasNext()) {
				String sFileLine = sInputFile.nextLine();
				String[] sField = sFileLine.split(",");
				FileType fVal = FileType.valueOf(sField[1]);
				double dVal = Double.parseDouble(sField[2]);
				int iVal = Integer.parseInt(sField[4]);
				Photo filePhoto = new Photo(sField[0], fVal, dVal, sField[3], iVal);
				photoAlbum.add(filePhoto);
			}
			
			photoFilePathValues = new String[photoAlbum.size()];
			
			for(int i = 0; i < photoAlbum.size(); i++) {
				photoFilePathValues[i] = photoAlbum.get(i).getFileName();
			}
			
			photoSelect.setListData(photoFilePathValues);
			
			
			
			window.revalidate(); window.repaint();
		
			sInputFile.close();
		}
	}
	
	/**
	 * Listener class for the Save item option, saves the file with the Photo Album class
	 *
	 * Date created: Nov 28, 2019
	 *
	 * @author Benjamin Pottinger
	 */
	private class SaveItemListener implements ActionListener {

		/**
		 * When action performed, saves the file with the Photo Album class
		 *
		 * Date created: Nov 28, 2019
		 * @param e - An action event
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
//			File selectedFile = null;
//			JFileChooser jc = new JFileChooser("TextFiles");
//			String sFileName = " ";
//		
//			jc.setDialogTitle("Select Existing File");
//			jc.setApproveButtonToolTipText("Select the file you want to open; then click me");
//			int iButton = jc.showOpenDialog(null);
//			if(iButton == JFileChooser.APPROVE_OPTION) {
//				sFileName = jc.getSelectedFile().getPath();
//				selectedFile = jc.getSelectedFile();
//			} else {
//				JOptionPane.showMessageDialog(null, "No file selected - cannot continue");
//				System.exit(-12);
//			}
//			
//			Scanner sInputFile = null;
//			try {
//				sInputFile = new Scanner(selectedFile);
//			} catch (FileNotFoundException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			
			try {
				pa.saveFile(sFileName);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
			
		}
	}
	
	/**
	 * Listener class for the Exit item option which terminates the program
	 *
	 * Date created: Nov 28, 2019
	 *
	 * @author Benjamin Pottinger
	 */
	private class ExitItemListener implements ActionListener {

		/**
		 * When action performed, terminate the program with severity of zero
		 *
		 * Date created: Nov 28, 2019
		 * @param e - An action event
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	/**
	 * Listener class for the About item option which opens a JDialog box
	 *
	 * Date created: Nov 28, 2019
	 *
	 * @author Benjamin Pottinger
	 */
	private class AboutItemListener implements ActionListener {

		/**
		 * When action performed, open an instance of the AboutDialog class
		 *
		 * Date created: Nov 28, 2019
		 * @param e - An action event
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			new AboutDialog(window);
		}
	}
	
	/**
	 * Listener class for the Add item option which adds a photo to the list of photos
	 *
	 * Date created: Nov 28, 2019
	 *
	 * @author Benjamin Pottinger
	 */
	private class AddItemListener implements ActionListener {

		/**
		 * When action performed, adds a photo with the specified data to the list of photos
		 *
		 * Date created: Nov 28, 2019
		 * @param e - An action event
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				
				
			String[] inputValues = {photoNameInput.getText(), String.valueOf(fileTypeInput.getSelectedItem()) ,fileSizeInput.getText(), takenByInput.getText()};
			derivedPhoto = new Photo(inputValues[0], FileType.valueOf(inputValues[1]), Double.parseDouble(inputValues[2]), inputValues[3], photoAlbum.size());
			photoAlbum.add(derivedPhoto);	
//			pa.addPhoto(derivedPhoto);
//			
//			for(int i = 0; i < photoFilePathValues.length; i++) {
//				photoFilePathValues[i] = null;
//			}
//			
//			for(int i = 0; i < photoAlbum.size(); i++) {
//				photoFilePathValues[i] = photoAlbum.get(i).getFileName();
//			}
			
//			rightPanel.remove(jcb);
//			jcb.remove(photoSelect);
			
			
			photoFilePathValues[photoAlbum.size()] = photoNameInput.getText();
			photoSelect.setListData(photoFilePathValues);
//			jcb.add(photoSelect);
//			rightPanel.add(jcb);
			window.revalidate(); window.repaint();
			
//			for(int i = 0; i < photoFilePathValues.length; i++) {
//				System.out.println(photoFilePathValues[i]);
//			}
			} catch(Exception ex) {
				// continue
		}
	}
	}
	
	/**
	 * Listener class for the Modify item option which modifies a photo in the list of photos
	 *
	 * Date created: Nov 28, 2019
	 *
	 * @author Benjamin Pottinger
	 */
	private class ModifyItemListener implements ActionListener {

		/**
		 * When action performed, modifies a specified photo with specified values in the list of photos
		 *
		 * Date created: Nov 28, 2019
		 * @param e - An action event
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
			String[] inputValues = {photoNameInput.getText(), String.valueOf(fileTypeInput.getSelectedItem()), fileSizeInput.getText(), takenByInput.getText()};
			derivedPhoto = new Photo(inputValues[0], FileType.valueOf(inputValues[1]), Double.parseDouble(inputValues[2]), inputValues[3], photoAlbum.size());
			pa.editPhoto(photoSelect.getSelectedIndex(), derivedPhoto);
			
			photoFilePathValues[photoSelect.getSelectedIndex()] = inputValues[0];
			
			for(int i = 0; i < photoAlbum.size(); i++) {
				photoFilePathValues[i] = photoAlbum.get(i).getFileName();
			}
			
			photoSelect.setListData(photoFilePathValues);
			
			window.revalidate(); window.repaint();
			} catch(Exception ex) {
				
			}
		}
		
	}
	
	/**
	 * Listener class for the Delete item option which deletes a photo in the list of photos
	 *
	 * Date created: Nov 28, 2019
	 *
	 * @author Benjamin Pottinger
	 */
	private class DeleteItemListener implements ActionListener {

		/**
		 * When action performed, delete a specified photo in the list of photos
		 *
		 * Date created: Nov 28, 2019
		 * @param e - An action event
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
//			
//			try {
//			//String[] inputValues = {photoNameInput.getText(), "JPG" ,fileSizeInput.getText(), takenByInput.getText()};
//			//derivedPhoto = new Photo(inputValues[0], FileType.valueOf(inputValues[1]), Double.parseDouble(inputValues[2]), inputValues[3], photoAlbum.size());
//			
//			photoAlbum.remove(photoSelect.getSelectedIndex());
//			pa.removePhoto(photoSelect.getSelectedIndex());
//			System.out.println(photoSelect.getModel().getSize());
//
//			//ArrayList<String> photoFilePathValues = new ArrayList<String>(100);
//			
////			int counter = 0;
////			for(int i = 0; i < photoAlbum.size(); i++) {
////				photoFilePathValues.add(i, photoAlbum.get(i).getFileName());
////				if(i > 0) {
////					if(photoAlbum.get(i).getFileName().equals(photoAlbum.get(i-1).getFileName())) {
////						counter++;
////					}
////					if(counter >= 1) {
////						photoFilePathValues.remove(i);
////						counter = 0;
////					}
////				}
////			}
//			
//	        String convertedPhotoFilePathValues[] = new String[photoAlbum.size()]; 
//	        
//	        // ArrayList to Array Conversion 
//	        for (int j = 0; j < photoAlbum.size(); j++) { 
//	  
//	            // Assign each value to String array 
//	        	convertedPhotoFilePathValues[j] = photoAlbum.get(j).getFileName(); 
//	        } 
//			
//			photoSelect.setListData(convertedPhotoFilePathValues);
//			
//			window.revalidate(); window.repaint();
//			} catch (Exception ex) {
//				//ex.printStackTrace();
//			}
			
			photoAlbum.remove(photoSelect.getSelectedIndex());
			//Photo p = new Photo();
			//photoAlbum.set(photoSelect.getSelectedIndex(), p);
			//pa.removePhoto(photoSelect.getSelectedIndex());
			
			for(int i = 0; i < photoFilePathValues.length; i++) {
				photoFilePathValues[i] = null;
			}
			
			for(int i = 0; i < photoAlbum.size(); i++) {
				photoFilePathValues[i] = photoAlbum.get(i).getFileName();
			}
			
//			for(int i = 0; i < photoFilePathValues.length; i++) {
//				System.out.println(photoFilePathValues[i]);
//			}
//			System.out.println(" ");
//			
//			for(int i = 0; i < photoFilePathValues.length; i++) {
//				System.out.println(photoFilePathValues[i]);
//			}
			
			photoSelect.setListData(photoFilePathValues);
			
			window.revalidate(); window.repaint();
		}
	}
}

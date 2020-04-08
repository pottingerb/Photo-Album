/**
 * ---------------------------------------------------------------------------
 * File name: Project1Driver.java
 * Project name: Photo Album
 * ---------------------------------------------------------------------------
 * Creator's name and email: Benjamin Pottinger, pottingerb@etsu.edu
 * Course:  CSCI 1260
 * Creation Date: Sep 15, 2019
 * ---------------------------------------------------------------------------
 */
package photoAlbum;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * The driver class of the program. Displays a menu and gets user input until user specifies otherwise.
 * Has a welcome and goodbye display message.
 *
 * Date created: Sep 15, 2019
 *
 * @author Benjamin Pottinger
 */
public class MenuDriver {

	private static String sFilePath;
	
	private static String displayOptions[] = {
			"Add a photo to the album", 
			"Remove a photo from the album", 
			"Display photo information for each photo in the album", 
			"Display information on all photos by specified photographer", 
			"Display information on all photos by a specified photo type",
			"Display information based on a photo based on its file name",
			"Display the total size of all photos in the album",
			"Sort the photos by photographer name",
			"Sort the photos by file name",
			"Open a new or existing photo album.",
			"Save the photo album.",
			"Exit" 
			};
	
	/**
	 * Main method which calls the welcome, processPhotoAlbum, and goodBye methods
	 *
	 * Date created: Sep 15, 2019
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater (
				new Runnable() {
					@Override
					public void run() {
						Window win = new Window();
					}
				}
			);
	}
	
	/**
	 * Operates as a menu and calls various Photo Album methods depending on user input
	 *
	 * Date created: Oct 5, 2019
	 */
	private static void processPhotoAlbum() {
		 
	boolean bStillActive;
	PhotoAlbum pa = new PhotoAlbum();
	
	bStillActive = true;
	
	System.out.println("Photo Album Menu");
	System.out.println("----------------\n");
	
	while (bStillActive) {

		for (int i = 0; i < displayOptions.length; i++) {
			System.out.println((i + 1) + ". " + displayOptions[i]);
		}

		System.out.println("\nWhat option do you want to choose?");
		Scanner kb = new Scanner(System.in);
		int iOptionSelected = kb.nextInt();

		switch (iOptionSelected) {
		case 1:
			pa.addPhoto();
			break;
		case 2:
			pa.removePhoto();
			break;
		case 3:
			pa.displayPhotoInformation();
			break;
		case 4:
			pa.displayPhotographerName();
			break;
		case 5:
			pa.displayPhotoFileType();
			break;
		case 6:
			pa.displayPhotoFileName();
			break;
		case 7:
			pa.displayTotalSize();
			break;
		case 8:
			pa.sortPhotographerName();
			break;
		case 9:
			pa.sortFileName();
			break;
		case 10:
			Scanner optionScanner = new Scanner(System.in);
			System.out.println("Would you like to open an existing Photo Album? (Yes or No)");
			String sUserOpenAlbum = optionScanner.nextLine();
			
			if(!pa.getPhotoAlbum().isEmpty()) {
				System.out.println("You have unsaved changes to your album. Do you want to save it? (Yes or No)");
				String sUserSaveAlbum = optionScanner.nextLine();
				
				if(sUserSaveAlbum.equalsIgnoreCase("Yes")) {
					try {
						System.out.println("Specify the location to save the file.");
						sFilePath = selectFile();
						pa.saveFile(sFilePath);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
			if(sUserOpenAlbum.equalsIgnoreCase("Yes")) {
				
				sFilePath = selectFile();
				
				pa = new PhotoAlbum(sFilePath);
			}
			else {
				System.out.println("A new Photo Album will be created.");
			}
			break;
		case 11:
			System.out.println("Please enter the file path.");
			sFilePath = selectFile();
			try {
				pa.saveFile(sFilePath);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case 12:
			if(!pa.getPhotoAlbum().isEmpty()) {
				try {
					System.out.println("This program will save information before exiting. Please specify file in which to save it.");
					sFilePath = selectFile();
					pa.saveFile(sFilePath);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			bStillActive = false;
			kb.close();
			break;
		default:
			System.out.println("Error. Invalid number.");
		}
	}
	}
	
	/**
	 * Welcomes the user with a display message
	 *
	 * Date created: Sep 15, 2019
	 */
	private static void welcome() {
		JOptionPane.showMessageDialog(null, "Welcome to the Photo Album Project, Phase III");
	}

	/**
     * Displays a goodbye message thanking the user before exiting the program.
	 *
     * Date created: Sep 15, 2019
	 */
	private static void goodBye() {
		JOptionPane.showMessageDialog(null, "Thank you for using this program. Please come back soon.");
		System.exit(0);
	}
	
	/**
	 * Creates a JFileChooser and returns a path depending on the selected file
	 *
	 * Date created: Oct 22, 2019
	 * @return sFilePath - the path of the file selected by the user in the prompt
	 */
	private static String selectFile() {
		int iButton;
		JFileChooser jc;
		
		sFilePath = null;
		jc = new JFileChooser("PhotoData");
		jc.setDialogTitle("Select Existing File");
		jc.setApproveButtonToolTipText("Select the file you want to open; then click me");
		iButton = jc.showOpenDialog(null);
		if(iButton == JFileChooser.APPROVE_OPTION) {
			sFilePath = jc.getSelectedFile().getPath();
		} else {
			JOptionPane.showMessageDialog(null, "No file selected - cannot continue");
			System.exit(-12);
		}
		
		return sFilePath;
	}
}

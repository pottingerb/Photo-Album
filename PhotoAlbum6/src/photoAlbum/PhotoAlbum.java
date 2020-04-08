/**
 * ---------------------------------------------------------------------------
 * File name: PhotoAlbum.java
 * Project name: Photo Album
 * ---------------------------------------------------------------------------
 * Creator's name and email: Benjamin Pottinger, pottingerb@etsu.edu
 * Course:  CSCI 1260
 * Creation Date: Oct 8, 2019
 * ---------------------------------------------------------------------------
 */

package photoAlbum;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * The purpose of this class is to simulate and manipulate a photo album (an array of photos)
 *
 * <hr>
 * Date created: Oct 5, 2019
 * <hr>
 * @author Benjamin Pottinger
 */
public class PhotoAlbum {
	
	private static ArrayList<Photo> photoAlbum = new ArrayList<Photo>();
	private Scanner kb = new Scanner(System.in);
	private static int iPhotoPos;
	private static boolean bSaveNeeded;
	
	/**
	 * A default constructor that sets default values for all attributes
	 *
	 * Date created: Oct 20, 2019
	 */
	public PhotoAlbum() {
		photoAlbum = new ArrayList<Photo>();
		kb = new Scanner(System.in);
		iPhotoPos = 0;
		bSaveNeeded = false;
	}
	
	/**
	 * A parameterized constructor that takes a file path and initializes a photo album based off the file's information 
	 *
	 * Date created: Oct 20, 2019
	 * @param sFilePath - The path of the file to be saved
	 */
	public PhotoAlbum(String sFilePath) {
		File inputFile = new File(sFilePath);
		Scanner sInputFile = null;
		try {
			sInputFile = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(sInputFile.hasNext()) {
			String sFileLine = sInputFile.nextLine();
			String[] sField = sFileLine.split(",");
			FileType fVal = FileType.valueOf(sField[1]);
			double dVal = Double.parseDouble(sField[2]);
			int iVal = Integer.parseInt(sField[4]);
			Photo filePhoto = new Photo(sField[0], fVal, dVal, sField[3], iVal);
			photoAlbum.add(filePhoto);
			bSaveNeeded = false;
			iPhotoPos++;
		}
		
		sInputFile.close();
	}
	
	/**
	 * Saves the file of a specified path, updating and overriding the old one
	 *
	 * Date created: Oct 20, 2019
	 * @param sFilePath - The path of the file to be saved
	 */
	public void saveFile(String sFilePath) throws FileNotFoundException {
		File selectedFile = new File(sFilePath);
		PrintWriter pw;
		pw = new PrintWriter(selectedFile);

		for(int i = 0; i < photoAlbum.size(); i++) {
			pw.println(photoAlbum.get(i).getFileName() + "," + photoAlbum.get(i).getFileType() + "," + photoAlbum.get(i).getFileSizeInMegabytes() + "," + photoAlbum.get(i).getPhotographerName() + "," + photoAlbum.get(i).getiPhotoPos());
		}
		
		pw.close();
		bSaveNeeded = false;
	}

	/**
	 * Adds a photo with specified information to photo album
	 *
	 * Date created: Oct 8, 2019
	 */
	public void addPhoto() {
		
		String sFileName;
		FileType fileType;
		double dFileSize;
		String sPhotographerName;
		
		System.out.println("Please type the filename and path of the photo.");
		sFileName = kb.nextLine();
		System.out.println("Please enter the file type. Choices are JPG, GIF, PNG, BMP, and OTHER");
		fileType = FileType.valueOf(kb.next());
		System.out.println("Specify the file size in megabytes.");
		dFileSize = kb.nextDouble();
		System.out.println("Who is the photographer for this photo?");
		kb.nextLine();
		sPhotographerName = kb.nextLine();
		iPhotoPos++;
		
		Photo displayPhoto = new Photo(sFileName, fileType, dFileSize, sPhotographerName, iPhotoPos);
		photoAlbum.add(displayPhoto);
		bSaveNeeded = true;
	}
	
	public void addPhoto(Photo photo) {
		photoAlbum.add(photo);
		bSaveNeeded = true;
	}
	public void removePhoto(int i) {
		photoAlbum.remove(i);
		bSaveNeeded = true;
	}
	public void editPhoto(int i, Photo photo) {
		photoAlbum.set(i, photo);
		bSaveNeeded = true;
	}
	
	/**
	 * Removes a specified photo from photo album
	 *
	 * Date created: Oct 8, 2019
	 */
	public void removePhoto() {
		boolean sFoundPhoto = false;
		
		System.out.println("What photo do you want to remove? (specify name)");
		String photoToRemove = kb.nextLine();
		for(int i = 0; i < photoAlbum.size(); i++) {
			if(photoAlbum.get(i).getFileName().equals(photoToRemove)) {
				photoAlbum.remove(i);
				sFoundPhoto = true;
			};	
		}
		if(sFoundPhoto == false) {
			System.out.println("That file does not exist.");
		}
		isEmpty();
		bSaveNeeded = true;
	}
	
	/**
	 * Displays photo information of all photos in the photo album
	 *
	 * Date created: Oct 8, 2019
	 */
	public void displayPhotoInformation() {
		for(int i = 0; i < photoAlbum.size(); i++) {
			System.out.println(photoAlbum.get(i));
		}
		isEmpty();
	}
	
	/**
	 * Displays all photos that have a photographer of a specified name
	 *
	 * Date created: Oct 8, 2019
	 */
	public void displayPhotographerName() {
		System.out.println("What name are you searching for?");
		String sNameSearch = kb.nextLine();
		boolean sFoundPhotographerName = false;
		
		for(int i = 0; i < photoAlbum.size(); i++) {
			if(photoAlbum.get(i).getPhotographerName().equals(sNameSearch)) {
				System.out.println(photoAlbum.get(i));
				sFoundPhotographerName = true;
			}
		}
		if(sFoundPhotographerName == false) {
			System.out.println("No photos with that photographer name were found.");
		}
	}
	
	/**
	 * Displays all photos that have a file of a specified type
	 *
	 * Date created: Oct 8, 2019
	 */
	public void displayPhotoFileType() {
		
		System.out.println("What photo file type are you searching for?");
		FileType ft = FileType.valueOf(kb.next());
		
		for(int i = 0; i < photoAlbum.size(); i++) {
			if(photoAlbum.get(i).getFileType() == ft) {
				System.out.println(photoAlbum.get(i));
			}
		}
		
		isEmpty();
	}
	
	/**
	 * Displays a photo with a specified file name
	 *
	 * Date created: Oct 8, 2019
	 */
	public void displayPhotoFileName() {
		
		System.out.println("What photo file name are you searching for?");
		String fileName = kb.nextLine();
		boolean sFoundFileName = false;
		
		for(int i = 0; i < photoAlbum.size(); i++) {
			if(photoAlbum.get(i).getFileName() == fileName) {
				System.out.println(photoAlbum.get(i));
				sFoundFileName = true;
			}
		}
		
		if(sFoundFileName == false) {
			System.out.println("No photos with that file name were found.");
		}
	}
	
	/**
	 * Displays total size of the photo album
	 *
	 * Date created: Oct 8, 2019
	 */
	public void displayTotalSize() {
		double total = 0;
		
		for(int i = 0; i < photoAlbum.size(); i++) {
			total += photoAlbum.get(i).getFileSizeInMegabytes();
		}
		
		System.out.println("The total size in MegaBytes of all photos are: " + total);
		isEmpty();
	}
	
	/**
	 * Sorts and displays the photo album in alphabetical order according to specified photographer name
	 *
	 * Date created: Oct 8, 2019
	 */
	public void sortPhotographerName() {
		
		for(int j = 0; j < photoAlbum.size(); j++) {
			for(int i = j+1; i < photoAlbum.size(); i++) {
				if(photoAlbum.get(i).getPhotographerName().compareTo(photoAlbum.get(j).getPhotographerName()) < 0) {
					Photo temp = photoAlbum.get(j);
					photoAlbum.set(j, photoAlbum.get(i));
					photoAlbum.set(i, temp);
				}
			}
		}
		
		isEmpty();
		bSaveNeeded = true;
	}
	
	/**
	 * Sorts and displays the photo album according to specified file name
	 *
	 * Date created: Oct 8, 2019
	 */
	public void sortFileName() {
		for(int j = 0; j < photoAlbum.size(); j++) {
			for(int i = j+1; i < photoAlbum.size(); i++) {
				if(photoAlbum.get(i).getFileName().compareTo(photoAlbum.get(j).getFileName()) < 0) {
					Photo temp = photoAlbum.get(j);
					photoAlbum.set(j, photoAlbum.get(i));
					photoAlbum.set(i, temp);
				}
			}
		}
		
		isEmpty();
		bSaveNeeded = true;
	}
	
	/*
	 * Helper method that tests whether the photo album is empty
	 * 
	 * Date created: Oct 10, 2019
	 */
	private void isEmpty() {
		if(photoAlbum.size() == 0) {
			System.out.println("The photo album is empty.");
		}
	}
	
	/**
	 * Gets the photoAlbum object and returns it
	 *
	 * Date created: Oct 22, 2019
	 * @return photoAlbum - The ArrayList of photo objects
	 */
	public static ArrayList<Photo> getPhotoAlbum() {
		return photoAlbum;
	}

	/**
	 * Returns the photoAlbum object
	 *
	 * Date created: Oct 22, 2019
	 * @photoAlbum - An arraylist of photo objects
	 */
	public static void setPhotoAlbum(ArrayList<Photo> photoAlbum) {
		PhotoAlbum.photoAlbum = photoAlbum;
	}
}

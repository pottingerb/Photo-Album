/**
 * ---------------------------------------------------------------------------
 * File name: Photo.java
 * Project name: Photo Album
 * ---------------------------------------------------------------------------
 * Creator's name and email: Benjamin Pottinger, pottingerb@etsu.edu
 * Course:  CSCI 1260
 * Creation Date: Sep 15, 2019
 * ---------------------------------------------------------------------------
 */
package photoAlbum;

/**
 * Models a photo by storing information related to a single photo. It also can output the information of a single photo as a formatted string.
 *
 * Date created: Sep 15, 2019
 * <hr>
 * @author Benjamin Pottinger
 */
public class Photo {

	private String sFileName;
	private FileType fileType;
	private double dFileSizeInMegabytes;
	private String sPhotographerName;
	private int iPhotoPos;
	
	/**
	 * Default constructor, initializes all attributes to default values 
	 *
	 * Date created: Sep 15, 2019
	 */
	public Photo() {
		sFileName = "photos/mypicture.jpg";
		fileType = fileType.JPG;
		dFileSizeInMegabytes = 1.23;
		sPhotographerName = "Jane Swan";
		iPhotoPos = 1;
	}
	
	/**
	 * Parameterized constructor, sets all attributes to parameterized values
	 *
	 * Date created: Sep 15, 2019
	 * 
	 * @param fileName - The name and path of the photo
	 * @param fileType - The extension type of the photo
	 * @param fileSizeInMegabytes - The size of the photo in megabytes
	 * @param photographerName - The first and last name of the photographer of the photo
	 */
	public Photo(String fileName, FileType fileType, double fileSizeInMegabytes, String photographerName, int iPhotoPos) {
		this.sFileName = fileName;
		this.fileType = fileType;
		this.dFileSizeInMegabytes = fileSizeInMegabytes;
		this.sPhotographerName = photographerName;
		this.iPhotoPos = iPhotoPos;
	}
	
	/**
	 * Copy constructor, takes existing photo object and copies values to the referenced photo object
	 *
	 * Date created: Sep 15, 2019
	 * 
	 * @param existingPhoto - The photo object that will be copied to a new photo object
	 */
	public Photo(Photo existingPhoto) {
		this.sFileName = existingPhoto.sFileName;
		this.fileType = existingPhoto.fileType;
		this.dFileSizeInMegabytes = existingPhoto.dFileSizeInMegabytes;
		this.sPhotographerName = existingPhoto.sPhotographerName;
		this.iPhotoPos = existingPhoto.iPhotoPos;
	}

	/**
	 * This method outputs a formatted string of the photo information
	 *
	 * Date created: Sep 15, 2019 
	 * 
	 * @return formatted string displaying the photo information
	 */
	public String toString() {
		return "\nPhoto " + iPhotoPos + ".\nFile name: " + sFileName + "\nFile type: " + fileType + "\nFile size: " + dFileSizeInMegabytes + "\nPhotographer: " + sPhotographerName;
	}
	
	/**
	 * Returns the position of the photo album in the array
	 *
	 * Date created: Oct 18, 2019 
	 * 
	 * @return iPhotoPos - the position of the photo album in the array
	 */
	public int getiPhotoPos() {
		return iPhotoPos;
	}

	/**
	 * Sets the position of the photo album in the array
	 *
	 * Date created: Oct 18, 2019 
	 * 
	 * @param iPhotoPos - the position of the photo album in the array
	 */
	public void setiPhotoPos(int iPhotoPos) {
		this.iPhotoPos = iPhotoPos;
	}

	/**
	 * Returns the name of the file
	 *
	 * Date created: Oct 18, 2019 
	 * 
	 * @return sFileName - the name of the file
	 */
	public String getFileName() {
		return sFileName;
	}

	/**
	 * Sets the name of the file
	 *
	 * Date created: Oct 18, 2019 
	 * 
	 * param sFileName - the name of the file
	 */
	public void setFileName(String fileName) {
		this.sFileName = fileName;
	}

	/**
	 * Returns the type of the file
	 *
	 * Date created: Oct 18, 2019 
	 * 
	 * @return fileType - the type of the file
	 */
	public FileType getFileType() {
		return fileType;
	}

	/**
	 * Sets the type of the file
	 *
	 * Date created: Oct 18, 2019 
	 * 
	 * @param fileType - the type of the file
	 */
	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}

	/**
	 * Returns the size of the file
	 *
	 * Date created: Oct 18, 2019 
	 * 
	 * @return dFileSizeInMegabytes - the size of the file
	 */
	public double getFileSizeInMegabytes() {
		return dFileSizeInMegabytes;
	}

	/**
	 * Sets the size of the file
	 *
	 * Date created: Oct 18, 2019 
	 * 
	 * @param dFileSizeInMegabytes - the size of the file
	 */
	public void setFileSizeInMegabytes(double fileSizeInMegabytes) {
		this.dFileSizeInMegabytes = fileSizeInMegabytes;
	}

	/**
	 * Returns the photographer name of the file
	 *
	 * Date created: Oct 18, 2019 
	 * 
	 * @return photographerName - the photographer name of the file
	 */
	public String getPhotographerName() {
		return sPhotographerName;
	}
	
	/**
	 * Sets the photographer name of the file
	 *
	 * Date created: Oct 18, 2019 
	 * 
	 * @param photographerName - the photographer name of the file
	 */
	public void setPhotographerName(String photographerName) {
		this.sPhotographerName = photographerName;
	}

	
}

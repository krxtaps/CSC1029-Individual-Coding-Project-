/*
 * Author: Kristaps Paeglis
 * Student number: 40410251
 */

// Package statement.
package Assignment2.part02;

// This import statement imports classes used for ImageRecord.

import java.time.LocalDate;


public class ImageRecord extends QUBMediaImages{

    private static ImageRecord[] imageArray; // Assuming this is your array of ImageRecord objects
    private static int currentIndex = -1; // Keeps track of the current index
    private int id;
    private String title;
    private String description;
    private Imagetype genre;
    private LocalDate date;
    private String thumbnail;
    private static int nextId = 1;

    public ImageRecord(String title, String description, Imagetype genre, LocalDate date, String thumbnail) {
        setId(id);
        setTitle(title);
        setDescription(description);
        setGenre(genre);
        setDate(date);
        setThumbnail(thumbnail);
    }

    /**
     * This is the method used to set ID of image.
     */
    private void setId(int id) {

        this.id = nextId++;
    }

    /**
     * This is the method used to set title of image.
     */
    public void setTitle(String title) {

        if (title == null) {
            this.title = "Unknown";
        } else {
            this.title = title;
        }
    }

    /**
     * This is the method used to set description of image.
     */
    public void setDescription(String description) {

        if (description == null) {
            this.description = "Unknown";
        } else {
            this.description = description;
        }
    }

    /**
     * This is the method used to set type of image.
     */
    public void setGenre(Imagetype genre) {

        this.genre = genre;
    }

    /**
     * This is the method used to set date of image.
     */
    public void setDate(LocalDate date) {

        this.date = date;
    }

    /**
     * This is the method used to set thumbnail of image.
     */
    public void setThumbnail(String thumbnail) {

        if (thumbnail == null) {
            this.thumbnail = "Unknown";
        } else {
            this.thumbnail = thumbnail;
        }
    }

    /**
     * This is the method used to get ID of image.
     */
    public int getId() {

        return this.id;
    }

    /**
     * This is the method used to get title of image.
     */
    public String getTitle() {

        return this.title;
    }

    /**
     * This is the method used to get description of image.
     */
    public String getDescription() {

        return this.description;
    }

    /**
     * This is the method used to get genre of image.
     */
    public Imagetype getGenre() {

        return this.genre;
    }

    /**
     * This is the method used to get date of image.
     */
    public LocalDate getDate() {

        return this.date;
    }

    /**
     * This is the method used to get thumbnail of image.
     */
    public String getThumbnail() {

        return this.thumbnail;
    }

    /**
     * This is the method used to get first of array.
     */
    public static ImageRecord getFirst() {
        if (imageArray != null && imageArray.length > 0) {
            currentIndex = 0;
            return imageArray[currentIndex];
        } else {
            return null; // Return null if the array is empty
        }
    }

    /**
     * This is the method used to get next of array.
     */
    public ImageRecord getNext() {
        if (imageArray != null && currentIndex < imageArray.length - 1) {
            currentIndex++;
            return imageArray[currentIndex];
        } else {
            return null; // Return null if the current image is the last one or the array is null/empty
        }
    }

    /**
     * This is the method used to get previous of array.
     */
    public ImageRecord getPrevious() {
        if (imageArray != null && currentIndex > 0) {
            currentIndex--;
            return imageArray[currentIndex];
        } else {
            return null; // Return null if the current image is the first one or the array is null/empty
        }
    }

    /**
     * This is the method used to return string format.
     */
    public String toString() {
        String result = "";

        result += "ID: " + getId() + "\t";
        result += "Title: " + getTitle() + "\t";
        result += "Description: " + getDescription() + "\t";
        result += "Genre: " + getGenre() + "\t";
        result += "Date: " + getDate() + "\t";
        result += "Thumbnail: " + getThumbnail() + "\t";
        return result;
    }
}

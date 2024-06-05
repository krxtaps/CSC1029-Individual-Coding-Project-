/*
 * Author: Kristaps Paeglis
 * Student number: 40410251
 */

// Package statement.
package Assignment2.part02;

// This import statement imports classes used for ImageAlbum.

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This is the method used for imagemanager.
 */
public class ImageManager {

    public String getTitle;
    private ArrayList<ImageRecord> gallery = new ArrayList<>();
    private ImageAlbum changeName;

    public void addImage(ImageRecord image) {
        this.gallery.add(image);
    }

    /**
     * This is the method used to return image id.
     */
    public ImageRecord searchId(int id) {
        for (ImageRecord image : gallery) {
            if (image.getId() == id) {
                return image; // Return the found image
            }
        }
        return null; // Return null if no matching image is found
    }

    /**
     * This is the method used to return image title.
     */
    public ImageAlbum searchTitle(String title) {
        ImageAlbum result = new ImageAlbum();
        for (ImageRecord image : gallery) {
            if (image.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.addImage(image);
            }
        }
        return result;
    }

    /**
     * This is the method used to return image description.
     */
    public ImageAlbum searchDescription(String description) {
        ImageAlbum result = new ImageAlbum();
        for (ImageRecord image : gallery) {
            if (image.getDescription().toLowerCase().contains(description.toLowerCase())) {
                result.addImage(image);
            }
        }
        return result;
    }

    /**
     * This is the method used to return image type.
     */
    public ImageAlbum searchType(Imagetype imagetype) {
        ImageAlbum result = new ImageAlbum();
        for (ImageRecord image : gallery) { // Iterate over all images in the gallery
            if (image.getGenre() == imagetype) { // Use equals() instead of ==
                result.addImage(image);
            }
        }
        return result;
    }

    /**
     * This is the method used to return image date.
     */
    public ImageAlbum searchDates(LocalDate start, LocalDate end) {
        ImageAlbum result = new ImageAlbum();
        for (ImageRecord image : gallery) { // Iterate over all images in the gallery
            if (image.getDate().compareTo(start) >= 0 && image.getDate().compareTo(end) <= 0) {
                result.addImage(image);
            }
        }
        return result;
    }

    /**
     * This is the method used to return image thumbnail.
     */
    public ImageAlbum searchThumbnail(String thumbnail) {
        ImageAlbum result = new ImageAlbum(); // Create a new ImageAlbum to store the matching images
        for (ImageRecord image : gallery) { // Iterate over all images in the gallery
            if (image.getDescription().toLowerCase().contains(thumbnail.toLowerCase())) {
                result.addImage(image); // Add the matching image to the result ImageAlbum
            }
        }
        return result;
    }


    /**
     * This is the method used to return all images.
     */
    public ImageAlbum getAllImages() {

        changeName = new ImageAlbum();

        for (int i = 0; i < gallery.size(); i++) {
            changeName.addImage(gallery.get(i));
        }
        return changeName;
    }
}
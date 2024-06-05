/*
 * Author: Kristaps Paeglis
 * Student number: 40410251
 */

// Package statement.
package Assignment2.part02;

// This import statement imports classes used for ImageAlbum.

import java.util.ArrayList;

public class ImageAlbum {

    private ArrayList<ImageRecord> gallery; // initialising array list

    /**
     * This is the method used to create array list.
     */
    public ImageAlbum() {
        this.gallery = new ArrayList<>();
    }

    /**
     * This is the method used to add image to record and potion.
     */
    public void addImage(ImageRecord image) {
        int imagePosition = sortGallery(image);
        gallery.add(imagePosition, image);
    }

    /**
     * This is the method used to sort images by date.
     */
    private int sortGallery(ImageRecord image) {
        int i = 0;
        while (i < gallery.size() && gallery.get(i).getDate().isBefore(image.getDate())) {
            i++;
        }
        return i;
    }

    /**
     * This is the method used to build string.
     */
    public ArrayList<ImageRecord> getAllImages() {
        return new ArrayList<>(this.gallery);
    }

    /**
     * This is the method used to build string.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (ImageRecord image : gallery) {
            result.append(image).append("\n");
        }
        return result.toString();
    }

    /**
     * This is the method used to get first of array.
     */
    public ImageRecord getFirst() {
        if (gallery.isEmpty()) {
            return null;
        }
        return gallery.get(0);
    }

    /**
     * This is the method used to return empty image list.
     */
    public boolean isEmpty() {
        return gallery.isEmpty();
    }
}

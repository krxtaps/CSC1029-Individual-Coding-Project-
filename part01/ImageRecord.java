package Assessment2.part01;

import java.time.LocalDate;

public class ImageRecord {

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

    private void setId(int id) {

        this.id = nextId++;
    }

    public void setTitle(String title) {

        if (title == null) {
            this.title = "Unknown";
        } else {
            this.title = title;
        }
    }

    public void setDescription(String description) {

        if (description == null) {
            this.description = "Unknown";
        } else {
            this.description = description;
        }
    }

    public void setGenre(Imagetype genre) {

        this.genre = genre;
    }

    public void setDate(LocalDate date) {

        this.date = date;
    }

    public void setThumbnail(String thumbnail) {

        if (thumbnail == null) {
            this.thumbnail = "Unknown";
        } else {
            this.thumbnail = thumbnail;
        }
    }

    public int getId() {

        return this.id;
    }

    public String getTitle() {

        return this.title;
    }

    public String getDescription() {

        return this.description;
    }

    public Imagetype getGenre() {

        return this.genre;
    }

    public LocalDate getDate() {

        return this.date;
    }

    public String getThumbnail() {

        return this.thumbnail;
    }

    public static ImageRecord getFirst() {
        if (imageArray != null && imageArray.length > 0) {
            currentIndex = 0;
            return imageArray[currentIndex];
        } else {
            return null; // Return null if the array is empty
        }
    }

    // Define method to get the next image
    public ImageRecord getNext() {
        if (imageArray != null && currentIndex < imageArray.length - 1) {
            currentIndex++;
            return imageArray[currentIndex];
        } else {
            return null; // Return null if the current image is the last one or the array is null/empty
        }
    }

    // Define method to get the previous image
    public ImageRecord getPrevious() {
        if (imageArray != null && currentIndex > 0) {
            currentIndex--;
            return imageArray[currentIndex];
        } else {
            return null; // Return null if the current image is the first one or the array is null/empty
        }
    }

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

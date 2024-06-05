package Assessment2.part01;

import java.util.ArrayList;
import java.util.Collection;

public class ImageAlbum {

    private ArrayList<ImageRecord> gallery;

    public ImageAlbum() {
        this.gallery = new ArrayList<>();
    }

    public void addImage(ImageRecord image) {
        int imagePosition = sortGallery(image);
        gallery.add(imagePosition, image);
    }

    private int sortGallery(ImageRecord image) {
        int i = 0;
        while (i < gallery.size() && gallery.get(i).getDate().isBefore(image.getDate())) {
            i++;
        }
        return i;
    }

    public ArrayList<ImageRecord> getAllImages() {
        return new ArrayList<>(this.gallery);
    }



    public String toString() {
        StringBuilder result = new StringBuilder();
        for (ImageRecord image : gallery) {
            result.append(image).append("\n");
        }
        return result.toString();
    }

    public ImageRecord getFirst() {
        if (gallery.isEmpty()) {
            return null;
        }
        return gallery.get(0);
    }


        /*
        public ImageAlbum getAllImages() {
        ImageAlbum sortedAlbum = new ImageAlbum();
        for (int i = 0; i < gallery.size(); i++) {
            sortedAlbum.addImage(gallery.get(i));
        }
        sortedAlbum.bubbleSortByDate(); // Sort images by date
        return sortedAlbum;
    }
     */

    public void bubbleSortByDate() {
        int n = gallery.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (gallery.get(j).getDate().compareTo(gallery.get(j + 1).getDate()) > 0) {
                    // Swap gallery[j] with gallery[j+1]
                    ImageRecord temp = gallery.get(j);
                    gallery.set(j, gallery.get(j + 1));
                    gallery.set(j + 1, temp);
                }
            }
        }
    }

    public boolean isEmpty() {
        return gallery.isEmpty();
    }
}

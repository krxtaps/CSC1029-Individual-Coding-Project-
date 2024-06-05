/*
 * Author: Kristaps Paeglis
 * Student number: 40410251
 */

// Package statement.
package Assignment2.part02;

// This import statement imports classes used for QUBImages.
import console.Console;

import java.awt.*;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;

/**
 * This is the method used to initialise images in memory.
 */
public class QUBMediaImages {
    public static Console con;
    public static Scanner scanner = new Scanner(System.in); // Declaring scanner import.
    public static ImageManager gallery = new ImageManager(); // Declaring gallery as ImageManager.

    /**
     * This is the method used to initialise images in memory.
     */
    private static ImageManager initialise() {
        // ImageRecord objects on startup.
        ImageRecord img1 = new ImageRecord("Andromeda Galaxy", "Image of the Andromeda galaxy.", Imagetype.ASTRONOMY, LocalDate.of(2023, 1, 1), "Andromeda.png");
        ImageRecord img2 = new ImageRecord("Lanyon QUB", "An image of the QUB Lanyon building.", Imagetype.ARCHITECTURE, LocalDate.of(2023, 2, 1), "LanyonQUB.png");
        ImageRecord img3 = new ImageRecord("Kermit Plays Golf", "An image of Kermit the frog playing golf.", Imagetype.SPORT, LocalDate.of(2023, 3, 1), "KermitGolf.png");
        ImageRecord img4 = new ImageRecord("Mourne Mountains", "A panoramic view of the Mourne mountains.", Imagetype.LANDSCAPE, LocalDate.of(2023, 4, 1), "Mournes.png");
        ImageRecord img5 = new ImageRecord("Homer Simpson", "Homer Simpson- A portrait of the man.", Imagetype.PORTRAIT, LocalDate.of(2023, 3, 1), "Homer.png");
        ImageRecord img6 = new ImageRecord("Red Kite", "A Red Kite bird of prey in flight.", Imagetype.NATURE, LocalDate.of(2023, 4, 1), "RedKite.png");
        ImageRecord img7 = new ImageRecord("Central Park", "An overhead view of Central Park New York USA.", Imagetype.AERIAL, LocalDate.of(2023, 5, 1), "CentralPark.png");
        ImageRecord img8 = new ImageRecord("Apples", "A bunch of apples.", Imagetype.FOOD, LocalDate.of(2023, 6, 1), "Apples.png");
        ImageRecord img9 = new ImageRecord("Programme Meme", "A Chat GPT programming meme.", Imagetype.OTHER, LocalDate.of(2023, 7, 1), "ChatGPT.png");

        // addImage statement for each image on startup.
        gallery.addImage(img1); // addImage statement to add img1.
        gallery.addImage(img2); // addImage statement to add img2.
        gallery.addImage(img3); // addImage statement to add img3.
        gallery.addImage(img4); // addImage statement to add img4.
        gallery.addImage(img5); // addImage statement to add img5.
        gallery.addImage(img6); // addImage statement to add img6.
        gallery.addImage(img7); // addImage statement to add img7.
        gallery.addImage(img8); // addImage statement to add img8.
        gallery.addImage(img9); // addImage statement to add img9.
        return gallery; // Returning gallery of images.
    }

    /**
     * This is the main method used for console.
     */
    private static Console initConsole() {
        Console con = new Console(true);

        con.setSize(600, 600);
        con.setVisible(true);
        con.setFont(new Font("Courier New", Font.BOLD, 20));
        // colour for background of console
        // Set background color
        float[] bgColour = Color.RGBtoHSB(68, 52, 191, null);
        con.setBgColour(Color.getHSBColor(bgColour[0], bgColour[1], bgColour[2]));

        // Set text color
        float[] txtColour = Color.RGBtoHSB(213, 219, 29, null);
        con.setColour(Color.getHSBColor(txtColour[0], txtColour[1], txtColour[2]));

        return con;
    }

    /**
     * This is the main method used to display images.
     */
    public static ImageIcon getImage(String thumbnail) {
        String userdir = System.getProperty("user.dir");
        String path = userdir + "/Images/" + thumbnail;
        ImageIcon img = new ImageIcon(path);
        return img;
    }


    /**
     * This is the main method used on startup.
     */
    public static void main(String[] args) {
        gallery = initialise(); // Initialising of gallery.
        con = initConsole();

        boolean active = true; // Boolean for when menu is active.

        while (active) {
            String[] option = {"Add Image", "Search Image", "Display All Image", "Quit"}; // Array string of options for menu.
            Menu myMenu = new Menu("Image Menu", option);
            int choice = myMenu.getUserChoice();
            con.println();

            // Switch statement for main startup menu.
            switch (choice) {
                // Case to add image.
                case 1:
                    addImage();
                    con.println("Image added successfully!\n");
                    con.println();
                    break;
                // Case to find image.
                case 2:
                    searchImage();
                    con.println("Image found successfully!\n");
                    con.println();
                    break;
                // Case to display image(s).
                case 3:
                    displayImages();
                    con.println();
                    break;
                // Case to quit program.
                case 4:
                    active = false;
                    break;
                // Default error for incorrect choice in switch.
                default:
                    con.println("Invalid choice.\n");
                    break;

            }
        }
    }

    /**
     * This is the method used to handle next, previous and back in images.
     *
     * @return boolean
     */
    private static boolean handleNextPrevious() {
        con.print("\nEnter 1 for next image, 2 for previous image, or 0 to go back: "); // Print for options going between images and to back.
        String input = con.readLn().trim(); // Scanner input for user input, with trim to eliminate spaces if accidentally entered.

        // Enhanced switch statement for going between images.
        return switch (input) {
            case "0" -> false; // Case to return false to indicate going back
            case "1" -> true; // Case to return true to indicate moving to the next image.
            case "2" -> true; // Case to return true to indicate moving to the previous image.
            // Default error for incorrect choice in enhanced switch.
            default -> {
                con.println("Invalid choice. Please enter 0, 1, or 2.");
                yield handleNextPrevious(); // Handle to go back at start of the enhanced switch statement.
            }
        };
    }

    /**
     * This is the method used to add a new image.
     */
    private static void addImage() {
        // Prompt user to enter image details
        String title = enterTitle(); // Call enterTitle() to get the title
        String description = enterDescription(); // Call enterDescription() to get the title
        Imagetype genre = Imagetype.valueOf(enterGenre());// Call enterGenre() to get the type.
        LocalDate date = enterDate();// Call enterDate() to get the date.
        String fileName = enterFile(); // Call enterFile() to get the thumbnail.

        // Create new ImageRecord object with entered details
        ImageRecord newImage = new ImageRecord(title, description, genre, date, fileName);

        // Add the new image to the gallery
        gallery.addImage(newImage);
    }

    // Method for enterTitle.
    private static String enterTitle() {
        con.print("Enter image title: "); // Print to enter title.
        return con.readLn(); // Return the entered title.
    }

    // Method for enterDescription.
    private static String enterDescription() {
        con.print("Enter file description: "); // Print to enter description.
        return con.readLn(); // Return the entered description.
    }

    /**
     * This is the method used to select type of genre.
     *
     * @return string
     */
    private static String enterGenre() {
        // While loop for entering type.
        while (true) {
            // Try catch for entering type.
            try {
                // Print for type menu.
                con.println("Enter valid genre:");
                con.println("1. ASTRONOMY - Photography or imagine of astronomical objects, celestial events, or areas of the night sky.");
                con.println("2. ARCHITECTURE - Focuses on the capture of images that accurately represent the design and feel of buildings.");
                con.println("3. SPORT - Covers all types of sports and can be considered a branch of photojournalism.");
                con.println("4. LANDSCAPE - The study of the textured surface of the Earth and features images of natural scenes.");
                con.println("5. PORTRAIT - Images of a person or a group of people where the face and facial features are predominant.");
                con.println("6. NATURE - Focused on elements of the outdoors including sky, water, and land, or the flora and fauna.");
                con.println("7. AERIAL - Images taken from an aircraft or other airborne platforms.");
                con.println("8. FOOD - Captures everything related to food, from fresh ingredients and plated dishes to the cooking process.");
                con.println("9. OTHER - Covers just about any other type of image and photography genre.");
                con.print("\nEnter your choice (1-9): ");

                // Scanner with parse for input.
                int choice = Integer.parseInt(con.readLn());
                // Switch statement for user choice.
                switch (choice) {
                    // Case to return type ASTRONOMY.
                    case 1:
                        return "ASTRONOMY";
                    // Case to return type ARCHITECTURE.
                    case 2:
                        return "ARCHITECTURE";
                    // Case to return type SPORT.
                    case 3:
                        return "SPORT";
                    // Case to return type LANDSCAPE.
                    case 4:
                        return "LANDSCAPE";
                    // Case to return type PORTRAIT.
                    case 5:
                        return "PORTRAIT";
                    // Case to return type NATURE.
                    case 6:
                        return "NATURE";
                    // Case to return type AERIAL.
                    case 7:
                        return "AERIAL";
                    // Case to return type FOOD.
                    case 8:
                        return "FOOD";
                    // Case to return type OTHER.
                    case 9:
                        return "OTHER";
                    // Default error for type menu.
                    default:
                        con.println("Invalid choice. Enter a number from 1 to 9.");
                }
                // Catch for exceptions.
            } catch (NumberFormatException e) {
                con.println("Invalid input. Enter a valid number.");
            }
        }
    }

    /**
     * This is the method used to enter date when adding image..
     *
     * @return LocalDate
     */
    private static LocalDate enterDate() {
        // While loop for entering date.
        while (true) {
            // Try catch for entering date.
            try {
                con.print("Do you want to use today's date? (Y/N): "); // Print asking if user wants LocalDate or manual entry.
                String choice = con.readLn().trim().toUpperCase(); // Scanner with trim and toUpperCase for choice.

                // If statement if Y is selected.
                if (choice.equals("Y")) {
                    return LocalDate.now(); // Return LocalDate.
                    // Else if statement if N is selected.
                } else if (choice.equals("N")) {
                    // While loop for manual date entry.
                    while (true) {
                        con.print("Enter date in YYYY-MM-DD format: "); // Print with date format.
                        String dateString = con.readLn().trim(); // Scanner with trim for date entry.

                        // If statement if date is 10 characters long.
                        if (dateString.length() == 10) {
                            // Parse and return LocalDate as a string.
                            return LocalDate.parse(dateString);
                            // Else statement for error.
                        } else {
                            con.println("Invalid date format. Enter in YYYY-MM-DD format."); // Print for incorrect date format.
                        }
                    }
                    // Else statement for error.
                } else {
                    con.println("Invalid choice. Enter Y or N."); // Print for incorrect input.
                }
                // Catch statement for error.
            } catch (DateTimeParseException e) {
                con.println("Incorrect input: " + e.getMessage()); // Print for incorrect input with error message.
                con.println("Try again."); // Print to try again.
                // Catch statement for error.
            } catch (Exception e) {
                con.println("An error occurred: " + e.getMessage()); // Print for error with error message.
                con.println("Try again."); // Print to try again.
            }
        }
    }

    /**
     * This is the method used to enter name of thumbnail when adding image.
     *
     * @return string
     */
    private static String enterFile() {
        con.print("Enter thumbnail name: "); // Print for entering thumbnail name.
        return con.readLn(); // Return scanner with thumbnail name.
    }

    /**
     * This is the method used to give menu to select what to search by.
     */
    private static void searchImage() {
        // Boolean for searchImage menu.
        boolean active = true;

        // While loop
        while (active) {
            String[] option = {"ID", "Title", "Description", "Image Type", "Date", "Thumbnail", "Back"}; // Array string of options for menu.
            Menu myMenu = new Menu("Search Menu", option); // Statement to use Menu class.
            int choice = myMenu.getUserChoice(); // Scanner for user input

            // Switch statement for searchImage menu.
            switch (choice) {
                // Case to search by ID.
                case 1:
                    searchId(); // Calling searchId method.
                    con.println(); // Print for line
                    break;
                // Case to search by ID.
                case 2:
                    searchTitle(); // Calling searchId method.
                    con.println(); // Print for line
                    break;
                // Case to search by ID.
                case 3:
                    searchDescription(); // Calling searchId method.
                    con.println(); // Print for line
                    break;
                // Case to search by ID.
                case 4:
                    searchType(); // Calling searchId method.
                    con.println(); // Print for line
                    break;
                // Case to search by ID.
                case 5:
                    searchDate(); // Calling searchId method.
                    con.println(); // Print for line
                    break;
                // Case to search by ID.
                case 6:
                    searchThumbnail(); // Calling searchId method.
                    con.println(); // Print for line
                    break;
                // Case to stop loop and return.
                case 7:
                    active = false; // Statement to make boolean false.
                    break;
                // Default case for error.
                default:
                    con.println("Invalid choice."); // Print for error.
                    break;
            }
        }
    }

    /**
     * This is the method used to search by ID.
     */
    private static void searchId() {
        // While loop for searching id.
        while (true) {
            try {
                con.print("Enter the ID of the image you want to search for (or type 'back' to go back): "); // Print for user choice.
                String input = con.readLn().trim(); // Trim the input.

                if (input.equalsIgnoreCase("back")) {
                    con.clear();
                    return; // Return to the previous menu.
                }

                String[] parts = input.split(" "); // Split the input by space and consider only the first part as the ID.
                int id = Integer.parseInt(parts[0]); // Parse for first number in array.

                ImageRecord foundImage = gallery.searchId(id); // Search for the image by ID

                // If statement foundImage is not null.
                if (foundImage != null) {
                    con.clear();
                    con.println("Image found successfully:"); // Print if image found.
                    con.println(foundImage); // Print the found image.
                    ImageIcon img = getImage(foundImage.getThumbnail());
                    con.println(img);
                    
                    
                    break; // Exit the loop once the image is found
                } else {
                    con.println("Image with ID " + id + " not found."); // Print if image not found.
                }
                // Catch statement for error.
            } catch (NumberFormatException e) {
                con.println("Invalid input. Enter a valid integer ID."); // Print for error.
            }
        }
    }



    /**
     * This is the method used to search by title.
     */
    private static void searchTitle() {
        // While loop for searching images by title.
        while (true) {
            con.print("Enter title of image (or type 'back' to go back): "); // Print to enter title of image.
            String title = con.readLn().trim(); // Trim the input title

            // If statement to check if the input is "back"
            if (title.equalsIgnoreCase("back")) {
                con.clear(); // Clear the console before returning.
                con.println("Returning to the previous menu..."); // Print for returning to the previous menu.
                return; // Exit the method
            }

            // Check if the input title is empty or consists only of whitespace
            if (title.isEmpty()) {
                con.println("Title cannot be empty."); // Print for error.
                continue; // Prompt for input again
            }

            // Search for the images by title.
            ImageAlbum result = gallery.searchTitle(title);

            // If statement result is not empty.
            if (!result.isEmpty()) {
                con.println("Image(s) found:"); // Print found images.
                // For every image.
                for (ImageRecord image : result.getAllImages()) {
                    con.println(image); // Print each found image without numbering
                    ImageIcon img = getImage(image.getThumbnail());
                        con.println(img);                
                        }
                // Else statement for result is empty.
            } else {
                con.println("No images found with the title \"" + title + "\"."); // Print for no images found.
            }

            // Ask if the user wants to search for another title
            String choice;
            // Do while loop until correct input.
            do {
                con.print("Do you want to search for another title? (Y/N): "); // Print for selection.
                choice = con.readLn().trim().toLowerCase(); // Scanner for letter choice trimmed and made into lower-case.
                // If the choice is neither y or n.
                if (!choice.equals("y") && !choice.equals("n")) {
                    con.println("Invalid choice. Enter 'Y' or 'N'."); // Print error for invalid choice.
                }
                // While not equal to either y or n.
            } while (!choice.equals("y") && !choice.equals("n"));

            // If choice n, then stop loop.
            if (choice.equals("n")) {
                break; // Exit the loop if the user does not want to search again
            }
        }
    }


    /**
     * This is the method used to search by image description.
     */
    private static void searchDescription() {
        boolean searchAgain = true;

        // While loop for searching images by description.
        while (searchAgain) {
            con.print("Enter description of image: "); // Print asking for description of image.
            String description = con.readLn(); // Scanner to take input for description.

            ImageAlbum result = gallery.searchDescription(description); // Search for the images by description

            // If statement for when empty result not is given.
            if (!result.isEmpty()) {
                con.println("Image(s) found:"); // Print for found images.
                // Foe every image.
                for (ImageRecord image : result.getAllImages()) {
                    con.println(image); // Print each found image without numbering
                }
                // Else statement for no images found with description.
            } else {
                con.println("No images found with description \"" + description + "\"."); // Print for images not found.
            }

            // Ask if the user wants to search for another description
            String choice;
            // Do while to ask if user wants to search for another description.
            do {
                con.print("Search for another description? (Y/N): "); // Print to search for another description.
                choice = con.readLn().trim().toLowerCase(); // Scanner to see if user entered y or n, trimmed and put to lowercase.
                // If not y or n.
                if (!choice.equals("y") && !choice.equals("n")) {
                    con.println("Invalid choice. Enter 'Y' or 'N'."); // Print error.
                }
                // While loop if not y or n.
            } while (!choice.equals("y") && !choice.equals("n"));

            // If choice is n then.
            if (choice.equals("n")) {
                con.clear(); // Clear the console before returning.
                searchAgain = false; // Exit the loop if the user does not want to search again
            }
        }
    }

    // Method for search by type.
    private static void searchType() {
        Imagetype type = selectImageType();

        ImageAlbum foundImages = gallery.searchType(type);

        // If foundImages is not empty then.
        if (!foundImages.isEmpty()) {
            con.println("Image(s) found:"); // Print that images have been found.
            // For every image.
            for (ImageRecord image : foundImages.getAllImages()) {
                con.println(image); // Print each found image without numbering
            }
            // Else statement if assert type is not null.
        } else {
            assert type != null;
            con.println("No images found for type: " + type); // Error for images not found.
        }
    }

    /**
     * This is the method used to search by image type.
     */
    private static Imagetype selectImageType() {
        // While loop for selecting the image type.
        while (true) {
            con.println("Select the image type:");
            con.println("1. ASTRONOMY");
            con.println("2. ARCHITECTURE");
            con.println("3. SPORT");
            con.println("4. LANDSCAPE");
            con.println("5. PORTRAIT");
            con.println("6. NATURE");
            con.println("7. AERIAL");
            con.println("8. FOOD");
            con.println("9. OTHER");
            con.println("0. Back");

            // Try catch for correct choice.
            try {
                con.print("Enter your choice (0-9): "); // Print asking for correct choice.
                String choice = con.readLn(); // Scanner taking input for choice.
                // Swtich statement for user choice based on input.
                switch (choice) {
                    case "0":
                        return null; // Indicates going back
                    case "1":
                        return Imagetype.ASTRONOMY; // Case to return type ARCHITECTURE.
                    case "2":
                        return Imagetype.ARCHITECTURE; // Case to return type ARCHITECTURE.
                    case "3":
                        return Imagetype.SPORT; // Case to return type SPORT.
                    case "4":
                        return Imagetype.LANDSCAPE; // Case to return type LANDSCAPE.
                    case "5":
                        return Imagetype.PORTRAIT; // Case to return type PORTRAIT.
                    case "6":
                        return Imagetype.NATURE; // Case to return type NATURE.
                    case "7":
                        return Imagetype.AERIAL; // Case to return type AERIAL.
                    case "8":
                        return Imagetype.FOOD; // Case to return type FOOD.
                    case "9":
                        return Imagetype.OTHER; // Case to return type OTHER.
                    default:
                        con.println("Invalid choice. Enter a number from 0 to 9."); // Print error if number not between 0-9.
                        break; // Ask for input again
                }
                // Catch for exception error.
            } catch (NumberFormatException e) {
                con.println("Invalid input. Enter a valid number."); // Print for incorrect error.
            }
        }
    }

    /**
     * This is the method used to search by date range.
     */
    private static void searchDate() {
        // While loop for searchDate menu.
        while (true) {
            // Try catch for menu correct input.
            try {
                con.print("Enter the start date (YYYY-MM-DD) (or type 'back' to go back): "); // Print asking for start date of range.
                String startDateInput = con.readLn().trim(); // Scan for input and trim it.

                // If user types back ignoring case, then.
                if (startDateInput.equalsIgnoreCase("back")) {
                    con.clear(); // Clear the console before returning.
                    return; // Return to the previous menu
                }

                LocalDate startDate = LocalDate.parse(startDateInput); // Parse the start date input.

                // While loop for end date.
                while (true) {
                    con.print("Enter the end date (YYYY-MM-DD) (or type 'back' to go back): "); // Print asking for end date or back.
                    String endDateInput = con.readLn().trim(); // Scan for input and trim it.

                    // If user types back ignoring case, then.
                    if (endDateInput.equalsIgnoreCase("back")) {
                        con.clear(); // Clear the console before returning.
                        return; // Return to the previous menu
                    }

                    // Try catch for end date.
                    try {
                        LocalDate endDate = LocalDate.parse(endDateInput);

                        if (endDate.isBefore(startDate)) {
                            con.println("End date cannot be before start date. Try again.");
                            continue; // Repeat the loop to get a valid end date
                        }

                        // Search for images within the specified date range
                        ImageAlbum foundImages = gallery.searchDates(startDate, endDate);

                        // If foundImages is not empty then.
                        if (!foundImages.isEmpty()) {
                            con.println("Image(s) found within the date range:");
                            for (ImageRecord image : foundImages.getAllImages()) {
                                con.println(image);
                            }
                        } else {
                            con.println("No images found within the date range.");
                        }

                        // Exit the loop if search is successful
                        break;
                    } catch (DateTimeParseException e) {
                        con.println("Invalid end date format. Enter dates in YYYY-MM-DD format.");
                    }
                }

                // Exit the loop if search is successful
                return;
            } catch (DateTimeParseException e) {
                con.println("Invalid start date format. Enter dates in YYYY-MM-DD format.");
            }
        }
    }

    /**
     * This is the method used to search by thumbnail.
     */
    private static void searchThumbnail() {
        boolean searchAgain = true;

        // While loop for searching prompts.
        while (searchAgain) {
            con.print("Enter thumbnail of image: ");
            String thumbnail = con.readLn();

            // Search for the images by thumbnail
            ImageAlbum result = gallery.searchThumbnail(thumbnail);

            // If the returned result is not empty then display images, else give error.
            if (!result.isEmpty()) {
                con.println("Image(s) found:");
                for (ImageRecord image : result.getAllImages()) {
                    con.println(image); // Print each found image without numbering
                }
            } else {
                con.println("No images found with thumbnail \"" + thumbnail + "\".");
            }

            // Ask if the user wants to search for another thumbnail
            String choice;
            do {
                con.print("Search for another thumbnail? (Y/N): ");
                choice = con.readLn().trim().toLowerCase();
                if (!choice.equals("y") && !choice.equals("n")) {
                    con.println("Invalid choice. Enter 'Y' or 'N'.");
                }
            } while (!choice.equals("y") && !choice.equals("n"));

            if (choice.equals("n")) {
                con.clear(); // Clear the console before returning.
                searchAgain = false; // Exit the loop if the user does not want to search again
            }
        }
    }

    /**
     * This is the method used to clear the console.
     */
    private static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cmd /c cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            con.println("Failed to clear console: " + e.getMessage());
        }
    }

    /**
     * This is the method used to display all images, and allow you to go between them using the handleNextPrevious method.
     */
    private static void displayImages() {
        ArrayList<ImageRecord> allImages = gallery.getAllImages().getAllImages();
        int currentIndex = 0;
        int totalImages = allImages.size();
        boolean returnSearchImage = false;

        // While loop for displaying images.
        while (!returnSearchImage) {
            ImageRecord currentImage = allImages.get(currentIndex);
            con.println("Image " + (currentIndex + 1) + " of " + totalImages + ":");
            ImageIcon img = getImage(currentImage.getThumbnail());
        	con.clear();
            con.println(img);
            con.println(currentImage);

            // Call handleNextPrevious method to handle next/previous image or go back.
            returnSearchImage = !handleNextPrevious(); // Set flag to return based on user choice.

            if (!returnSearchImage) {
                // Move to next or previous image based on user choice.
                currentIndex = (currentIndex + 1) % totalImages; // Move to next image
            }
        }
    	con.clear();
    }

}
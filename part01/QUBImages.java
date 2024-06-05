package Assessment2.part01;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import java.time.format.DateTimeParseException;

public class QUBImages {
    public static Scanner scanner = new Scanner(System.in);
    public static ImageManager gallery = new ImageManager();

        private static ImageManager initialise() {
            ImageRecord img1 = new ImageRecord("Andromeda Galaxy", "Image of the Andromeda galaxy.", Imagetype.ASTRONOMY, LocalDate.of(2023, 1, 1), "Andromeda.png");
            ImageRecord img2 = new ImageRecord("Lanyon QUB", "An image of the QUB Lanyon building.", Imagetype.ARCHITECTURE, LocalDate.of(2023, 2, 1), "LanyonQUB.png");
            ImageRecord img3 = new ImageRecord("Kermit Plays Golf", "An image of Kermit the frog playing golf.", Imagetype.SPORT, LocalDate.of(2023, 3, 1), "KermitGolf.png");
            ImageRecord img4 = new ImageRecord("Mourne Mountains", "A panoramic view of the Mourne mountains.", Imagetype.LANDSCAPE, LocalDate.of(2023, 4, 1), "Mournes.png");
            ImageRecord img5 = new ImageRecord("Homer Simpson", "Homer Simpson- A portrait of the man.", Imagetype.PORTRAIT, LocalDate.of(2023, 3, 1), "Homer.png");
            ImageRecord img6 = new ImageRecord("Red Kite", "A Red Kite bird of prey in flight.", Imagetype.NATURE, LocalDate.of(2023, 4, 1), "RedKite.png");
            ImageRecord img7 = new ImageRecord("Central Park", "An overhead view of Central Park New York USA.", Imagetype.AERIAL, LocalDate.of(2023, 5, 1), "CentralPark.png");
            ImageRecord img8 = new ImageRecord("Apples", "A bunch of apples.", Imagetype.FOOD, LocalDate.of(2023, 6, 1), "Apples.png");
            ImageRecord img9 = new ImageRecord("Programme Meme", "A Chat GPT programming meme.", Imagetype.OTHER, LocalDate.of(2023, 7, 1), "ChatGPT.png");

            gallery.addImage(img1);
            gallery.addImage(img2);
            gallery.addImage(img3);
            gallery.addImage(img4);
            gallery.addImage(img5);
            gallery.addImage(img6);
            gallery.addImage(img7);
            gallery.addImage(img8);
            gallery.addImage(img9);
            return gallery;
        }

    public static void main(String[] args) {
        gallery = initialise();

        boolean active = false;

        while (!active) {
            String option[] = {"Add Image", "Search Image", "Display All Image", "Quit"};
            Menu myMenu = new Menu("Image Menu", option);
            int choice = myMenu.getUserChoice();
            System.out.println();

            switch (choice) {
                case 1:
                    addImage();
                    System.out.println("image added successfully");
                    System.out.println();
                    break;
                case 2:
                    searchImage();
                    System.out.println("image found");
                    System.out.println();
                    break;
                case 3:
                    displayImages();
                    System.out.println();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;

            }
        }
    }

    private static boolean handleNextPrevious() {
        System.out.print("Enter 1 for next image, 2 for previous image, or 0 to go back: ");
        String input = scanner.nextLine().trim();

        switch (input) {
            case "0":
                return false; // Return false to indicate going back
            case "1":
                return true; // Return true to indicate moving to the next image
            case "2":
                return true; // Return true to indicate moving to the previous image
            default:
                System.out.println("Invalid choice. Please enter 0, 1, or 2.");
                return handleNextPrevious(); // Ask for input again
        }
    }

    private static void addImage() {
        // Prompt user to enter image details
        String title = enterTitle(); // Call enterTitle() to get the title
        String description = enterDescription();
        Imagetype genre = Imagetype.valueOf(enterGenre());
        LocalDate date = enterDate();
        String fileName = enterFile();

        // Create new ImageRecord object with entered details
        ImageRecord newImage = new ImageRecord(title, description, genre, date, fileName);

        // Add the new image to the gallery
        gallery.addImage(newImage);
    }

    private static String enterTitle() {
        System.out.print("Enter image title: ");
        return scanner.nextLine(); // Return the entered title
    }

    private static String enterDescription() {
        System.out.print("Enter file description: ");
        return scanner.nextLine();
    }

    private static String enterGenre() {
        while (true) {
            try {
                System.out.println("Enter valid genre:");
                System.out.println("1. ASTRONOMY - Photography or imagine of astronomical objects, celestial events, or areas of the night sky.");
                System.out.println("2. ARCHITECTURE - Focuses on the capture of images that accurately represent the design and feel of buildings.");
                System.out.println("3. SPORT - Covers all types of sports and can be considered a branch of photojournalism.");
                System.out.println("4. LANDSCAPE - The study of the textured surface of the Earth and features images of natural scenes.");
                System.out.println("5. PORTRAIT - Images of a person or a group of people where the face and facial features are predominant.");
                System.out.println("6. NATURE - Focused on elements of the outdoors including sky, water, and land, or the flora and fauna.");
                System.out.println("7. AERIAL - Images taken from an aircraft or other airborne platforms.");
                System.out.println("8. FOOD - Captures everything related to food, from fresh ingredients and plated dishes to the cooking process.");
                System.out.println("9. OTHER - Covers just about any other type of image and photography genre.");
                System.out.print("\nEnter your choice (1-9): ");

                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        return "ASTRONOMY";
                    case 2:
                        return "ARCHITECTURE";
                    case 3:
                        return "SPORT";
                    case 4:
                        return "LANDSCAPE";
                    case 5:
                        return "PORTRAIT";
                    case 6:
                        return "NATURE";
                    case 7:
                        return "AERIAL";
                    case 8:
                        return "FOOD";
                    case 9:
                        return "OTHER";
                    default:
                        System.out.println("Invalid choice. Enter a number from 1 to 9.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a valid number.");
            }
        }
    }

    private static LocalDate enterDate() {
        while (true) {
            try {
                System.out.print("Do you want to use today's date? (Y/N): ");
                String choice = scanner.nextLine().trim().toUpperCase();

                if (choice.equals("Y")) {
                    return LocalDate.now();
                } else if (choice.equals("N")) {
                    while (true) {
                        System.out.print("Enter date in YYYY-MM-DD format: ");
                        String dateString = scanner.nextLine().trim();

                        if (dateString.length() == 10) {
                            return LocalDate.parse(dateString);
                        } else {
                            System.out.println("Invalid date format. Enter in YYYY-MM-DD format.");
                        }
                    }
                } else {
                    System.out.println("Invalid choice. Enter Y or N.");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Incorrect input: " + e.getMessage());
                System.out.println("Try again.");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                System.out.println("Try again.");
            }
        }
    }

    private static String enterFile() {
        System.out.print("Enter thumbnail name: ");
        return scanner.nextLine();
    }

    private static void searchImage() {
        boolean active = true;

        while (active) {
            String[] option = {"ID", "Title", "Description", "Image Type", "Date", "Thumbnail", "Back"};
            Menu myMenu = new Menu("Search Menu", option);
            int choice = myMenu.getUserChoice();

            switch (choice) {
                case 1:
                    searchId();
                    System.out.println();
                    break;
                case 2:
                    searchTitle();
                    System.out.println();
                    break;
                case 3:
                    searchDescription();
                    System.out.println();
                    break;
                case 4:
                    searchType();
                    System.out.println();
                    break;
                case 5:
                    searchDate();
                    System.out.println();
                    break;
                case 6:
                    searchThumbnail();
                    System.out.println();
                    break;
                case 7:
                    active = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }



    private static void searchId() {
        while (true) {
            try {
                System.out.print("Enter the ID of the image you want to search for (or type 'back' to go back): ");
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("back")) {
                    return; // Return to the previous menu
                }

                // Split the input by space and consider only the first part as the ID
                String[] parts = input.split(" ");
                int id = Integer.parseInt(parts[0]);

                // Search for the image by ID
                ImageRecord foundImage = gallery.searchId(id);

                if (foundImage != null) {
                    System.out.println("Image found successfully:");
                    System.out.println(foundImage); // Print the found image
                } else {
                    System.out.println("Image with ID " + id + " not found.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a valid integer ID.");
            }
        }
    }




    private static void searchTitle() {
        while (true) {
            System.out.print("Enter title of image: ");
            String title = scanner.nextLine().trim(); // Trim the input title

            // Check if the input title is empty or consists only of whitespace
            if (title.isEmpty()) {
                System.out.println("Title cannot be empty.");
                continue; // Prompt for input again
            }

            // Search for the images by title
            ImageAlbum result = gallery.searchTitle(title);

            if (!result.isEmpty()) {
                System.out.println("Image(s) found:");
                for (ImageRecord image : result.getAllImages()) {
                    System.out.println(image); // Print each found image without numbering
                }
            } else {
                System.out.println("No images found with the title \"" + title + "\".");
            }

            // Ask if the user wants to search for another title
            String choice;
            do {
                System.out.print("Do you want to search for another title? (Y/N): ");
                choice = scanner.nextLine().trim().toLowerCase();
                if (!choice.equals("y") && !choice.equals("n")) {
                    System.out.println("Invalid choice. Enter 'Y' or 'N'.");
                }
            } while (!choice.equals("y") && !choice.equals("n"));

            if (choice.equals("n")) {
                break; // Exit the loop if the user does not want to search again
            }
        }
    }


    private static void searchDescription() {
        boolean searchAgain = true;

        while (searchAgain) {
            System.out.print("Enter description of image: ");
            String description = scanner.nextLine();

            // Search for the images by description
            ImageAlbum result = gallery.searchDescription(description);

            if (!result.isEmpty()) {
                System.out.println("Image(s) found:");
                for (ImageRecord image : result.getAllImages()) {
                    System.out.println(image); // Print each found image without numbering
                }
            } else {
                System.out.println("No images found with description \"" + description + "\".");
            }

            // Ask if the user wants to search for another description
            String choice;
            do {
                System.out.print("Search for another description? (Y/N): ");
                choice = scanner.nextLine().trim().toLowerCase();
                if (!choice.equals("y") && !choice.equals("n")) {
                    System.out.println("Invalid choice. Enter 'Y' or 'N'.");
                }
            } while (!choice.equals("y") && !choice.equals("n"));

            if (choice.equals("n")) {
                searchAgain = false; // Exit the loop if the user does not want to search again
            }
        }
    }


    private static void searchType() {
        Imagetype type = selectImageType();

        ImageAlbum foundImages = gallery.searchType(type);

        if (!foundImages.isEmpty()) {
            System.out.println("Image(s) found:");
            for (ImageRecord image : foundImages.getAllImages()) {
                System.out.println(image);
            }
        } else {
            assert type != null;
            System.out.println("No images found for type: " + type);
        }
    }

    private static Imagetype selectImageType() {
        while (true) {
            System.out.println("Select the image type:");
            System.out.println("1. ASTRONOMY");
            System.out.println("2. ARCHITECTURE");
            System.out.println("3. SPORT");
            System.out.println("4. LANDSCAPE");
            System.out.println("5. PORTRAIT");
            System.out.println("6. NATURE");
            System.out.println("7. AERIAL");
            System.out.println("8. FOOD");
            System.out.println("9. OTHER");
            System.out.println("0. Back");

            try {
                System.out.print("Enter your choice (0-9): ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "0":
                        return null; // Indicates going back
                    case "1":
                        return Imagetype.ASTRONOMY;
                    case "2":
                        return Imagetype.ARCHITECTURE;
                    case "3":
                        return Imagetype.SPORT;
                    case "4":
                        return Imagetype.LANDSCAPE;
                    case "5":
                        return Imagetype.PORTRAIT;
                    case "6":
                        return Imagetype.NATURE;
                    case "7":
                        return Imagetype.AERIAL;
                    case "8":
                        return Imagetype.FOOD;
                    case "9":
                        return Imagetype.OTHER;
                    default:
                        System.out.println("Invalid choice. Enter a number from 0 to 9.");
                        break; // Ask for input again
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a valid number.");
            }
        }
    }



    private static void searchDate() {
        while (true) {
            try {
                System.out.print("Enter the start date (YYYY-MM-DD) (or type 'back' to go back): ");
                String startDateInput = scanner.nextLine().trim();

                if (startDateInput.equalsIgnoreCase("back")) {
                    return; // Return to the previous menu
                }

                LocalDate startDate = LocalDate.parse(startDateInput);

                while (true) {
                    System.out.print("Enter the end date (YYYY-MM-DD) (or type 'back' to go back): ");
                    String endDateInput = scanner.nextLine().trim();

                    if (endDateInput.equalsIgnoreCase("back")) {
                        return; // Return to the previous menu
                    }

                    try {
                        LocalDate endDate = LocalDate.parse(endDateInput);

                        if (endDate.isBefore(startDate)) {
                            System.out.println("End date cannot be before start date. Try again.");
                            continue; // Repeat the loop to get a valid end date
                        }

                        // Search for images within the specified date range
                        ImageAlbum foundImages = gallery.searchDates(startDate, endDate);

                        if (!foundImages.isEmpty()) {
                            System.out.println("Image(s) found within the date range:");
                            for (ImageRecord image : foundImages.getAllImages()) {
                                System.out.println(image);
                            }
                        } else {
                            System.out.println("No images found within the date range.");
                        }

                        // Exit the loop if search is successful
                        break;
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid end date format. Enter dates in YYYY-MM-DD format.");
                    }
                }

                // Exit the loop if search is successful
                return;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid start date format. Enter dates in YYYY-MM-DD format.");
            }
        }
    }

    private static void searchThumbnail() {
        boolean searchAgain = true;

        while (searchAgain) {
            System.out.print("Enter thumbnail of image: ");
            String thumbnail = scanner.nextLine();

            // Search for the images by description
            ImageAlbum result = gallery.searchDescription(thumbnail);

            if (!result.isEmpty()) {
                System.out.println("Image(s) found:");
                for (ImageRecord image : result.getAllImages()) {
                    System.out.println(image); // Print each found image without numbering
                }
            } else {
                System.out.println("No images found with description \"" + thumbnail + "\".");
            }

            // Ask if the user wants to search for another description
            String choice;
            do {
                System.out.print("Search for another thumbnail? (Y/N): ");
                choice = scanner.nextLine().trim().toLowerCase();
                if (!choice.equals("y") && !choice.equals("n")) {
                    System.out.println("Invalid choice. Enter 'Y' or 'N'.");
                }
            } while (!choice.equals("y") && !choice.equals("n"));

            if (choice.equals("n")) {
                searchAgain = false; // Exit the loop if the user does not want to search again
            }
        }
    }



    private static void displayImages() {
        ArrayList<ImageRecord> allImages = gallery.getAllImages().getAllImages();
        int currentIndex = 0;
        int totalImages = allImages.size();
        boolean returnToSearchImage = false;

        while (!returnToSearchImage) {
            ImageRecord currentImage = allImages.get(currentIndex);
            System.out.println("Image " + (currentIndex + 1) + " of " + totalImages + ":");
            System.out.println(currentImage);
            System.out.print("Enter 0 to go back, 1 for next image, or 2 for previous image: ");

            String input = scanner.nextLine().trim();
            if (input.contains(" ")) {
                System.out.println("Invalid choice. Enter 0, 1, or 2.");
                continue;
            }

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Enter 0, 1, or 2.");
                continue;
            }

            switch (choice) {
                case 0:
                    returnToSearchImage = true; // Set flag to return to searchImage()
                    break;
                case 1:
                    currentIndex = (currentIndex + 1) % totalImages; // Move to next image
                    break;
                case 2:
                    currentIndex = (currentIndex - 1 + totalImages) % totalImages; // Move to previous image
                    break;
                default:
                    System.out.println("Invalid choice. Enter 0, 1, or 2.");
                    break;
            }
        }
    }
    }
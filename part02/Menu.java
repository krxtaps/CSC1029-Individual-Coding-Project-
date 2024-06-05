/*
 * Author: Kristaps Paeglis
 * Student number: 40410251
 */

// Package statement.
package Assignment2.part02;

// This import statement imports classes used for Menu.
import java.util.Scanner;

/**
 * This is the main method for the menu
 */
public class Menu extends QUBMediaImages {
    private final String[] items;
    private final String title;
    private final Scanner input;

    public Menu(String title, String[] data) {
        this.title = title;
        this.items = data;
        this.input = new Scanner(System.in);
    }

    /**
     * This is the method used to display.
     */
    private void display() {
        System.out.println(title);
        for (int count = 0; count < title.length(); count++) {
            con.print("+");
        }
        con.println();
        for (int option = 1; option <= items.length; option++) {
            con.println(option + ". " + items[option - 1]);
        }
        con.println();
    }

    /**
     * This is the method used to get the user choice.
     */
    public int getUserChoice() {
        display();
        int value = 0;
        do {
            try {
                con.print("Enter Selection: ");
                value = Integer.parseInt(con.readLn().trim());
                break;
            } catch (Exception ex) {
                con.readLn();
            }
        } while (true);
        return value;
    }
}

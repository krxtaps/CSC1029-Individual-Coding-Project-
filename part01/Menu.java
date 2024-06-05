package Assessment2.part01;

import java.util.*;
public class Menu {
    private String items[];
    private String title;
    private Scanner input;
    public Menu(String title, String data[]) {
        this.title = title;
        this.items = data;
        this.input = new Scanner(System.in);
    }
    private void display() {
        System.out.println(title);
        for (int count = 0; count < title.length(); count++) {
            System.out.print("+");
        }
        System.out.println();
        for (int option = 1; option <= items.length; option++) {
            System.out.println(option + ". " + items[option - 1]);
        }
        System.out.println();
    }
    public int getUserChoice() {
        display();
        int value = 0;
        do {
            try {
                System.out.print("Enter Selection: ");
                value = Integer.parseInt(input.nextLine().trim());
                break;
            } catch (Exception ex) {
                input.nextLine();
            }
        } while (true);
        return value;
    }
}

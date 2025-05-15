package DrinksMachine;

import java.io.IOException;
import java.util.Scanner;

// [mainMenuChoice] START MACHINE --> CHOOSE YOUR OPTIONS
public class MachineCommands {

    static Scanner input = new Scanner(System.in);
    static boolean running = true;

    public static void mainMenuChoice() throws IOException {
        System.out.println("Hello!");
        while (running) {
            System.out.println("Enter command: ");
            System.out.println("[1] User");
            System.out.println("[2] Administrator");
            System.out.println("[0] Exit");

            String choice = input.nextLine();
            switch (choice) {
                case "1":
                    MachineUser.userChoiceB1();
                    running = false;
                    break;
                case "2":
                    AdministratorLogIn.LogIn();
                    MachineAdministrator.AdministratorMenu();
                case "0":
                    running = false;
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid command");
                    break;
            }
        }
    }
}

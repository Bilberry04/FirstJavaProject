package DrinksMachine;

import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;
import Formatter.TextFormatter;
import Formatter.InputFormatter;
import org.w3c.dom.Text;

// [mainMenuChoice] START MACHINE --> CHOOSE YOUR OPTIONS
public class MachineCommands {

    static Scanner input = new Scanner(System.in);
    static boolean running = true;

    public static void mainMenuChoice() throws IOException {
        System.out.println(TextFormatter.header("Hello!") + TextFormatter.RESET);
        while (running) {

                        String menu =
                            TextFormatter.BLUE + "Enter command:" + TextFormatter.RESET + "\n" +
                            TextFormatter.GREEN + "[1] User" + TextFormatter.RESET + "\n" +
                            TextFormatter.GREEN + "[2] Administrator" + TextFormatter.RESET + "\n" +
                            TextFormatter.GREEN + "[0] Exit" + TextFormatter.RESET;
            System.out.println(TextFormatter.boxed(menu));

            String choice = InputFormatter.promptAndClear("Choose");
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

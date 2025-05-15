package DrinksMachine;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;


//LOGOWANIE
public class AdministratorLogIn {
    static void LogIn() throws IOException {

        Scanner input = new Scanner(System.in);
        boolean loggedIn = false;

        while (!loggedIn) {
            System.out.println("Log in to your administrator account: ");
            System.out.println("Login: ");
            String adminLogin = input.nextLine();


            List<AccountDataBase> account = AccountLoader.loadAccount("src/DrinksMachine/AccountDataBase.txt");
            AccountDataBase matchingAccount = null;

            for (AccountDataBase acc : account) {
                if (acc.getAccountLogin().equals(adminLogin)) {
                    matchingAccount = acc;
                    break;
                }
            }


            if (matchingAccount != null) {
                System.out.println("Password: ");
                String adminPass = input.nextLine();


                if (adminPass.equals(matchingAccount.getAccountPassword())) {
                    System.out.println("Log in successfully!");
                    System.out.println("Hello " + matchingAccount.getAccountLogin() + "!");
                    loggedIn = true;
                } else {
                    System.out.println("Wrong password! Try again. ");
                }


            } else {
                System.out.println("Invalid login. Try again. ");
            }

        }
    }
}


package DrinksMachine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccountLoader {
    public static List<AccountDataBase> loadAccount(String filePath) throws IOException {
        List<AccountDataBase> accounts = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");

                if (parts.length == 5) {
                    int accountID = Integer.parseInt(parts[0]);
                    String accountLogin = parts[1];
                    String accountPassword = parts[2];
                    String accountRole = parts[3];
                    int accountLogCount = Integer.parseInt(parts[4]);

                    accounts.add(new AccountDataBase(accountID, accountLogin, accountPassword, accountRole, accountLogCount));
                }
            }
            } catch (IOException e) {
                System.out.println("File reading error: " + e.getMessage());
        }
        return accounts;
    }
}

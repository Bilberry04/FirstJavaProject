package DrinksMachine;

import java.util.List;

public class AccountDataBase {
    int accountID;
    String accountLogin;
    String accountPassword;
    String accountRole;
    int accountLogCount;

    public AccountDataBase(int accountID, String accountLogin, String accountPassword, String role, int accountLogCount) {
        this.accountID = accountID;
        this.accountLogin = accountLogin;
        this.accountPassword = accountPassword;
        this.accountRole = accountRole;
        this.accountLogCount = accountLogCount;

    }

    public int getAccountID() { return accountID; }

    public String getAccountLogin() { return accountLogin; }

    public String getAccountPassword() { return accountPassword;}

    public String getAccountRole() { return accountRole; }

    public int getAccountLogCount() { return accountLogCount; }

}

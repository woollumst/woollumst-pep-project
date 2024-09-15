package Service;

import Model.Account;
import DAO.AccountDAO;

import java.util.List;

public class AccountService {
    public AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public AccountService (AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    public Account registerAccount (Account account){
        if (account.getUsername() == null || account.getPassword().length() < 4) 
            return null; //username can't be blank, password > 4
        List<String> usernameList = accountDAO.getAllUsernames();
        for (String username : usernameList) {
            if (username == account.getUsername()){
                return null; // username already exists
            }
        }
        return accountDAO.registerAccount(account); //passed checks, register account
    }

    public Account accountLogin(Account account){
        return accountDAO.accountLogin(account);
    }
}

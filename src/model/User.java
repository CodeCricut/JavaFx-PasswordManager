package model;

import java.io.Serializable;
import java.util.HashSet;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Account loginInformation;
    private HashSet<Account> savedAccounts;

    public User(Account loginInformation){
        this.loginInformation = loginInformation;
        savedAccounts = new HashSet<>();
    }

    public HashSet<Account> getSavedAccounts() {
        return savedAccounts;
    }

    public boolean accountInfoValid(Account accountEntered){
        return loginInformation.equals(accountEntered);
    }

    public Account getLoginInformation() {
        return loginInformation;
    }

    public void addAccount(Account accountToAdd){
        savedAccounts.add(accountToAdd);
    }

    public void removeAccount(Account accountToRemove) throws AccountDoesNotExistException {
        boolean accountExists = false;
        for (Account savedAccount : savedAccounts){
            if (savedAccount.equals(accountToRemove)){
                accountExists = true;
                accountToRemove = savedAccount;
                break;
            }
        }
        if (accountExists)
            savedAccounts.remove(accountToRemove);
        else
            throw new AccountDoesNotExistException();
    }


}

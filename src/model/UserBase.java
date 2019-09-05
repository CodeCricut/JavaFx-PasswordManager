package model;

import java.io.*;
import java.util.HashSet;

public class UserBase implements Serializable {
    private static final long serialVersionUID = 3L;

    private User activeUser;

    private HashSet<User> registeredUsers;



    public UserBase() {
        activeUser = null;
        registeredUsers = new HashSet<>();
    }

    public void logIn(Account accountLogin) throws AccountDoesNotExistException {
        for (User user : registeredUsers){
            if (user.accountInfoValid(accountLogin)) {
                activeUser = user;
                return;
            }
        }
        throw new AccountDoesNotExistException();
    }

    public void logOutAndSave(){
        activeUser = null;
        writeDatabaseToFile();
    }

    public void signUp(Account accountToSignUp){
        User userToSignUp = new User(accountToSignUp);
        registeredUsers.add(userToSignUp);
        activeUser = userToSignUp;

        writeDatabaseToFile();
    }

    public void clearUserBaseFromFile(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("userBase.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(new UserBase());
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeDatabaseToFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("userBase.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(this);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User getActiveUser() {
        return activeUser;
    }
}

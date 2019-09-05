package model;

import view.AlertBox;

import java.io.*;

public class UserBaseFactory {

    public static UserBase createUserBase(){
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("userBase.txt");
        } catch (FileNotFoundException e) {
            AlertBox.display("File Exception", "The file \"userBase.txt\" is missing or corrupt.");
        }

        UserBase userBase;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            userBase = (UserBase) objectInputStream.readObject();
        } catch(IOException | ClassNotFoundException e){
            userBase = new UserBase();
        }
        return userBase;
    }
}

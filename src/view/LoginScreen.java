package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Account;
import model.AccountDoesNotExistException;
import model.UserBase;

public class LoginScreen extends BaseScene {

    private Scene loginScene;

    private TextField usernameField, passwordField;

    public LoginScreen(UserBase userBase) {
        super(userBase);
    }

    @Override
    public void setUpScene(Stage stage){
        this.stage = stage;

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(20));
        vBox.setSpacing(20);

        Label usernameLabel = new Label("Username");
        usernameField = new TextField();

        HBox usernameRow = new HBox();
        usernameRow.setSpacing(8);
        usernameRow.getChildren().addAll(usernameLabel, usernameField);

        Label passwordLabel = new Label("Password");
        passwordField = new TextField();

        HBox passwordRow = new HBox();
        passwordRow.setSpacing(8);
        passwordRow.getChildren().addAll(passwordLabel, passwordField);

        Button loginButton = new Button("Log In");
        loginButton.setOnAction(e -> logIn());

        Button signUpButton = new Button("Sign Up");
        signUpButton.setOnAction(e -> signUp());

        vBox.getChildren().addAll(usernameRow, passwordRow, loginButton, signUpButton);
        loginScene = new Scene(vBox, 400, 400);
    }

    @Override
    public void display(){
        stage.setScene(loginScene);
        stage.show();
    }

    private void logIn(){
        Account account = new Account(usernameField.getText(), passwordField.getText());
        try {
            userBase.logIn(account);
            UserViewScreen userViewScreen = new UserViewScreen(userBase);
            userViewScreen.setUpScene(stage);
            userViewScreen.display();
        } catch (AccountDoesNotExistException e) {
            AlertBox.display("Login Error", "Invalid login information. Please double check the information.");
        }
    }

    private void signUp(){
        Account account = new Account(usernameField.getText(), passwordField.getText());
        userBase.signUp(account);
        UserViewScreen userViewScreen = new UserViewScreen(userBase);
        userViewScreen.setUpScene(stage);
        userViewScreen.display();
    }
}

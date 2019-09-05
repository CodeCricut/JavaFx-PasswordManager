package view;

import javafx.application.Application;
import javafx.stage.Stage;
import model.UserBase;
import model.UserBaseFactory;

public class Main extends Application {

    private Stage primaryStage;
    private UserBase userBase;

    @Override
    public void start(Stage primaryStage) throws Exception{
        userBase = UserBaseFactory.createUserBase();
        this.primaryStage = primaryStage;
        LoginScreen loginScreen = new LoginScreen(userBase);
        loginScreen.setUpScene(primaryStage);
        loginScreen.display();

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

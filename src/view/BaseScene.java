package view;

import javafx.stage.Stage;
import model.UserBase;

public abstract class BaseScene {

    Stage stage;
    protected UserBase userBase;

    public BaseScene(UserBase userBase){
        this.userBase = userBase;
    }

    abstract void setUpScene(Stage stage);

    abstract void display();
}

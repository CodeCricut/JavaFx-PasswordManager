package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Account;
import model.User;
import model.UserBase;

public class UserViewScreen extends BaseScene {

    private Scene userViewScene;
    private User activeUser;

    private TableView table;
    private ObservableList<Account> tableData;
    private TextField addServiceField, addUsernameField, addPasswordField;
    private Button addTableRowButton;

    public UserViewScreen(UserBase userBase) {
        super(userBase);
    }

    @Override
    public void setUpScene(Stage stage){
        this.stage = stage;
        activeUser = userBase.getActiveUser();

        VBox vBox = new VBox();

        Label usernameLabel = new Label();
        usernameLabel.setText("Your Username: " + activeUser.getLoginInformation().getUsername());
        Label passwordLabel = new Label();
        passwordLabel.setText("Your Password: " + activeUser.getLoginInformation().getPassword());

        HBox userInfoRow = new HBox();
        userInfoRow.setPadding(new Insets(10));
        userInfoRow.setSpacing(8);
        userInfoRow.getChildren().addAll(usernameLabel, passwordLabel);

        table = new TableView();
        table.setEditable(true);

        TableColumn serviceCol = new TableColumn("Service");
        serviceCol.setCellValueFactory(new PropertyValueFactory<Account, String>("service"));
        TableColumn usernameCol = new TableColumn("Username");
        usernameCol.setCellValueFactory(new PropertyValueFactory<Account, String>("username"));
        TableColumn passwordCol = new TableColumn("Password");
        passwordCol.setCellValueFactory(new PropertyValueFactory<Account, String>("password"));

        table.getColumns().addAll(serviceCol, usernameCol, passwordCol);
        setUpTable();

        Button logOutButton = new Button("Log Out");
        logOutButton.setOnAction(e -> logOut());


        HBox tableAddRow = new HBox();
        tableAddRow.getChildren().addAll(addServiceField, addUsernameField, addPasswordField, addTableRowButton);

        vBox.getChildren().addAll(userInfoRow, table, tableAddRow, logOutButton);

        userViewScene = new Scene(vBox, 400, 700);
    }

    private void logOut() {
        userBase.logOutAndSave();
        LoginScreen loginScreen = new LoginScreen(userBase);
        loginScreen.setUpScene(stage);
        loginScreen.display();
    }

    private void setUpTable() {
        tableData = FXCollections.observableArrayList();
        tableData.addAll(activeUser.getSavedAccounts());
        table.setItems(tableData);

        addServiceField = new TextField();
        addServiceField.setPromptText("Service");
        addUsernameField = new TextField();
        addUsernameField.setPromptText("Username");
        addPasswordField = new TextField();
        addPasswordField.setPromptText("Password");

        addTableRowButton = new Button("Add");
        addTableRowButton.setOnAction(e -> {
            if (! validateAccountInformation()){
                AlertBox.display("Invalid Account Information", "Please fill out all fields");
            } else {
                Account accountToAdd = new Account(addServiceField.getText(),
                        addUsernameField.getText(),
                        addPasswordField.getText());
                tableData.add(accountToAdd);
                addServiceField.clear();
                addUsernameField.clear();
                addPasswordField.clear();

                activeUser.addAccount(accountToAdd);
            }
        });
    }

    private boolean validateAccountInformation(){
        return (! (addServiceField.getText().isEmpty() ||
        addUsernameField.getText().isEmpty() ||
        addPasswordField.getText().isEmpty()));
    }


    @Override
    public void display() {
        stage.setScene(userViewScene);
    }

}

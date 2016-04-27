package pl.edu.agh.marcskow.ftpserver.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import pl.edu.agh.marcskow.ftpserver.data.User;
import pl.edu.agh.marcskow.ftpserver.database.Hibernate;
import pl.edu.agh.marcskow.ftpserver.server.implementation.Server;
import pl.edu.agh.marcskow.ftpserver.server.ftpServer.FtpServerContext;
import pl.edu.agh.marcskow.ftpserver.server.implementation.FtpServerContextImpl;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Main class of the controller layer.
 * View is defined in fxml that's why in the controller we can simple use FXML annotation
 * to inject view elements.
 */
public class ServerController extends AnchorPane implements Initializable {

    //Action panel
    @FXML private TextField firstTextField;
    @FXML private TextField secondTextField;
    @FXML private TextField thirdTextField;
    @FXML private Label firstLabel;
    @FXML private Label secondLabel;
    @FXML private Label thirdLabel;
    @FXML private Label subtitleLabel;

    //Additional options
    @FXML private TextField fourthTextField;
    @FXML private TextField fifthTextField;
    @FXML private Label fourthLabel;
    @FXML private Label fifthLabel;
    @FXML private TableView<User> databaseTable;

    //Console
    @FXML private TextArea consoleTextArea;

    //Visibility
    private Action actualAction = Action.NONE;
    private Map<String,Node> nodes = new HashMap<>();

    //Server
    private Server server = new Server(new FtpServerContextImpl());
    private FtpServerContext context = new FtpServerContextImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeView();
    }

    /**
     * Run button onClick method.
     * This method starts the server. If server settings were defined before
     * it will run with specified settings. Otherwise server will run with default settings.
     */
    @FXML
    public void runServer(){
        if(!server.isRunning()) {
            Thread thread = new Thread(server);
            thread.start();
        }
    }

    /**
     * This method stops the server
     */
    @FXML
    public void stopServer(){
        if(server.isRunning()) {
            server.stop();
        }
    }

    /**
     * Load data from database and display it on the TableView
     */
    @FXML
    public void setDatabase(){
        DatabaseTable databaseTableView = new DatabaseTable(databaseTable);
        databaseTableView.updateTableView();
    }

    /**
     * Add User button listener. Add new user to database.
     * If this method will be executed once it will show two text fields
     * if this method will is performed for the second time it will read data from text fields
     * and sends them to the database (by calling Hibernate.addUser method)
     */
    @FXML
    public void addUser() {
        String name;
        String password;

        if (actualAction != Action.ADD_USER) {
            setAllVisibility(false);
            actualAction = Action.ADD_USER;
            changeVisibilityOf("FIRST_TF", "FIRST_L", "SECOND_TF", "SECOND_L", "SUBTITLE");
            changeText("FIRST_L", "Username: ", "SECOND_L", "Password: ", "SUBTITLE", "Add User");
        } else if ((name = firstTextField.getText()).isEmpty() || (password = secondTextField.getText()).isEmpty()) {
            consoleTextArea.appendText("Name or password cannot be empty! \n");
        } else {
            Hibernate.addUser(name, password);

            consoleTextArea.appendText("User: " + name + " added successfully. \n");
            setAllVisibility(false);
            actualAction = Action.NONE;
        }
    }

    /**
     * Add Group button listener. Add new group to database.
     * If this method will be executed once it will show one text fields
     * if this method will is performed for the second time it will read data from text fields
     * and sends them to the database (by calling Hibernate.addGroup method)
     */
    @FXML
    public void addGroup() {
        String name;
        if (actualAction != Action.ADD_GROUP) {
            setAllVisibility(false);
            actualAction = Action.ADD_GROUP;
            changeVisibilityOf("FIRST_TF", "FIRST_L", "SUBTITLE");
            changeText("FIRST_L", "Username: ", "SUBTITLE", "Add Group");
        } else if ((name = firstTextField.getText()).isEmpty()) {
            consoleTextArea.appendText("Group name cannot be empty! \n");
        } else {
            Hibernate.addGroup(name);

            consoleTextArea.appendText("Group: " + name + " added successfully. \n");
            setAllVisibility(false);
            actualAction = Action.NONE;
        }
    }

    /**
     * Edit user button listener. Update user data.
     * If this method will be executed once it will show one text fields
     * if this method will is performed for the second time it will read data from text fields
     * and sends them to the database
     */
    @FXML
    public void editUser(){
        String oldName;
        String newName;
        String newPassword;

        if(actualAction != Action.EDIT_USER){
            setAllVisibility(false);
            actualAction = Action.EDIT_USER;
            changeVisibilityOf("FIRST_TF","FIRST_L","SECOND_TF","SECOND_L","THIRD_TF","THIRD_L","SUBTITLE");
            changeText("FIRST_L","Old name: ","SECOND_L","New name: ","THIRD_L","Password: ","SUBTITLE","Edit User");
        }
        else if(((oldName = firstTextField.getText()).isEmpty()) || (newName = secondTextField.getText()).isEmpty()
                || (newPassword = thirdTextField.getText()).isEmpty()) {
            consoleTextArea.appendText("Fields cannot be empty! \n");
        }
        else {
            Hibernate.editUser(oldName, newName, newPassword);

            consoleTextArea.appendText("User: " + oldName + " edited successfully. \n");
            setAllVisibility(false);
            actualAction = Action.NONE;
        }
    }

    /**
     * Edit group button listener. Update group data.
     * If this method will be executed once it will show one text fields
     * if this method will is performed for the second time it will read data from text fields
     * and sends them to the database
     */
    @FXML
    public void editGroup() {
        String oldName;
        String newName;

        if (actualAction != Action.EDIT_GROUP) {
            setAllVisibility(false);
            actualAction = Action.EDIT_GROUP;
            changeVisibilityOf("FIRST_TF", "FIRST_L", "SECOND_TF", "SECOND_L","SUBTITLE");
            changeText("FIRST_L", "Old name: ", "SECOND_L", "New name: ","SUBTITLE","Edit Group");
        } else if (((oldName = firstTextField.getText()).isEmpty()) || (newName = secondTextField.getText()).isEmpty()) {
            consoleTextArea.appendText("Fields cannot be empty! \n");
        } else {
            Hibernate.editGroup(oldName, newName);

            consoleTextArea.appendText("Group: " + oldName + " edited successfully. \n");
            setAllVisibility(false);
            actualAction = Action.NONE;
        }
    }

    /**
     * Remove User button listener.
     * Invoke deleting user from database.
     */
    @FXML
    public void removeUser() {
        String username;
        if (actualAction != Action.REMOVE_USER) {
            setAllVisibility(false);
            actualAction = Action.REMOVE_USER;
            changeVisibilityOf("FIRST_TF", "FIRST_L", "SUBTITLE");
            changeText("FIRST_L", "Username: ", "SUBTITLE", "Remove User");
        } else if ((username = firstTextField.getText()).isEmpty()) {
            consoleTextArea.appendText("User name cannot be empty! \n");
        } else {
            Hibernate.removeUser(username);

            consoleTextArea.appendText("User: " + username + " removed successfully. \n");
            setAllVisibility(false);
            actualAction = Action.NONE;
        }
    }

    /**
     * Remove Group button listener.
     * Invoke deleting group from database.
     */
    @FXML
    public void removeGroup(){
        String name;
        if(actualAction != Action.REMOVE_GROUP){
            setAllVisibility(false);
            actualAction = Action.REMOVE_GROUP;
            changeVisibilityOf("FIRST_TF","FIRST_L","SUBTITLE");
            changeText("FIRST_L","Group: ","SUBTITLE","Remove Group");
        }
        else if((name = firstTextField.getText()).isEmpty()) {
            consoleTextArea.appendText("Group name cannot be empty! \n");
        }
        else {
            Hibernate.removeGroup(name);

            consoleTextArea.appendText("Group: " + name + " removed successfully. \n");
            setAllVisibility(false);
            actualAction = Action.NONE;
        }
    }

    /**
     * Change user group
     * Invoke updating user group in database.
     */
    @FXML
    public void setUserGroup(){
        String username;
        int id;

        if(actualAction != Action.EDIT_USER_GROUP){
            setAllVisibility(false);
            actualAction = Action.EDIT_USER_GROUP;
            changeVisibilityOf("FOURTH_TF","FOURTH_L","FIFTH_TF","FIFTH_L","SUBTITLE");
            changeText("FOURTH_L","Username: ","FIFTH_L","Group Id: ","SUBTITLE","Set Group");
        }
        else if(((username = firstTextField.getText()).isEmpty()) || (secondTextField.getText()).isEmpty()) {
            consoleTextArea.appendText("Fields cannot be empty! \n");
        }
        else {
            id = Integer.parseInt(secondTextField.getText());

            Hibernate.setUserGroup(username, id);

            consoleTextArea.appendText("User group of: " + username + " edited successfully. \n");
            setAllVisibility(false);
            actualAction = Action.NONE;
        }
    }

    /**
     * Change server's root folder.
     */
    @FXML
    public void setRootFolder(){
        String rootPath;
        String port;
        String pool;

        if(actualAction != Action.ROOT_FOLDER){
            setAllVisibility(false);
            actualAction = Action.ROOT_FOLDER;
            changeVisibilityOf("FIRST_TF","FIRST_L","SECOND_TF","SECOND_L","THIRD_TF","THIRD_L","SUBTITLE");
            changeText("FIRST_L","Path: ","SECOND_L","Port: ","THIRD_L","Pool: ","SUBTITLE","Settings");
        }
        else {
            if(!(rootPath = firstTextField.getText()).isEmpty()) {
                context.setRoot(rootPath);
                consoleTextArea.appendText("Root path changed to: " + rootPath + "\n");
            }
            if(!(port = secondTextField.getText()).isEmpty()){
                context.setPort(Integer.parseInt(port));
                consoleTextArea.appendText("Port changed to: " + port + "\n");
            }
            if(!(pool = thirdTextField.getText()).isEmpty()){
                context.setThreadPool(Integer.parseInt(pool));
                consoleTextArea.appendText("Thread pool changed to: " + pool + "\n");
            }

            setAllVisibility(false);
            actualAction = Action.NONE;
        }
    }

    private void setAllVisibility(boolean visible){
        firstTextField.setVisible(visible);
        secondTextField.setVisible(visible);
        thirdTextField.setVisible(visible);
        fourthTextField.setVisible(visible);
        fifthTextField.setVisible(visible);
        firstLabel.setVisible(visible);
        secondLabel.setVisible(visible);
        thirdLabel.setVisible(visible);
        fourthLabel.setVisible(visible);
        fifthLabel.setVisible(visible);
        subtitleLabel.setVisible(visible);
    }

    private void changeVisibilityOf(String... args){
        for (String s : args) {
            nodes.get(s).setVisible(!nodes.get(s).isVisible());
        }
    }

    private void changeText(String... args){
        for (int i = 0; i < args.length; i += 2) {
            ((Label)nodes.get(args[i])).setText(args[i+1]);
        }
    }

    private void initializeView(){
        nodes.put("FIRST_TF", firstTextField);
        nodes.put("SECOND_TF", secondTextField);
        nodes.put("THIRD_TF", thirdTextField);
        nodes.put("FOURTH_TF", fourthTextField);
        nodes.put("FIFTH_TF", fifthTextField);
        nodes.put("FIRST_L", firstLabel);
        nodes.put("SECOND_L", secondLabel);
        nodes.put("THIRD_L", thirdLabel);
        nodes.put("FOURTH_L", fourthLabel);
        nodes.put("FIFTH_L", fifthLabel);
        nodes.put("SUBTITLE",subtitleLabel);
    }
}

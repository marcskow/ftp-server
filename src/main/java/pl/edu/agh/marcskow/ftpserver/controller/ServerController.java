package pl.edu.agh.marcskow.ftpserver.controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.edu.agh.marcskow.ftpserver.clientHandler.Session;
import pl.edu.agh.marcskow.ftpserver.data.User;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.BooleanSupplier;

public class ServerController extends AnchorPane implements Initializable {

    //Main buttons
    @FXML private Button runButton;
    @FXML private Button stopButton;
    @FXML private Button databaseButton;

    //Additional buttons
    @FXML Button addUserButton;
    @FXML Button addGroupButton;
    @FXML Button editUserButton;
    @FXML Button editGroupButton;
    @FXML Button rootFolderButton;

    //Option panel
    @FXML AnchorPane optionsPane;
    @FXML TextField firstTextField;
    @FXML TextField secondTextField;
    @FXML TextField thirdTextField;
    @FXML Label firstLabel;
    @FXML Label secondLabel;
    @FXML Label thirdLabel;

    //Additional options
    @FXML CheckBox moreOptionsCheckBox;
    @FXML AnchorPane additionalOptionsPane;
    @FXML TextField fourthTextField;
    @FXML TextField fifthTextField;
    @FXML Label fourthLabel;
    @FXML Label fifthLabel;

    //Console
    @FXML
    TextArea consoleTextArea;

    //Database table // FIXME: 22.04.16
    //@FXML TableView<TableField> databaseTable;

    //Model
    Thread server;

    //Visibility
    boolean areAdditionalOptionsVisible = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void runServer(){
    }

    @FXML
    public void stopServer(){
    }

    @FXML
    public void setDatabase(){
    }

    @FXML
    public void setRootFolder(){

    }

    @FXML
    public void addUser(){



        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session hibernateSession = sessionFactory.openSession();

        User user = new User();
        user.setPassword();
    }

    @FXML
    public void addGroup(){
        String newGroup = newNameTextField.getCharacters().toString();
    }

    @FXML
    public void editUser(){
        String newName = newNameTextField.getCharacters().toString();
       }

    @FXML
    public void editGroup(){
        String newName = newNameTextField.getCharacters().toString();
       }

    @FXML
    public void removeUser(){
        String user = newNameTextField.getCharacters().toString();
    }

    @FXML
    public void removeGroup(){
        String name = newNameTextField.getCharacters().toString();
    }

    @FXML
    public void setUserGroup(){
        String userId = newNameTextField.getCharacters().toString();
        //newGroupTextField.getCharacters().toString());
    }

    @FXML
    public void moreOptions(){
        if(areAdditionalOptionsVisible){
            areAdditionalOptionsVisible = false;
            additionalOptionsPane.setVisible(false);
        } else {
            areAdditionalOptionsVisible = true;
            additionalOptionsPane.setVisible(true);
        }
    }

}

package pl.edu.agh.marcskow.ftpserver.controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.edu.agh.marcskow.ftpserver.data.Group;
import pl.edu.agh.marcskow.ftpserver.data.User;
import pl.edu.agh.marcskow.ftpserver.database.Hibernate;
import pl.edu.agh.marcskow.ftpserver.server.Server;
import pl.edu.agh.marcskow.ftpserver.server.implementation.FtpServerContextImpl;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ServerController extends AnchorPane implements Initializable {

    //Main buttons
    @FXML private Button runButton;
    @FXML private Button stopButton;
    @FXML private Button databaseButton;

    //Additional buttons
    @FXML private Button addUserButton;
    @FXML private Button addGroupButton;
    @FXML private Button editUserButton;
    @FXML private Button editGroupButton;
    @FXML private Button rootFolderButton;
    @FXML private Button setGroupButton;

    //Action panel
    @FXML private AnchorPane optionsPane;
    @FXML private TextField firstTextField;
    @FXML private TextField secondTextField;
    @FXML private TextField thirdTextField;
    @FXML private Label firstLabel;
    @FXML private Label secondLabel;
    @FXML private Label thirdLabel;
    @FXML private Label subtitleLabel;

    //Additional options
    @FXML private CheckBox moreOptionsCheckBox;
    @FXML private AnchorPane additionalOptionsPane;
    @FXML private TextField fourthTextField;
    @FXML private TextField fifthTextField;
    @FXML private Label fourthLabel;
    @FXML private Label fifthLabel;
    @FXML private TextArea additionalTextArea;
    @FXML private TableView<User> databaseTable;

    //Console
    @FXML private TextArea consoleTextArea;

    //Database table // FIXME: 22.04.16
    //@FXML TableView<TableField> databaseTable;

    //Model
    private Thread servert;
    private Server server;

    //Visibility
    private boolean areAdditionalOptionsVisible = false;
    private Action actualAction = Action.NONE;
    private Map<String,Node> nodes = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeView();
    }

    @FXML
    public void runServer(){
        server = new Server(new FtpServerContextImpl());
        Thread thread = new Thread(server);
        thread.start();
    }

    @FXML
    public void stopServer(){
        server.setRunning(false);
    }

    @FXML
    public void setDatabase(){
        DatabaseTable databaseTableView = new DatabaseTable(databaseTable);
        databaseTableView.updateTableView();
    }

    @FXML
    public void setRootFolder(){

    }

    @FXML
    public void addUser(){
        if(actualAction != Action.ADD_USER){
            setAllVisibility(false);
            actualAction = Action.ADD_USER;
            changeVisibilityOf("FIRST_TF","FIRST_L","SECOND_TF","SECOND_L","SUBTITLE");
            changeText("FIRST_L","Username: ","SECOND_L","Password: ","SUBTITLE","ADD USER");
        }
        else {
            String name = firstTextField.getText();
            String password = secondTextField.getText();

            if(name.isEmpty() || password.isEmpty()) {
                consoleTextArea.appendText("Name or password cannot be empty! \n");
            }
            else {
                SessionFactory sessionFactory = Hibernate.setUp();
                org.hibernate.Session hibernateSession = sessionFactory.openSession();
                hibernateSession.beginTransaction();

                User user = new User();
                user.setUsername(name);
                user.setPassword(password);

                hibernateSession.save(user);
                hibernateSession.getTransaction().commit();
                hibernateSession.close();

                consoleTextArea.appendText("User: " + name + " added successfully. \n");
            }
            setAllVisibility(false);
            actualAction = Action.NONE;
        }
    }

    @FXML
    public void addGroup(){
        String name;
        if(actualAction != Action.ADD_GROUP){
            setAllVisibility(false);
            actualAction = Action.ADD_GROUP;
            changeVisibilityOf("FIRST_TF","FIRST_L");
            changeText("FIRST_L","Username: ");
        }
        else if((name = firstTextField.getText()).isEmpty()) {
            consoleTextArea.appendText("Group name cannot be empty! \n");
        }
        else {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            org.hibernate.Session hibernateSession = sessionFactory.openSession();
            hibernateSession.beginTransaction();

            Group group = new Group();
            group.setName(name);

            hibernateSession.save(group);
            hibernateSession.getTransaction().commit();
            hibernateSession.close();

            consoleTextArea.appendText("Group: " + name + " added successfully. \n");
            setAllVisibility(false);
            actualAction = Action.NONE;
        }
    }

    @FXML
    public void editUser(){
        String oldName;
        String newName;
        String newPassword;

        if(actualAction != Action.EDIT_USER){
            setAllVisibility(false);
            actualAction = Action.EDIT_USER;
            changeVisibilityOf("FIRST_TF","FIRST_L","SECOND_TF","SECOND_L","THIRD_TF","THIRD_L");
            changeText("FIRST_L","Old name: ","SECOND_L","New name: ","THIRD_L","Password: ");
        }
        else if(((oldName = firstTextField.getText()).isEmpty()) || (newName = secondTextField.getText()).isEmpty()
                || (newPassword = thirdTextField.getText()).isEmpty()) {
            consoleTextArea.appendText("Fields cannot be empty! \n");
        }
        else {
            SessionFactory sessionFactory = Hibernate.setUp();
            org.hibernate.Session hibernateSession = sessionFactory.openSession();
            hibernateSession.beginTransaction();

            User user = new User();
            user.setId(Hibernate.nameToId(oldName));
            user.setUsername(newName);
            user.setPassword(newPassword);

            hibernateSession.update(user);
            hibernateSession.getTransaction().commit();
            hibernateSession.close();

            consoleTextArea.appendText("User: " + oldName + " edited successfully. \n");
            setAllVisibility(false);
            actualAction = Action.NONE;
        }
    }

    @FXML
    public void editGroup() {
        String oldName;
        String newName;

        if (actualAction != Action.EDIT_GROUP) {
            setAllVisibility(false);
            actualAction = Action.EDIT_GROUP;
            changeVisibilityOf("FIRST_TF", "FIRST_L", "SECOND_TF", "SECOND_L");
            changeText("FIRST_L", "Old name: ", "SECOND_L", "New name: ");
        } else if (((oldName = firstTextField.getText()).isEmpty()) || (newName = secondTextField.getText()).isEmpty()) {
            consoleTextArea.appendText("Fields cannot be empty! \n");
        } else {
            SessionFactory sessionFactory = Hibernate.setUp();
            org.hibernate.Session hibernateSession = sessionFactory.openSession();
            hibernateSession.beginTransaction();

            Query query = hibernateSession.createQuery("FROM Group G WHERE G.name = :name");
            query.setParameter("name", oldName);

            List result = query.list();
            Group group = (Group) result.get(0);

            group.setName(newName);

            hibernateSession.update(group);
            hibernateSession.getTransaction().commit();
            hibernateSession.close();

            consoleTextArea.appendText("Group: " + oldName + " edited successfully. \n");
            setAllVisibility(false);
            actualAction = Action.NONE;
        }
    }

    @FXML
    public void removeUser(){
        String username;
        if(actualAction != Action.REMOVE_USER){
            setAllVisibility(false);
            actualAction = Action.REMOVE_USER;
            changeVisibilityOf("FIRST_TF","FIRST_L");
            changeText("FIRST_L","Username: ");
        }
        else if((username = firstTextField.getText()).isEmpty()) {
            consoleTextArea.appendText("User name cannot be empty! \n");
        }
        else {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            org.hibernate.Session hibernateSession = sessionFactory.openSession();
            hibernateSession.beginTransaction();

            Query query = hibernateSession.createQuery("FROM User U WHERE U.username = :name");
            query.setParameter("name", username);

            List result = query.list();
            User user = (User) result.get(0);

            hibernateSession.delete(user);
            hibernateSession.getTransaction().commit();
            hibernateSession.close();

            consoleTextArea.appendText("User: " + username + " removed successfully. \n");
            setAllVisibility(false);
            actualAction = Action.NONE;
        }
    }

    @FXML
    public void removeGroup(){
        String groupname;
        if(actualAction != Action.REMOVE_GROUP){
            setAllVisibility(false);
            actualAction = Action.REMOVE_GROUP;
            changeVisibilityOf("FIRST_TF","FIRST_L");
            changeText("FIRST_L","Group name: ");
        }
        else if((groupname = firstTextField.getText()).isEmpty()) {
            consoleTextArea.appendText("Group name cannot be empty! \n");
        }
        else {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            org.hibernate.Session hibernateSession = sessionFactory.openSession();
            hibernateSession.beginTransaction();

            Query query = hibernateSession.createQuery("FROM Group G WHERE G.name = :name");
            query.setParameter("name", groupname);

            List result = query.list();
            Group group = (Group) result.get(0);

            hibernateSession.delete(group);
            hibernateSession.getTransaction().commit();
            hibernateSession.close();

            consoleTextArea.appendText("Group: " + groupname + " removed successfully. \n");
            setAllVisibility(false);
            actualAction = Action.NONE;
        }
    }

    @FXML
    public void setUserGroup(){
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

    private void setAllVisibility(boolean visible){
        firstTextField.setVisible(visible);
        secondTextField.setVisible(visible);
        thirdTextField.setVisible(visible);
        fourthTextField.setVisible(visible);
        fifthTextField.setVisible(visible);
        fifthLabel.setVisible(visible);
        secondLabel.setVisible(visible);
        thirdLabel.setVisible(visible);
        fourthLabel.setVisible(visible);
        fifthLabel.setVisible(visible);
        moreOptionsCheckBox.setVisible(visible);
        setGroupButton.setVisible(visible);
        additionalTextArea.setVisible(visible);
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
        nodes.put("DESCRIPTION", additionalTextArea);
        nodes.put("ADDITIONAL_OPT", additionalOptionsPane);
        nodes.put("ADDITIONAL_CB", moreOptionsCheckBox);
    }
}

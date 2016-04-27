package pl.edu.agh.marcskow.ftpserver.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.edu.agh.marcskow.ftpserver.data.User;

import java.util.List;

@SuppressWarnings("ALL")
public class DatabaseTable {
    private TableView<User> tableView;

    public DatabaseTable(TableView<User> tableView){
        this.tableView = tableView;
    }

    /**
     * This method is responsible for retrieving data from the database
     * and showing them in the TableView
     */
    public void updateTableView(){
        tableView.setEditable(true);
        ObservableList<User> users = getUsers();

        TableColumn userId = new TableColumn("Id");
        userId.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        userId.setMinWidth(50);

        TableColumn username = new TableColumn("Username");
        username.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        username.setMinWidth(250);

        TableColumn userGroup = new TableColumn("Group");
        userGroup.setCellValueFactory(new PropertyValueFactory<User, Integer>("groupId"));
        userGroup.setMinWidth(250);

        tableView.setItems(users);
        tableView.getColumns().clear();
        tableView.getColumns().addAll(userId, username, userGroup);


    }

    private ObservableList<User> getUsers(){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session hibernateSession = sessionFactory.openSession();
        hibernateSession.beginTransaction();

        List list = hibernateSession.createCriteria(User.class).list();
        ObservableList<User> observableList = FXCollections.observableList(list);

        hibernateSession.close();

        return observableList;
    }
}

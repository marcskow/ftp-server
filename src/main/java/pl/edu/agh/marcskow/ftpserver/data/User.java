package pl.edu.agh.marcskow.ftpserver.data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="users")
@SecondaryTable(name = "usergroup", pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
@Getter @Setter
/* More about @PrimaryKeyJoinColumn
   http://docs.oracle.com/javaee/5/api/javax/persistence/PrimaryKeyJoinColumn.html
   http://stackoverflow.com/questions/3417097/jpa-difference-between-joincolumn-and-primarykeyjoincolumn
   There look for: 5.1.2.2.7. Identity copy (foreign generator)
   http://docs.jboss.org/hibernate/core/3.6/reference/en-US/html_single/#mapping-declaration-id
    */
public class User {
    @Id
    @SequenceGenerator(name = "userGenerator", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userGenerator")
    private int id;

    @Column(name="username", nullable = false, length = 20)
    private String username;

    // TODO: 05.03.16
    // Application will use md5 (or SHA) method to get hash
    // There is example
    // http://stackoverflow.com/questions/415953/how-can-i-generate-an-md5-hash
    // http://docs.oracle.com/javase/6/docs/api/java/security/MessageDigest.html
    // http://stackoverflow.com/questions/6593801/matching-users-password-from-record-in-db

    @Column(name="password", nullable = false)
    private String password;

    @Column(table = "usergroup",nullable = false, name = "group_id")
    private int groupId = 2;

}

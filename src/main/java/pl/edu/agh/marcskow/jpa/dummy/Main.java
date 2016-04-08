package pl.edu.agh.marcskow.jpa.dummy;

import pl.edu.agh.marcskow.jpa.data.File;
import pl.edu.agh.marcskow.jpa.data.Group;
import pl.edu.agh.marcskow.jpa.data.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by intenso on 03.03.16.
 */
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory myDatabase = Persistence.createEntityManagerFactory("testdb");
        EntityManager entityManager = myDatabase.createEntityManager();

        entityManager.getTransaction().begin();
        User user = new User();
        user.setUsername("Marcin");
        user.setPassword("pas");
        User user2 = new User();
        user2.setUsername("Marcin2");
        user2.setPassword("pas2");
        Group group = new Group();
        group.setName("default");
        File file = new File();
        file.setFilename("Dupakowy");
        file.setGroup_id(0);
        file.setOwner_id(0);
        entityManager.persist(file);
        entityManager.persist(group);
        entityManager.persist(user);
        entityManager.persist(user2);
        entityManager.getTransaction().commit();

        entityManager.close();
        myDatabase.close();
    }
}

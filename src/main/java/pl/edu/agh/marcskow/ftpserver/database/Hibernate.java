package pl.edu.agh.marcskow.ftpserver.database;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import pl.edu.agh.marcskow.ftpserver.data.Group;
import pl.edu.agh.marcskow.ftpserver.data.User;

import java.util.List;

/**
 * Static class that contains methods for connecting to database using Hibernate.
 */
public class Hibernate {
    /**
     * Set up session factory
     * @return SessionFactory
     */
    public static SessionFactory setUp() {
        SessionFactory sessionFactory = null;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        try{
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e){
            StandardServiceRegistryBuilder.destroy( registry );
        }
        return sessionFactory;
    }

    /**
     * Return name corresponding to such id
     * @param name username
     * @return user id
     */
    private static int nameToId(String name){
        SessionFactory sessionFactory = setUp();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("FROM User U WHERE U.username = :name");
        query.setParameter("name",name);

        List result = query.list();
        User user = (User)result.get(0);

        session.close();

        return user.getId();
    }

    /**
     * Add user to database
     * @param username name of user
     * @param password password
     */
    public static void addUser(String username, String password){
        SessionFactory sessionFactory = setUp();
        org.hibernate.Session hibernateSession = sessionFactory.openSession();
        hibernateSession.beginTransaction();

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        hibernateSession.save(user);
        hibernateSession.getTransaction().commit();
        hibernateSession.close();
    }

    /**
     * Add group to database
     * @param name name of group
     */
    public static void addGroup(String name){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session hibernateSession = sessionFactory.openSession();
        hibernateSession.beginTransaction();

        Group group = new Group();
        group.setName(name);

        hibernateSession.save(group);
        hibernateSession.getTransaction().commit();
        hibernateSession.close();
    }

    /**
     * Edit user data
     * @param oldName old username
     * @param newName new username
     * @param newPassword new password
     */
    public static void editUser(String oldName, String newName, String newPassword){
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
    }

    /**
     * Edit group data
     * @param oldName old group name
     * @param newName new group name
     */
    public static void editGroup(String oldName, String newName){
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
    }

    /**
     * User to delete from database
     * @param username name of user to delete
     */
    public static void removeUser(String username){
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
    }

    /**
     * Group to delete from database
     * @param name group to delete
     */
    public static void removeGroup(String name){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session hibernateSession = sessionFactory.openSession();
        hibernateSession.beginTransaction();

        Query query = hibernateSession.createQuery("FROM Group G WHERE G.name = :name");
        query.setParameter("name", name);

        List result = query.list();
        Group group = (Group) result.get(0);

        hibernateSession.delete(group);
        hibernateSession.getTransaction().commit();
        hibernateSession.close();
    }

    /**
     * Method to change user group
     * @param username username
     * @param id id of new user group
     */
    public static void setUserGroup(String username, int id){
        SessionFactory sessionFactory = Hibernate.setUp();
        org.hibernate.Session hibernateSession = sessionFactory.openSession();
        hibernateSession.beginTransaction();

        Query query = hibernateSession.createQuery("FROM User U WHERE U.username = :name");
        query.setParameter("name", username);

        List result = query.list();
        User user = (User) result.get(0);
        user.setGroupId(id);

        hibernateSession.update(user);
        hibernateSession.getTransaction().commit();
        hibernateSession.close();
    }
}

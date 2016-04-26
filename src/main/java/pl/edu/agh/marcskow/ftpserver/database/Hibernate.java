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


public class Hibernate {
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

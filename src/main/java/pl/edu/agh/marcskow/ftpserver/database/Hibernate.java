package pl.edu.agh.marcskow.ftpserver.database;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
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

    public static int nameToId(String name){
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
}

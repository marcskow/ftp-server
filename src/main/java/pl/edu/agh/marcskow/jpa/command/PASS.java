package pl.edu.agh.marcskow.jpa.command;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import pl.edu.agh.marcskow.jpa.clientHandler.Session;
import pl.edu.agh.marcskow.jpa.data.File;
import pl.edu.agh.marcskow.jpa.data.Group;
import pl.edu.agh.marcskow.jpa.data.User;
import pl.edu.agh.marcskow.jpa.util.Message;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.List;

/**
 * Created by intenso on 09.04.16.
 */
public class PASS extends AbstractCommand{
    private Session session;
    private Message body;
    private String password;

    public PASS(Session session, Message body){
        this.session = session;
        this.body = body;
    }

    @Override
    public String execute() {
        if(session.getNeededCommand().equals("PASS")
                && !(body.getArgs().length == 0)){
            password = body.getArgument(0);
            session.setUserPassword(password);

            if(logIn()){
                session.setIsLoggedIn(true);
                return "230 User logged in";
            }
            else {
                return "502 Wrong username or password";
            }
        }
        else{
            return "503 Bad sequence of commands";
        }
    }

    private boolean logIn()  {
        String login = session.getUserLogin();
        String passwrod = session.getUserPassword();

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session hibernateSession = sessionFactory.openSession();
        String hql = "FROM User U WHERE U.username = :login";
        Query query = hibernateSession.createQuery(hql);
        query.setParameter("login",login);
        List result = query.list();
        try {
            session.write(((User)result.get(0)).getUsername());
        }
        catch (IOException e){
            System.out.println("XDDDDD");
        }

        return true;
    }

}

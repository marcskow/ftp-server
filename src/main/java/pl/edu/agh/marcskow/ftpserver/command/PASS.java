package pl.edu.agh.marcskow.ftpserver.command;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.edu.agh.marcskow.ftpserver.clientHandler.Session;
import pl.edu.agh.marcskow.ftpserver.data.User;
import pl.edu.agh.marcskow.ftpserver.util.Message;

import java.io.IOException;
import java.util.List;

/**
 * PASS command has to be send after sending USER command.
 * Receive password from user and check is it valid.
 */
public class PASS implements Command{
    private final Session session;
    private final Message body;

    public PASS(Session session, Message body){
        this.session = session;
        this.body = body;
    }

    @Override
    public void execute() throws IOException {
        if(session.getLastCommand().equals("USER")
                && !(body.getArgs().length == 0)){
            String password = body.getArgument(0);

            if(logIn(password)){
                session.setIsLoggedIn(true);
                session.write("230 User logged in");
            }
            else {
                session.write("502 Wrong username or password");
            }
        }
        else{
            session.write("503 Bad sequence of commands");
        }
    }

    private boolean logIn(String password)  {
        String login = session.getUserLogin();

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session hibernateSession = sessionFactory.openSession();

        String hql = "FROM User U WHERE U.username = :login";

        Query query = hibernateSession.createQuery(hql);
        query.setParameter("login",login);

        List result = query.list();
        User user = (User)result.get(0);

        hibernateSession.close();

        return user.getPassword().equals(password) && user.getUsername().equals(login);
    }

}

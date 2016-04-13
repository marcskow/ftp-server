package pl.edu.agh.marcskow.jpa.command;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.edu.agh.marcskow.jpa.clientHandler.FtpSession;
import pl.edu.agh.marcskow.jpa.data.User;
import pl.edu.agh.marcskow.jpa.util.Message;

import java.util.List;

/**
 * Created by intenso on 09.04.16.
 */
public class PASS extends ActiveCommand{
    private FtpSession session;
    private Message body;

    public PASS(FtpSession session, Message body){
        this.session = session;
        this.body = body;
    }

    @Override
    public String execute() {
        if(session.getNeededCommand().equals("PASS")
                && !(body.getArgs().length == 0)){
            String password = body.getArgument(0);

            if(logIn(password)){
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

    private boolean logIn(String password)  {
        String login = session.getUserLogin();

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session hibernateSession = sessionFactory.openSession();

        String hql = "FROM User U WHERE U.username = :login";

        Query query = hibernateSession.createQuery(hql);
        query.setParameter("login",login);

        List result = query.list();
        User user = (User)result.get(0);

        hibernateSession.close();

        return user.getPassword().equals(password) && user.getUsername().equals(login);
    }

}

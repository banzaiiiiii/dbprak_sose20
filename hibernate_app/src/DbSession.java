import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class DbSession {

    private SessionFactory sessionFactory;

    public void CreateSession(){


        try {
            System.out.println( "Initializing Hibernate" );
            sessionFactory = new Configuration().configure().buildSessionFactory();
            System.out.println( "Finished Initializing Hibernate" );
        } catch( HibernateException ex ) {
            ex.printStackTrace();
            System.exit( 5 );
        }

    }

}

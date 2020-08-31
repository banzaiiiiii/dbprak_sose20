import jdk.jshell.spi.ExecutionControl;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class DbSession {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

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

    public void Save(){

        try {
             session = sessionFactory.openSession();
             transaction = session.beginTransaction();

            // create objects here//
            transaction.commit();


        } catch (HibernateException exception) {
            if(transaction != null)
                try{transaction.rollback(); }catch (HibernateException rollbackException){rollbackException.getMessage();}
            throw new RuntimeException(exception.getMessage());

        }finally {
            try {
                if (session != null) session.close();
            } catch (Exception closeException) {
                closeException.getMessage();
            }
        }
    }

    public void Load(){

       throw new UnsupportedOperationException("not implemented yet");
    }
}

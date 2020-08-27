package com.hibernate.application;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hibernate.pojos.Person;


public class PersonRelatedAPI
{
    private static SessionFactory factory;

    public static void main(String[] args)
    {
        try
        {
            factory = new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable ex)
        {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        PersonRelatedAPI prapi = new PersonRelatedAPI();

        /* List down all the employees */
        prapi.getProfile();
    }

    /* Ausgabe des Profils einer Person, d.h. alle personenbezogenen Informationen (Name, Geschlecht, Wohnort …) */
    public void getProfile()
    {
        Session session = factory.openSession();
        Transaction tx = null;

        try
        {
            tx = session.beginTransaction();
            List persons = session.createQuery("FROM Person").list();
            for (Iterator iterator = persons.iterator(); iterator.hasNext(); )
            {
                Person person = (Person) iterator.next();
                System.out.print("First Name: " + person.getPersonFirstName());
                System.out.print("  Last Name: " + person.getPersonLastName());
                System.out.print("  Gender: " + person.getPersonGender());
                System.out.print("  Birthday: " + person.getPersonBirthday());
                System.out.print("  City: " + person.getCity().getPlaceName());
            }
            tx.commit();
        }
        catch (HibernateException e)
        {
            if (tx != null)
            {
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally
        {
            session.close();
        }
    }

}

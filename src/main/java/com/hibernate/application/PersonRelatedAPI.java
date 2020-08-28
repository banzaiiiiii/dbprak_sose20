package com.hibernate.application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hibernate.pojos.Person;
import com.hibernate.pojos.Tag;


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
        prapi.getProfile(12094627905604L);
        prapi.getCommonInterestsOfMyFriends(5497558138908L);
    }

    /* Ausgabe des Profils einer Person, d.h. alle personenbezogenen Informationen (Name, Geschlecht, Wohnort …) */
    public void getProfile(final long personId)
    {
        Session session = factory.openSession();
        Transaction tx = null;

        try
        {
            tx = session.beginTransaction();
            Person person = (Person) session.createQuery("FROM Person P WHERE P.personId=" + personId).list().get(0);

            System.out.println("1. - PROFILE:");
            System.out.println("First Name: " + person.getPersonFirstName());
            System.out.println("Last Name: " + person.getPersonLastName());
            System.out.println("Gender: " + person.getPersonGender());
            System.out.println("Birthday: " + person.getPersonBirthday());
            System.out.println("City: " + person.getCityByPersonCityId().getPlaceName());

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

    public void getCommonInterestsOfMyFriends(final long personId)
    {
        Session session = factory.openSession();
        Transaction tx = null;

        try
        {
            tx = session.beginTransaction();
            Person person = (Person) session.createQuery("FROM Person P WHERE P.personId=" + personId).list().get(0);

            List<Person> bidirFriends = new ArrayList<>();
            person.getKnowsByPersonId().forEach (e -> bidirFriends.add(e.getPersonByKnowsOtherPersonId()));
            person.getKnowsByPersonId_0().forEach(e -> bidirFriends.add(e.getPersonByKnowsPersonId()));
            for(Person friend : bidirFriends)
            {
                System.out.println();
                System.out.println("2. - Common INTERESTS:");
                System.out.println("tags: ");
                getIntersection(person.retrieveTags(), friend.retrieveTags()).forEach(
                    e -> System.out.println("   tagID: " + e.getTagId()));
                System.out.println("First Name: " + friend.getPersonFirstName());
                System.out.println("Last Name: " + friend.getPersonLastName());
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

    private static List<Tag> getIntersection(List<Tag> list1, List<Tag> list2)
    {
        list1.retainAll(list2);
        return list1;
    }

}

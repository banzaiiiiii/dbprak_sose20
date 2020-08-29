package com.hibernate.application;

import java.util.Scanner;

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

        while(true)
        {
            prapi.listenToInput();
        }

        /* List down all the employees */
        // 1 - 12094627905604
        // 2 - 5497558138908
        // 3 - 5497558138908 , 12094627905604
    }

    /* Ausgabe des Profils einer Person, d.h. alle personenbezogenen Informationen (Name, Geschlecht, Wohnort …) */
    public void getProfile(final long personId, Session session)
    {
        Person person = (Person) session.createQuery("FROM Person P WHERE P.personId=" + personId).list().get(0);

        System.out.println();
        System.out.println("PROFILE:");
        System.out.println("First Name: " + person.getPersonFirstName());
        System.out.println("Last Name: " + person.getPersonLastName());
        System.out.println("Gender: " + person.getPersonGender());
        System.out.println("Birthday: " + person.getPersonBirthday());
        System.out.println("City: " + person.getCityByPersonCityId().getPlaceName());
    }

    public void getCommonInterestsOfMyFriends(final long personId, Session session)
    {
        Person person = (Person) session.createQuery("FROM Person P WHERE P.personId=" + personId).list().get(0);

        for (Person friend : person.retrieveBiDirFriends())
        {
            System.out.println();
            System.out.println("COMMON INTERESTS:");
            System.out.println("tags: ");
            ApiUtils.getIntersection(person.retrieveTags(), friend.retrieveTags()).forEach(
                e -> System.out.println("   tagID: " + e.getTagId()));
            System.out.println("First Name: " + friend.getPersonFirstName());
            System.out.println("Last Name: " + friend.getPersonLastName());
        }
    }

    public void getCommonFriends(final long thisPersId, final long otherPersId, Session session)
    {
        Person thisPers = (Person) session.createQuery("FROM Person P WHERE P.personId=" + thisPersId).list().get(0);
        Person otherPers = (Person) session.createQuery("FROM Person P WHERE P.personId=" + otherPersId).list().get(0);

        System.out.println("COMMON FRIENDS:");
        ApiUtils.getIntersection(thisPers.retrieveBiDirFriends(), otherPers.retrieveBiDirFriends()).forEach(
            e -> System.out.println("First Name: " + e.getPersonFirstName() + '\n' +
                "Last Name: " + e.getPersonLastName()));
    }

    /**
     * Listens to user input
     */
    public void listenToInput()
    {
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_YELLOW = "\u001B[33m";

        int n=0;
        boolean input = false;

        System.out.println("\n\n" + ANSI_GREEN + "This is the PersonRelatedAPI:" + ANSI_RESET);
        System.out.println("(1) getProfile");
        System.out.println("(2) getCommonInterestsOfMyFriends");
        System.out.println("(3) getPersonsWithMostCommonInterests");
        System.out.println("(4) getJobRecommendation");
        System.out.println("(5) getShorthestFriendshipPath");
        System.out.print("Please choose: ");

        while(!input)
        {
            try
            {
                Scanner sc = new Scanner(System.in);
                n = sc.nextInt();
                if(n >= 1 && n <= 5)
                {
                    input = true;
                }
                else throw new Exception(ANSI_YELLOW + "Number is not in range [1-5]." + ANSI_RESET);
            }catch(Exception ex1)
            {
                System.out.print(ANSI_RED + "Invalid input. Please enter a number!: " + ANSI_RESET);
            }
        }

        Session session = factory.openSession();
        Transaction tx = null;

        try
        {
            tx = session.beginTransaction();
            switch(n)
            {
                case 1: // getProfile
                {
                    // set parameters
                    System.out.print("Please enter a " + ANSI_PURPLE + "person ID" + ANSI_RESET + ": ");
                    Long personId = validateInput();
                    getProfile(personId, session);
                    break;
                }
                case 2: // getCommonInterestsOfMyFriends
                {
                    // set parameters
                    System.out.print("Please enter a " + ANSI_PURPLE + "person ID" + ANSI_RESET + ": ");
                    Long personId = validateInput();
                    getCommonInterestsOfMyFriends(personId, session);
                    break;
                }
                case 3: // getCommonFriends
                {
                    // set parameters
                    System.out.print("Please enter a " + ANSI_PURPLE + "person ID" + ANSI_RESET + " for the FIRST person: ");
                    Long thisPersId = validateInput();
                    System.out.print("Please enter a " + ANSI_PURPLE + "person ID" + ANSI_RESET + " for the SECOND person: ");
                    Long otherPersId = validateInput();
                    getCommonFriends(thisPersId, otherPersId, session);
                    break;
                }
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

    private Long validateInput()
    {
        boolean input = false;
        long n = 0L;

        while(!input)
        {
            try
            {
                Scanner sc = new Scanner(System.in);
                n = sc.nextLong();
                input = true;
            }catch(Exception e)
            {
                System.out.print("Invalid input. Please enter a number!: ");
            }
        }
        return n;
    }

}

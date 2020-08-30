package com.hibernate.application;

import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

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

        while (true)
        {
            prapi.listenToInput();
        }

        /* List down all the employees */
        // 1 - 12094627905604
        // 2 - 5497558138908
        // 3 - 5497558138908 , 12094627905604
        // 4 - 2199023255611
    }

    /* Ausgabe des Profils einer Person, d.h. alle personenbezogenen Informationen (Name, Geschlecht, Wohnort …) */
    private void getProfile(final long personId, Session session)
    {
        Person person = (Person) session.createQuery("FROM Person P WHERE P.personId=" + personId).list().get(0);

        System.out.println("PROFILE:");
        System.out.println("First Name: " + person.getPersonFirstName());
        System.out.println("Last Name: " + person.getPersonLastName());
        System.out.println("Gender: " + person.getPersonGender());
        System.out.println("Birthday: " + person.getPersonBirthday());
        System.out.println("City: " + person.getCityByPersonCityId().getPlaceName());
    }

    private void getCommonInterestsOfMyFriends(final long personId, Session session)
    {
        Person person = (Person) session.createQuery("FROM Person P WHERE P.personId=" + personId).list().get(0);

        for (Person friend : person.retrieveBiDirFriends())
        {
            System.out.println("COMMON INTERESTS:");
            System.out.println("tags: ");
            ApiUtils.getIntersection(person.retrieveTags(), friend.retrieveTags()).forEach(
                e -> System.out.println("   tagID: " + e.getTagId()));
            System.out.println("First Name: " + friend.getPersonFirstName());
            System.out.println("Last Name: " + friend.getPersonLastName());
        }
    }

    private void getCommonFriends(final long thisPersId, final long otherPersId, Session session)
    {
        Person thisPers = (Person) session.createQuery("FROM Person P WHERE P.personId=" + thisPersId).list().get(0);
        Person otherPers = (Person) session.createQuery("FROM Person P WHERE P.personId=" + otherPersId).list().get(0);

        System.out.println("COMMON FRIENDS:");
        ApiUtils.getIntersection(thisPers.retrieveBiDirFriends(), otherPers.retrieveBiDirFriends()).forEach(
            e -> System.out.println("First Name: " + e.getPersonFirstName() + '\n' +
                "Last Name: " + e.getPersonLastName()));
    }

    private void getPersonsWithMostCommonInterests(final long personId, Session session)
    {
        List<Person> allExcept = session.createQuery("FROM Person p WHERE p.personId <> " + personId, Person.class).getResultList();
        Person person = (Person) session.createQuery("FROM Person P WHERE P.personId=" + personId).list().get(0);

        Person persWithMaxInterests = null;
        int maxInterests = 0;
        for (Person otherPers : allExcept)
        {
            int currInterests = ApiUtils.getIntersection(person.retrieveTags(), otherPers.retrieveTags()).size();
            if (currInterests > maxInterests)
            {
                maxInterests = currInterests;
                persWithMaxInterests = otherPers;
            }
        }

        System.out.println("PERSON WITH MOST COMMON INTERESTS:");
        if (persWithMaxInterests != null)
        {
            System.out.println("Person ID: " + persWithMaxInterests.getPersonId());
            System.out.println("First Name: " + persWithMaxInterests.getPersonFirstName());
            System.out.println("Last Name: " + persWithMaxInterests.getPersonLastName());
            System.out.println("Number of common interests: " + maxInterests);
        }
        else
        {
            System.out.println("--> There is no person sharing the same interests.");
        }
    }

    private void getJobRecommendation(final long personId, Session session)
    {
        Person person = (Person) session.createQuery("FROM Person P WHERE P.personId=" + personId).list().get(0);
        List<Person> friends = person.retrieveBiDirFriends();
        if (friends.isEmpty())
        {
            System.out.println("This person has not friends!");
        }
        else
        {
            for (Person friend : friends)
            {
                System.out.println("Uni recommendations:");
                friend.retrieveUniversities().forEach(e -> {
                    if (e.getCityByUniversityCityId() == person.getCityByPersonCityId())
                    {
                        System.out.println("University ID: " + e.getUniversityId());
                        System.out.println("University Name: " + e.getOrganisationName());
                    }
                });

                System.out.println("Job recommendation:");
                if (friend.getCityByPersonCityId() == person.getCityByPersonCityId())
                {
                    friend.retrieveCompanies().forEach(e -> {
                        System.out.println("Company ID: " + e.getCompanyId());
                        System.out.println("Company Name: " + e.getOrganisationName());
                    });
                }
            }
        }
    }

    private void getShorthestFriendshipPath(final long thisPersonId, final long otherPersonId, Session session)
    {
        Query query = session.createSQLQuery("SELECT * FROM pkp_SP()").addEntity(Person.class);
        List<Person> allFoos = query.list();

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

        int n = 0;
        boolean input = false;

        System.out.println("\n\n" + ANSI_GREEN + "This is the PersonRelatedAPI:" + ANSI_RESET);
        System.out.println("(1) getProfile");
        System.out.println("(2) getCommonInterestsOfMyFriends");
        System.out.println("(3) getCommonFriends");
        System.out.println("(4) getPersonsWithMostCommonInterests");
        System.out.println("(5) getJobRecommendation");
        System.out.println("(6) getShorthestFriendshipPath");
        System.out.print("Please choose: ");

        while (!input)
        {
            try
            {
                Scanner sc = new Scanner(System.in);
                n = sc.nextInt();
                if (n >= 1 && n <= 5)
                {
                    input = true;
                }
                else
                {
                    throw new Exception(ANSI_YELLOW + "Number is not in range [1-5]." + ANSI_RESET);
                }
            }
            catch (Exception ex1)
            {
                System.out.print(ANSI_RED + "Invalid input. Please enter a number!: " + ANSI_RESET);
            }
        }

        Session session = factory.openSession();
        Transaction tx = null;

        try
        {
            tx = session.beginTransaction();
            switch (n)
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
                case 4: // getPersonsWithMostCommonInterests
                {
                    // set parameters
                    System.out.print("Please enter a " + ANSI_PURPLE + "person ID" + ANSI_RESET + ": ");
                    Long personId = validateInput();
                    getPersonsWithMostCommonInterests(personId, session);
                    break;
                }
                case 5: // getJobRecommendation
                {
                    // set parameters
                    System.out.print("Please enter a " + ANSI_PURPLE + "person ID" + ANSI_RESET + ": ");
                    Long personId = validateInput();
                    getJobRecommendation(personId, session);
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

        while (!input)
        {
            try
            {
                Scanner sc = new Scanner(System.in);
                n = sc.nextLong();
                input = true;
            }
            catch (Exception e)
            {
                System.out.print("Invalid input. Please enter a number!: ");
            }
        }
        return n;
    }

}

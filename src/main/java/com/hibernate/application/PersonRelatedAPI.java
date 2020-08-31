package com.hibernate.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.stream.Collectors;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hibernate.application.dijkstra.Dijkstra;
import com.hibernate.application.dijkstra.Edge;
import com.hibernate.application.dijkstra.Vertex;
import com.hibernate.pojos.Person;
import com.hibernate.pojos.Tag;


public class PersonRelatedAPI
{
    private final String ANSI_GREEN = "\u001B[32m";
    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_RED = "\u001B[31m";
    private final String ANSI_PURPLE = "\u001B[35m";
    private final String ANSI_YELLOW = "\u001B[33m";

    private static SessionFactory factory;

    public static void main(String[] args)
    {
        LogManager.getLogManager().getLogger("").setLevel(Level.SEVERE);
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
        // 5 - 9895604650036 (company) , 13194139533352 (uni)
        // 6 -
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

        System.out.println("COMMON INTERESTS:");
        for (Person friend : person.retrieveBiDirFriends())
        {
            List<Tag> intersect = ApiUtils.getIntersection(person.retrieveTags(), friend.retrieveTags());
            if (intersect.isEmpty())
            {
                System.out.println("-> No common interests with this friend:");
            }
            else
            {
                System.out.println("tags: ");
                intersect.forEach(
                    e -> System.out.println("   tagID: " + e.getTagId()));
            }
            System.out.println("First Name: " + friend.getPersonFirstName());
            System.out.println("Last Name: " + friend.getPersonLastName());
            System.out.println();
        }
    }

    private void getCommonFriends(final long thisPersId, final long otherPersId, Session session)
    {
        Person thisPers = (Person) session.createQuery("FROM Person P WHERE P.personId=" + thisPersId).list().get(0);
        Person otherPers = (Person) session.createQuery("FROM Person P WHERE P.personId=" + otherPersId).list().get(0);

        System.out.println("COMMON FRIENDS:");
        ApiUtils.getIntersection(thisPers.retrieveBiDirFriends(), otherPers.retrieveBiDirFriends()).forEach(e ->
        {
            System.out.println("ID: " + e.getPersonId());
            System.out.println("First Name: " + e.getPersonFirstName() + '\n' + "Last Name: " + e.getPersonLastName() + "\n");
        });

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
        if (person.retrieveBiDirFriends().isEmpty())
        {
            System.out.println("This person has no friends!");
        }
        else
        {
            if (person.recommendUnis().isEmpty())
            {
                System.out.println("No uni can be recommended under the current constraints!");
            }
            else
            {
                System.out.println("Uni recommendations:");
                System.out.println("Uni name: " + person.recommendUnis().get(0).getOrganisationName());
                System.out.println();
            }
            if (person.recommendCompanies().isEmpty())
            {
                System.out.println("No company/job can be recommended under the current constraints!");
            }
            else
            {
                System.out.println("Company/Job recommendations:");
                System.out.println("Company name: " + person.recommendCompanies().get(0).getOrganisationName());
            }
        }
    }

    private void getShorthestFriendshipPath(final long thisPersonId, final long otherPersonId, Session session)
    {
        // Query query = session.createSQLQuery("CALL pkp_SP()").addEntity(Person.class);
        // List<Person> allPers = query.list();
        List<Person> all = session.createQuery("FROM Person", Person.class).getResultList();

        List<Vertex> vertices = new ArrayList<>();
        for (Person p : all)
        {
            Vertex v = new Vertex(String.valueOf(p.getPersonId()));
            vertices.add(v);
        }
        for (Vertex v : vertices)
        {
            Person myPers = (Person) session.createQuery("FROM Person P WHERE P.personId=" + v.name).list().get(0);
            List<Edge> myEdges = new ArrayList<>();

            myPers.retrieveBiDirFriends().forEach(e ->
            {
                myEdges.add(new Edge(vertices.stream().filter(a -> a.name.equals(String.valueOf(e.getPersonId()))).collect(Collectors.toList()).get(0),
                    1));
            });

            Edge[] edges = new Edge[myEdges.size()];
            v.adjacencies = myEdges.toArray(edges);
        }
        Dijkstra.computePaths(vertices.stream().filter(a -> a.name.equals(String.valueOf(thisPersonId))).collect(Collectors.toList()).get(0)); // run Dijkstra
        Vertex targetVertex = vertices.stream().filter(a -> a.name.equals(String.valueOf(otherPersonId))).collect(Collectors.toList()).get(0);
        System.out.println("Distance to " + targetVertex + ": " + targetVertex.minDistance);
        List<Vertex> path = Dijkstra.getShortestPathTo(targetVertex);
        System.out.println("Path: " + path);
    }

    /**
     * Listens to user input
     */
    public void listenToInput()
    {
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
                if (n >= 1 && n <= 6)
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
                    Long personId = validateInput(session);
                    getProfile(personId, session);
                    break;
                }
                case 2: // getCommonInterestsOfMyFriends
                {
                    // set parameters
                    System.out.print("Please enter a " + ANSI_PURPLE + "person ID" + ANSI_RESET + ": ");
                    Long personId = validateInput(session);
                    getCommonInterestsOfMyFriends(personId, session);
                    break;
                }
                case 3: // getCommonFriends
                {
                    // set parameters
                    System.out.print("Please enter a " + ANSI_PURPLE + "person ID" + ANSI_RESET + " for the FIRST person: ");
                    Long thisPersId = validateInput(session);
                    System.out.print("Please enter a " + ANSI_PURPLE + "person ID" + ANSI_RESET + " for the SECOND person: ");
                    Long otherPersId = validateInput(session);
                    getCommonFriends(thisPersId, otherPersId, session);
                    break;
                }
                case 4: // getPersonsWithMostCommonInterests
                {
                    // set parameters
                    System.out.print("Please enter a " + ANSI_PURPLE + "person ID" + ANSI_RESET + ": ");
                    Long personId = validateInput(session);
                    getPersonsWithMostCommonInterests(personId, session);
                    break;
                }
                case 5: // getJobRecommendation
                {
                    // set parameters
                    System.out.print("Please enter a " + ANSI_PURPLE + "person ID" + ANSI_RESET + ": ");
                    Long personId = validateInput(session);
                    getJobRecommendation(personId, session);
                    break;
                }
                case 6: // getShorthestFriendshipPath
                {
                    // set parameters
                    System.out.print("Please enter a " + ANSI_PURPLE + "person ID" + ANSI_RESET + " for the FIRST person: ");
                    Long thisPersId = validateInput(session);
                    System.out.print("Please enter a " + ANSI_PURPLE + "person ID" + ANSI_RESET + " for the SECOND person: ");
                    Long otherPersId = validateInput(session);
                    getShorthestFriendshipPath(thisPersId, otherPersId, session);
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

    private Long validateInput(Session session)
    {
        boolean input = false;
        long n = 0L;

        while (!input)
        {
            try
            {
                Scanner sc = new Scanner(System.in);
                n = sc.nextLong();
                session.createQuery("FROM Person P WHERE P.personId=" + n).list().get(0); // force IndexOutOfBoundsException to fail here!
                input = true;
            }
            catch (IndexOutOfBoundsException iob)
            {
                System.out.println(ANSI_RED + "This person does not exist in this database!" + ANSI_RESET);
                System.out.println(ANSI_RED + "Please enter a valid ID!: " + ANSI_RESET);
            }
            catch (Exception e)
            {
                System.out.print(ANSI_RED + "Invalid input. Please enter a number!: " + ANSI_RESET);
            }
        }
        return n;
    }

}

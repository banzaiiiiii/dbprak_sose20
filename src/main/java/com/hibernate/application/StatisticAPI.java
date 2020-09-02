package com.hibernate.application;

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

import com.hibernate.pojos.Comment;
import com.hibernate.pojos.Country;
import com.hibernate.pojos.TagClass;


public class StatisticAPI
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

        StatisticAPI stapi = new StatisticAPI();
        while (true)
        {
            stapi.listenToInput();
        }
        // 2.: e.g. set k = 20
    }

    public void getTagClassHierarchy(Session session)
    {
        List<TagClass> classes = session.createQuery("FROM TagClass", TagClass.class).getResultList();
        TagClass rootClass = classes.get(0).retrieveRoot(); // get any TagClass
        String taxoHead = "0";
        System.out.println(taxoHead + " " + rootClass.getTagClassName());
        rootClass.printTaxonomy(taxoHead);
    }

    public void getPopularComments(int k, Session session)
    {
        List<Comment> filteredComments = session.createQuery("FROM Comment", Comment.class)
            .getResultList().stream()
            .filter(s -> s.countLikes() > k)
            .collect(Collectors.toList());
        System.out.println("Comments with more than " + k + " likes:");
        filteredComments.forEach(e -> {
            System.out.println();
            System.out.println(e.getCommentId());
            System.out.println(e.getMessageByCommentMessageId().getPersonByMessagePersonId().getPersonFirstName());
            System.out.println(e.getMessageByCommentMessageId().getPersonByMessagePersonId().getPersonLastName());
        });
    }

    public void getMostPostingCountry(Session session)
    {
        List<Country> countries = session.createQuery("FROM Country ", Country.class).getResultList();

        int mostMessages = 0;
        Country countryWithMostMessages = null;
        for (Country country : countries)
        {
            int currMessages = country.getMessagesByCountryId().size();
            if (currMessages > mostMessages)
            {
                mostMessages = currMessages;
                countryWithMostMessages = country;
            }
        }

        if (countryWithMostMessages == null)
        {
            System.out.println("There is no country with messages at all!");
        }
        else
        {
            System.out.println("Country with most posts AND comments:");
            System.out.println(countryWithMostMessages.getPlaceName());
        }
    }

    /**
     * Listens to user input
     */
    public void listenToInput()
    {
        int n = 0;
        boolean input = false;

        System.out.println("\n\n" + ANSI_GREEN + "This is the StatisticAPI:" + ANSI_RESET);
        System.out.println("(1) getTagClassHierarchy");
        System.out.println("(2) getPopularComments");
        System.out.println("(3) getMostPostingCountry");
        System.out.print("Please choose: ");

        while (!input)
        {
            try
            {
                Scanner sc = new Scanner(System.in);
                n = sc.nextInt();
                if (n >= 1 && n <= 3)
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
                case 1: // getTagClassHierarchy
                {
                    getTagClassHierarchy(session);
                    break;
                }
                case 2: // getPopularComments
                {
                    // set parameters
                    System.out.print("Please enter a " + ANSI_PURPLE + "threshold value" + ANSI_RESET + ": ");
                    int k = validateInput();
                    getPopularComments(k, session);
                    break;
                }
                case 3: // getMostPostingCountry
                {
                    getMostPostingCountry(session);
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

    private int validateInput()
    {
        boolean input = false;
        int n = 0;

        while (!input)
        {
            try
            {
                Scanner sc = new Scanner(System.in);
                n = sc.nextInt();
                input = true;
            }
            catch (Exception e)
            {
                System.out.print(ANSI_RED + "Invalid input. Please enter a number!: " + ANSI_RESET);
            }
        }
        return n;
    }

}

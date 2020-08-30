package com.hibernate.application;

import java.util.List;
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

        StatisticAPI stapi = new StatisticAPI();
        stapi.getTagClassHierarchy();
        stapi.getPopularComments(20);
        stapi.getMostPostingCountry();

    }

    public void getTagClassHierarchy()
    {
        Session session = factory.openSession();
        Transaction tx = null;

        try
        {
            tx = session.beginTransaction();

            List<TagClass> classes = session.createQuery("FROM TagClass", TagClass.class).getResultList();
            TagClass rootClass = classes.get(0).retrieveRoot(); // get any TagClass
            String taxoHead = "0";
            System.out.println(taxoHead + " " + rootClass.getTagClassName());
            rootClass.printTaxonomy(taxoHead);

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

    public void getPopularComments(int k)
    {
        Session session = factory.openSession();
        Transaction tx = null;

        try
        {
            tx = session.beginTransaction();

            List<Comment> filteredComments = session.createQuery("FROM Comment", Comment.class)
                .getResultList().stream()
                .filter(s -> s.countLikes() > k)
                .collect(Collectors.toList());
            System.out.println("Number of Comments with more than " + k + " likes:");
            System.out.println(filteredComments.size());

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

    public void getMostPostingCountry()
    {
        Session session = factory.openSession();
        Transaction tx = null;

        try
        {
            tx = session.beginTransaction();

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

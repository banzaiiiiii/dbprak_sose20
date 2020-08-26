package com.dbfiller;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/socialnetwork",
                    "postgres",
                    "1234"
            );
            Filler filler = new Filler(con);

            boolean fillDB = true;

            if (fillDB) {
                System.out.println("addPlaces...");
                filler.addPlaces();

                System.out.println("addOrganisations...");
                filler.addOrganisations();

                System.out.println("addTag...");
                filler.addTag();
                System.out.println("addTagClass...");
                filler.addTagClass();
                System.out.println("addTagClassIsSubclassOfTagClass...");
                filler.addTagClassIsSubclassOf();
                System.out.println("addHasType...");
                filler.addHasType();

                System.out.println("addPerson...");
                filler.addPerson(); // speaks!
                System.out.println("addSpeaks");
                filler.addSpeaks();
                System.out.println("addEmail...");
                filler.addEmail(); // List!
                System.out.println("addKnows...");
                filler.addKnows();
                System.out.println("addStudyAt...");
                filler.addStudyAt();
                System.out.println("addWorkAt...");
                filler.addWorkAt();
                System.out.println("addHasInterest...");
                filler.addHasInterest();

                System.out.println("addForum...");
                filler.addForum();
                System.out.println("addHasMember...");
                filler.addHasMember();
                System.out.println("addForumHasTag...");
                filler.addForumHasTag();

                System.out.println("addMessagePost...");
                filler.addMessagePost();
                System.out.println("addMessageComment...");
                filler.addMessageComment();
                System.out.println("addHasTagPost...");
                filler.addHasTagPost();
                System.out.println("addHasTagComment...");
                filler.addHasTagComment();
                System.out.println("addLikesPost...");
                filler.addLikesPost();
                System.out.println("addLikesComment...");
                filler.addLikesComment();
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

        System.out.println("finished");
    }

}

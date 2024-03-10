package fel.cvut.cz;

import fel.cvut.cz.serviceDAO.TaskService;
import jakarta.persistence.*;

import java.sql.*;

public class Main {


    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Object o = new Object();
        // create Connection
        Connection c = null;
        // create Entity Manager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        TaskService t = new TaskService(em);
        try {
            // choose Driver
            Class.forName("org.postgresql.Driver");
            // create database connection
            c = DriverManager.getConnection(
                    "jdbc:postgresql://slon.felk.cvut.cz:5432/hollepat",
                    "hollepat",
                    "hollepat_DBS2023"
            );
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load the PostgresSQL JDBC driver");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database");
            e.printStackTrace();
        } finally {
            // Close the database connection
            if (c != null) {
                try {
                    // execute transaction
                    transaction.begin();

                    //t.createPersons();
                    //t.createMembership();
                    t.readWorkoutClass();
                    //t.myTransaction();

                    transaction.commit();
                    c.close();
                } catch (SQLException e) {
                    System.out.println("Failed to close the database connection");
                    e.printStackTrace();
                } finally {
                    if (transaction.isActive()) {
                        transaction.rollback();
                    }
                }
            }
        }
        em.close();
        emf.close();

    }
}

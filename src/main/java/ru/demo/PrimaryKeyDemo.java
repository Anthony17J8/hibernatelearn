package ru.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.entity.Student;

public class PrimaryKeyDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();
        try {
            // create three student objects
            System.out.println("Creating three student objects...");
            Student tempStudent1 = new Student("Bruce", "Lee", "lee@google.com");
            Student tempStudent2 = new Student("Bruce", "Willis", "bruce@google.com");
            Student tempStudent3 = new Student("Jack", "Rambo", "jack@google.com");

            // start a transaction
            session.beginTransaction();

            // save the student objects
            System.out.println("Saving the students...");
            session.save(tempStudent1);
            session.save(tempStudent2);
            session.save(tempStudent3);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            factory.close();
        }
    }
}

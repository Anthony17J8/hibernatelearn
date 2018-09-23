package ru.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.entity.Student;

public class CreateStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();
        try {
            // create a student object
            System.out.println("Creating a student object...");
            Student tempStudent = new Student("Mark", "Twein", "mark@google.com");

            // start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving the student...");
            session.save(tempStudent);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            factory.close();
        }

    }
}

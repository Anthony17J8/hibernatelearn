package ru.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.demo.util.DateUtils;
import ru.entity.Student;

import java.text.ParseException;
import java.util.Date;

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
            String strDate = "5/06/2018";
            Date dateOfBirth = DateUtils.parse(strDate);

            // create a student object
            System.out.println("Creating a student object...");
            Student tempStudent = new Student("Mark", "Twein", "mark@google.com", dateOfBirth);

            // start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving the student...");
            session.save(tempStudent);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }
}

package ru.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.entity.Student;

import java.util.List;

public class QueryStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();
        try {

            // start a transaction
            session.beginTransaction();

            // query students
            List<Student> theStudents = session.createQuery("FROM Student", Student.class).getResultList();

            // display the students
            displayStudents(theStudents);

            // query students: lastName = "Rambo"
            theStudents = session.createQuery("FROM Student s WHERE s.lastName='Rambo'", Student.class).getResultList();

            // display the students
            System.out.println("\n\nStudents who have last name of Rambo");
            displayStudents(theStudents);

            // query students: lastName = "Anniston" or firstname = "Bruce"
            theStudents = session.createQuery("FROM Student s WHERE"
                    + " s.lastName='Aniston' OR s.firstName='Bruce'", Student.class).getResultList();

            // display the students
            System.out.println("\n\nStudents who have last name of Aniston or firstName of Bruce");
            displayStudents(theStudents);

            // query student where email LIKE '%google.com'
            theStudents = session.createQuery("FROM Student s WHERE" +
                    " s.email LIKE '%google.com'", Student.class).getResultList();

            System.out.println("\n\nStudent whose email ends with google.com");
            displayStudents(theStudents);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            factory.close();
        }
    }

    private static void displayStudents(List<Student> theStudents) {
        for (Student tempStudent : theStudents) {
            System.out.println(tempStudent);
        }
    }
}

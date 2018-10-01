package ru.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.entity.Student;

public class UpdateStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();
        try {

            int studentId = 1;
            // create a new session and start transaction
            session.beginTransaction();
            // retrieve the student object based on the id: primary key
            System.out.println("\nGetting student with id: " + studentId);

            Student myStudent = session.get(Student.class, studentId);

            System.out.println("Updating student...");
            myStudent.setFirstName("Jessica");

            //commit the transaction
            session.getTransaction().commit();

            // NEW CODE
            session = factory.getCurrentSession();
            session.beginTransaction();

            // update email for all students
            System.out.println("Update email for all students");

            session.createQuery("UPDATE Student SET email='hibernate@gmail.com'")
                    .executeUpdate();

            //commit the transaction
            session.getTransaction().commit();

        } finally {
            factory.close();
        }
    }
}

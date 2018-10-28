package hb_05_many_to_many;

import hb_05_many_to_many.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateMoreCoursesForStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();
        try {

            // start a transaction
            session.beginTransaction();

            // get the student from database
            int studentId = 2;
            Student theStudent = session.get(Student.class, studentId);
            System.out.println("\nStudent loaded: " + theStudent);
            System.out.println("\nStudent's courses: " + theStudent.getCourses());

            // create more courses
            Course theCourse1 = new Course("Rubik's Cube - How to speed Cube");
            Course theCourse2 = new Course("Atari 2600 - Game Development");

            // add student to those courses
            theCourse1.addStudent(theStudent);
            theCourse2.addStudent(theStudent);
            System.out.println("Saving the courses...");

            // save the courses
            session.save(theCourse1);
            session.save(theCourse2);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } finally {
            // add clean up code
            session.close();
            factory.close();
        }
    }
}

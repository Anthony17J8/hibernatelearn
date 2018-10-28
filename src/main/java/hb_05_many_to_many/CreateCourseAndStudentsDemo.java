package hb_05_many_to_many;

import hb_05_many_to_many.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentsDemo {

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

            // create a course
            Course tempCourse = new Course("Pacman - How to score 1 Million points");

            // save the course
            System.out.println("\nSaving course...");
            session.save(tempCourse);
            System.out.println("Saved the course: " + tempCourse);

            // create students
            Student student1 = new Student("Mark", "Avenue", "mark@g2.com");
            Student student2 = new Student("Simona", "Sanchez", "sanch@g2.com");

            // add student to the course
            tempCourse.addStudent(student1);
            tempCourse.addStudent(student2);

            // save the students
            System.out.println("\nSaving students ...");
            session.save(student1);
            session.save(student2);
            System.out.println("Saved students: " + tempCourse.getStudents());

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

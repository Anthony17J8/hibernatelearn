package hb_03_one_to_many;

import hb_03_one_to_many.entity.Course;
import hb_03_one_to_many.entity.Instructor;
import hb_03_one_to_many.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();
        try {

            // create the objects
            Instructor tempInstructor = new Instructor("Anna", "Gucci", "ann.gucci@gmail.com");

            InstructorDetail tempInstructorDetail = new InstructorDetail(
                    "http://www.leelearnhibernate.com/youtube.com", "Video games");

            // associate the objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            // start a transaction
            session.beginTransaction();

            // save the instructor
            //
            // Note: this will ALSO save the detail objects
            // because of CascadeType.ALL
            System.out.println("Saving instructor: " + tempInstructor);
            session.save(tempInstructor);

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

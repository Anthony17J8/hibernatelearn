package hb_lazy_vs_eager_demo;

import hb_03_one_to_many.entity.Course;
import hb_03_one_to_many.entity.Instructor;
import hb_03_one_to_many.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FetchJoinDemo {

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

            // start a transaction
            session.beginTransaction();

            //Hibernate query with HQL

            // get the instructor from the db
            int theId = 1;

            Query<Instructor> query =
                    session.createQuery("select i from Instructor i " +
                                    "JOIN FETCH i.courses " +
                                    "WHERE i.id=:theInstructorId",
                            Instructor.class);

            // set parameter on query
            query.setParameter("theInstructorId", theId);

            // execute query and get instructor
            Instructor tempInstructor = query.getSingleResult();

            System.out.println("Lazy: Instructor: " + tempInstructor);


            // commit transaction
            session.getTransaction().commit();

            // close the session
            session.close();

            System.out.println("\nThe session is now closed!\n");

            // get course for the instructor
            System.out.println("Lazy: Courses: " + tempInstructor.getCourses());

            System.out.println("Lazy: Done!");

        } finally {
            // add clean up code
            session.close();
            factory.close();
        }
    }
}

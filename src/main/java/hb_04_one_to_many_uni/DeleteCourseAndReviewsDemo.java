package hb_04_one_to_many_uni;


import hb_04_one_to_many_uni.entity.Course;
import hb_04_one_to_many_uni.entity.Instructor;
import hb_04_one_to_many_uni.entity.InstructorDetail;
import hb_04_one_to_many_uni.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseAndReviewsDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();
        try {

            // start a transaction
            session.beginTransaction();

            // get the course
            int theId = 10;
            Course theCourse = session.get(Course.class, theId);

            // print the course
            System.out.println("Deleting the course ... ");
            System.out.println("Course: " + theCourse);

            // print the reviews
            System.out.println("Reviews: " + theCourse.getReviews());

            // delete the course
            session.delete(theCourse);

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

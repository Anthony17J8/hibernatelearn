package hb_04_one_to_many_uni;


import hb_04_one_to_many_uni.entity.Course;
import hb_04_one_to_many_uni.entity.Instructor;
import hb_04_one_to_many_uni.entity.InstructorDetail;
import hb_04_one_to_many_uni.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndReviewsDemo {

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

            // create a course
            Course tempCourse = new Course("Pacman - How to score 1 Million points");

            // add some reviews
            tempCourse.addReview(new Review("Great a course!"));
            tempCourse.addReview(new Review("WOW! It's fantastic!"));
            tempCourse.addReview(new Review("Cool! I did it!"));

            // save the course ... (saving reviews cascade all)
            System.out.println("Saving course...");
            System.out.println("Course: " + tempCourse);
            System.out.println("Reviews:" + tempCourse.getReviews());
            session.save(tempCourse);

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

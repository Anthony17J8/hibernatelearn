package ru.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class PracticeDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            List<Employee> employees = new ArrayList<>();
            fillEmployeesList(employees);

            // save to database
            for (Employee e : employees) {
                session.save(e);
            }

            // retrieve an object by primary key
            int id = 3;
            System.out.println("Getting objects with id=3...");
            Employee myEmployee = session.get(Employee.class, id);
            System.out.println("Employee with id=3: " + myEmployee);

            // retrieve objects by company
            System.out.println("\nEmployees at a company 'Apple'");
            List<Employee> theEmployees = session.createQuery("FROM Employee e WHERE e.company='Apple'", Employee.class).getResultList();
            displayEmployees(theEmployees);

            // delete the object by primary key
            System.out.println("\nDeleting oject with id=4");
            session.createQuery("DELETE FROM Employee e WHERE e.id=4").executeUpdate();

            session.getTransaction().commit();

        } finally {
            factory.close();
        }
    }

    private static void displayEmployees(List<Employee> employees) {
        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    private static void fillEmployeesList(List<Employee> employees) {
        employees.add(new Employee("Monica", "Belucci", "Apple"));
        employees.add(new Employee("Jim", "Kerry", "Amazon"));
        employees.add(new Employee("Frank", "Lampard", "Chelsea"));
        employees.add(new Employee("Jessica", "Parker", "Apple"));
    }
}

package app;

import domain.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain")
public class Application implements CommandLineRunner {
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    BookRepository bookRepository;
    @Autowired
    PublisherRepository publisherRepository;

    @Autowired
    PassengerRepository passengerRepository;
    @Autowired
    FlightRepository flightRepository;

    @Autowired
    SchoolRepository schoolRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        //a) Create a Bidirectional OneToMany association between Department and Employee.
        Department department = new Department();
        department.setName("Engineering");
        System.out.println("------Department------");
        System.out.println(department);

        Employee emp1 = new Employee("Alice");
        Employee emp2 = new Employee("Bob");
        System.out.println("------Employee-----");
        System.out.println(emp1);
        System.out.println(emp2);

        System.out.println("------Department after setting employees----");
        System.out.println(department);
        department.addEmployee(emp1);
        department.addEmployee(emp2);
        System.out.println("------Department after adding employees-----");
        printDepartment(department);

        departmentRepository.save(department);
        System.out.println("------Department after persisting-----------");
        printDepartment(department);

        //b) Create an Optional Unidirectional ManyToOne association between Book and Publisher without using NULL fields in the database
        Publisher publisher = new Publisher();
        publisher.setName("O'Reilly");
        publisherRepository.save(publisher);

        Book book = new Book("ISBN123", "Spring In Action", "Craig Walls");
        book.setPublisher(publisher);
        bookRepository.save(book);
        System.out.println("Publisher ID before saving book: " + publisher.getId());
        System.out.println("-----------Book-------------");
        System.out.println(book);

        //c) Create a Unidirectional OneToMany association between Passenger and Flight using a List
        Flight f1 = new Flight("FL001", "New York", "Chicago", LocalDate.of(2025, 1, 1));
        Flight f2 = new Flight("FL002", "Chicago", "Los Angeles", LocalDate.of(2025, 1, 2));

        Passenger passenger = new Passenger("Tom Cruise");
        passenger.getFlights().addAll(List.of(f1, f2));

        passengerRepository.save(passenger);

        System.out.println("Passenger with flights saved.");
        System.out.println("---------Passenger and Flights---------");
        System.out.println(passenger);

        //d) Create a Unidirectional OneToMany association between School and Student using a Map
        Student s1 = new Student(1001L, "Alice", "Smith");
        Student s2 = new Student(1002L, "Bob", "Johnson");

        School school = new School("MIU");
        school.getStudents().put(s1.getStudentid(), s1);
        school.getStudents().put(s2.getStudentid(), s2);

        schoolRepository.save(school);
        System.out.println("School and students saved.");

        System.out.println("--------School and Student-------");
        System.out.println(school);
    }

    public static void printDepartment (Department department) {
        System.out.println("Department id: " + department.getId() + " name: " + department.getName());
        System.out.println("------Employee List-----------");
        Collection<Employee> employees = department.getEmployees();
        for (Employee e: employees) {
            System.out.println(e);
        }
    }
}

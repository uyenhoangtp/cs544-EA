package domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
public class Department {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    @OneToMany (mappedBy = "department", cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "department_id")
    private Collection<Employee> employees = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Employee> getEmployees() {
        return Collections.unmodifiableCollection(employees);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean addEmployee(Employee employee) {
        boolean added = employees.add(employee);
        if (added) {
            employee.setDepartment(this);
        }
        return added;
    }

    public boolean removeEmployee(Employee employee) {
        boolean removed = employees.remove(employee);
        if (removed) {
            employees.remove(employee);
        }
        return removed;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employees=" + employees +
                '}';
    }
}

package domain;

import jakarta.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private int employeenumber;
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Employee(String name) {
        this.name = name;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeenumber=" + employeenumber +
                ", name='" + name + '\'' +
                '}';
    }
}

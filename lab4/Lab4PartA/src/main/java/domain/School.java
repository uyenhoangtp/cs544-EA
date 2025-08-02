package domain;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

@Entity
public class School {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "school_id")
    @MapKey(name = "studentid")
    private Map<Long, Student> students = new HashMap<>();

    public School() {}

    public School(String name) {
        this.name = name;
    }

    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Map<Long, Student> getStudents() { return students; }
    public void setStudents(Map<Long, Student> students) { this.students = students; }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", students=" + students +
                '}';
    }
}

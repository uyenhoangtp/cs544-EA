package repositories;

import domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
    // Method name queries
    List<Student> findByDepartmentName(String departmentName);
    List<Student> findByGradesCourseName(String courseName);

    // @Query style
    @Query("SELECT s FROM Student s WHERE s.department.name = :deptName")
    List<Student> findStudentsByDepartment(@Param("deptName") String deptName);

    @Query("SELECT s FROM Student s JOIN FETCH s.grades g WHERE g.course.name = :courseName")
    List<Student> findStudentsByCourseName(@Param("courseName") String courseName);

}

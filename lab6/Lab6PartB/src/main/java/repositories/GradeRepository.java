package repositories;

import domain.Grade;
import domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long>, JpaSpecificationExecutor<Student> {
//    @Query("SELECT g.student FROM Grade g WHERE g.course.name = :courseName")
//    List<Student> findStudentsByCourseName(@Param("courseName") String courseName);
}

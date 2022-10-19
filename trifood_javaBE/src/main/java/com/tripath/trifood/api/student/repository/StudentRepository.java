package com.tripath.trifood.api.student.repository;

import com.tripath.trifood.api.student.dto.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);

    @Query(value = "SELECT * FROM students WHERE id =?", nativeQuery = true)
    Student findBysId(Long id);

    @Query(value = "SELECT id FROM students", nativeQuery = true)
    List<Long> findAllStudentId();
}

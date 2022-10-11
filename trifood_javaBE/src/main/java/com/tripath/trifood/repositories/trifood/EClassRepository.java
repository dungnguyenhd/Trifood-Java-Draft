package com.tripath.trifood.repositories.trifood;

import com.tripath.trifood.entities.EClass;
import com.tripath.trifood.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EClassRepository extends JpaRepository<EClass, Integer> {
    @Query(value ="SELECT * FROM eating_classes c WHERE c.e_class_name LIKE %?1%",nativeQuery = true)
    Page<EClass> searchByName(String search, Pageable pageable);

    @Query(value = "SELECT * FROM eating_classes WHERE e_group_id = ?", nativeQuery = true)
    Page<EClass> findClassesOfGroup(Integer groupId, Pageable pageable);

    @Query(value = "SELECT * FROM students WHERE e_class_e_class_id = ?", nativeQuery = true)
    List<Student> findStudentsOfClass(Integer classId);

    @Query(value = "SELECT * FROM eating_classes WHERE e_class_level = ? OR e_class_grade = ? OR e_class_name = ? OR (e_class_start_year = ? AND e_class_end_year = ?)", nativeQuery = true)
    Page<EClass> sortClass(String classLevel, String classGrade, String className, Integer startYear, Integer endYear, Pageable pageable);
}

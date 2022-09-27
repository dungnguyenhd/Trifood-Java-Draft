package com.tripath.trifood.repositories;

import com.tripath.trifood.models.EClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EClassRepository extends JpaRepository<EClass, Integer> {
    @Query(value ="SELECT * FROM eating_classes c WHERE c.e_class_name LIKE %?1%",nativeQuery = true)
    public List<EClass> searchByName(String search);
}

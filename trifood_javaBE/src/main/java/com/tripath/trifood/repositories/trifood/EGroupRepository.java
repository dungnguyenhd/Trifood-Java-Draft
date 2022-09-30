package com.tripath.trifood.repositories.trifood;

import com.tripath.trifood.entities.EGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EGroupRepository extends JpaRepository<EGroup, Integer> {
    @Query(value ="SELECT * FROM eating_groups g WHERE g.e_group_name LIKE %?1%",nativeQuery = true)
    public List<EGroup> searchByName(String search);
}

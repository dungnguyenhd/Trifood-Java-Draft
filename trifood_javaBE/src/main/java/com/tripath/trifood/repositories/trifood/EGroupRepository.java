package com.tripath.trifood.repositories.trifood;

import com.tripath.trifood.entities.EGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EGroupRepository extends JpaRepository<EGroup, Integer> {
    @Query(value ="SELECT * FROM eating_groups g WHERE g.e_group_name LIKE %?1%",nativeQuery = true)
    Page<EGroup> searchByName(String search, Pageable pageable);
}

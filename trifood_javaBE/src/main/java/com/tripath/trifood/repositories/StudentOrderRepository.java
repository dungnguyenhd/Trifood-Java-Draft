package com.tripath.trifood.repositories;

import com.tripath.trifood.models.StudentOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentOrderRepository extends JpaRepository<StudentOrder, Long> {
}

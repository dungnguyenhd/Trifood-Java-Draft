package com.tripath.trifood.repositories;

import com.tripath.trifood.models.StudentPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentPaymentRepository extends JpaRepository<StudentPayment, Integer> {
}

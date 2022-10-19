package com.tripath.trifood.repositories.trifood;

import com.tripath.trifood.api.trifood.services.service.PaymentManagerService;
import com.tripath.trifood.entities.StudentPayment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentPaymentRepo extends JpaRepository<StudentPayment, Long> {
    @Query(value = "SELECT SUM(food_price) FROM ((e_daily_schedule d INNER JOIN meal m ON d.e_daily_id = m.e_daily_id) INNER JOIN food f ON m.food_id = f.food_id) WHERE  d.e_weekly_id = ?", nativeQuery = true)
    Integer countWeeklySchedulePayment(Long eWeeklyId);

    @Query(value = "SELECT SUM(food_price) FROM ((e_daily_schedule d INNER JOIN meal m ON d.e_daily_id = m.e_daily_id) INNER JOIN food f ON m.food_id = f.food_id) WHERE  m.id = ?",nativeQuery = true)
    Integer countOrderMinusPayment(Long mealId);

    @Query(value = "SELECT student_fullname as fullName, total_money as TotalMoney, is_paid as IsPaid FROM ((student_payments p INNER JOIN students s ON p.student_id = s.id) INNER JOIN e_classes c ON s.e_class_e_class_id = c.e_class_id) " + "WHERE pay_month = ?", nativeQuery = true)
    Page<PaymentManagerService> getAllPayment(Integer payMonth, Pageable pageable);

    @Query(value = "SELECT student_fullname as fullName, total_money as TotalMoney, is_paid as IsPaid FROM ((student_payments p INNER JOIN students s ON p.student_id = s.id) INNER JOIN e_classes c ON s.e_class_e_class_id = c.e_class_id) " + "WHERE pay_month = ? AND c.e_class_level = ? OR c.e_class_grade = ? OR c.e_class_name = ?", nativeQuery = true)
    Page<PaymentManagerService> sortPayment(Integer payMonth, String classLevel, String classGrade, String className, Pageable pageable);

    @Query(value = "SELECT student_fullname as fullName, total_money as TotalMoney, is_paid as IsPaid FROM ((student_payments p INNER JOIN students s ON p.student_id = s.id) INNER JOIN e_classes c ON s.e_class_e_class_id = c.e_class_id) WHERE pay_month = ? AND s.student_fullname = ?", nativeQuery = true)
    Page<PaymentManagerService> searchPaymentByStudentName(Integer payMonth, String studentName, Pageable pageable);
}

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
    @Query(value = "SELECT SUM(food_price) FROM (students h INNER JOIN (e_classes c INNER JOIN (e_groups g INNER JOIN (week_schedule w INNER JOIN(( meal m INNER JOIN assign_schedule s ON m.meal_id = s.meal_id)  INNER JOIN food f ON m.food_id = f.food_id) ON w.week_id = s.week_id) ON g.e_group_id = w.e_group_id) ON c.e_group_id = g.e_group_id) ON h.e_class_id = c.e_class_id) WHERE h.id = ? AND week_month = ? AND week_year = ? GROUP BY h.id", nativeQuery = true)
    Integer getStudentMothlyPayment(Long studentId, Integer weekMonth, Integer weekYear);

    @Query(value = "SELECT student_fullname as fullName, total_money as TotalMoney, is_paid as IsPaid FROM ((student_payments p INNER JOIN students s ON p.student_id = s.id) INNER JOIN e_classes c ON s.e_class_id = c.e_class_id) " + "WHERE pay_month = ?", nativeQuery = true)
    Page<PaymentManagerService> getAllPayment(Integer payMonth, Pageable pageable);

    @Query(value = "SELECT student_fullname as fullName, total_money as TotalMoney, is_paid as IsPaid FROM ((student_payments p INNER JOIN students s ON p.student_id = s.id) INNER JOIN e_classes c ON s.e_class_id = c.e_class_id) " + "WHERE pay_month = ? AND c.e_class_level = ? OR c.e_class_grade = ? OR c.e_class_name = ? OR s.student_fullname LIKE %?%", nativeQuery = true)
    Page<PaymentManagerService> sortPayment(Integer payMonth, String classLevel, String classGrade, String className, String studentName , Pageable pageable);

    @Query(value = "SELECT COUNT(m.food_id) as Amount FROM (students h INNER JOIN (e_classes c INNER JOIN (e_groups g INNER JOIN (week_schedule w INNER JOIN(( meal m INNER JOIN assign_schedule s ON m.meal_id = s.meal_id)  INNER JOIN food f ON m.food_id = f.food_id) ON w.week_id = s.week_id) ON g.e_group_id = w.e_group_id) ON c.e_group_id = g.e_group_id) ON h.e_class_id = c.e_class_id) WHERE h.id = ? AND week_month = ? AND week_year = ? GROUP BY h.id", nativeQuery = true)
    Integer countMonthlyTotalFood(Long studentId, Integer weekMonth, Integer weekYear);
}

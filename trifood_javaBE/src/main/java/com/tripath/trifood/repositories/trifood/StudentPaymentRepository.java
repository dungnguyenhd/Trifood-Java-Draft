package com.tripath.trifood.repositories.trifood;

import com.tripath.trifood.api.trifood.services.service.PaymentManagerService;
import com.tripath.trifood.entities.StudentPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentPaymentRepository extends JpaRepository<StudentPayment, Long> {
    @Query(value = "SELECT SUM(minus_payment) FROM student_orders WHERE (order_date BETWEEN ? AND ?) AND student_id = ?", nativeQuery = true)
    Integer getMonthlyMinusPayment(String startDate, String endDate, Integer studentId);

    @Query(value = "SELECT group_schedule_id FROM student_orders WHERE student_id = ?", nativeQuery = true)
    Integer findStudentScheduleId(Integer studentId);

    @Query(value = "SELECT COUNT(id) FROM meal WHERE (meal_date BETWEEN ? AND ?) AND e_group_schedule_id = ?", nativeQuery = true)
    Integer countGroupScheduleMeal(String startDate, String endDate, Integer groupScheduleId);

    @Query(value = "SELECT COUNT(id)\n" +
            "FROM (meal m INNER JOIN student_orders o ON m.e_group_schedule_id = o.group_schedule_id) WHERE m.meal_name = o.register_meal\n" +
            "AND m.meal_date = o.order_date AND o.student_id = ?", nativeQuery = true)
    Integer countStudentDeleteMeal(Integer studentId);

    @Query(value = "SELECT student_fullname as StudenName, total_money as TotalMoney, is_paid as IsPaid FROM ((student_payments p INNER JOIN students s ON p.student_id = s.id) INNER JOIN eating_classes c ON s.e_class_e_class_id = c.e_class_id) " +
            "WHERE (p.pay_date BETWEEN ? AND ?)", nativeQuery = true)
    List<PaymentManagerService> getAllPayment(String startDate, String endDate);

    @Query(value = "SELECT student_fullname, total_money, is_paid FROM ((student_payments p INNER JOIN students s ON p.student_id = s.id) INNER JOIN eating_classes c ON s.e_class_e_class_id = c.e_class_id) " +
            "WHERE (p.pay_date BETWEEN ? AND ?) AND (c.e_class_level = ? OR c.e_class_grade = ? OR c.e_class_name = ?)", nativeQuery = true)
    List<PaymentManagerService> sortPayment(String startDate, String endDate, String classLevel, String classGrade, String className);

    @Query(value = "SELECT student_fullname, total_money, is_paid FROM ((student_payments p INNER JOIN students s ON p.student_id = s.id) INNER JOIN eating_classes c ON s.e_class_e_class_id = c.e_class_id) " +
            "WHERE (p.pay_date BETWEEN ? AND ?) AND s.student_fullname = ?", nativeQuery = true)
    List<PaymentManagerService> searchPaymentByStudentName(String startDate, String endDate, String studentName);
}

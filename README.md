# Trifood-Java-Draft

Link swagger : http://localhost:9090/swagger-ui/index.html#/

Trifood Draft - Một số chức năng cơ bản:
-	E-class : 
  + Thêm sửa xoá lớp ăn.
  + Tìm kiếm lớp ăn theo tên ( /search/{keyword} ).
  + Lọc lớp ăn ( /sortClass ).
-	E-group:
  + Thêm sửa xoá group.
  + Khi tạo group tự động tạo lịch trống cho group.
  + Tìm kiếm group theo tên ( /search/{keyword} ).
-	Food:
  + Thêm sửa xoá món ăn.
  + Tìm kiếm món ăn ( /search/{keyword} ).
-	Meal: 
  + Thêm sửa xoá các món ăn cho thực đơn.
  + Thống kê số lượng món ăn của lịch ăn ( /getTotalFoodAmount ).
  + Thống kê các món ăn bị huỷ ( /getTotalFoodDelete ).
-	Assign-schedule:
  + Cài đặt thực đơn cho một tuần hoặc mặc định hàng tuần ( /createForAllWeek  or /crateForSingleWeek).
  + Sửa thực đơn .
-	E-weekly-schedule:
  + Tạo mới thực đơn.
  + Xoá thực đơn.
-	Student-order:
  + Xoá món ăn trong thực đơn của học sinh
-	Student-payment:
  + Hoá đơn tự động tạo hàng tháng.
  + Thêm sửa xoá, tìm kiếm hoá đơn.
  + Cập nhật trạng thái thanh toán.

![image](https://user-images.githubusercontent.com/77835584/196845904-8eba8ad1-62d7-4c1d-b92f-10bc7a059a8b.png)

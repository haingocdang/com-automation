@Check_BieuPhiChiTietSucKhoe12
Feature: Check Tỷ lệ VCX

########################################### Scenario 1 ###########################################
  Scenario Outline: Data display correctly for VCX
    Given I launch and login Jupiter page as admin
  #  And I open "Bản chào" page
    When I click "Tạo mới" button
    And I select "Nghiệp vụ" dropdown list with "Nghiệp vụ" value
      | Nghiệp vụ          |
      | Con người |
    And I select "Tên sản phẩm" dropdown list with "Tên sản phẩm" value
      | Tên sản phẩm                |
      | Bảo hiểm sức khỏe toàn diện |
    And I click "Nhóm" radio button
    And I select "Kênh phân phối" dropdown list with "Kênh phân phối" value
      | Kênh phân phối |
      | Bancassurance |
    And I create customer with "<Tên khách hàng>" value and "<Số định danh>" value and "<Giới tính>" value and "<Số điện thoại>" value and "<Loại địa chỉ>" value and "<Quốc gia>" value and "<Tỉnh/Thành phố>" value and "<Quận/Huyện>" value and "<Phường/Xã>" value and "<Địa chỉ>" value
    And I click "Người được bảo hiểm" tab
  #  And I click "Báo giá chi tiết" radio button
  #  And I click "Thêm thông tin đối tượng" icon
    And Phi thanh toan SK12 display correct value

Examples:
  | Tên khách hàng   | Số định danh   | Giới tính | Số điện thoại | Loại địa chỉ | Quốc gia | Tỉnh/Thành phố | Quận/Huyện |Phường/Xã |Địa chỉ |
  | HD 060301 | 976894544 | Nam | 0247464603  | Địa chỉ thường trú                | Việt Nam | Thành phố Hồ Chí Minh | Quận 1 | Phường Đa Kao | 185 |
  # And Phi thanh toan VCX display correct value <Phí> when selecting "Hãng xe" with value <Hãng Xe> and "Phiên bản" with value <Phiên Bản> and "Mục đích sử dụng" with value <Mục Đích Sử Dụng> and "Loại xe" with value <Loại Xe> and "Nhóm xe" with value <Nhóm Xe>
   #   | Hãng Xe | Phiên Bản | Mục Đích Sử Dụng | Loại Xe | Nhóm Xe | Phí |

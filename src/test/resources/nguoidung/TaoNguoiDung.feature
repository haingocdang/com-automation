@Tao_Nguoi_Dung
Feature: Tao Nguoi Dung
  As a Admin User I want to Tao Nguoi Dung

  Scenario Outline: Create Người Dùng with all fields
    # Login
    # I launch login Admin page as Admin
    # I launch and login Jupiter page as admin
    Given I launch login Admin page as admin
    # Go to a page
    # I open "page name" page
   # And I open "Người dùng" page
    #Click button
    # I click "button name" button
    When I click "Tạo mới" button
    #Then "Tạo người dùng" page appears
    # Input textbox
    # I input "textbox name" textbox with value "value"
    And I input "Tên đăng nhập" textbox with value "<Tên Đăng Nhập>"
    And I input "Họ và tên" textbox with value "<Họ Và Tên>"
    And I input "Email" textbox with value "<Email>"
    And I input "Số điện thoại" textbox with value "<Số điện thoại>"

    #Select dropdown list
    And I select "Đơn vị" dropdown list with "Đơn Vị" value
      | Đơn Vị   |
      | <Đơn Vị> |
    And I select "Phòng ban" dropdown list with "Phòng Ban" value
      | Phòng Ban   |
      | <Phòng Ban> |
    And I select "Chức năng" dropdown list with "Chức Năng" value
      | Chức Năng   |
      | <Chức Năng> |
    And I select "Chức danh" dropdown list with "Chức Danh" value
      | Chức Danh   |
      | <Chức Danh> |
   # And I select "Vai trò" dropdown list with "Vai Trò" value
    #  | Vai Trò   |
     # | <Vai Trò> |
    And I click Tạo icon
    And I logout
    When I activate account with value "<Email>"
   # Then toast message "Tạo thành công." appears
   # When I search with value "<Tên Đăng Nhập>"
   # Then Chỉnh Sửa Lần Cuối of "<Tên Đăng Nhập>" displays DDcorrectly
   #And "Họ và tên" of "<Tên Đăng Nhập>" displays "<Họ Và Tên>"
   # And "Email" of "<Tên Đăng Nhập>" displays "<Email>"
   # And "Đơn Vị" of "<Tên Đăng Nhập>" displays "<Đơn Vị>"
   # And "Phòng ban" of "<Tên Đăng Nhập>" displays "<Phòng Ban>"
   # And "Chức năng" of "<Tên Đăng Nhập>" displays "<Chức Năng>"
   # And "Vai trò" of "<Tên Đăng Nhập>" displays "<Vai Trò>"
   # And Trạng thái of "<Tên Đăng Nhập>" displays "<Trạng Thái>"

    Examples:
      | Tên Đăng Nhập | Họ Và Tên | Email | Số điện thoại | Đơn Vị       | Phòng Ban     | Chức Năng | Chức Danh |
      | trankimhuy 	| Trần Kim Huy 	| trankimhuy@mailinator.com 	|  	| Trụ sở chính 	| Tài chính kế hoạch 	| Tài chính 	| Nhân viên |
      | tranaithy 	| Trần Ái Thy 	| tranaithy@mailinator.com 	|  	| Trụ sở chính 	| Tài chính kế hoạch 	| Tài chính 	| Nhân viên |




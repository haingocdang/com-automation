@Check_PhiThanhToanVCX
Feature: Check Tỷ lệ VCX

########################################### Scenario 1 ###########################################
  Scenario: Data display correctly for VCX
    Given I launch and login Jupiter page as admin
    And I open "Bản chào" page
    When I click "Tạo mới" button
    And I select "Nghiệp vụ" dropdown list with "Nghiệp vụ" value
      | Nghiệp vụ|
      |  Xe cơ giới |
    And I select "Tên sản phẩm" dropdown list with "Tên sản phẩm" value
      | Tên sản phẩm |
      | Xe ô tô đơn |
   # And I select "Loại sản phẩm" dropdown list with "Loại sản phẩm" value
   #   | Loại sản phẩm |
   #   | Sản phẩm đơn |

    And I click "Đối tượng bảo hiểm" tab
    And I click "Thêm thông tin đối tượng" icon

   # And I select "Loại bản chào" drop down list with "Loại Bản Chào" value
   #  | Loại Bản Chào |
   # | Mới           |
   # When I click "Thông tin đối tượng tham gia" section
    #And I click "Phạm vi bảo hiểm" section
    #And I click + button
    #Then Đối tượng tham gia bảo hiểm Nhóm section appears
    And Phi thanh toan VCX display correct value <Phí> when selecting "Hãng xe" with value <Hãng Xe> and "Phiên bản" with value <Phiên Bản> and "Mục đích sử dụng" with value <Mục Đích Sử Dụng> and "Loại xe" with value <Loại Xe> and "Nhóm xe" with value <Nhóm Xe>
      | Hãng Xe | Phiên Bản | Mục Đích Sử Dụng | Loại Xe | Nhóm Xe | Phí |

@TaoHopDong
Feature: Check Tỷ lệ VCX

########################################### Scenario 1 ###########################################
  Scenario: Data display correctly for VCX
    Given I launch and login Jupiter page as admin
    # And I open "Bản chào" page

   # And I select "Loại bản chào" drop down list with "Loại Bản Chào" value
   #  | Loại Bản Chào |
   # | Mới           |
   # When I click "Thông tin đối tượng tham gia" section
    #And I click "Phạm vi bảo hiểm" section
    #And I click + button
    #Then Đối tượng tham gia bảo hiểm Nhóm section appears
    And I generate policies

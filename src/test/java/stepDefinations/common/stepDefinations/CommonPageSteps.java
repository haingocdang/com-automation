package stepDefinations.common.stepDefinations;

import commons.CommonPageObjects;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import commons.VerifyHelper;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOption.Hooks;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pageObjects.mercury.LoginPageObject;
import pageObjects.mercury.TaoChucNangPageObject;
import pageUIs.CommonPageUI;
import utils.excelutils.ExcelReader;
import utils.excelutils.ExcelUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommonPageSteps {
    WebDriver driver;
    VerifyHelper verify;
    ExcelUtil excel;
    TaoChucNangPageObject taoChucNangPage;
    public static CommonPageObjects commonPage;
    //DanhSachBanChaoPageObject danhSachBanChaoPage;
    ExcelReader externalData;
    List<Map<String, String>> testData;
    List<String> optionUIValues, dataTableValues;
    String dateTime;

    public CommonPageSteps() {
        this.driver = Hooks.openAndQuitBrowser();
        //Hooks.setupTestData();
        verify = VerifyHelper.getData();
        //excel=ExcelUtil.getData();

        commonPage = PageGeneratorManager.getCommonPage(driver);
    }

    @Given("^I launch and login Jupiter page as admin$")
    public void i_launch_and_login_Jupiter_page() {
        // driver.get(GlobalConstants.JUPITER_LOGIN_SIT_ENV_URL);
        //  driver.get(GlobalConstants.JUPITER_LOGIN_SIT_ENV_URL);
        driver.get(GlobalConstants.JUPITER_LOGIN_TEST_ENV_URL);
        //   driver.get(GlobalConstants.JUPITER_LOGIN_UAT_ENV_URL);
        //   driver.get(GlobalConstants.JUPITER_LOGIN_TEST_ENV_URL);
        driver.manage().window().maximize();
        LoginPageObject loginPage;
        loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.inputUserName("hd22201");
        //  loginPage.inputUserName("hd11801");
        //   loginPage.inputUserName("phanquocdung");
        //  loginPage.inputUserPassword("Aa@123a");
        loginPage.inputUserPassword("Alpaca#2022");
        loginPage.clickDangNhapButton();
        //driver.get(GlobalConstants.JUPITER_LOGIN_TEST_ENV_URL);
        // driver.get("http://61.14.237.89:3201/");
        //danhSachBanChaoPage = PageGeneratorManager.getDanhSachBanChaoPage(driver);
    }

    @Given("^I launch login Admin page as admin$")
    public void iLoginAdminPageAsAdmin() {
        //driver.get(GlobalConstants.MERCURY_LOGIN_SIT_ENV_URL);
           driver.get(GlobalConstants.MERCURY_LOGIN_UAT_ENV_URL);
        driver.manage().window().maximize();
        LoginPageObject loginPage;
        loginPage = PageGeneratorManager.getLoginPage(driver);
        //  loginPage.inputUserName("hd11801");
        // loginPage.inputUserPassword("ScoreAdmin8@");
        loginPage.inputUserName("phanquocdung");
         loginPage.inputUserPassword("Aa@123");

     /*   loginPage.inputUserName("owner");
        loginPage.inputUserPassword("Alpaca#2023");*/

        loginPage.clickDangNhapButton();
        //  driver.get("https://sale-mercury.alpaca.vn/users");

    }

    @Given("^I open \"([^\"]*)\" page$")
    public void iOpenPage(String pageName) {
        commonPage.goToPage(driver, pageName);
    }

    @When("^I click \"([^\"]*)\" button$")
    public void iClickButton(String buttonName) {
        commonPage.clickToDynamicButton(driver, buttonName);
    }

    @When("^I click Tạo icon")
    public void iClickTaoIcon() {
        commonPage.clickToTaoButton(driver);
        // commonPage.sleepInSecond(10);
    }

    @When("^I logout")
    public void iLogout() {
        commonPage.clickToElement(driver, CommonPageUI.AVATAR);
        commonPage.clickToElement(driver, CommonPageUI.DANG_XUAT_LINK);

    }

    @When("^I click \"([^\"]*)\" section$")
    public void iClickSection(String sectioName) {
        commonPage.clickToDynamicSection(driver, sectioName);
    }

    @When("^I click Close button$")
    public void iClickCloseButton() {
        commonPage.closePopupbutton(driver);
    }

    @And("^I select \"([^\"]*)\" dropdown list with \"([^\"]*)\"value$")
    public void i_select_something_drop_down_list_with_somethingvalue(String nhmsnphm, String strArg1, String strArg2) {

    }
   /* @When("^I click \\+ button$")
    public void iClickAddButton() {
           commonPage.clickAddDoiTuongNhomButton();
    }*/

    /*@When("^I click Tạo button$")
    public void iClickTaoButton() {
        commonPage.clickTaoButton(driver);
        //danhSachChucNangPage = PageGeneratorManager.getDanhSachChucNangPage(driver);
    }*/

    @Then("^toast message \"([^\"]*)\" appears$")
    public void toastMessageAppears(String message) {
        commonPage.checkToastMessage(driver, message);
    }

    @When("^I select \"([^\"]*)\" dropdown list with \"([^\"]*)\" value$")
    public void iSelectDropDownListWithValue(String tenDropdown, String giaTri, DataTable dataTable) {
        commonPage.sleepInSecond(1);
        for (Map<String, String> value : dataTable.asMaps(String.class, String.class)) {
            commonPage.chonGiaTri(driver, tenDropdown, value.get(giaTri));
        }
    }

    @When("^I select \"([^\"]*)\" record \"([^\"]*)\" from context menu$")
    public void iSelectOptionFromContextMenu(String option, String recordValue) {
        commonPage.selectOptionFromContextmenu(driver, option, recordValue);

    }

    @When("^I input \"([^\"]*)\" textbox with value \"([^\"]*)\"$")
    public void iInputTextbox(String tenField, String value) {
        commonPage.inputValueIntoTextbox(driver, tenField, value);
    }

    @When("^I input \"([^\"]*)\" textarea with value \"([^\"]*)\"$")
    public void iInputArea(String tenField, String value) {
        commonPage.inputValueIntoTextArea(driver, tenField, value);

    }

    @When("^I input \"([^\"]*)\" datetime picker with current date time plus \"([^\"]*)\" minutes$")
    public void iInputDateTimePicer(String tenField, String minutes) {
        dateTime = commonPage.getDateTimeInTheFuture(driver, minutes);
        commonPage.iInputDateTimePicker(driver, tenField, dateTime);

    }

    @And("^I view detail \"([^\"]*)\" with value \"([^\"]*)\"$")
    public void iViewDetail(String columnName, String value) {
        commonPage.clickToCell(driver, columnName, value);
        System.out.println("Text header is " + commonPage.getElementText(driver, "//div[@class='badge badge-sm badge-success']"));

    }

    @When("^I select \"([^\"]*)\" of a \"([^\"]*)\" from context menu$")
    public void iSelectOfAFromContextMenu(String arg1, String arg2) {

    }

    @When("^I search with value \"([^\"]*)\"$")
    public void searchByKeyword(String keyword) {
        commonPage.searchValue(driver, keyword);
    }

    @Then("^\"([^\"]*)\" popup appears$")
    public void popupAppears(String tenPopup) {
        Assert.assertTrue(commonPage.isElementDisplay(driver, CommonPageUI.POPUP, tenPopup));

    }

    @Then("^\"([^\"]*)\" popup has button \"([^\"]*)\"$")
    public void popupHasButton(String tenPopup, String tenButton) {
        Assert.assertTrue(commonPage.isElementDisplay(driver, CommonPageUI.COMMON_BUTTON, tenButton));

    }

    @Then("^\"([^\"]*)\" popup has label \"([^\"]*)\"$")
    public void popupHasLabel(String tenPopup, String label) {
        Assert.assertEquals(label, commonPage.getElementTextByJS(driver, CommonPageUI.POPUP_CSS));
        // System.out.println("Text popup is " + commonPage.getElementTextByJS(driver, CommonPageUI.POPUP_CSS));
    }

    @Then("^\"([^\"]*)\" popup has text area \"([^\"]*)\"$")
    public void popupHasTextArea(String tenPopup, String tenTextArea) {
        Assert.assertTrue(commonPage.isElementDisplay(driver, CommonPageUI.COMMON_TEXTAREA, tenTextArea));

    }

    @Then("^\"([^\"]*)\" popup has date time picker \"([^\"]*)\"$")
    public void popupHasDateTimePicker(String tenPopup, String dateTimePicker) {
        Assert.assertTrue(commonPage.isElementDisplay(driver, CommonPageUI.NGAY_HIEU_LUC_DATE_PICKER, dateTimePicker));

    }

    @Then("^date time picker \"([^\"]*)\" display current time$")
    public void dateTimePickerDisplayCurrentTime(String arg1) {

    }

    @Then("^\"([^\"]*)\" button is disabled")
    public void buttonIsDisabledIfLabelIsBlank(String buttonName) {
        Assert.assertFalse(commonPage.isElementEnable(driver, CommonPageUI.COMMON_BUTTON, buttonName));
    }

    @Then("^User unabled to select the last date from date time picker \"([^\"]*)\"$")
    public void userUnabledToSelectTheLastDateFromDateTimePicker(String arg1) {

    }

    @Then("^\"([^\"]*)\" popup is closed$")
    public void popupIsClosed(String tenPopup) {
        Assert.assertTrue(commonPage.isElementNotDisplay(driver, CommonPageUI.POPUP, tenPopup));

    }

    @When("^I select a date in future \"([^\"]*)\"$")
    public void iSelectADateInFuture(String arg1) {

    }

    @When("^I input \"([^\"]*)\"$")
    public void iInput(String arg1) {

    }


    @And("^I wait to inputted date time$")
    public void iWaitTo() {
        //dateTime=commonPage.getDateTimeInTheFuture(driver,dateTime);
        //commonPage.iInputDateTimePicker(driver, tenField, dateTime);
        commonPage.waitTo(driver, dateTime);

    }

    @Then("^\"([^\"]*)\" button is enabled$")
    public void buttonIsEnabled(String button) {
        Assert.assertTrue(commonPage.isElementEnable(driver, CommonPageUI.COMMON_BUTTON, button));

    }

    @Then("^\"([^\"]*)\" of \"([^\"]*)\" is \"([^\"]*)\" if current date time is before scheduled time \"([^\"]*)\"$")
    public void ofIsIfCurrentDateTimeIsBeforeScheduledTime(String arg1, String arg2, String arg3, String arg4) {

    }


    @Then("^\"([^\"]*)\" of \"([^\"]*)\" is \"([^\"]*)\" if current date time is equal scheduled time \"([^\"]*)\"$")
    public void ofIsIfCurrentDateTimeIsEqualScheduledTime(String arg1, String arg2, String arg3, String arg4) {

    }

    @Then("^Vô Hiệu Hóa Chức Năng action is recored into Lịch Sử Chức Năng$")
    public void vôHiệuHóaChứcNăngActionIsRecoredIntoLịchSửChứcNăng() {

    }

    @Then("^\"([^\"]*)\" is disabled in \"([^\"]*)\" page$")
    public void isDisabledInPage(String arg1, String arg2) {

    }

    @Then("^\"([^\"]*)\" is disabled in detail \"([^\"]*)\" page$")
    public void isDisabledInDetailPage(String arg1, String arg2) {

    }

    @Then("^Khôi Phục Chức Năng action is recored into Lịch Sử Chức Năng$")
    public void khôiPhụcChứcNăngActionIsRecoredIntoLịchSửChứcNăng() {

    }

    @Then("^Trạng thái of \"([^\"]*)\" displays \"([^\"]*)\"$")
    public void checkTrangThai(String tenValue, String trangThai) {
      /*  for (Map<String, String> sanPham : dataTable.asMaps(String.class, String.class)) {
            commonPage.chonGiaTri(driver, tenDropdown, sanPham.get(giaTri));
        }*/
        Assert.assertEquals(trangThai, commonPage.getTrangThai(driver, "Trạng thái", tenValue));
    }

    @Then("^Chỉnh Sửa Lần Cuối of \"([^\"]*)\" displays correctly$")
    public void checkChinhSuaLanCuoi(String tenValue) {
      /*  for (Map<String, String> sanPham : dataTable.asMaps(String.class, String.class)) {
            commonPage.chonGiaTri(driver, tenDropdown, sanPham.get(giaTri));
        }*/

        Assert.assertEquals(commonPage.getCurrentDateTime(driver), commonPage.getDateTimeChinhSua(driver, "Chỉnh sửa lần cuối", tenValue));
    }

    @Then("^\"([^\"]*)\" of \"([^\"]*)\" displays \"([^\"]*)\"$")
    public void checkColumnValue(String tenColumn, String entity, String value) {
      /*  for (Map<String, String> sanPham : dataTable.asMaps(String.class, String.class)) {
            commonPage.chonGiaTri(driver, tenDropdown, sanPham.get(giaTri));
        }*/
        Assert.assertEquals(value, commonPage.getTextFromCell(driver, tenColumn, entity));
    }

    @Then("^\"([^\"]*)\" drop down list show correct \"([^\"]*)\"$")
    public void dropDownListShowCorrectValue(String tenDropdown, String tenColumn, DataTable dataTable) {
        optionUIValues = new ArrayList<>();
        dataTableValues = new ArrayList<>();
        optionUIValues = commonPage.getAllGiaTriTrongSelectBox(driver, tenDropdown);

        for (Map<String, String> value : dataTable.asMaps(String.class, String.class)) {
            if (value.get(tenColumn).equals("")) {
                break;
            }
            dataTableValues.add(value.get(tenColumn));
        }
        System.out.println("Option values " + dataTableValues);
        Assert.assertEquals(dataTableValues, optionUIValues);


    }

    @And("^I click \"([^\"]*)\" tab$")
    public void iClickTab(String tabName) {
        commonPage.sleepInSecond(3);
        commonPage.waitElementVisible(driver, CommonPageUI.COMMON_TAB, tabName);
        commonPage.clickToElement(driver, CommonPageUI.COMMON_TAB, tabName);
    }

    @And("^I click \"([^\"]*)\" icon$")
    public void iClickIcon(String iconName) {
        commonPage.waitElementVisible(driver, CommonPageUI.COMMON_ICON, iconName);
        commonPage.clickToElement(driver, CommonPageUI.COMMON_ICON, iconName);
    }

    @And("^I activate account with value \"([^\"]*)\"$")
    public void iActivateAccount(String emailAddress) {
        driver.get("https://www.mailinator.com/index.jsp");
        driver.manage().window().maximize();
        commonPage.sendKeyToElement(driver, "//input[@placeholder='Enter Public Mailinator Inbox']", emailAddress);
        commonPage.clickToElement(driver, "//button[contains(text(),'GO')]");
        commonPage.sleepInSecond(5);
        commonPage.refreshCurrentPage(driver);
        commonPage.waitElementVisible(driver, "//td[contains(text(),'Tài khoản của bạn đã được tạo')]");
        commonPage.clickToElement(driver, "//td[contains(text(),'Tài khoản của bạn đã được tạo')]");

        commonPage.switchToFrameOrIframe(driver, "//iframe[@id='html_msg_body']");
        commonPage.waitElementVisible(driver, "//a[contains(text(),'Bấm vào đây để kích hoạt tài khoản')]");
        String url = commonPage.getElementAttribute(driver, "//a[contains(text(),'Bấm vào đây để kích hoạt tài khoản')]", "href");
        //   System.out.println("URL: " + url);
        commonPage.switchToDefaultContent(driver);
        driver.get(url);
        commonPage.sendKeyToElement(driver, "//input[@id='password']", "Aa@123");
        commonPage.sendKeyToElement(driver, "//input[@id='verifyPassword']", "Aa@123");
        commonPage.clickToElement(driver, "//button[@type='submit']");

    }

    @And("^I click \"([^\"]*)\" radio button$")
    public void iClickRadioButton(String radioButtonName) {
        // Write code here that turns the phrase above into concrete actions
        commonPage.sleepInSecond(3);
        commonPage.waitElementVisible(driver, CommonPageUI.COMMON_RADIO_BUTTON, radioButtonName);
        commonPage.clickToElement(driver, CommonPageUI.COMMON_RADIO_BUTTON, radioButtonName);
        //  commonPage.clickToElement(driver, CommonPageUI.COMMON_BUTTON, "Xác nhận");

    }

    @And("^I create customer with \"([^\"]*)\" value and \"([^\"]*)\" value and \"([^\"]*)\" value and \"([^\"]*)\" value and \"([^\"]*)\" value and \"([^\"]*)\" value and \"([^\"]*)\" value and \"([^\"]*)\" value and \"([^\"]*)\" value and \"([^\"]*)\" value$")
    public void iCreateCustomer(String tenKhachHang, String soDinhDanh, String gioiTinh, String soDienThoai, String loaiDiachi, String quocGia, String tinhThanhPho, String quanHuyen, String phuongXa, String diaChi) {
        commonPage.clickToElement(driver, CommonPageUI.TAO_KHACH_HANG_ICON);
        commonPage.sleepInSecond(2);
        commonPage.switchToFrameOrIframe(driver, CommonPageUI.KHACH_HANG_FORM_TAO_IFRAME);
        commonPage.sendKeyToElement(driver, CommonPageUI.COMMON_TEXTBOX, soDinhDanh, "Số định danh");
        commonPage.clickToElement(driver, CommonPageUI.COMMON_BUTTON, "Kiểm tra");
        commonPage.sendKeyToElement(driver, CommonPageUI.COMMON_TEXTBOX, tenKhachHang, "Họ và tên");
        commonPage.chonGiaTri(driver, "Giới tính", gioiTinh);
        commonPage.sendKeyToElement(driver, CommonPageUI.COMMON_TEXTBOX, soDienThoai, "Số điện thoại");
        commonPage.chonGiaTri(driver, "Loại địa chỉ", loaiDiachi);
        commonPage.chonGiaTri(driver, "Quốc gia", quocGia);
        commonPage.chonGiaTri(driver, "Tỉnh/Thành phố", tinhThanhPho);
        commonPage.chonGiaTri(driver, "Quận/Huyện", quanHuyen);
        commonPage.chonGiaTri(driver, "Phường/Xã", phuongXa);
        commonPage.sendKeyToElement(driver, CommonPageUI.COMMON_TEXTAREA, diaChi, "Địa chỉ");
        // commonPage.sleepInSecond(2);
        commonPage.clickToElement(driver, CommonPageUI.COMMON_ICON, "Tạo");
        commonPage.switchToDefaultContent(driver);

    }


    @Then("^\"([^\"]*)\" page appears$")
    public void pageAppears(String tenPage) {
        verify.verifyEquals(tenPage, commonPage.getElementText(driver, CommonPageUI.PAGE_TITLE));
        //  verify.verifyTrue(true);
    }
}
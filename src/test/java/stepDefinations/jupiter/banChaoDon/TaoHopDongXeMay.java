package stepDefinations.jupiter.banChaoDon;

import commons.GlobalConstants;
import commons.VerifyHelper;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumberOption.Hooks;
import org.openqa.selenium.WebDriver;
import pageUIs.alpaca.CommonPageUI;
import utils.excelutils.SpreadSheetIntegration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static stepDefinations.common.stepDefinations.CommonPageSteps.commonPage;

public class TaoHopDongXeMay {
    WebDriver driver;
    VerifyHelper verify;

    private SpreadSheetIntegration spreadsheet;

    public TaoHopDongXeMay() {
        driver = Hooks.openAndQuitBrowser();
        verify = VerifyHelper.getData();
    }


 /*   @When("^I click \\+ button$")
    public void iClickAddButton() {
        taoBanChaoPage.clickAddDoiTuongNhomButton();
    }

    @Then("^Đối tượng tham gia bảo hiểm Nhóm section appears$")
    public void đốiTượngThamGiaBảoHiểmNhómSectionAppears() {

    }*/


    @And("^I generate policies$")
    public void iGeneratePolicies() throws Exception {
        int row = 0;
        List<Map<String, Object>> bienKiemSoatSheet;
        bienKiemSoatSheet = new ArrayList<Map<String, Object>>();

        spreadsheet = new SpreadSheetIntegration();

        bienKiemSoatSheet = spreadsheet.readSheet(GlobalConstants.sheetBienKiemSoat, GlobalConstants.readRangeBienKiemSoatSheet);


        for (Map<String, Object> bienKiemSoatValue : bienKiemSoatSheet) {
            row = row + 1;
            if (bienKiemSoatValue.get("Biển kiểm soát").equals("")) {
                break;
            }
            if (bienKiemSoatValue.get("Execute").equals("RUN")) {

                commonPage.clickToElement(driver, CommonPageUI.COMMON_BUTTON, "Tạo mới");
                commonPage.sleepInSecond(5);

                commonPage.chonGiaTri(driver, "Nghiệp vụ", "Xe cơ giới");
                commonPage.chonGiaTri(driver, "Tên sản phẩm", "Xe máy");
                commonPage.chonGiaTri(driver, "Kênh phân phối", "Bảo Long");
                commonPage.sleepInSecond(1);
                commonPage.chonGiaTri(driver, "Tên khách hàng", "VÃNG LAI");
               commonPage.sleepInSecond(1);

                commonPage.clickToElementByJS(driver, CommonPageUI.COMMON_TAB, "Đối tượng bảo hiểm");
               // commonPage.sleepInSecond(1);
                commonPage.clickToElementByJS(driver, CommonPageUI.COMMON_ICON, "Thêm thông tin đối tượng");

                commonPage.chonGiaTri(driver, "Hãng xe", "KHAC");
                commonPage.chonGiaTri(driver, "Phiên bản", "KHAC");
                commonPage.chonGiaTri(driver, "Loại xe", "Trên 50 cc");

                commonPage.inputValueIntoTextbox(driver, "Biển kiểm soát", (String) bienKiemSoatValue.get("Biển kiểm soát"));
                commonPage.clickToElementByJS(driver, CommonPageUI.COMMON_TAB, "Chủ xe và lái xe");
         //       commonPage.sleepInSecond(1);

                commonPage.chonGiaTri(driver, "Tỉnh/Thành phố", "Tỉnh An Giang");
                commonPage.inputValueIntoTextbox(driver, "Tên chủ xe", "Đặng Văn A");

                commonPage.clickToElementByJS(driver, CommonPageUI.COMMON_TAB, "Loại hình bảo hiểm");
                commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, "TNDS bắt buộc");
                commonPage.sleepInSecond(3);
                commonPage.clickToElementByJS(driver, CommonPageUI.COMMON_BUTTON, "Lưu");
                commonPage.sleepInSecond(2);


                commonPage.clickToElementByJS(driver, CommonPageUI.TAO_BAN_CHAO_ICON);
                commonPage.waitElementVisible(driver, CommonPageUI.TOAST_MESSAGE, "Tạo thành công");

                //    commonPage.sleepInSecond(1);
                commonPage.clickToElementByJS(driver, CommonPageUI.ACTION_BUTTON, "Duyệt bản chào");
                //    commonPage.sleepInSecond(1);
                commonPage.clickToElementByJS(driver, CommonPageUI.COMMON_BUTTON, "Xác nhận");

                commonPage.clickToElementByJS(driver, CommonPageUI.ACTION_BUTTON, "Chuyển thành hợp đồng");
                commonPage.clickToElementByJS(driver, CommonPageUI.COMMON_BUTTON, "Xác nhận");
                commonPage.sleepInSecond(2);
                commonPage.clickToElementByJS(driver, CommonPageUI.COMMON_BUTTON, "Phát hành");
                commonPage.clickToElementByJS(driver, CommonPageUI.PHAT_HANH_BUTTON);

                commonPage.sleepInSecond(1);
                commonPage.clickToElementByJS(driver, CommonPageUI.CLOSE_TAB_ICON);
                commonPage.clickToElementByJS(driver, CommonPageUI.MAIN_PAGE_ICON);
                commonPage.clickToElementByJS(driver, CommonPageUI.CLOSE_TAB_ICON);
               // commonPage.sleepInSecond(2);
                commonPage.goToPage(driver, "Bản chào");
              // commonPage.clickToElement(driver, CommonPageUI.CLOSE_TAB_ICON);


            }
        }

    }
}
//  }








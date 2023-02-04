package stepDefinations.jupiter.banChaoDon;

import commons.GlobalConstants;
import commons.VerifyHelper;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumberOption.Hooks;

import org.openqa.selenium.WebDriver;
import pageUIs.alpaca.CommonPageUI;
import utils.excelutils.SpreadSheetIntegration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static stepDefinations.common.stepDefinations.CommonPageSteps.commonPage;

public class CheckTyLeVCXSteps {
    WebDriver driver;
    VerifyHelper verify;

    private SpreadSheetIntegration spreadsheet;

    public CheckTyLeVCXSteps() {
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

    @And("^Ty le VCX display correct value <Phí> when selecting \"([^\"]*)\" with value <Hãng Xe> and \"([^\"]*)\" with value <Phiên Bản> and \"([^\"]*)\" with value <Mục Đích Sử Dụng> and \"([^\"]*)\" with value <Loại Xe> and \"([^\"]*)\" with value <Nhóm Xe>$")
    public void checkTyleVCX(String hangXe, String phienBan, String MĐSD,
                             String loaiXe, String nhomXe, DataTable data) throws Exception {


        int row = 0;
        List<Map<String, Object>> VCXSheet, hangHieuXeSheet;

        List<String> optionNhomXeUIValues, optionLoaiXeUIValues,
                nhomXeDataTableValues, loaiXeDataTabaleValues;
        VCXSheet = new ArrayList<Map<String, Object>>();
        hangHieuXeSheet = new ArrayList<Map<String, Object>>();

        spreadsheet = new SpreadSheetIntegration();

        List<List<Object>> resultVCX = spreadsheet.readDataFromSpreadSheet(GlobalConstants.sheetVCXID, GlobalConstants.readRangeVCX);
        List<List<Object>> resultHangHieuXe = spreadsheet.readDataFromSpreadSheet(GlobalConstants.sheetHangHieuXeID, GlobalConstants.readRangeHangHieuXe);
        VCXSheet = resultVCX.stream().map(SpreadSheetIntegration::toMapVCX).collect(Collectors.toList());
        hangHieuXeSheet = resultHangHieuXe.stream().map(SpreadSheetIntegration::toMapHangHieuXeVCX).collect(Collectors.toList());


      /*  for (Map<String, Object> hangHieuXeValue : hangHieuXeSheet) {
            if (hangHieuXeValue.get("Hãng xe").equals("")) {
                break;
            }*/
        //System.out.println(hangXeValue.get("Hãng Xe"));


        row = 1;
/*            if (!hangHieuXeValue.get("Hãng xe").equals("Hãng xe")) {

                commonPage.chonGiaTri(driver, hangXe, (String) hangHieuXeValue.get("Hãng xe"));
                commonPage.chonGiaTri(driver, phienBan, (String) hangHieuXeValue.get("Phiên bản"));
                commonPage.chonGiaTri(driver, MĐSD, (String) hangHieuXeValue.get("MĐSD"));
                commonPage.chonGiaTri(driver, nhomXe, (String) hangHieuXeValue.get("Nhóm xe"));
                commonPage.chonGiaTri(driver, loaiXe, (String) hangHieuXeValue.get("Loại xe"));*/

        for (Map<String, Object> VCXValue : VCXSheet) {
            row = row + 1;
            if (VCXValue.get("Hãng xe").equals("")) {
                break;
            }
            if (!VCXValue.get("Hãng xe").equals("Hãng xe")) {

                commonPage.chonGiaTri(driver, hangXe, (String) VCXValue.get("Hãng xe"));
                commonPage.chonGiaTri(driver, phienBan, (String) VCXValue.get("Phiên bản"));
                commonPage.chonGiaTri(driver, MĐSD, (String) VCXValue.get("MĐSD"));
                commonPage.chonGiaTri(driver, nhomXe, (String) VCXValue.get("Nhóm xe"));
                commonPage.chonGiaTri(driver, loaiXe, (String) VCXValue.get("Loại xe"));
/*
                    if (VCXValue.get("Hãng xe").equals(VCXValue.get("Hãng xe"))
                            && VCXValue.get("Phiên bản").equals(hangHieuXeValue.get("Phiên bản"))
                            && VCXValue.get("Dòng xe").equals(hangHieuXeValue.get("Dòng xe"))
                            && VCXValue.get("MĐSD").equals(hangHieuXeValue.get("MĐSD"))
                            && VCXValue.get("Nhóm xe").equals(hangHieuXeValue.get("Nhóm xe"))
                            && VCXValue.get("Loại xe").equals(hangHieuXeValue.get("Loại xe"))) {*/

                commonPage.iInputDateTimePicker(driver, "Ngày đăng ký lần đầu", (String) VCXValue.get("Ngày ĐKLĐ"));

                commonPage.iInputDateTimePicker(driver, "Ngày bắt đầu", (String) VCXValue.get("Ngày bắt đầu"));
                commonPage.iInputDateTimePicker(driver, "Ngày kết thúc", (String) VCXValue.get("Ngày kết thúc"));

                commonPage.inputValueIntoTextbox(driver, "Giá trị xe thực tế", (String) VCXValue.get("Giá trị xe"));
                commonPage.inputValueIntoTextbox(driver, "Số chỗ ngồi", (String) VCXValue.get("Số chỗ"));

                if (!VCXValue.get("Nhóm xe").equals("Nhóm xe chở người")) {
                    commonPage.inputValueIntoTextbox(driver, "Trọng tải", (String) VCXValue.get("Trọng tải"));
                }
                commonPage.chonGiaTri(driver, "Nơi sản xuất", (String) VCXValue.get("Nơi sản xuất"));
                commonPage.chonGiaTri(driver, "Năm sản xuất", (String) VCXValue.get("Năm sản xuất"));
                commonPage.sleepInSecond(1);
                commonPage.clickToElement(driver, CommonPageUI.COMMON_TAB, "Loại hình bảo hiểm");

                commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, "Vật chất xe");
                commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, "Bổ sung 02");
                commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, "Bổ sung 04a");
                commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, "Bổ sung 04b");
                commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, "Bổ sung 07");
                commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, "Bổ sung 08");
                commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, "Bổ sung 01");
                commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, "Bổ sung 03");
                commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, "Bổ sung 05");
                commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, "Bổ sung 06");
                commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, "Bổ sung 09");
                commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, "Bổ sung 10");


                String TLPphamViCoBanUI = commonPage.getValueFromCell(driver, GlobalConstants.TLP_CHUAN_SAU_THUE_LABEL, "Phạm vi cơ bản");
                String TLPboSung01UI = commonPage.getValueFromCell(driver, GlobalConstants.TLP_CHUAN_SAU_THUE_LABEL, "Bổ sung 01");
                String TLPboSung02UI = commonPage.getValueFromCell(driver, GlobalConstants.TLP_CHUAN_SAU_THUE_LABEL, "Bổ sung 02");
                //  String boSung03UI = commonPage.getValueFromCell(driver, "Tỉ Lệ Phí Quy Định (Gồm Thuế)", "Bổ sung 03");
                String TLPboSung04aUI = commonPage.getValueFromCell(driver, GlobalConstants.TLP_CHUAN_SAU_THUE_LABEL, "Bổ sung 04a");
                String TLPboSung04bUI = commonPage.getValueFromCell(driver, GlobalConstants.TLP_CHUAN_SAU_THUE_LABEL, "Bổ sung 04b");
                String TLPboSung05UI = commonPage.getValueFromCell(driver, GlobalConstants.TLP_CHUAN_SAU_THUE_LABEL, "Bổ sung 05");
                //   String boSung06UI = commonPage.getValueFromCell(driver, "Tỉ Lệ Phí Quy Định (Gồm Thuế)", "Bổ sung 06");
                String TLPboSung07UI = commonPage.getValueFromCell(driver, GlobalConstants.TLP_CHUAN_SAU_THUE_LABEL, "Bổ sung 07");
                String TLPboSung08UI = commonPage.getValueFromCell(driver, GlobalConstants.TLP_CHUAN_SAU_THUE_LABEL, "Bổ sung 08");
                String TLPboSung09UI = commonPage.getValueFromCell(driver, GlobalConstants.TLP_CHUAN_SAU_THUE_LABEL, "Bổ sung 09");
                String TLPboSung10UI = commonPage.getValueFromCell(driver, GlobalConstants.TLP_CHUAN_SAU_THUE_LABEL, "Bổ sung 10");


                String cellTLPCoBan = (String) VCXValue.get("TLP cơ bản (gồm thuế)");
                String cellTLPBS01 = (String) VCXValue.get("TLP BS01 (gồm thuế)");
                String cellTLPBS02 = (String) VCXValue.get("TLP BS02 (gồm thuế)");
                String cellTLPBS04A = (String) VCXValue.get("TLP BS04A (gồm thuế)");
                String cellTLPBS04B = (String) VCXValue.get("TLP BS04B (gồm thuế)");
                String cellTLPBS05 = (String) VCXValue.get("TLP BS05 (gồm thuế)");
                String cellTLPBS07 = (String) VCXValue.get("TLP BS07 (gồm thuế)");
                String cellTLPBS08 = (String) VCXValue.get("TLP BS08 (gồm thuế)");
                String cellTLPBS09 = (String) VCXValue.get("TLP BS09 (gồm thuế)");
                String cellTLPBS010 = (String) VCXValue.get("TLP BS10 (gồm thuế)");


                //  Assert.assertEquals(cellPhamViCoBan, TLPphamViCoBanUI.replace("%", ""));
                if (verify.verifyEquals(cellTLPCoBan, TLPphamViCoBanUI) &&
                        verify.verifyEquals(cellTLPBS01, TLPboSung01UI) &&
                        verify.verifyEquals(cellTLPBS02, TLPboSung02UI) &&
                        verify.verifyEquals(cellTLPBS04A, TLPboSung04aUI) &&
                        verify.verifyEquals(cellTLPBS04B, TLPboSung04bUI) &&
                        verify.verifyEquals(cellTLPBS05, TLPboSung05UI) &&
                        verify.verifyEquals(cellTLPBS07, TLPboSung07UI) &&
                        verify.verifyEquals(cellTLPBS08, TLPboSung08UI) &&
                        verify.verifyEquals(cellTLPBS09, TLPboSung09UI) &&
                        verify.verifyEquals(cellTLPBS010, TLPboSung10UI)) {
                    spreadsheet.appendDataToSpreadSheet("PASSED", GlobalConstants.sheetVCXID, GlobalConstants.appendResultRangeVCX + row);

                } else {
                    spreadsheet.appendDataToSpreadSheet("FAILED", GlobalConstants.sheetVCXID, GlobalConstants.appendResultRangeVCX + row);
                    spreadsheet.addNoteToCell(row - 1, row, 31, 32, 0, TLPphamViCoBanUI, GlobalConstants.sheetVCXID);
                    spreadsheet.addNoteToCell(row - 1, row, 32, 33, 0, TLPboSung01UI, GlobalConstants.sheetVCXID);
                    spreadsheet.addNoteToCell(row - 1, row, 33, 34, 0, TLPboSung02UI, GlobalConstants.sheetVCXID);
                    spreadsheet.addNoteToCell(row - 1, row, 34, 35, 0, TLPboSung04aUI, GlobalConstants.sheetVCXID);
                    spreadsheet.addNoteToCell(row - 1, row, 35, 36, 0, TLPboSung04bUI, GlobalConstants.sheetVCXID);
                    spreadsheet.addNoteToCell(row - 1, row, 36, 37, 0, TLPboSung05UI, GlobalConstants.sheetVCXID);
                    spreadsheet.addNoteToCell(row - 1, row, 37, 38, 0, TLPboSung07UI, GlobalConstants.sheetVCXID);
                    spreadsheet.addNoteToCell(row - 1, row, 38, 39, 0, TLPboSung08UI, GlobalConstants.sheetVCXID);
                    spreadsheet.addNoteToCell(row - 1, row, 39, 40, 0, TLPboSung09UI, GlobalConstants.sheetVCXID);
                    spreadsheet.addNoteToCell(row - 1, row, 40, 41, 0, TLPboSung10UI, GlobalConstants.sheetVCXID);
                }
                commonPage.sleepInSecond(1);
                commonPage.clickToElement(driver, CommonPageUI.COMMON_TAB, "Thông tin xe");

            }
            // break;
        }
    }
}
  //  }








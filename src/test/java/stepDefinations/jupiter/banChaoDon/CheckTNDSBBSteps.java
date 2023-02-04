package stepDefinations.jupiter.banChaoDon;

import commons.GlobalConstants;
import commons.VerifyHelper;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumberOption.Hooks;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import pageObjects.jupiter.DanhSachBanChaoPageObject;
import pageObjects.jupiter.TaoBanChaoPageObject;
import pageUIs.alpaca.CommonPageUI;
import utils.excelutils.ExcelReader;
import utils.excelutils.ExcelUtil;
import utils.excelutils.SpreadSheetIntegration;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import static stepDefinations.common.stepDefinations.CommonPageSteps.commonPage;

public class CheckTNDSBBSteps {
    WebDriver driver;
    VerifyHelper verify;
    TaoBanChaoPageObject taoBanChaoPage;
    DanhSachBanChaoPageObject danhSachBanChaoPage;

    private SpreadSheetIntegration spreadsheet;

    public CheckTNDSBBSteps() {
        driver = Hooks.openAndQuitBrowser();
        verify = VerifyHelper.getData();
    }

    @Then("^TNDSBB display correct value <Phí> when selecting \"([^\"]*)\" with value <Hãng Xe> and \"([^\"]*)\" with value <Phiên Bản> and \"([^\"]*)\" with value <Mục Đích Sử Dụng> and \"([^\"]*)\" with value <Loại Xe> and \"([^\"]*)\" with value <Nhóm Xe>$")
    public void checkTyLeTNDSBB(String hangXe, String phienBan, String MĐSD,
                                String loaiXe, String nhomXe, DataTable data) throws Exception {

        int row = 0;
        List<Map<String, Object>> TNDSBBSheet, hangHieuXeSheet;
        TNDSBBSheet = new ArrayList<Map<String, Object>>();
        hangHieuXeSheet = new ArrayList<Map<String, Object>>();


        spreadsheet = new SpreadSheetIntegration();

      //  List<List<Object>> resultTNDSBB = spreadsheet.readDataFromSpreadSheet(GlobalConstants.sheetTNDSBBID, GlobalConstants.readRangeTNDSBB);
      //  List<List<Object>> resultHangHieuXe = spreadsheet.readDataFromSpreadSheet(GlobalConstants.sheetHangHieuXeID, GlobalConstants.readRangeHangHieuXe);
        // TNDSBBSheet = resultTNDSBB.stream().map(SpreadSheetIntegration::toMapTNDSBB).collect(Collectors.toList());
        //   hangHieuXeSheet = resultHangHieuXe.stream().map(SpreadSheetIntegration::toMapHangHieuXe).collect(Collectors.toList());
        TNDSBBSheet = spreadsheet.readSheet(GlobalConstants.sheetTNDSBBID, GlobalConstants.readRangeTNDSBB);
        hangHieuXeSheet = spreadsheet.readSheet(GlobalConstants.sheetHangHieuXeID, GlobalConstants.readRangeHangHieuXe);
     /*   for (Map<String, Object> hangHieuXevalue : hangHieuXeSheet) {
            if (hangHieuXevalue.get("Hãng xe").equals("")) {
                break;
            }*/
        row = 0;
        //           if (!hangHieuXevalue.get("Hãng xe").equals("Hãng xe")) {

           /*     commonPage.chonGiaTri(driver, hangXe, (String) hangHieuXevalue.get("Hãng xe"));
                commonPage.chonGiaTri(driver, phienBan, (String) hangHieuXevalue.get("Phiên bản"));
                commonPage.chonGiaTri(driver, MĐSD, (String) hangHieuXevalue.get("MĐSD"));
                commonPage.chonGiaTri(driver, nhomXe, (String) hangHieuXevalue.get("Nhóm xe"));
                commonPage.chonGiaTri(driver, loaiXe, (String) hangHieuXevalue.get("Loại xe"));*/

        // commonPage.sleepInSecond(1);
        for (Map<String, Object> TNDSBBValue : TNDSBBSheet) {
            row = row + 1;
            if (!TNDSBBValue.get("Hãng xe").equals("Hãng xe")&&
                    TNDSBBValue.get("Result").equals("RUN")) {
               // System.out.println("Row "+row);

                if (TNDSBBValue.get("Hãng xe").equals("")) {
                    break;
                }
                commonPage.chonGiaTri(driver, hangXe, (String) TNDSBBValue.get("Hãng xe"));
                commonPage.chonGiaTri(driver, phienBan, (String) TNDSBBValue.get("Phiên bản"));
                commonPage.chonGiaTri(driver, MĐSD, (String) TNDSBBValue.get("MĐSD"));
                commonPage.chonGiaTri(driver, nhomXe, (String) TNDSBBValue.get("Nhóm xe"));
                commonPage.chonGiaTri(driver, loaiXe, (String) TNDSBBValue.get("Loại xe"));
                   /* if (TNDSBBValue.get("Hãng xe").equals(hangHieuXevalue.get("Hãng xe"))
                            && TNDSBBValue.get("Phiên bản").equals(hangHieuXevalue.get("Phiên bản"))
                            && TNDSBBValue.get("MĐSD").equals(hangHieuXevalue.get("MĐSD"))
                            && TNDSBBValue.get("Nhóm xe").equals(hangHieuXevalue.get("Nhóm xe"))
                            && TNDSBBValue.get("Loại xe").equals(hangHieuXevalue.get("Loại xe"))) {*/

                if (!TNDSBBValue.get("Nhóm xe").equals("Nhóm xe chở người")) {
                    commonPage.inputValueIntoTextbox(driver, "Trọng tải", (String) TNDSBBValue.get("Trọng tải"));
                }

                commonPage.inputValueIntoTextbox(driver, "Số chỗ ngồi", (String) TNDSBBValue.get("Số chỗ"));

                if (!TNDSBBValue.get("Nhóm xe").equals("Nhóm xe chở người")) {
                    commonPage.inputValueIntoTextbox(driver, "Trọng tải", (String) TNDSBBValue.get("Trọng tải"));
                }
                commonPage.iInputDateTimePicker(driver, "Ngày bắt đầu", (String) TNDSBBValue.get("Ngày bắt đầu"));
                commonPage.iInputDateTimePicker(driver, "Ngày kết thúc", (String) TNDSBBValue.get("Ngày kết thúc"));

                commonPage.sleepInSecond(1);
                commonPage.clickToElement(driver, CommonPageUI.COMMON_TAB, "Loại hình bảo hiểm");

                if (!((String) TNDSBBValue.get("Phí TNDSBB (gồm thuế)")).contains("KC")) {
                    commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, "TNDS bắt buộc");
                    String phiBBUI = commonPage.getValueFromCell(driver, "Phí thanh toán (sau thuế)", "TNDS bắt buộc").replace(",", ".");

                    String cellValue = (String) TNDSBBValue.get("Phí TNDSBB (gồm thuế)");
                    cellValue = ((String) TNDSBBValue.get("Phí TNDSBB (gồm thuế)")).replace(" ", "");
                    if (verify.verifyEquals(cellValue, phiBBUI)) {
                        spreadsheet.appendDataToSpreadSheet("PASSED", GlobalConstants.sheetTNDSBBID, GlobalConstants.appendResultRangeTNDSBB + row);
                      //  System.out.println("Rang result "+GlobalConstants.appendResultRangeTNDSBB + row );
                    } else {
                        spreadsheet.appendDataToSpreadSheet("FAILED", GlobalConstants.sheetTNDSBBID, GlobalConstants.appendResultRangeTNDSBB + row);
                        spreadsheet.addNoteToCell(row - 1, row, 9, 10, 0, phiBBUI, GlobalConstants.sheetTNDSBBID);

                    }
                    commonPage.clickToElement(driver, CommonPageUI.COMMON_TAB, "Thông tin xe");
                } else {
                    if (commonPage.isElementNotDisplay(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX)) {
                        spreadsheet.appendDataToSpreadSheet("PASSED", GlobalConstants.sheetTNDSBBID, GlobalConstants.appendResultRangeTNDSBB + row);
                    } else {

                        spreadsheet.appendDataToSpreadSheet("FAILED", GlobalConstants.sheetTNDSBBID, GlobalConstants.appendResultRangeTNDSBB + row);
                        spreadsheet.addNoteToCell(row - 1, row, 9, 10, 0, "CÓ", GlobalConstants.sheetTNDSBBID);

                    }
                    commonPage.clickToElement(driver, CommonPageUI.COMMON_TAB, "Thông tin xe");
                }
            }
        }
    }
}





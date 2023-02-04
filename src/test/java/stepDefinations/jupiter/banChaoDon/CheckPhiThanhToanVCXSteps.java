package stepDefinations.jupiter.banChaoDon;

import commons.GlobalConstants;
import commons.VerifyHelper;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumberOption.Hooks;
import org.openqa.selenium.WebDriver;
import pageUIs.alpaca.CommonPageUI;
import utils.excelutils.SpreadSheetIntegration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static stepDefinations.common.stepDefinations.CommonPageSteps.commonPage;

public class CheckPhiThanhToanVCXSteps {
    WebDriver driver;
    VerifyHelper verify;

    private SpreadSheetIntegration spreadsheet;

    public CheckPhiThanhToanVCXSteps() {
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

    @And("^Phi thanh toan VCX display correct value <Phí> when selecting \"([^\"]*)\" with value <Hãng Xe> and \"([^\"]*)\" with value <Phiên Bản> and \"([^\"]*)\" with value <Mục Đích Sử Dụng> and \"([^\"]*)\" with value <Loại Xe> and \"([^\"]*)\" with value <Nhóm Xe>$")
    public void checkPhiThanhToanVCX(String hangXe, String phienBan, String MĐSD,
                                     String loaiXe, String nhomXe, DataTable data) throws Exception {


        int row = 0;
        List<Map<String, Object>> VCXSheet;
        List<Map<String, Object>> hangHieuXeSheet;
        List<Map<String, Object>> hangHieuXeSheet1;

        List<String> optionNhomXeUIValues, optionLoaiXeUIValues,
                nhomXeDataTableValues, loaiXeDataTabaleValues;
        VCXSheet = new ArrayList<Map<String, Object>>();
        hangHieuXeSheet = new ArrayList<Map<String, Object>>();
        hangHieuXeSheet1 = new ArrayList<Map<String, Object>>();

        spreadsheet = new SpreadSheetIntegration();

        //  List<List<Object>> resultVCX = spreadsheet.readDataFromSpreadSheet(GlobalConstants.sheetVCXID, GlobalConstants.readRangeVCX);
        //  List<List<Object>> resultHangHieuXe = spreadsheet.readDataFromSpreadSheet(GlobalConstants.sheetHangHieuXeID, GlobalConstants.readRangeHangHieuXe);
        //  hangHieuXeSheet = spreadsheet.readSheet(GlobalConstants.sheetHangHieuXeID, GlobalConstants.readRangeHangHieuXe);
        VCXSheet = spreadsheet.readSheet(GlobalConstants.sheetVCXID, GlobalConstants.readRangeVCX);
        row = 0;

        for (Map<String, Object> VCXValue : VCXSheet) {
            row = row + 1;
            if (VCXValue.get("Hãng xe").equals("")) {
                break;
            }
            if ((!VCXValue.get("Hãng xe").equals("Hãng xe")) &&
                    VCXValue.get("Result").equals("RUN")) {

                commonPage.chonGiaTri(driver, hangXe, (String) VCXValue.get("Hãng xe"));
                commonPage.chonGiaTri(driver, phienBan, (String) VCXValue.get("Phiên bản"));
                commonPage.chonGiaTri(driver, MĐSD, (String) VCXValue.get("MĐSD"));
                commonPage.chonGiaTri(driver, nhomXe, (String) VCXValue.get("Nhóm xe"));
                commonPage.chonGiaTri(driver, loaiXe, (String) VCXValue.get("Loại xe"));

                commonPage.iInputDateTimePicker(driver, "Ngày đăng ký lần đầu", (String) VCXValue.get("Ngày ĐKLĐ"));

                commonPage.iInputDateTimePicker(driver, "Ngày bắt đầu", (String) VCXValue.get("Ngày bắt đầu"));
                commonPage.iInputDateTimePicker(driver, "Ngày kết thúc", (String) VCXValue.get("Ngày kết thúc"));

                commonPage.inputValueIntoTextbox(driver, "Giá trị xe thực tế", ((String) VCXValue.get("Giá trị xe")).replace(" ", ""));
                commonPage.inputValueIntoTextbox(driver, "Số chỗ ngồi", (String) VCXValue.get("Số chỗ"));

                if (!VCXValue.get("Nhóm xe").equals("Nhóm xe chở người")) {
                    commonPage.inputValueIntoTextbox(driver, "Trọng tải", (String) VCXValue.get("Trọng tải"));
                }
                commonPage.chonGiaTri(driver, "Nơi sản xuất", (String) VCXValue.get("Nơi sản xuất"));
                commonPage.chonGiaTri(driver, "Năm sản xuất", (String) VCXValue.get("Năm sản xuất"));
                commonPage.sleepInSecond(1);
                commonPage.clickToElement(driver, CommonPageUI.COMMON_TAB, "Loại hình bảo hiểm");

                commonPage.unCheckToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, "Vật chất xe");
                commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, "Vật chất xe");
                commonPage.inputValueLoaiHinhBH(driver, (String) VCXValue.get("STBH VCX"), GlobalConstants.STBH_LABEL, "Vật chất xe");
                commonPage.unCheckToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, "Bổ sung 05");
                String phiChuanPhamViCoBanUI = commonPage.getValueFromCell(driver, GlobalConstants.PHI_CHUAN_SAU_THUE_LABEL, "Phạm vi cơ bản").replace(",", ".");
                commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, "Bổ sung 05");

                commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, "Bổ sung 02");
                commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, "Bổ sung 04a");
                commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, "Bổ sung 04b");
                commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, "Bổ sung 07");
                commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, "Bổ sung 08");
                commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, "Bổ sung 01");
                commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, "Bổ sung 03");
                commonPage.selectValueLoaiHinhBH(driver, ((String) VCXValue.get("BS03")).replace(".", ","), GlobalConstants.STBH_LABEL, "Bổ sung 03");
                String phiChuanBoSung03UI = commonPage.getValueFromCell(driver, GlobalConstants.PHI_CHUAN_SAU_THUE_LABEL, "Bổ sung 03").replace(",", ".");

                commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, "Bổ sung 06");
                commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, "Bổ sung 09");
                commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, "Bổ sung 10");


                String phiChuanBoSung01UI = commonPage.getValueFromCell(driver, GlobalConstants.PHI_CHUAN_SAU_THUE_LABEL, "Bổ sung 01").replace(",", ".");
                String phiChuanBoSung02UI = commonPage.getValueFromCell(driver, GlobalConstants.PHI_CHUAN_SAU_THUE_LABEL, "Bổ sung 02").replace(",", ".");
                String phiChuanBoSung04AUI = commonPage.getValueFromCell(driver, GlobalConstants.PHI_CHUAN_SAU_THUE_LABEL, "Bổ sung 04a").replace(",", ".");
                String phiChuanBoSung04BUI = commonPage.getValueFromCell(driver, GlobalConstants.PHI_CHUAN_SAU_THUE_LABEL, "Bổ sung 04b").replace(",", ".");
                String phiChuanBoSung06UI = commonPage.getValueFromCell(driver, GlobalConstants.PHI_CHUAN_SAU_THUE_LABEL, "Bổ sung 06").replace(",", ".");
                String phiChuanBoSung07UI = commonPage.getValueFromCell(driver, GlobalConstants.PHI_CHUAN_SAU_THUE_LABEL, "Bổ sung 07").replace(",", ".");
                String phiChuanBoSung08UI = commonPage.getValueFromCell(driver, GlobalConstants.PHI_CHUAN_SAU_THUE_LABEL, "Bổ sung 08").replace(",", ".");
                String phiChuanBoSung09UI = commonPage.getValueFromCell(driver, GlobalConstants.PHI_CHUAN_SAU_THUE_LABEL, "Bổ sung 09").replace(",", ".");
                String phiChuanBoSung10UI = commonPage.getValueFromCell(driver, GlobalConstants.PHI_CHUAN_SAU_THUE_LABEL, "Bổ sung 10").replace(",", ".");
                String phiChuanBoSung05UI = commonPage.getValueFromCell(driver, GlobalConstants.PHI_CHUAN_SAU_THUE_LABEL, "Bổ sung 05").replace(",", ".");
                String tongPhiThanhToanSauThueUI = commonPage.getValueFromCell(driver, GlobalConstants.PHI_THANH_TOAN_SAU_THUE_LABEL, "Vật chất xe").replace(",", ".");

                String phiChuanPhamViCoBanExpected = (String) VCXValue.get("Phí cơ bản");
                String phiChuanBS01Expected = (String) VCXValue.get("Phí BS01 (gồm thuế)");
                String phiChuanBS02Expected = (String) VCXValue.get("Phí BS02 (gồm thuế)");
                String phiChuanBS03Expected = (String) VCXValue.get("Phí BS03 (gồm thuế)");
                String phiChuanBS04AExpected = (String) VCXValue.get("Phí BS04A (gồm thuế)");
                String phiChuanBS04BExpected = (String) VCXValue.get("Phí BS04B (gồm thuế)");
                String phiChuanBS05Expected = (String) VCXValue.get("Phí BS05 (gồm thuế)");
                String phiChuanBS06Expected = (String) VCXValue.get("Phí BS06 (gồm thuế)");
                String phiChuanBS07Expected = (String) VCXValue.get("Phí BS07 (gồm thuế)");
                String phiChuanBS08Expected = (String) VCXValue.get("Phí BS08 (gồm thuế)");
                String phiChuanBS09Expected = (String) VCXValue.get("Phí BS09 (gồm thuế)");
                String phiChuanBS010Expected = (String) VCXValue.get("Phí BS10 (gồm thuế)");
                String phiChuanPhiThanhToanSauThueExpected = (String) VCXValue.get("Tổng phí VCX (gồm thuế)");
                //  Assert.assertEquals(cellPhamViCoBan, TLPphamViCoBanUI.replace("%", ""));
                if (verify.verifyEquals(phiChuanPhamViCoBanExpected, phiChuanPhamViCoBanUI) &&
                        verify.verifyEquals(phiChuanBS01Expected, phiChuanBoSung01UI) &&
                        verify.verifyEquals(phiChuanBS02Expected, phiChuanBoSung02UI) &&
                        verify.verifyEquals(phiChuanBS03Expected, phiChuanBoSung03UI) &&
                        verify.verifyEquals(phiChuanBS04AExpected, phiChuanBoSung04AUI) &&
                        verify.verifyEquals(phiChuanBS04BExpected, phiChuanBoSung04BUI) &&
                        verify.verifyEquals(phiChuanBS05Expected, phiChuanBoSung05UI) &&
                        verify.verifyEquals(phiChuanBS06Expected, phiChuanBoSung06UI) &&
                        verify.verifyEquals(phiChuanBS07Expected, phiChuanBoSung07UI) &&
                        verify.verifyEquals(phiChuanBS08Expected, phiChuanBoSung08UI) &&
                        verify.verifyEquals(phiChuanBS09Expected, phiChuanBoSung09UI) &&
                        verify.verifyEquals(phiChuanBS010Expected, phiChuanBoSung10UI) &&
                        verify.verifyEquals(phiChuanPhiThanhToanSauThueExpected, tongPhiThanhToanSauThueUI) )
                {
                    spreadsheet.appendDataToSpreadSheet("PASSED", GlobalConstants.sheetVCXID, GlobalConstants.appendResultRangeVCX + row);

                } else {
                    spreadsheet.appendDataToSpreadSheet("FAILED", GlobalConstants.sheetVCXID, GlobalConstants.appendResultRangeVCX + row);
                    //   spreadsheet.appendDataToSpreadSheet(row, GlobalConstants.sheetVCXID, "BE" + row);
                    spreadsheet.addNoteToCell(row - 1, row, 41, 42, 0, phiChuanPhamViCoBanUI, GlobalConstants.sheetVCXID);
                    spreadsheet.addNoteToCell(row - 1, row, 42, 43, 0, phiChuanBoSung01UI, GlobalConstants.sheetVCXID);
                    spreadsheet.addNoteToCell(row - 1, row, 43, 44, 0, phiChuanBoSung02UI, GlobalConstants.sheetVCXID);
                    spreadsheet.addNoteToCell(row - 1, row, 44, 45, 0, phiChuanBoSung03UI, GlobalConstants.sheetVCXID);
                    spreadsheet.addNoteToCell(row - 1, row, 45, 46, 0, phiChuanBoSung04AUI, GlobalConstants.sheetVCXID);
                    spreadsheet.addNoteToCell(row - 1, row, 46, 47, 0, phiChuanBoSung04BUI, GlobalConstants.sheetVCXID);
                    spreadsheet.addNoteToCell(row - 1, row, 47, 48, 0, phiChuanBoSung05UI, GlobalConstants.sheetVCXID);
                    spreadsheet.addNoteToCell(row - 1, row, 48, 49, 0, phiChuanBoSung06UI, GlobalConstants.sheetVCXID);
                    spreadsheet.addNoteToCell(row - 1, row, 49, 50, 0, phiChuanBoSung07UI, GlobalConstants.sheetVCXID);
                    spreadsheet.addNoteToCell(row - 1, row, 50, 51, 0, phiChuanBoSung08UI, GlobalConstants.sheetVCXID);
                    spreadsheet.addNoteToCell(row - 1, row, 51, 52, 0, phiChuanBoSung09UI, GlobalConstants.sheetVCXID);
                    spreadsheet.addNoteToCell(row - 1, row, 52, 53, 0, phiChuanBoSung10UI, GlobalConstants.sheetVCXID);
                    spreadsheet.addNoteToCell(row - 1, row, 53, 54, 0, tongPhiThanhToanSauThueUI, GlobalConstants.sheetVCXID);

                }
               // commonPage.sleepInSecond(1);
                commonPage.clickToElement(driver, CommonPageUI.COMMON_TAB, "Thông tin xe");

            }
            // break;
        }
    }
}
//  }








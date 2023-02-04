package stepDefinations.jupiter.banChaoDon;

import commons.GlobalConstants;
import commons.VerifyHelper;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumberOption.Hooks;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.WebDriver;
import pageObjects.jupiter.DanhSachBanChaoPageObject;
import pageObjects.jupiter.TaoBanChaoPageObject;
import pageUIs.alpaca.CommonPageUI;
import utils.excelutils.ExcelReader;
import utils.excelutils.ExcelUtil;
import utils.excelutils.SpreadSheetIntegration;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.*;
import java.util.stream.Collectors;

import static stepDefinations.common.stepDefinations.CommonPageSteps.commonPage;

public class CheckPhiThanhToanTNDSTNSteps {
    WebDriver driver;
    VerifyHelper verify;
    TaoBanChaoPageObject taoBanChaoPage;
    DanhSachBanChaoPageObject danhSachBanChaoPage;
    ExcelReader externalData;
    List<Map<String, String>> testData;
    List<String> optionUIValues, dataTableValues;
    ExcelUtil excelFile;
    XSSFRow rowData;
    private SpreadSheetIntegration spreadsheet;
    private List<List<Object>> object;

    public CheckPhiThanhToanTNDSTNSteps() {
        driver = Hooks.openAndQuitBrowser();
        verify = VerifyHelper.getData();
    }


    @Then("^Phi thanh toan TNDSTN display correct value <Phí> when selecting \"([^\"]*)\" with value <Hãng Xe> and \"([^\"]*)\" with value <Phiên Bản> and \"([^\"]*)\" with value <Mục Đích Sử Dụng> and \"([^\"]*)\" with value <Loại Xe> and \"([^\"]*)\" with value <Nhóm Xe>$")
    public void checkTyLeTNDSTN(String hangXe, String phienBan, String MĐSD,
                                String loaiXe, String nhomXe, DataTable data) throws Exception {
        externalData = new ExcelReader();
        excelFile = new ExcelUtil();
        int row = 0;
        List<Map<String, Object>> TNDSTNSheet;
        List<Map<String, Object>> hangHieuXeSheet;

        TNDSTNSheet = new ArrayList<Map<String, Object>>();
        hangHieuXeSheet = new ArrayList<Map<String, Object>>();
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("JavaScript");

        spreadsheet = new SpreadSheetIntegration();

    //    List<List<Object>> resultTNDSTN = spreadsheet.readDataFromSpreadSheet(GlobalConstants.sheetTNDSTNID, GlobalConstants.readRangeTNDSTN);
     //   List<List<Object>> resultHangHieuXe = spreadsheet.readDataFromSpreadSheet(GlobalConstants.sheetHangHieuXeID, GlobalConstants.readRangeHangHieuXe);
        //  System.out.println("Read result+ "+result);
      //  TNDSTNSheet = resultTNDSTN.stream().map(SpreadSheetIntegration::toMapTNDSTN).collect(Collectors.toList());
     //   hangHieuXeSheet = resultHangHieuXe.stream().map(SpreadSheetIntegration::toMapHangHieuXe).collect(Collectors.toList());
        TNDSTNSheet=spreadsheet.readSheet(GlobalConstants.sheetTNDSTNID, GlobalConstants.readRangeTNDSTN);

        /*for (Map<String, Object> hangHieuXevalue : hangHieuXeSheet) {
            if (hangHieuXevalue.get("Hãng xe").equals("")) {
                break;
            }
            row = 1;
*/
          /*  if (!hangHieuXevalue.get("Hãng xe").equals("Hãng xe")) {
                commonPage.chonGiaTri(driver, hangXe, (String) hangHieuXevalue.get("Hãng xe"));
                commonPage.chonGiaTri(driver, phienBan, (String) hangHieuXevalue.get("Phiên bản"));
                commonPage.chonGiaTri(driver, MĐSD, (String) hangHieuXevalue.get("MĐSD"));
                commonPage.chonGiaTri(driver, nhomXe, (String) hangHieuXevalue.get("Nhóm xe"));
                commonPage.chonGiaTri(driver, loaiXe, (String) hangHieuXevalue.get("Loại xe"));*/

                // commonPage.sleepInSecond(1);
                for (Map<String, Object> TNDSTNValue : TNDSTNSheet) {
                    row = row + 1;
                    if (TNDSTNValue.get("Hãng xe").equals("")) {
                        break;
                    }
                    if (!TNDSTNValue.get("Hãng xe").equals("Hãng xe")
                    && TNDSTNValue.get("Result").equals("RUN")){
                        commonPage.chonGiaTri(driver, hangXe, (String) TNDSTNValue.get("Hãng xe"));
                        commonPage.chonGiaTri(driver, phienBan, (String) TNDSTNValue.get("Phiên bản"));
                        commonPage.chonGiaTri(driver, MĐSD, (String) TNDSTNValue.get("MĐSD"));
                        commonPage.chonGiaTri(driver, nhomXe, (String) TNDSTNValue.get("Nhóm xe"));
                        commonPage.chonGiaTri(driver, loaiXe, (String) TNDSTNValue.get("Loại xe"));

                    /*if (TNDSTNValue.get("Hãng xe").equals(TNDSTNValue.get("Hãng xe"))
                            && TNDSTNValue.get("Phiên bản").equals(TNDSTNValue.get("Phiên bản"))
                            && TNDSTNValue.get("MĐSD").equals(TNDSTNValue.get("MĐSD"))
                            && TNDSTNValue.get("Nhóm xe").equals(hangHieuXevalue.get("Nhóm xe"))
                            && TNDSTNValue.get("Loại xe").equals(hangHieuXevalue.get("Loại xe"))) {*/
                        commonPage.inputValueIntoTextbox(driver, "Số chỗ ngồi", (String) TNDSTNValue.get("Số chỗ"));

                        if (!TNDSTNValue.get("Nhóm xe").equals("Nhóm xe chở người")) {
                            commonPage.inputValueIntoTextbox(driver, "Trọng tải", (String) TNDSTNValue.get("Trọng tải"));
                        }

                        commonPage.iInputDateTimePicker(driver, "Ngày bắt đầu", (String) TNDSTNValue.get("Ngày bắt đầu"));
                        commonPage.iInputDateTimePicker(driver, "Ngày kết thúc", (String) TNDSTNValue.get("Ngày kết thúc"));
                        commonPage.sleepInSecond(2);
                        commonPage.clickToElement(driver, CommonPageUI.COMMON_TAB, "Loại hình bảo hiểm");
                        commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, "TNDS tự nguyện");

                        String phiThanhToanVeNguoiUI = "";
                        String phiThanhToanVeNguoiExpected = "";
                        String tyLePhiVeNguoiExpected = "";

                        String phiThanhToanVeTaiSanUI = "";
                        String phiThanhToanVeTaiSanExpected = "";
                        String tyLePhiVeTaiSanExpected = "";

                        String phiThanhToanVeHanhKhachUI = "";
                        String phiThanhToanVeHanhKhachExpected = "";
                        String tyLePhiVeHanhKhachExpected = "";

                        String phiThanhToanVeHangHoaUI = "";
                        String phiThanhToanVeHangHoaExpected = "";
                        String tyLePhiVeHangHoaExpected = "";

                        String MTN_BT3_VE_NGUOI_IS_DISPLAY = "CO";
                        String MTN_BT3_VE_TAI_SAN_IS_DISPLAY = "CO";
                        String MTN_VE_HANH_KHACH_IS_DISPLAY = "CO";
                        String MTN_VE_HANG_HOA_IS_DISPLAY = "CO";
                        String heSo = "";
                        boolean phiVeNguoi = true;
                        boolean phiVeTaiSan = true;
                        boolean phiVeHanhKhach = true;
                        boolean phiVeHangHoa = true;

                        phiThanhToanVeNguoiExpected = ((String) TNDSTNValue.get("Phí thanh toán BT3 về người (gồm thuế)")).replace(" ", "");
                        phiThanhToanVeTaiSanExpected = ((String) TNDSTNValue.get("Phí thanh toán BT3 về tài sản (gồm thuế)")).replace(" ", "");
                        phiThanhToanVeHanhKhachExpected = ((String) TNDSTNValue.get("Phí thanh toán về hành khách (gồm thuế)")).replace(" ", "");

                        phiThanhToanVeHangHoaExpected = ((String) TNDSTNValue.get("Phí thanh toán về hàng hóa (gồm thuế)")).replace(" ", "");


                        tyLePhiVeNguoiExpected = ((String) TNDSTNValue.get("TLP thanh toán BT3 về người (gồm thuế)")).replace(" ", "");

                        tyLePhiVeTaiSanExpected = ((String) TNDSTNValue.get("TLP thanh toán BT3 về tài sản (gồm thuế)")).replace(" ", "");

                        tyLePhiVeHanhKhachExpected = ((String) TNDSTNValue.get("TLP thanh toán về hành khách (gồm thuế)")).replace(" ", "");

                        tyLePhiVeHangHoaExpected = ((String) TNDSTNValue.get("TLP thanh toán về hàng hoá (gồm thuế)")).replace(" ", "");


                        heSo = (String) TNDSTNValue.get("Hệ số ngắn hạn dài hạn");
                        if (!phiThanhToanVeNguoiExpected.contains("KC")) {
                            //   System.out.println("Phi Ve Nguoi " + phiThanhToanVeNguoiExpected);

                            commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, GlobalConstants.MTN_BT3_VE_NGUOI_LABEL);
                            commonPage.inputValueLoaiHinhBH(driver, (String) TNDSTNValue.get(GlobalConstants.MTN_BT3_VE_NGUOI_LABEL), GlobalConstants.STBH_LABEL, GlobalConstants.MTN_BT3_VE_NGUOI_LABEL);


                            phiThanhToanVeNguoiUI = commonPage.getValueFromCell(driver, GlobalConstants.PHI_THANH_TOAN_SAU_THUE_LABEL, GlobalConstants.MTN_BT3_VE_NGUOI_LABEL).replace(",", ".");
                            ;
                            phiVeNguoi = verify.verifyEquals(phiThanhToanVeNguoiExpected, phiThanhToanVeNguoiUI);

                            // tyLePhiVeNguoiExpected = excelFile.getCellData(row, 16);
                        } else {
                            if (commonPage.isElementNotDisplay(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, GlobalConstants.MTN_BT3_VE_NGUOI_LABEL)) {
                                //excelFile.setCellData("PASSED", row, 26);
                                MTN_BT3_VE_NGUOI_IS_DISPLAY = "KC";

                            } else {
                                spreadsheet.appendDataToSpreadSheet("FAILED", GlobalConstants.sheetTNDSTNID, GlobalConstants.appendResultRangeTNDSTN + row);
                                spreadsheet.addNoteToCell(row - 1, row, 24, 25, 0, "CÓ", GlobalConstants.sheetTNDSTNID);

                            /*excelFile.setCellData("FAILED", row, 26);
                            excelFile.setCellComment("CÓ", row, 20);*/
                            }
                            //  commonPage.clickToElement(driver, CommonPageUI.COMMON_TAB, "Thông tin xe");
                        }

                        if (!phiThanhToanVeTaiSanExpected.contains("KC")) {
                            commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, GlobalConstants.MTN_BT3_VE_TAI_SAN_LABEL);
                            commonPage.inputValueLoaiHinhBH(driver, (String) TNDSTNValue.get(GlobalConstants.MTN_BT3_VE_TAI_SAN_LABEL), GlobalConstants.STBH_LABEL, GlobalConstants.MTN_BT3_VE_TAI_SAN_LABEL);

                            phiThanhToanVeTaiSanUI = commonPage.getValueFromCell(driver, GlobalConstants.PHI_THANH_TOAN_SAU_THUE_LABEL, GlobalConstants.MTN_BT3_VE_TAI_SAN_LABEL).replace(",", ".");
                            ;

                            phiVeTaiSan = verify.verifyEquals(phiThanhToanVeTaiSanExpected, phiThanhToanVeTaiSanUI);

                        } else {
                            if (commonPage.isElementNotDisplay(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, GlobalConstants.MTN_BT3_VE_TAI_SAN_LABEL)) {
                                //excelFile.setCellData("PASSED", row, 26);
                                MTN_BT3_VE_TAI_SAN_IS_DISPLAY = "KC";

                            } else {
                                spreadsheet.appendDataToSpreadSheet("FAILED", GlobalConstants.sheetTNDSTNID, GlobalConstants.appendResultRangeTNDSTN + row);
                                spreadsheet.addNoteToCell(row - 1, row, 25, 26, 0, "CÓ", GlobalConstants.sheetTNDSTNID);

                            /*excelFile.setCellData("FAILED", row, 26);
                            excelFile.setCellComment("CÓ", row, 21);*/
                            }
                            //  commonPage.clickToElement(driver, CommonPageUI.COMMON_TAB, "Thông tin xe");
                        }

                        if (!phiThanhToanVeHanhKhachExpected.contains("KC")) {
                            commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, GlobalConstants.MTN_VE_HANH_KHACH_LABEL);
                            commonPage.inputValueLoaiHinhBH(driver, (String) TNDSTNValue.get(GlobalConstants.MTN_VE_HANH_KHACH_LABEL), GlobalConstants.STBH_LABEL, GlobalConstants.MTN_VE_HANH_KHACH_LABEL);

                            phiThanhToanVeHanhKhachUI = commonPage.getValueFromCell(driver, GlobalConstants.PHI_THANH_TOAN_SAU_THUE_LABEL, GlobalConstants.MTN_VE_HANH_KHACH_LABEL).replace(",", ".");
                            ;

                            phiVeHanhKhach = verify.verifyEquals(phiThanhToanVeHanhKhachExpected, phiThanhToanVeHanhKhachUI);

                        } else {
                            if (commonPage.isElementNotDisplay(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, GlobalConstants.MTN_VE_HANH_KHACH_LABEL)) {
                                //excelFile.setCellData("PASSED", row, 26);
                                MTN_VE_HANH_KHACH_IS_DISPLAY = "KC";

                            } else {
                                spreadsheet.appendDataToSpreadSheet("FAILED", GlobalConstants.sheetTNDSTNID, GlobalConstants.appendResultRangeTNDSTN + row);
                                spreadsheet.addNoteToCell(row - 1, row, 26, 27, 0, "CÓ", GlobalConstants.sheetTNDSTNID);

                            /*excelFile.setCellData("FAILED", row, 26);
                            excelFile.setCellComment("CÓ", row, 22);*/
                            }
                            //  commonPage.clickToElement(driver, CommonPageUI.COMMON_TAB, "Thông tin xe");
                        }
                        if (!phiThanhToanVeHangHoaExpected.contains("KC")) {
                            commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, GlobalConstants.MTN_VE_HANG_HOA_LABEL);
                            commonPage.inputValueLoaiHinhBH(driver, (String) TNDSTNValue.get("STBH/tấn"), GlobalConstants.THONG_TIN_BO_SUNG_LABEL, GlobalConstants.STBH_TAN_LABEL);
                            // String STBH_HANG_HOA = commonPage.getValueFromCell(driver, GlobalConstants.STBH_LABEL, GlobalConstants.MTN_VE_HANG_HOA_LABEL);
                            phiThanhToanVeHangHoaUI = commonPage.getValueFromCell(driver, GlobalConstants.PHI_THANH_TOAN_SAU_THUE_LABEL, GlobalConstants.MTN_VE_HANG_HOA_LABEL).replace(",", ".");

                            phiVeHangHoa = verify.verifyEquals(phiThanhToanVeHangHoaExpected, phiThanhToanVeHangHoaUI);

                        } else {
                            if (commonPage.isElementNotDisplay(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, GlobalConstants.MTN_VE_HANG_HOA_LABEL)) {
                                //excelFile.setCellData("PASSED", row, 26);
                                MTN_VE_HANG_HOA_IS_DISPLAY = "KC";
                            } else {
                                spreadsheet.appendDataToSpreadSheet("FAILED", GlobalConstants.sheetTNDSTNID, GlobalConstants.appendResultRangeTNDSTN + row);
                                spreadsheet.addNoteToCell(row - 1, row, 27, 28, 0, "CÓ", GlobalConstants.sheetTNDSTNID);

                           /* excelFile.setCellData("FAILED", row, 26);
                            excelFile.setCellComment("CÓ", row, 23);*/
                            }
                            //  commonPage.clickToElement(driver, CommonPageUI.COMMON_TAB, "Thông tin xe");
                        }

                        if (phiVeNguoi && phiVeTaiSan && phiVeHanhKhach && phiVeHangHoa) {
                            spreadsheet.appendDataToSpreadSheet("PASSED", GlobalConstants.sheetTNDSTNID, GlobalConstants.appendResultRangeTNDSTN + row);
                            //excelFile.setCellData("PASSED", row, 26);
                        } else if (MTN_BT3_VE_NGUOI_IS_DISPLAY.equals("KC")
                                && MTN_BT3_VE_TAI_SAN_IS_DISPLAY.equals("KC")
                                && MTN_VE_HANH_KHACH_IS_DISPLAY.equals("KC")
                                && MTN_VE_HANG_HOA_IS_DISPLAY.equals("KC")) {
                            spreadsheet.appendDataToSpreadSheet("PASSED", GlobalConstants.sheetTNDSTNID, GlobalConstants.appendResultRangeTNDSTN + row);

                            //excelFile.setCellData("PASSED", row, 26);
                        } else {
                                       /* excelFile.setCellData("FAILED", row, 26);
                                        excelFile.setCellComment("Actual value: " + phiThanhToanVeNguoiUI, row, 20);
                                        excelFile.setCellComment("Actual value: " + phiThanhToanVeTaiSanUI, row, 21);
                                        excelFile.setCellComment("Actual value: " + phiThanhToanVeHanhKhachUI, row, 22);
                                        excelFile.setCellComment("Actual value: " + phiThanhToanVeHangHoaUI, row, 23);*/

                            spreadsheet.appendDataToSpreadSheet("FAILED", GlobalConstants.sheetTNDSTNID, GlobalConstants.appendResultRangeTNDSTN + row);
                            spreadsheet.addNoteToCell(row - 1, row, 24, 25, 0, phiThanhToanVeNguoiUI, GlobalConstants.sheetTNDSTNID);
                            spreadsheet.addNoteToCell(row - 1, row, 25, 26, 0, phiThanhToanVeTaiSanUI, GlobalConstants.sheetTNDSTNID);
                            spreadsheet.addNoteToCell(row - 1, row, 26, 27, 0, phiThanhToanVeHanhKhachUI, GlobalConstants.sheetTNDSTNID);
                            spreadsheet.addNoteToCell(row - 1, row, 27, 28, 0, phiThanhToanVeHangHoaUI, GlobalConstants.sheetTNDSTNID);
                        }
                        commonPage.clickToElement(driver, CommonPageUI.COMMON_TAB, "Thông tin xe");
                    }
                }
            }
        }

















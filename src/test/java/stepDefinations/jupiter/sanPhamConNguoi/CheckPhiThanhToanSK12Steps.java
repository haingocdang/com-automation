package stepDefinations.jupiter.sanPhamConNguoi;

import commons.GlobalConstants;
import commons.VerifyHelper;
import cucumber.api.java.en.And;
import cucumberOption.Hooks;
import org.openqa.selenium.WebDriver;
import pageUIs.alpaca.CommonPageUI;
import utils.excelutils.SpreadSheetIntegration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static stepDefinations.common.stepDefinations.CommonPageSteps.commonPage;

public class CheckPhiThanhToanSK12Steps {
    WebDriver driver;
    VerifyHelper verify;

    private SpreadSheetIntegration spreadsheet;

    public CheckPhiThanhToanSK12Steps() {
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

    @And("^Phi thanh toan SK12 display correct value")
    public void checkPhiThanhToanVCX() throws Exception {


        int row;
        List<Map<String, Object>> SK12Sheet,quyenLoiBHSheet ;
        SK12Sheet = new ArrayList<Map<String, Object>>();
        quyenLoiBHSheet = new ArrayList<Map<String, Object>>();
        spreadsheet = new SpreadSheetIntegration();

        SK12Sheet = spreadsheet.readSheet(GlobalConstants.sheetSK12ID, GlobalConstants.readRangeSK12Sheet);
        quyenLoiBHSheet = spreadsheet.readSheet(GlobalConstants.sheetSK12ID, GlobalConstants.QUYENLOIBH_SHEET_NAME+GlobalConstants.readRangeQuyenLoiBHSheet);
        row = 0;
        String mucTrachNhiemQLBHChinhUI, mucTrachnhiemQLBHChinhExpected,
                phiChuanQLBHChinhUI, phiChuanQLBHChinhExpected,
                phiThanhToanQLBHChinhUI, phiThanhToanQLBHChinhExpected,
                mucTrachNhiemDieuTriNgoaiTruUI, mucTrachNhiemDieuTriNgoaiTruExpected,
                phiChuanDieuTriNgoaiTruUI, phiChuanDieuTriNgoaiTruExpected,
                phiThanhToanDieuTriNgoaiTruUI, phiThanhToanDieuTriNgoaiTruExpected,
                mucTrachNhiemDieuTriNhaKhoaUI, mucTrachNhiemDieuTriNhaKhoaExpected,
                phiChuanDieuTriNhaKhoaUI, phiChuanDieuTriNhaKhoaExpected,
                phiThanhToanDieuTriNhaKhoaUI, phiThanhToanDieuTriNhaKhoaExpected;
        mucTrachNhiemQLBHChinhUI = "0";
        mucTrachnhiemQLBHChinhExpected = "0";
        phiChuanQLBHChinhUI = "0";
        phiChuanQLBHChinhExpected = "0";
        phiThanhToanQLBHChinhUI = "0";
        phiThanhToanQLBHChinhExpected = "0";
        mucTrachNhiemDieuTriNgoaiTruUI = "0";
        mucTrachNhiemDieuTriNgoaiTruExpected = "0";
        phiChuanDieuTriNgoaiTruUI = "0";
        phiChuanDieuTriNgoaiTruExpected = "0";
        phiThanhToanDieuTriNgoaiTruUI = "0";
        phiThanhToanDieuTriNgoaiTruExpected = "0";
        mucTrachNhiemDieuTriNhaKhoaUI = "0";
        mucTrachNhiemDieuTriNhaKhoaExpected = "0";
        phiChuanDieuTriNhaKhoaUI = "0";
        phiChuanDieuTriNhaKhoaExpected = "0";
        phiThanhToanDieuTriNhaKhoaUI = "0";
        phiThanhToanDieuTriNhaKhoaExpected = "0";

        for (Map<String, Object> SK12Value : SK12Sheet) {
            row = row + 1;
            if (SK12Value.get(GlobalConstants.hoVaTen).equals("")) {
                break;
            }
            if (!(GlobalConstants.khongApDung.equals((String) SK12Value.get("Phí chuẩn Chi phí y tế điều trị tai nạn, ốm đau bệnh tật")))) {

                if (SK12Value.get("Execute").equals("RUN")) {
                    commonPage.clickToElementByJS(driver, CommonPageUI.COMMON_ICON, "Thêm thông tin đối tượng");
                    commonPage.inputValueIntoTextbox(driver, GlobalConstants.hoVaTen, (String) SK12Value.get(GlobalConstants.hoVaTen));
                    commonPage.inputValueIntoTextbox(driver, GlobalConstants.ngaySinh, (String) SK12Value.get(GlobalConstants.ngaySinh));
                    commonPage.chonGiaTri(driver, GlobalConstants.gioiTinh, (String) SK12Value.get(GlobalConstants.gioiTinh));
                    commonPage.chonGiaTri(driver, GlobalConstants.loaiDinhDanh, (String) SK12Value.get(GlobalConstants.loaiDinhDanh));
                    commonPage.inputValueIntoTextbox(driver, GlobalConstants.soDinhDanh, (String) SK12Value.get(GlobalConstants.soDinhDanh));
                    commonPage.chonGiaTri(driver, GlobalConstants.loaiDiaChi, (String) SK12Value.get(GlobalConstants.loaiDiaChi));
                    commonPage.chonGiaTri(driver, GlobalConstants.quocGia, (String) SK12Value.get(GlobalConstants.quocGia));
                    commonPage.chonGiaTri(driver, GlobalConstants.tinhThanhPho, (String) SK12Value.get(GlobalConstants.tinhThanhPho));
                    commonPage.chonGiaTri(driver, GlobalConstants.quanHuyen, (String) SK12Value.get(GlobalConstants.quanHuyen));
                    commonPage.chonGiaTri(driver, GlobalConstants.phuongXa, (String) SK12Value.get(GlobalConstants.phuongXa));
                    commonPage.inputValueIntoTextbox(driver, GlobalConstants.diaChi, (String) SK12Value.get(GlobalConstants.diaChi));
                    commonPage.chonGiaTri(driver, GlobalConstants.loaiNgheNghiep, (String) SK12Value.get(GlobalConstants.loaiNgheNghiep));
                    commonPage.inputValueIntoTextbox(driver, GlobalConstants.soDienThoai, (String) SK12Value.get(GlobalConstants.soDienThoai));


                    commonPage.clickToElementByJS(driver, CommonPageUI.COMMON_TAB, GlobalConstants.quyenLoiBHTab);
                    commonPage.sleepInSecond(2);
                    commonPage.chonGiaTri(driver, "Chương trình bảo hiểm", (String) SK12Value.get("Chương trình bảo hiểm"));
                    commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, "Chi phí y tế điều trị tai nạn, ốm đau bệnh tật");

                    commonPage.inputValueLoaiHinhBH(driver, ((String) SK12Value.get("Tỷ lệ giảm phí")).replace(",", "."), "Tỷ lệ giảm phí", "Chi phí y tế điều trị tai nạn, ốm đau bệnh tật");

                    mucTrachNhiemQLBHChinhUI = commonPage.getValueFromCell(driver, GlobalConstants.MUC_TRACH_NHIEM_LABEL, "Chi phí y tế điều trị tai nạn, ốm đau bệnh tật").replace(",", ".");
                    mucTrachnhiemQLBHChinhExpected = (String) SK12Value.get("Mức trách nhiệm Chi phí y tế điều trị tai nạn, ốm đau bệnh tật");
                    phiChuanQLBHChinhUI = commonPage.getValueFromCell(driver, GlobalConstants.PHI_CHUAN_LABEL, "Chi phí y tế điều trị tai nạn, ốm đau bệnh tật").replace(",", ".");
                    phiChuanQLBHChinhExpected = (String) SK12Value.get("Phí chuẩn Chi phí y tế điều trị tai nạn, ốm đau bệnh tật");
                    phiThanhToanQLBHChinhUI = commonPage.getValueFromCell(driver, GlobalConstants.PHI_THANH_TOAN_LABEL, "Chi phí y tế điều trị tai nạn, ốm đau bệnh tật").replace(",", ".");
                    phiThanhToanQLBHChinhExpected = (String) SK12Value.get("Phí thanh toán Chi phí y tế điều trị tai nạn, ốm đau bệnh tật");

                    if (!(GlobalConstants.khongApDung.equals((String) SK12Value.get("Phí chuẩn Điều trị ngoại trú do ốm bện")))) {

                        commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, "Điều trị ngoại trú do ốm bệnh");
                        commonPage.inputValueLoaiHinhBH(driver, ((String) SK12Value.get(GlobalConstants.tiLeGiamPhi)).replace(",", "."), GlobalConstants.tiLeGiamPhi, "Điều trị ngoại trú do ốm bệnh");
                        mucTrachNhiemDieuTriNgoaiTruUI = commonPage.getValueFromCell(driver, GlobalConstants.MUC_TRACH_NHIEM_LABEL, "Điều trị ngoại trú do ốm bệnh").replace(",", ".");
                        mucTrachNhiemDieuTriNgoaiTruExpected = (String) SK12Value.get("Mức trách nhiệm Điều trị ngoại trú do ốm bệnh");
                        phiChuanDieuTriNgoaiTruUI = commonPage.getValueFromCell(driver, GlobalConstants.PHI_CHUAN_LABEL, "Điều trị ngoại trú do ốm bệnh").replace(",", ".");
                        phiChuanDieuTriNgoaiTruExpected = (String) SK12Value.get("Phí chuẩn Điều trị ngoại trú do ốm bệnh");
                        phiThanhToanDieuTriNgoaiTruUI = commonPage.getValueFromCell(driver, GlobalConstants.PHI_THANH_TOAN_LABEL, "Điều trị ngoại trú do ốm bệnh").replace(",", ".");
                        phiThanhToanDieuTriNgoaiTruExpected = (String) SK12Value.get("Phí thanh toán Điều trị ngoại trú do ốm bệnh");
                    }

                    if (!(GlobalConstants.khongApDung.equals((String) SK12Value.get("Phí chuẩn Điều trị nha khoa")))) {
                        commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, "Điều trị nha khoa");
                        commonPage.inputValueLoaiHinhBH(driver, ((String) SK12Value.get(GlobalConstants.tiLeGiamPhi)).replace(",", "."), GlobalConstants.tiLeGiamPhi, "Điều trị nha khoa");
                        mucTrachNhiemDieuTriNhaKhoaUI = commonPage.getValueFromCell(driver, GlobalConstants.MUC_TRACH_NHIEM_LABEL, "Điều trị nha khoa").replace(",", ".");
                        mucTrachNhiemDieuTriNhaKhoaExpected = (String) SK12Value.get("Mức trách nhiệm Điều trị nha khoa");
                        phiChuanDieuTriNhaKhoaUI = commonPage.getValueFromCell(driver, GlobalConstants.PHI_CHUAN_LABEL, "Điều trị nha khoa").replace(",", ".");
                        phiChuanDieuTriNhaKhoaExpected = (String) SK12Value.get("Phí chuẩn Điều trị nha khoa");
                        phiThanhToanDieuTriNhaKhoaUI = commonPage.getValueFromCell(driver, GlobalConstants.PHI_THANH_TOAN_LABEL, "Điều trị nha khoa").replace(",", ".");
                        phiThanhToanDieuTriNhaKhoaExpected = (String) SK12Value.get("Phí thanh toán Điều trị nha khoa");
                    }

                    if (!(GlobalConstants.khongApDung.equals((String) SK12Value.get("Phí chuẩn Tai nạn cá nhân")))) {
                        commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, "Tai nạn cá nhân");
                        commonPage.inputValueLoaiHinhBH(driver, ((String) SK12Value.get("Mức trách nhiệm Tai nạn cá nhân")).replace(",", "."), GlobalConstants.mucTrachNhiem, "Tai nạn cá nhân");
                        commonPage.sleepInSecond(1);
                        commonPage.inputValueLoaiHinhBH(driver, ((String) SK12Value.get(GlobalConstants.tiLeGiamPhi)).replace(",", "."), GlobalConstants.tiLeGiamPhi, "Tai nạn cá nhân");

                    }

                    if (!(GlobalConstants.khongApDung.equals((String) SK12Value.get("Phí chuẩn Sinh mạng cá nhân")))) {
                        commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, "Sinh mạng cá nhân");
                        commonPage.inputValueLoaiHinhBH(driver, ((String) SK12Value.get("Mức trách nhiệm Sinh mạng cá nhân")).replace(",", "."), GlobalConstants.mucTrachNhiem, "Sinh mạng cá nhân");
                        commonPage.sleepInSecond(1);

                        commonPage.inputValueLoaiHinhBH(driver, ((String) SK12Value.get(GlobalConstants.tiLeGiamPhi)).replace(",", "."), GlobalConstants.tiLeGiamPhi, "Sinh mạng cá nhân");

                    }

                    if (!(GlobalConstants.khongApDung.equals((String) SK12Value.get("Phí chuẩn thai sản")))) {
                        commonPage.checkToCheckbox(driver, CommonPageUI.LOAI_HINH_BH_CHECKBOX, "Thai sản");
                    }


                    if ((verify.verifyEquals(mucTrachnhiemQLBHChinhExpected, mucTrachNhiemQLBHChinhUI)) &&
                            (verify.verifyEquals(phiChuanQLBHChinhUI, phiChuanQLBHChinhExpected)) &&
                            (verify.verifyEquals(phiThanhToanQLBHChinhExpected, phiThanhToanQLBHChinhUI)) &&

                            (verify.verifyEquals(mucTrachNhiemDieuTriNgoaiTruExpected, mucTrachNhiemDieuTriNgoaiTruUI)) &&
                            (verify.verifyEquals(phiChuanDieuTriNgoaiTruExpected, phiChuanDieuTriNgoaiTruUI)) &&
                            (verify.verifyEquals(phiThanhToanDieuTriNgoaiTruExpected, phiThanhToanDieuTriNgoaiTruUI)) &&

                            (verify.verifyEquals(mucTrachNhiemDieuTriNhaKhoaExpected, mucTrachNhiemDieuTriNhaKhoaUI)) &&
                            (verify.verifyEquals(phiChuanDieuTriNhaKhoaExpected, phiChuanDieuTriNhaKhoaUI)) &&
                            (verify.verifyEquals(phiThanhToanDieuTriNhaKhoaExpected, phiThanhToanDieuTriNhaKhoaUI))
                    ) {
                        spreadsheet.appendDataToSpreadSheet("PASSED", GlobalConstants.sheetSK12ID, GlobalConstants.appendResultRangeSK12 + row);

                    } else {
                        spreadsheet.appendDataToSpreadSheet("FAILED", GlobalConstants.sheetSK12ID, GlobalConstants.appendResultRangeSK12 + row);
                        //   spreadsheet.appendDataToSpreadSheet(row, GlobalConstants.sheetVCXID, "BE" + row);
                        spreadsheet.addNoteToCell(row - 1, row, 20, 21, 0, mucTrachNhiemQLBHChinhUI, GlobalConstants.sheetSK12ID);

                        spreadsheet.addNoteToCell(row - 1, row, 28, 29, 0, phiChuanQLBHChinhUI, GlobalConstants.sheetSK12ID);

                        spreadsheet.addNoteToCell(row - 1, row, 35, 36, 0, phiThanhToanQLBHChinhUI, GlobalConstants.sheetSK12ID);

                        spreadsheet.addNoteToCell(row - 1, row, 21, 22, 0, mucTrachNhiemDieuTriNgoaiTruUI, GlobalConstants.sheetSK12ID);

                        spreadsheet.addNoteToCell(row - 1, row, 29, 30, 0, phiChuanDieuTriNgoaiTruUI, GlobalConstants.sheetSK12ID);

                        spreadsheet.addNoteToCell(row - 1, row, 36, 37, 0, phiThanhToanDieuTriNgoaiTruUI, GlobalConstants.sheetSK12ID);


                        spreadsheet.addNoteToCell(row - 1, row, 22, 23, 0, mucTrachNhiemDieuTriNhaKhoaUI, GlobalConstants.sheetSK12ID);

                        spreadsheet.addNoteToCell(row - 1, row, 30, 31, 0, phiChuanDieuTriNhaKhoaUI, GlobalConstants.sheetSK12ID);

                        spreadsheet.addNoteToCell(row - 1, row, 37, 38, 0, phiThanhToanDieuTriNhaKhoaUI, GlobalConstants.sheetSK12ID);
                    }

                    commonPage.sleepInSecond(2);
                    commonPage.clickToElementByJS(driver, CommonPageUI.COMMON_BUTTON, "Lưu");

                }
            }


        }

        commonPage.clickToElement(driver, CommonPageUI.TAO_BAN_CHAO_ICON);
        commonPage.waitElementVisible(driver, CommonPageUI.TOAST_MESSAGE, "Tạo thành công");

    }


}








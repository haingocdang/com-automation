package commons;

import org.apache.commons.logging.Log;

public class GlobalConstants {
    public static String ROOT_FOLDER = System.getProperty("user.dir");
    public static String MERCURY_LOGIN_TEST_ENV_URL = "http://test-mercury.alpaca.vn";
   // public static String MERCURY_LOGIN_SIT_ENV_URL = "http://uat1-mercury.alpaca.vn";
    public static String MERCURY_LOGIN_SIT_ENV_URL = "https://phase2-sit-mercury.alpaca.vn/";
    public static String MERCURY_LOGIN_SALES_ENV_URL = "https://sale-sso.alpaca.vn/oauth/login";
    public static String MERCURY_LOGIN_UAT_ENV_URL = "http://uat-mercury.alpaca.vn";
    //public static String MERCURY_LOGIN_TEST_ENV_URL="http://61.14.237.83:8903/oauth/login";
    //public static String JUPITER_LOGIN_TEST_ENV_URL="http://test-jupiter.alpaca.vn";
    public static String JUPITER_LOGIN_TEST_ENV_URL = "https://phase2-test-jupiter.alpaca.vn/";
    public static String JUPITER_LOGIN_SIT_ENV_URL = "http://uat1-jupiter.alpaca.vn";
    public static String JUPITER_LOGIN_UAT_ENV_URL = "https://uat-jupiter.alpaca.vn/quote";
    //public static String JUPITER_LOGIN_TEST_ENV_URL="http://103.70.30.134:3201";
    //public static String JUPITER_LOGIN_TEST_ENV_URL="http://test-jupiter.alpaca.vn/test-schema/new-schema";
//	public static String JUPITER_LOGIN_DEV_ENV_URL="http://61.14.237.89:3201/login";
	/*public static long LONG_TIMEOUT=30;
	public static long SHORT_TIMEOUT=10;*/
    public static String BROWSER_LOG_FOLDER = ROOT_FOLDER + "\\browserLog";
    public static String DOWNLOAD_FOLDER = ROOT_FOLDER + "\\bdownloadFiles";
    public static String UPLOAD_FOLDER = ROOT_FOLDER + "\\uploadFiles\\";
    //public static String TEST_DATA_EXCEL_FILE_NAME=System.getProperty("user.dir")+"/src/test/resources/datatest/DataTable.xlsx";
    public static String TEST_DATA_EXCEL_FILE_NAME = System.getProperty("user.dir") + "/src/test/resources/datatest/Untitled 1.xlsx";
    public static String FILE_NAME = "file.xlsx";
    public static String QUYENLOIBH_SHEET_NAME = "Quyền Lợi BH";
    public static String MTN_BT3_VE_NGUOI_LABEL = "MTN BT3 về người";
    public static String MTN_BT3_VE_TAI_SAN_LABEL = "MTN BT3 về tài sản";
    public static String MTN_VE_HANH_KHACH_LABEL = "MTN về hành khách";
    public static String MTN_VE_HANG_HOA_LABEL = "MTN về hàng hóa";
    public static String TLP_CHUAN_SAU_THUE_LABEL = "TLP chuẩn (sau thuế)";
    public static String STBH_TAN_LABEL = "- STBH/tấn:";
    public static String SO_TAN_THAM_GIA_LABEL = "- Số tấn tham gia:";
    public static String STBH_LABEL = "STBH/MTN";
    public static String THONG_TIN_BO_SUNG_LABEL = "Thông tin bổ sung";
    public static String PHI_CHUAN_SAU_THUE_LABEL = "Phí chuẩn (sau thuế)";
    public static String PHI_THANH_TOAN_SAU_THUE_LABEL = "Phí thanh toán (sau thuế)";
    public static String PHI_THANH_TOAN_LABEL = "Phí thanh toán";
    public static String PHI_CHUAN_LABEL = "Phí chuẩn";
    public static String MUC_TRACH_NHIEM_LABEL = "Mức trách nhiệm";
    public static String sheetTNDSTNID = "1HVqqVsW2Co78CNt4I1Vp2LlbQwrAVbY7rZ0NJvz7nrs";
    public static String sheetTNDSBBID = "1NdufzdgTFNGzJN4MUDeML4V8qUSuuMYpWCAsjEpOssE";
    public static String sheetVCXID = "13wgFKSjMe808WtB7qEoq-WwEopEsaj_VCQs9zWhX7-k";
    public static String sheetHangHieuXeID = "1rB1jHf-G01P64oRSdnD-u1njlH2dpSwJxUXJyMcCRFo";
    public static String sheetSK12ID = "1bG8RKeWE2C5_Uc32vJKY0MEXTz8Oo9O-l-QAc5xqdJE";
    public static String sheetBienKiemSoat = "1afgH42z1R9B9EEfS6x-lbWqjgYDbwbrK32-N3onLcs8";

    public static String appendResultRangeTNDSTN = "AF";
    public static String appendResultRangeTNDSBB = "M";
    public static String appendResultRangeVCX = "BE";
    public static String appendResultRangeSK12 = "AX";
    public static String readRangeTNDSTN = "A:AE";
    public static String readRangeTNDSBB = "A:L";
    public static String readRangeVCX = "A:BD";
    public static String readRangeHangHieuXe = "A:F";
    public static String readRangeSK12Sheet = "A:AW";
    public static String readRangeBienKiemSoatSheet = "A:B";
    public static String readRangeQuyenLoiBHSheet = "!A:A";


    public static String thongTinChinhTab = "Thông tin chính";
    public static String thongTinBoSungTab = "Thông tin bổ sung";
    public static String quyenLoiBHTab = "Quyền lợi bảo hiểm";
    public static String khongApDung = "Không áp dụng";
    public static String mucTrachNhiem = "Mức trách nhiệm";
    public static String tiLeGiamPhi = "Tỷ lệ giảm phí";
    public static String tiLePhiChuan = "Tỷ lệ phí chuẩn";
    public static String phiChuan = "Phí chuẩn";
    public static String phiThanhToan = "Phí thanh toán";
    public static String hoVaTen = "Họ và tên";
    public static String ngaySinh = "Ngày sinh";
    public static String gioiTinh = "Giới tính";
    public static String loaiDinhDanh = "Loại định danh";
    public static String soDinhDanh = "Số định danh";
    public static String loaiDiaChi = "Loại địa chỉ";
    public static String quocGia = "Quốc gia";
    public static String tinhThanhPho = "Tỉnh/Thành phố";
    public static String quanHuyen = "Quận/Huyện";
    public static String phuongXa = "Phường/Xã";
    public static String diaChi = "Địa chỉ";
    public static String loaiNgheNghiep = "Loại nghề nghiệp";
    public static String soDienThoai = "Số điện thoại";


    //public static String TEST_DATA_CSV_FILE_NAME=System.getProperty("user.dir")+"/src/test/resources/datatest/TNDSTNDataTable.csv";
    //public static String TEST_DATA_EXCEL_FILE_NAME1=System.getProperty("user.dir")+"/src/test/resources/datatest/TNDSTNDataTableOut.xlsx";


    //protected static Log log;

    //Constants: dữ liệu ko dc phép thay đổi trong quá trình run
    //Dùng chung cho toàn bộ Framework (system)
    //Bất kì class/package nào cũng có thể use class này dc
    //URL hay server/DB/User, Pass
    //Folder path/ Setting/.....
}

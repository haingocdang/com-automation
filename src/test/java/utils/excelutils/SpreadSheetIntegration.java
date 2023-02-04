package utils.excelutils;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.*;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.util.*;

public class SpreadSheetIntegration {

    public String ROOT_FORLDER = System.getProperty("user.dir");
    public final String APPLICATION_NAME = "Test Spreadsheet";
    public final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    /**
     * tokens directory will be created in your project when you will run this code first time.
     * you will find a file named StoredCredential, in this directory
     * private static final String TOKENS_DIRECTORY_PATH = "tokens";
     * <p>
     * <p>
     * /**
     * Value of this SCOPES variable will decide the reading and writing rights of your bot.
     * Currently SPREADSHEETS_READONLY attribute is choosen which mean that our code can only read values from the spreadsheet.
     * For writing rights just use SPREADSHEETS in place of SPREADSHEETS_READONLY.
     */

    public final String TOKENS_DIRECTORY_PATH = "tokens";
    public final String CREDENTIAL_FILE_PATH = "/tokens/credentials.json";

    public final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);
    public NetHttpTransport HTTP_TRANSPORT = null; //Khai báo HTTP Transport
    public Sheets service; //Khai báo Sheet service
    public String valueInputOption = "USER_ENTERED"; //Khai báo option của input
    public ValueRange response; //Khai báo response
    public String spreadSheetID; //Lưu trữ spread sheet ID của sheet được tạo ra
    // public String range = "A:AE"; //Khai báo range muốn xử lý trong google sheet
    //s public Function<? super List<Object>,?> toMap;

    //Tạo constructure để khởi tạo service và HTTP_TRANSPORT
    public SpreadSheetIntegration() throws Exception {
        HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredential(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    /**
     * Function to create an authorized credential object
     *
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object
     * @throws IOException If the credentials.json file can not be found
     */
    public Credential getCredential(final NetHttpTransport HTTP_TRANSPORT) throws Exception {
        //Load client secrets
        InputStream in = SpreadSheetIntegration.class.getResourceAsStream(CREDENTIAL_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIAL_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        //Build flow and trigger user authorization request
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        //  System.out.println("clientScrets"+clientSecrets);


        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("hai.dang@alpaca.vn");
    }

    public void createANewSheet() throws IOException {
        Spreadsheet spreadsheet = new Spreadsheet()
                .setProperties(new SpreadsheetProperties()
                        .setTitle("Sheet Test"));
        spreadsheet = service.spreadsheets().create(spreadsheet)
                .setFields("spreadsheetId")
                .execute();
        spreadSheetID = spreadsheet.getSpreadsheetId(); //Gán giá trị spreadSheetID cho sheet mới tạo
        // spreadSheetID="1kXYgxrAnTLUedG9CKbV0n_hQYL_jEzdj7ISGNUHNWt4";
        //In ra link Google Sheet vừa tạo
        System.out.println("http://docs.google.com/spreadsheets/d/" + spreadSheetID);
    }

    public void appendDataToSpreadSheet(Object object, String spreadSheetID, String range) {
        List<List<Object>> values = Arrays.asList(
                Arrays.asList(object)
        );
        ValueRange body = new ValueRange().setValues(values);
        // System.out.println("Body " + body);
        // spreadSheetID="1k2yb8TBWJF1LBWalHUg-S-wIXRKUx0TY";
        try {
            UpdateValuesResponse result = service.spreadsheets().values().update(spreadSheetID, range, body).setValueInputOption(valueInputOption).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static Map<String, Object> toMapTNDSTN(List<Object> values) {
        Map<String, Object> sheetData = new HashMap<>();
        sheetData.put("Hãng xe", values.get(0));
        sheetData.put("Phiên bản", values.get(1));
        sheetData.put("MĐSD", values.get(2));
        sheetData.put("Nhóm xe", values.get(3));
        sheetData.put("Loại xe", values.get(4));
        sheetData.put("Số chỗ", values.get(5));
        sheetData.put("Trọng tải", values.get(6));
        sheetData.put("Ngày bắt đầu", values.get(7));
        sheetData.put("Ngày kết thúc", values.get(8));
        sheetData.put("MTN BT3 về người", values.get(9));
        sheetData.put("MTN BT3 về tài sản", values.get(10));
        sheetData.put("MTN về hành khách", values.get(11));
        sheetData.put("Số tấn tham gia", values.get(12));
        sheetData.put("STBH/tấn", values.get(13));
        sheetData.put("Số tháng tham gia", values.get(14));
        sheetData.put("Hệ số ngắn hạn dài hạn", values.get(15));
        sheetData.put("TLP thanh toán BT3 về người (gồm thuế)", values.get(16));
        sheetData.put("TLP thanh toán BT3 về tài sản (gồm thuế)", values.get(17));
        sheetData.put("TLP thanh toán về hành khách (gồm thuế)", values.get(18));
        sheetData.put("TLP thanh toán về hàng hoá (gồm thuế)", values.get(19));
        sheetData.put("TLP thanh toán BT3 về người (gồm thuế)", values.get(20));
        sheetData.put("TLP thanh toán BT3 về tài sản (gồm thuế)", values.get(21));
        sheetData.put("TLP thanh toán về hành khách (gồm thuế)", values.get(22));
        sheetData.put("TLP thanh toán về hàng hoá (gồm thuế)", values.get(23));
        sheetData.put("Phí thanh toán BT3 về người (gồm thuế)", values.get(24));
        sheetData.put("Phí thanh toán BT3 về tài sản (gồm thuế)", values.get(25));
        sheetData.put("Phí thanh toán về hành khách (gồm thuế)", values.get(26));
        sheetData.put("Phí thanh toán về hàng hóa (gồm thuế)", values.get(27));
        sheetData.put("Tổng phí TNDSTN (gồm thuế)", values.get(28));
        sheetData.put("Ghi chú", values.get(29));
        return sheetData;
    }

    public static Map<String, Object> toMapTNDSBB(List<Object> values) {
        Map<String, Object> sheetData = new HashMap<>();
        sheetData.put("Hãng xe", values.get(0));
        sheetData.put("Phiên bản", values.get(1));
        sheetData.put("MĐSD", values.get(2));
        sheetData.put("Nhóm xe", values.get(3));
        sheetData.put("Loại xe", values.get(4));
        sheetData.put("Số chỗ", values.get(5));
        sheetData.put("Trọng tải", values.get(6));
        sheetData.put("Ngày bắt đầu", values.get(7));
        sheetData.put("Ngày kết thúc", values.get(8));
        sheetData.put("Phí TNDSBB (gồm thuế)", values.get(9));
        sheetData.put("Ghi chú", values.get(10));
        return sheetData;
    }

    public static Map<String, Object> toMapVCX(List<Object> values) {
        Map<String, Object> sheetData = new HashMap<>();
        sheetData.put("Ngày tính phí", values.get(0));
        sheetData.put("Hãng xe", values.get(1));
        sheetData.put("Phiên bản", values.get(2));
        sheetData.put("Dòng xe", values.get(3));

        sheetData.put("MĐSD", values.get(4));
        sheetData.put("Nhóm xe", values.get(5));
        sheetData.put("Loại xe", values.get(6));
        sheetData.put("Số chỗ", values.get(7));
        sheetData.put("Trọng tải", values.get(8));
        sheetData.put("Nơi sản xuất", values.get(9));
        sheetData.put("Năm sản xuất", values.get(10));
        sheetData.put("Ngày ĐKLĐ", values.get(11));
        sheetData.put("Giá trị xe", values.get(12));
        sheetData.put("Ngày bắt đầu", values.get(13));
        sheetData.put("Ngày kết thúc", values.get(14));
        sheetData.put("STBH VCX", values.get(15));
        sheetData.put("BS01", values.get(16));
        sheetData.put("BS02", values.get(17));
        sheetData.put("BS03", values.get(18));
        sheetData.put("BS04A", values.get(19));
        sheetData.put("BS04B", values.get(20));
        sheetData.put("BS05", values.get(21));
        sheetData.put("BS06", values.get(22));
        sheetData.put("BS07", values.get(23));
        sheetData.put("BS08", values.get(24));
        sheetData.put("BS09", values.get(25));
        sheetData.put("BS10", values.get(26));
        sheetData.put("Tuoi xe", values.get(27));
        sheetData.put("Khung GTX", values.get(28));
        sheetData.put("Số tháng tham gia", values.get(29));
        sheetData.put("Hệ số ngắn hạn dài hạn", values.get(30));
        sheetData.put("TLP cơ bản (gồm thuế)", values.get(31));
        sheetData.put("TLP BS01 (gồm thuế)", values.get(32));
        sheetData.put("TLP BS02 (gồm thuế)", values.get(33));
        sheetData.put("TLP BS04A (gồm thuế)", values.get(34));
        sheetData.put("TLP BS04B (gồm thuế)", values.get(35));
        sheetData.put("TLP BS05 (gồm thuế)", values.get(36));
        sheetData.put("TLP BS07 (gồm thuế)", values.get(37));
        sheetData.put("TLP BS08 (gồm thuế)", values.get(38));
        sheetData.put("TLP BS09 (gồm thuế)", values.get(39));
        sheetData.put("TLP BS10 (gồm thuế)", values.get(40));
        sheetData.put("Phí cơ bản", values.get(41));
        sheetData.put("Phí BS01 (gồm thuế)", values.get(42));
        sheetData.put("Phí BS02 (gồm thuế)", values.get(43));
        sheetData.put("Phí BS03 (gồm thuế)", values.get(44));
        sheetData.put("Phí BS04A (gồm thuế)", values.get(45));
        sheetData.put("Phí BS04B (gồm thuế)", values.get(46));
        sheetData.put("Phí BS05 (gồm thuế)", values.get(47));
        sheetData.put("Phí BS06 (gồm thuế)", values.get(48));
        sheetData.put("Phí BS07 (gồm thuế)", values.get(49));
        sheetData.put("Phí BS08 (gồm thuế)", values.get(50));
        sheetData.put("Phí BS09 (gồm thuế)", values.get(51));
        sheetData.put("Phí BS10 (gồm thuế)", values.get(52));
        sheetData.put("Tổng phí VCX (gồm thuế)", values.get(53));
        sheetData.put("Mức khấu trừ mặc định", values.get(54));
        sheetData.put("Mức khấu trừ mặc định", values.get(54));

        return sheetData;
    }

    public static Map<String, Object> toMapHangHieuXe(List<Object> values) {
        Map<String, Object> sheetData = new HashMap<>();
        sheetData.put("Hãng xe", values.get(0));
        sheetData.put("Phiên bản", values.get(1));
        sheetData.put("MĐSD", values.get(2));
        sheetData.put("Nhóm xe", values.get(3));
        sheetData.put("Loại xe", values.get(4));
        return sheetData;
    }

    public static Map<String, Object> toMapHangHieuXeVCX(List<Object> values) {
        Map<String, Object> sheetData = new HashMap<>();
        sheetData.put("Hãng xe", values.get(0));
        sheetData.put("Phiên bản", values.get(1));
        sheetData.put("Dòng xe", values.get(2));
        sheetData.put("MĐSD", values.get(3));
        sheetData.put("Nhóm xe", values.get(4));
        sheetData.put("Loại xe", values.get(5));
        return sheetData;
    }

    public Request buildRequestAddNoteCell(int startRow, int endRow, int startColumn, int endColumn, int sheetID, String note) {
        Request request = new Request();
        request.setRepeatCell(new RepeatCellRequest()
                .setCell(new CellData().setNote(note))
                .setRange(new GridRange()
                        .setSheetId(sheetID)
                        .setStartRowIndex(startRow)
                        .setEndRowIndex(endRow)
                        .setStartColumnIndex(startColumn)
                        .setEndColumnIndex(endColumn))
                .setFields("note")
        );

        return request;
    }
    public List<List<Object>> readDataFromSpreadSheet(String spreadSheetID, String range) throws IOException {
        //  List<Map<String, String>> dataSet = new ArrayList<Map<String, String>>();
        response = service.spreadsheets().values().get(spreadSheetID, range).execute();
        List<List<Object>> values = response.getValues();
        return values;
    }

    public List<Map<String, Object>> readSheet(String spreadSheetID, String range) throws IOException {
       // List<List<Object>> data
        response = service.spreadsheets().values().get(spreadSheetID, range).execute();
        List<List<Object>> data = response.getValues();


        int totalRow = data.size();
        List<Map<String, Object>> excelRows = new ArrayList<Map<String, Object>>();
    //    int headerRowNumber = getHeaderRowNumber(sheet);
      //  if (headerRowNumber != -1) {
            int totalColumn = data.get(0).size();

            int setCurrentRow = 0;
            for (int currentRow = setCurrentRow; currentRow < totalRow; currentRow++) {
             //   row = getRow(sheet, sheet.getFirstRowNum() + currentRow);
                LinkedHashMap<String, Object> columnMapdata = new LinkedHashMap<String, Object>();
                for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {
                    columnMapdata.put(((String) data.get(0).get(currentColumn)).trim(), ((String) data.get(currentRow).get(currentColumn)).trim());
                }
                excelRows.add(columnMapdata);
            }
    //    System.out.println("table " +excelRows);
     //   }
        return excelRows;
    }


    public void addNoteToCell(int startRow, int endRow, int startColumn, int endColumn, int sheetID, String note, String spreadSheetID) throws IOException {
        List<Request> requests = null;
        requests = Collections.singletonList(buildRequestAddNoteCell(startRow, endRow, startColumn, endColumn, sheetID, note));
        BatchUpdateSpreadsheetRequest body = new BatchUpdateSpreadsheetRequest()
                .setRequests(requests);
        //  System.out.println("body "+body);

        service.spreadsheets().batchUpdate(spreadSheetID, body).execute();

    }
}
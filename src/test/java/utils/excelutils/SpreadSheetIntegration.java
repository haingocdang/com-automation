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
    public NetHttpTransport HTTP_TRANSPORT = null; //Khai b??o HTTP Transport
    public Sheets service; //Khai b??o Sheet service
    public String valueInputOption = "USER_ENTERED"; //Khai b??o option c???a input
    public ValueRange response; //Khai b??o response
    public String spreadSheetID; //L??u tr??? spread sheet ID c???a sheet ???????c t???o ra
    // public String range = "A:AE"; //Khai b??o range mu???n x??? l?? trong google sheet
    //s public Function<? super List<Object>,?> toMap;

    //T???o constructure ????? kh???i t???o service v?? HTTP_TRANSPORT
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


        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("hai.dang@com.vn");
    }

    public void createANewSheet() throws IOException {
        Spreadsheet spreadsheet = new Spreadsheet()
                .setProperties(new SpreadsheetProperties()
                        .setTitle("Sheet Test"));
        spreadsheet = service.spreadsheets().create(spreadsheet)
                .setFields("spreadsheetId")
                .execute();
        spreadSheetID = spreadsheet.getSpreadsheetId(); //G??n gi?? tr??? spreadSheetID cho sheet m???i t???o
        // spreadSheetID="1kXYgxrAnTLUedG9CKbV0n_hQYL_jEzdj7ISGNUHNWt4";
        //In ra link Google Sheet v???a t???o
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
        sheetData.put("H??ng xe", values.get(0));
        sheetData.put("Phi??n b???n", values.get(1));
        sheetData.put("M??SD", values.get(2));
        sheetData.put("Nh??m xe", values.get(3));
        sheetData.put("Lo???i xe", values.get(4));
        sheetData.put("S??? ch???", values.get(5));
        sheetData.put("Tr???ng t???i", values.get(6));
        sheetData.put("Ng??y b???t ?????u", values.get(7));
        sheetData.put("Ng??y k???t th??c", values.get(8));
        sheetData.put("MTN BT3 v??? ng?????i", values.get(9));
        sheetData.put("MTN BT3 v??? t??i s???n", values.get(10));
        sheetData.put("MTN v??? h??nh kh??ch", values.get(11));
        sheetData.put("S??? t???n tham gia", values.get(12));
        sheetData.put("STBH/t???n", values.get(13));
        sheetData.put("S??? th??ng tham gia", values.get(14));
        sheetData.put("H??? s??? ng???n h???n d??i h???n", values.get(15));
        sheetData.put("TLP thanh to??n BT3 v??? ng?????i (g???m thu???)", values.get(16));
        sheetData.put("TLP thanh to??n BT3 v??? t??i s???n (g???m thu???)", values.get(17));
        sheetData.put("TLP thanh to??n v??? h??nh kh??ch (g???m thu???)", values.get(18));
        sheetData.put("TLP thanh to??n v??? h??ng ho?? (g???m thu???)", values.get(19));
        sheetData.put("TLP thanh to??n BT3 v??? ng?????i (g???m thu???)", values.get(20));
        sheetData.put("TLP thanh to??n BT3 v??? t??i s???n (g???m thu???)", values.get(21));
        sheetData.put("TLP thanh to??n v??? h??nh kh??ch (g???m thu???)", values.get(22));
        sheetData.put("TLP thanh to??n v??? h??ng ho?? (g???m thu???)", values.get(23));
        sheetData.put("Ph?? thanh to??n BT3 v??? ng?????i (g???m thu???)", values.get(24));
        sheetData.put("Ph?? thanh to??n BT3 v??? t??i s???n (g???m thu???)", values.get(25));
        sheetData.put("Ph?? thanh to??n v??? h??nh kh??ch (g???m thu???)", values.get(26));
        sheetData.put("Ph?? thanh to??n v??? h??ng h??a (g???m thu???)", values.get(27));
        sheetData.put("T???ng ph?? TNDSTN (g???m thu???)", values.get(28));
        sheetData.put("Ghi ch??", values.get(29));
        return sheetData;
    }

    public static Map<String, Object> toMapTNDSBB(List<Object> values) {
        Map<String, Object> sheetData = new HashMap<>();
        sheetData.put("H??ng xe", values.get(0));
        sheetData.put("Phi??n b???n", values.get(1));
        sheetData.put("M??SD", values.get(2));
        sheetData.put("Nh??m xe", values.get(3));
        sheetData.put("Lo???i xe", values.get(4));
        sheetData.put("S??? ch???", values.get(5));
        sheetData.put("Tr???ng t???i", values.get(6));
        sheetData.put("Ng??y b???t ?????u", values.get(7));
        sheetData.put("Ng??y k???t th??c", values.get(8));
        sheetData.put("Ph?? TNDSBB (g???m thu???)", values.get(9));
        sheetData.put("Ghi ch??", values.get(10));
        return sheetData;
    }

    public static Map<String, Object> toMapVCX(List<Object> values) {
        Map<String, Object> sheetData = new HashMap<>();
        sheetData.put("Ng??y t??nh ph??", values.get(0));
        sheetData.put("H??ng xe", values.get(1));
        sheetData.put("Phi??n b???n", values.get(2));
        sheetData.put("D??ng xe", values.get(3));

        sheetData.put("M??SD", values.get(4));
        sheetData.put("Nh??m xe", values.get(5));
        sheetData.put("Lo???i xe", values.get(6));
        sheetData.put("S??? ch???", values.get(7));
        sheetData.put("Tr???ng t???i", values.get(8));
        sheetData.put("N??i s???n xu???t", values.get(9));
        sheetData.put("N??m s???n xu???t", values.get(10));
        sheetData.put("Ng??y ??KL??", values.get(11));
        sheetData.put("Gi?? tr??? xe", values.get(12));
        sheetData.put("Ng??y b???t ?????u", values.get(13));
        sheetData.put("Ng??y k???t th??c", values.get(14));
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
        sheetData.put("S??? th??ng tham gia", values.get(29));
        sheetData.put("H??? s??? ng???n h???n d??i h???n", values.get(30));
        sheetData.put("TLP c?? b???n (g???m thu???)", values.get(31));
        sheetData.put("TLP BS01 (g???m thu???)", values.get(32));
        sheetData.put("TLP BS02 (g???m thu???)", values.get(33));
        sheetData.put("TLP BS04A (g???m thu???)", values.get(34));
        sheetData.put("TLP BS04B (g???m thu???)", values.get(35));
        sheetData.put("TLP BS05 (g???m thu???)", values.get(36));
        sheetData.put("TLP BS07 (g???m thu???)", values.get(37));
        sheetData.put("TLP BS08 (g???m thu???)", values.get(38));
        sheetData.put("TLP BS09 (g???m thu???)", values.get(39));
        sheetData.put("TLP BS10 (g???m thu???)", values.get(40));
        sheetData.put("Ph?? c?? b???n", values.get(41));
        sheetData.put("Ph?? BS01 (g???m thu???)", values.get(42));
        sheetData.put("Ph?? BS02 (g???m thu???)", values.get(43));
        sheetData.put("Ph?? BS03 (g???m thu???)", values.get(44));
        sheetData.put("Ph?? BS04A (g???m thu???)", values.get(45));
        sheetData.put("Ph?? BS04B (g???m thu???)", values.get(46));
        sheetData.put("Ph?? BS05 (g???m thu???)", values.get(47));
        sheetData.put("Ph?? BS06 (g???m thu???)", values.get(48));
        sheetData.put("Ph?? BS07 (g???m thu???)", values.get(49));
        sheetData.put("Ph?? BS08 (g???m thu???)", values.get(50));
        sheetData.put("Ph?? BS09 (g???m thu???)", values.get(51));
        sheetData.put("Ph?? BS10 (g???m thu???)", values.get(52));
        sheetData.put("T???ng ph?? VCX (g???m thu???)", values.get(53));
        sheetData.put("M???c kh???u tr??? m???c ?????nh", values.get(54));
        sheetData.put("M???c kh???u tr??? m???c ?????nh", values.get(54));

        return sheetData;
    }

    public static Map<String, Object> toMapHangHieuXe(List<Object> values) {
        Map<String, Object> sheetData = new HashMap<>();
        sheetData.put("H??ng xe", values.get(0));
        sheetData.put("Phi??n b???n", values.get(1));
        sheetData.put("M??SD", values.get(2));
        sheetData.put("Nh??m xe", values.get(3));
        sheetData.put("Lo???i xe", values.get(4));
        return sheetData;
    }

    public static Map<String, Object> toMapHangHieuXeVCX(List<Object> values) {
        Map<String, Object> sheetData = new HashMap<>();
        sheetData.put("H??ng xe", values.get(0));
        sheetData.put("Phi??n b???n", values.get(1));
        sheetData.put("D??ng xe", values.get(2));
        sheetData.put("M??SD", values.get(3));
        sheetData.put("Nh??m xe", values.get(4));
        sheetData.put("Lo???i xe", values.get(5));
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
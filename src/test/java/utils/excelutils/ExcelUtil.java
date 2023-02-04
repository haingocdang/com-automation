package utils.excelutils;

import commons.GlobalConstants;
import cucumber.api.DataTable;
import cucumber.api.Transformer;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.openqa.selenium.Platform;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

//import static tests.BaseTest.testDataExcelFileName;

/**
 * Created by obaskirt on 28-Oct-17.
 * Updated by obaskirt on 02-Apr-2019
 */
public class ExcelUtil extends Transformer<DataTable> {
    //Main Directory of the project

    @Override
    public DataTable transform(String filePath) {
        return null;
    }

    public static ExcelUtil getData() {
        return new ExcelUtil();

    }

    public static final String currentDir = System.getProperty("user.dir");

    //Location of Test data excel file
    public static String testDataExcelPath = null;

    //Excel WorkBook
    public static XSSFWorkbook excelWBook;

    //Excel Sheet
    public static XSSFSheet excelWSheet;

    //Excel cell
    public static XSSFCell cell;

    //Excel row
    public static XSSFRow row;

    //Row Number
    public static int rowNumber;

    //Column Number
    public static int columnNumber;

    //Setter and Getters of row and columns
    public static void setRowNumber(int pRowNumber) {
        rowNumber = pRowNumber;
    }

    public static int getRowNumber() {
        return rowNumber;
    }

    public static void setColumnNumber(int pColumnNumber) {
        columnNumber = pColumnNumber;
    }

    public static int getColumnNumber() {
        return columnNumber;
    }

    // This method has two parameters: "Test data excel file name" and "Excel sheet name"
    // It creates FileInputStream and set excel file and excel sheet to excelWBook and excelWSheet variables.
    public static void setExcelFileSheet(String sheetName) {
        //MAC or Windows Selection for excel path
        if (Platform.getCurrent().toString().equalsIgnoreCase("MAC")) {
            testDataExcelPath = currentDir + "//src//test//resources//datatest//";
        } else if (Platform.getCurrent().toString().contains("WIN")) {
            testDataExcelPath = currentDir + "\\src\\test\\resources\\datatest\\";
        }
        try {
            // Open the Excel file
            FileInputStream ExcelFile = new FileInputStream(GlobalConstants.TEST_DATA_EXCEL_FILE_NAME);
            //  System.out.println("File path is"+testDataExcelPath+GlobalConstants.FILE_NAME);
            excelWBook = new XSSFWorkbook(ExcelFile);
            excelWSheet = excelWBook.getSheet(sheetName);
        } catch (Exception e) {
            try {
                throw (e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    //This method reads the test data from the Excel cell.
    //We are passing row number and column number as parameters.
    public static String getCellData(int RowNum, int ColNum) {
        String cellData = null;
        CellType cellType;
        DataFormatter formatter = new DataFormatter();

        try {
            cell = excelWSheet.getRow(RowNum).getCell(ColNum);
            //  cell.setCellType(CellType.STRING);
            //  DataFormatter formatter = new DataFormatter();
            //    cellData = cell.getStringCellValue();

           /* if (cell.getCellStyle().getDataFormatString().contains("%")) {
                // Detect Percent Values
                Double value = Double.parseDouble(cellData) * 100;
                DecimalFormat df = new DecimalFormat("#.##");
                df.setRoundingMode(RoundingMode.HALF_EVEN);
               // System.out.println("value " + value);

                // cellData=value.toString();
                cellData = df.format(value.doubleValue());
                //cellData=cellData+"%";
            }*/
            //String cellData = Integer.toString((int) cell.getNumericCellValue());
            //return cellData;
            if (cell != null) {
                cellType = cell.getCellType();
                if (cellType== CellType.FORMULA) {
                    switch (cell.getCachedFormulaResultType()) {
                        case FORMULA:
                            XSSFFormulaEvaluator evaluator = excelWBook.getCreationHelper().createFormulaEvaluator();
                            //    evaluator.evaluateFormulaCell(cell);
                           // cellData= formatter.formatCellValue(cell, evaluator);
                            cellData = ""; //= String.valueOf(( cell.getNumericCellValue()));
                            //  cellData = formatter.formatCellValue(cell.getCellFormula());//.getNumericCellValue());
                            //  cellData= String.valueOf(formatter.formatCellValue(cell, evaluator));
                        //    System.out.println("Cell " + cellData);
             /*           System.out.println("Cell "+ cell.getCellType());
                        cellData= formatter.formatCellValue(cell, evaluator);*/
                            // cellData= evaluator.evaluate(cell);//, null);

                            // cellData = String.valueOf((cell.getNumericCellValue()));
                            if (cellData.equals("0.0")) {
                                cellData = String.valueOf(0);
                            }
                       /* if (cellData.contains(".0")) {
                            cellData = cellData.replace(".0", "");
                        }*/
                            //cellData = cellData.substring(0, cellData.length() - 2);
                            break;
                        case STRING:
                            cellData = cell.getStringCellValue();
                       //     System.out.println("Cell " + cellData);

                            if (cellData.equals("0.0")) {
                                cellData = String.valueOf(0);
                            }
                      /*  if (cellData.contains(".0")) {
                            cellData = cellData.replace(".0", "");
                        }*/
                            break;
                        case NUMERIC:
                            // cellData = String.valueOf(((int) cell.getNumericCellValue()));
                            cellData = String.valueOf((cell.getNumericCellValue()));
                          //  System.out.println("Cell " + cellData);

                            if (cellData.equals("KC")) {
                                break;
                            }
                            if (cellData.equals("0.0")) {
                                cellData = String.valueOf(0);
                            }
                       /* if (cellData.contains(".0")) {
                            cellData = cellData.replace(".0", "");
                        }*/
                            break;

                    }
                }
                else{
                    switch (cell.getCellType()) {
                       case STRING:
                            cellData = cell.getStringCellValue();
                       //     System.out.println("Cell " + cellData);

                            if (cellData.equals("0.0")) {
                                cellData = String.valueOf(0);
                            }
                      /*  if (cellData.contains(".0")) {
                            cellData = cellData.replace(".0", "");
                        }*/
                            break;
                        case NUMERIC:
                            // cellData = String.valueOf(((int) cell.getNumericCellValue()));
                            cellData = String.valueOf((cell.getNumericCellValue()));
                          //  System.out.println("Cell " + cellData);

                            if (cellData.equals("KC")) {
                                break;
                            }
                            if (cellData.equals("0.0")) {
                                cellData = String.valueOf(0);
                            }
                       /* if (cellData.contains(".0")) {
                            cellData = cellData.replace(".0", "");
                        }*/
                            break;

                    }
                }
                    if (cell.getCellStyle().getDataFormatString().contains("%")) {
                        // Detect Percent Values
                        if (cellData.equals("KC")) {
                            return cellData;
                        }
                        Double value = Double.parseDouble(cellData) * 100;
                        DecimalFormat df = new DecimalFormat("#.##");
                        df.setRoundingMode(RoundingMode.HALF_EVEN);

                        cellData = df.format(value.doubleValue());
                        if (cellData.equals("0.0")) {
                            cellData = String.valueOf(0);
                        }
                    /*if (cellData.contains(".0")) {
                        cellData = cellData.replace(".0", "");
                    }*/
                    }
                }
            } catch(Throwable e){
                throw (e);
            }
            return cellData;

    }
    public static String getCellDate(int RowNum, int ColNum) {
        String cellData = null;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        CellType cellType;
        try {
            cell = excelWSheet.getRow(RowNum).getCell(ColNum);

            cellData = df.format(cell.getDateCellValue());

        } catch (Throwable e) {
            e.printStackTrace();
        }
        return cellData;
    }


    //This method takes row number as a parameter and returns the data of given row number.
    public static XSSFRow getRowData(int RowNum) {
        try {
            row = excelWSheet.getRow(RowNum);
            return row;
        } catch (Exception e) {
            throw (e);
        }
    }

    //This method gets excel file, row and column number and set a value to the that cell.
    public static void setCellData(String value, int rowNum, int colNum) {
        try {
            row = excelWSheet.getRow(rowNum);
            cell = row.getCell(colNum);
            if (cell == null) {
                cell = row.createCell(colNum);
                cell.setCellValue(value);
            } else {
                cell.setCellValue(value);
            }
            // Constant variables Test Data path and Test Data file name
            FileOutputStream fileOut = new FileOutputStream(GlobalConstants.TEST_DATA_EXCEL_FILE_NAME);
            excelWBook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setCellComment(String value, int rowNum, int colNum) {
      /*  Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();*/
        int dx1 = 100, dy1 = 50, dx2 = 100, dy2 = 50;


        CreationHelper creationHelper = (XSSFCreationHelper) excelWBook.getCreationHelper();

        //Drawing<Shape> drawing = (Drawing<Shape>) sheet.createDrawingPatriarch();
        ClientAnchor clientAnchor = creationHelper.createClientAnchor();
        // ClientAnchor clientAnchor = drawing.createAnchor(dx1, dy1, dx2, dy2, (short) 1, 1, (short) 6, 5);

        //    Comment comment = (Comment) drawing.createCellComment(clientAnchor);
        Drawing drawing = excelWSheet.createDrawingPatriarch();

        Comment comment; //= drawing.createCellComment(clientAnchor);

        RichTextString richTextString = creationHelper.createRichTextString(value);
     /*   comment.setString(richTextString);
        System.out.println("Comment "+comment.toString());*/
        try {
            row = excelWSheet.getRow(rowNum);
            cell = row.getCell(colNum);
            if (cell == null) {
               /* cell = row.createCell(colNum);
                clientAnchor.setCol1(cell.getColumnIndex() + 1); //the box of the comment starts at this given column...
                clientAnchor.setCol2(cell.getColumnIndex() + 3); //...and ends at that given column
                clientAnchor.setRow1(rowNum + 1); //one row below the cell...
                clientAnchor.setRow2(rowNum + 5); //...and 4 rows high
                clientAnchor.setDx1(100);
                clientAnchor.setDx2(100);
                clientAnchor.setDy1(100);
                clientAnchor.setDy2(100);*/
                comment = drawing.createCellComment(clientAnchor);
                comment.setString(creationHelper.createRichTextString(value));

                cell.setCellComment(comment);
            } else {
               /* clientAnchor.setCol1(cell.getColumnIndex() + 1); //the box of the comment starts at this given column...
                clientAnchor.setCol2(cell.getColumnIndex() + 3); //...and ends at that given column
                clientAnchor.setRow1(rowNum + 1); //one row below the cell...
                clientAnchor.setRow2(rowNum + 5); //...and 4 rows high
                clientAnchor.setDx1(100);
                clientAnchor.setDx2(100);
                clientAnchor.setDy1(100);
                clientAnchor.setDy2(100);*/
                comment = drawing.createCellComment(clientAnchor);
                comment.setString(creationHelper.createRichTextString(value));

                cell.setCellComment(comment);
            }
            // Constant variables Test Data path and Test Data file name
            FileOutputStream fileOut = new FileOutputStream(GlobalConstants.TEST_DATA_EXCEL_FILE_NAME);
            excelWBook.write(fileOut);
            //  fileOut.s();
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

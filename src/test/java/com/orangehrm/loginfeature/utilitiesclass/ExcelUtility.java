package com.orangehrm.loginfeature.utilitiesclass;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    // SENIOR FIX: There are NO static or ThreadLocal fields here anymore!
    // This makes it impossible for threads to return a null pointer.

    public static String[][] getTestData(String filePath, String sheetName, String tableName) {
        String[][] testData = null;

        // Open file streams inside local method memory space so threads remain isolated
        try (FileInputStream excelFile = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(excelFile)) {
            
            XSSFSheet sheet = workbook.getSheet(sheetName);
            DataFormatter formatter = new DataFormatter();
            
            // Pass the local sheet instance down to the finder loop
            XSSFCell[] boundaryCells = findCells(sheet, tableName);
            XSSFCell startCell = boundaryCells[0];
            XSSFCell endCell = boundaryCells[1];
            
            int startRow = startCell.getRowIndex() + 1;
            int endRow = endCell.getRowIndex() - 1;
            int startCol = startCell.getColumnIndex() + 1;
            int endCol = endCell.getColumnIndex() - 1;

            testData = new String[endRow - startRow + 1][endCol - startCol + 1];

            for (int i = startRow; i < endRow + 1; i++) {
                for (int j = startCol; j < endCol + 1; j++) {
                    Cell cell = sheet.getRow(i).getCell(j);
                    testData[i - startRow][j - startCol] = formatter.formatCellValue(cell);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return testData;
    }

    // Fixed: Accepts the local sheet object from the thread context, bypassing ThreadLocal lookups
    public static XSSFCell[] findCells(XSSFSheet sheet, String tableName) {
        DataFormatter formatter = new DataFormatter();
        String pos = "begin";
        XSSFCell[] cells = new XSSFCell[2];

        // Safe loop over the locally provided sheet instance
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (tableName.equals(formatter.formatCellValue(cell))) {
                    if (pos.equalsIgnoreCase("begin")) {
                        cells[0] = (XSSFCell) cell;
                        pos = "end";
                    } else {
                        cells[1] = (XSSFCell) cell;
                    }
                }
            }
        }
        return cells;
    }
}
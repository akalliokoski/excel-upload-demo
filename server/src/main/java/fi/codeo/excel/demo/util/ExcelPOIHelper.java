package fi.codeo.excel.demo.util;

import fi.codeo.excel.demo.model.TableCell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExcelPOIHelper {

    public Map<Integer, List<TableCell>> readExcel(Path filePath) throws IOException {
        FileInputStream fis = new FileInputStream(new File(filePath.toString()));
        Workbook workbook = null;
        Map<Integer, List<TableCell>> data = new HashMap<>();

        try {
            workbook = filePath.toString().endsWith(".xlsx")
                    ? new XSSFWorkbook(fis)
                    : new HSSFWorkbook(fis);
            data = readWorkbook(workbook);
        }
        finally {
            if (workbook != null) {
                workbook.close();
            }
        }

        return data;
    }

    private Map<Integer, List<TableCell>> readWorkbook(Workbook workbook) throws IOException {
        Map<Integer, List<TableCell>> data = new HashMap<>();

        Sheet sheet = workbook.getSheetAt(0);
        for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            List<TableCell> cellRow = new ArrayList<>();

            for (int j = 0; j < row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);
                String content = cell != null
                        ? readCellContent(cell)
                        : "";
                TableCell tableCell = new TableCell(content);
                tableCell.setRowIndex(i);
                tableCell.setColumnIndex(j);

                cellRow.add(tableCell);
            }

            data.put(i, cellRow);
        }

        return data;
    }

    private String readCellContent(Cell cell) {
        String content;
        switch (cell.getCellTypeEnum()) {
            case STRING:
                content = cell.getStringCellValue();
                break;
            case NUMERIC:
                content = DateUtil.isCellDateFormatted(cell)
                    ? cell.getDateCellValue().toString()
                    : String.valueOf(cell.getNumericCellValue());
                break;
            case BOOLEAN:
                content = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA:
                content = String.valueOf(cell.getCellFormula());
                break;
            default:
                content = "";
        }
        return content;
    }
}
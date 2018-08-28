package fi.codeo.excel.demo.service;

import fi.codeo.excel.demo.model.TableCell;
import fi.codeo.excel.demo.util.ExcelPOIHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Service
public class ExcelTableService implements TableService {

    private ExcelPOIHelper excelPOIHelper;

    public ExcelTableService(ExcelPOIHelper excelPOIHelper) {
        this.excelPOIHelper = excelPOIHelper;
    }

    @Override
    public Map<Integer, List<TableCell>> parseTable(MultipartFile multipartFile) throws IOException {
        byte[] bytes = multipartFile.getBytes();
        Path path = Paths.get(".", multipartFile.getOriginalFilename());
        Files.write(path, bytes);

        return excelPOIHelper.readExcel(path);
    }
}

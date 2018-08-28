package fi.codeo.excel.demo.service;

import fi.codeo.excel.demo.model.TableCell;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface TableService {
    Map<Integer, List<TableCell>> parseTable(MultipartFile file) throws IOException;
}

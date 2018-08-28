package fi.codeo.excel.demo.controller;

import fi.codeo.excel.demo.model.TableCell;
import fi.codeo.excel.demo.service.TableService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class TableController {
    TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/uploadTable")
    public Map<Integer, List<TableCell>> upload(@RequestParam("file") MultipartFile file) throws IOException {
        return tableService.parseTable(file);
    }
}
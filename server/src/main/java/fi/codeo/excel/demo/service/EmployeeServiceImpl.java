package fi.codeo.excel.demo.service;

import fi.codeo.excel.demo.model.Employee;
import fi.codeo.excel.demo.model.TableCell;
import fi.codeo.excel.demo.util.ExcelPOIHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    public enum Column {
        FIRST_NAME(0),
        LAST_NAME(1),
        ADDRESS(2),
        PHONE_NUMBER(3);

        private final int value;

        Column(final int newValue) {
            value = newValue;
        }

        public int getValue() { return value; }
    }

    private ExcelPOIHelper excelPOIHelper;

    public EmployeeServiceImpl(ExcelPOIHelper excelPOIHelper) {
        this.excelPOIHelper = excelPOIHelper;
    }

    @Override
    public Collection<Employee> parseEmployees(MultipartFile multipartFile) throws IOException {
        byte[] bytes = multipartFile.getBytes();
        Path path = Paths.get(".", multipartFile.getOriginalFilename());
        Files.write(path, bytes);

        Map<Integer, List<TableCell>> output = excelPOIHelper.readExcel(path);

        return output.keySet()
                .stream()
                .map(index -> toEmployee(index, output.get(index)))
                .collect(Collectors.toList());
    }

    private Employee toEmployee(int index, List<TableCell> cells) {
        Employee employee = new Employee(new Long(index));
        employee.setFirstName(getContent(cells, Column.FIRST_NAME));
        employee.setLastName(getContent(cells, Column.LAST_NAME));
        employee.setAddress(getContent(cells, Column.ADDRESS));
        employee.setPhoneNumber(getContent(cells, Column.PHONE_NUMBER));

        return employee;
    }

    private String getContent(List<TableCell> cells, Column column) {
        int columnIndex = column.getValue();
        if (columnIndex >= cells.size()) {
            return "";
        }

        TableCell cell = cells.get(columnIndex);
        return cell.getContent();
    }
}

package fi.codeo.excel.demo.service;

import fi.codeo.excel.demo.model.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;

public interface EmployeeService {
    Collection<Employee> parseEmployees(MultipartFile file) throws IOException;
}

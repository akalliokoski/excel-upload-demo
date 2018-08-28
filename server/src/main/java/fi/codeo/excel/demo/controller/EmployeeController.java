package fi.codeo.excel.demo.controller;

import fi.codeo.excel.demo.model.Employee;
import fi.codeo.excel.demo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;

@RestController
public class EmployeeController {
    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/upload")
    public Collection<Employee> upload(@RequestParam("file") MultipartFile file) throws IOException {
        return employeeService.parseEmployees(file);
    }
}
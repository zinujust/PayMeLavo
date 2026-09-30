package com.paymelavo.employee;

import com.paymelavo.employee.dto.CreateEmployeeRequest;
import com.paymelavo.employee.dto.EmployeeResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody CreateEmployeeRequest request) {
        EmployeeResponse response = employeeService.createEmployee(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> findByEmploymentStatus(@RequestParam(defaultValue = "ACTIVE") EmploymentStatus status) {
        List<EmployeeResponse> response = employeeService.findByEmploymentStatus(status);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

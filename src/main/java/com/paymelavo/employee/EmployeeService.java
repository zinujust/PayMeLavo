package com.paymelavo.employee;

import com.paymelavo.employee.dto.CreateEmployeeRequest;
import com.paymelavo.employee.dto.CreateEmployeeResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repo;

    @Transactional
    public CreateEmployeeResponse createEmployee(CreateEmployeeRequest request) {

        if(repo.existsByTin(request.tin())) {
            return null;
        }

        Employee employee = Employee.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .phoneNumber(request.phoneNumber())
                .address(request.address())
                .tin(request.tin())
                .employmentStatus(request.status())
                .payRate(request.payRate())
                .startDate(request.startDate())
                .createdAt(LocalDateTime.now(ZoneId.of("America/Los_Angeles")))
                .updatedAt(LocalDateTime.now(ZoneId.of("America/Los_Angeles")))
                .build();

        Employee savedEmployee = repo.save(employee);

        return new CreateEmployeeResponse(
                savedEmployee.getFirstName(),
                savedEmployee.getLastName(),
                savedEmployee.getEmail(),
                savedEmployee.getPhoneNumber(),
                savedEmployee.getAddress(),
                savedEmployee.getTin(),
                savedEmployee.getEmploymentStatus(),
                savedEmployee.getPayRate()
        );
    }
}

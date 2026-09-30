package com.paymelavo.employee;

import com.paymelavo.employee.dto.CreateEmployeeRequest;
import com.paymelavo.employee.dto.EmployeeResponse;
import com.paymelavo.exception_handler.DataIntegrityViolationException;
import com.paymelavo.exception_handler.DuplicateResourceException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repo;

    @Transactional
    public EmployeeResponse createEmployee(CreateEmployeeRequest request) {


        if(repo.existsByTin(request.tin())) {
            throw new DuplicateResourceException("Tin " + request.tin() + " already exists");
        }

        if(repo.existsByEmail(request.email())) {
            throw new DataIntegrityViolationException("Email " + request.email() + " already exists");
        }

        if(repo.existsByPhoneNumber(request.phoneNumber())) {
            throw new DataIntegrityViolationException("Phone Number " + request.phoneNumber() + " already exists");
        }

        Employee employee = Employee.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email().equals("") ?  null : request.email())
                .phoneNumber(request.phoneNumber().equals("") ? null : request.phoneNumber())
                .address(request.address())
                .tin(request.tin())
                .employmentStatus(request.status())
                .payRate(request.payRate())
                .startDate(request.startDate())
                .createdAt(LocalDateTime.now(ZoneId.of("Pacific/Fiji")))
                .updatedAt(LocalDateTime.now(ZoneId.of("Pacific/Fiji")))
                .build();

        Employee savedEmployee = repo.save(employee);

        return EmployeeResponse.fromEntity(savedEmployee);
    }

    @Transactional
    public List<EmployeeResponse> findByEmploymentStatus(EmploymentStatus status) {

        List<Employee> employees = repo.findByEmploymentStatus(status);

        List<EmployeeResponse> response = new ArrayList<>();

        for(Employee employee : employees){

            EmployeeResponse emp = EmployeeResponse.fromEntity(employee);
            response.add(emp);
        }

        return response;
    }

}

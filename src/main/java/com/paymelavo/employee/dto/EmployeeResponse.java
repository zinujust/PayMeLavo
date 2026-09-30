package com.paymelavo.employee.dto;

import com.paymelavo.employee.Employee;
import com.paymelavo.employee.EmploymentStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EmployeeResponse(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String address,
        String tin,
        EmploymentStatus status,
        BigDecimal payRate,
        LocalDate startDate
){
    public static EmployeeResponse fromEntity(Employee entity) {
        return new EmployeeResponse(
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPhoneNumber(),
                entity.getAddress(),
                entity.getTin(), // handles field mismatches explicitly
                entity.getEmploymentStatus(),
                entity.getPayRate(),
                entity.getStartDate()
        );
    }
}

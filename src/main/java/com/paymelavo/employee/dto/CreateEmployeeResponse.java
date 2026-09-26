package com.paymelavo.employee.dto;

import com.paymelavo.employee.EmploymentStatus;

import java.math.BigDecimal;

public record CreateEmployeeResponse (
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String address,
        String tin,
        EmploymentStatus status,
        BigDecimal payRate
){
}

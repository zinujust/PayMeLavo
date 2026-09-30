package com.paymelavo.employee.dto;

import com.paymelavo.employee.Employee;
import com.paymelavo.employee.EmploymentStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateEmployeeRequest(

        @NotBlank
        String firstName,

        @NotBlank
        String lastName,

        @Email
        String email,

        String phoneNumber,

        @NotBlank
        String address,

        @NotBlank
        String tin,

        @NotNull
        EmploymentStatus status,

        @NotNull
        BigDecimal payRate,

        @NotNull
        LocalDate startDate
){
        public static CreateEmployeeRequest fromEntity(Employee entity) {
                return new CreateEmployeeRequest(
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

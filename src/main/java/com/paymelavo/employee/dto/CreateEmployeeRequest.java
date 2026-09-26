package com.paymelavo.employee.dto;

import com.paymelavo.employee.EmploymentStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateEmployeeRequest(

        @NotNull
        String firstName,

        @NotNull
        String lastName,

        @Email
        @NotNull
        String email,

        @NotNull
        String phoneNumber,

        @NotNull
        String address,

        @NotNull
        String tin,

        @NotNull
        EmploymentStatus status,

        @NotNull
        BigDecimal payRate,

        @NotNull
        LocalDate startDate
){}

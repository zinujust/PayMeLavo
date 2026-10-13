package com.paymelavo.payrun.dto;

import com.paymelavo.exception_handler.IllegalDateException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreatePayRun(
        @NotNull
        LocalDate startDate,

        @NotNull
        LocalDate endDate
) {
    public CreatePayRun(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;

        if (startDate == null || endDate == null || endDate.isBefore(startDate)) {
            throw new IllegalDateException("Start date cannot be after the end date");
        }
    }

}

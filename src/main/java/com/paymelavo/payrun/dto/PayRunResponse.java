package com.paymelavo.payrun.dto;

import com.paymelavo.payrun.PayRun;
import com.paymelavo.payrun.PayRunStatus;
import com.paymelavo.payrunline.PayRunLine;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record PayRunResponse(
        Long id,
        List<PayRunLineResponse> lines,
        LocalDate startDate,
        LocalDate endDate,
        PayRunStatus status,
        LocalDateTime finalizedAt
){
    public record PayRunLineResponse(
            Long id,
            Long employeeId,
            BigDecimal payRate,
            BigDecimal normalHours,
            BigDecimal overtimeHours,
            BigDecimal totalHours,
            BigDecimal grossPay,
            BigDecimal fnpfEmployee,
            BigDecimal fnpfEmployer,
            BigDecimal paye,
            BigDecimal netPay
    ) {
        public static PayRunLineResponse fromEntity(PayRunLine line) {
            return new PayRunLineResponse(
                    line.getId(),
                    line.getEmployee().getId(),
                    line.getPayRate(),
                    line.getNormalHours(),
                    line.getOvertimeHours(),
                    line.getTotalHours(),
                    line.getGrossPay(),
                    line.getFnpfEmployee(),
                    line.getFnpfEmployer(),
                    line.getPaye(),
                    line.getNetPay()
            );
        }
    }

    public static PayRunResponse fromEntity(PayRun payRun) {
        List<PayRunLineResponse> lineDtos = payRun.getLines().stream()
                .map(PayRunLineResponse::fromEntity)
                .toList();

        return new PayRunResponse(
                payRun.getId(),
                lineDtos, // 👈 Mapped DTO list passed here
                payRun.getStartDate(),
                payRun.getEndDate(),
                payRun.getStatus(),
                payRun.getFinalizedAt()
        );
    }
}

package com.paymelavo.payrunline.dto;

import com.paymelavo.employee.Employee;
import com.paymelavo.payrun.PayRun;
import com.paymelavo.payrun.dto.PayRunResponse;
import com.paymelavo.payrunline.PayRunLine;

import java.math.BigDecimal;

public record PayRunLineResponse(
    Long id,
    Long payRunId,
    Long employeeId,
    BigDecimal normalHours,
    BigDecimal overtimeHours,
    BigDecimal totalHours,
    BigDecimal payRate,
    BigDecimal grossPay,
    BigDecimal taxablePay,
    BigDecimal fnpfEmployee,
    BigDecimal fnpfEmployer,
    BigDecimal paye,
    BigDecimal netPay,
    BigDecimal totalEmployerCost
) {

    public static PayRunLineResponse fromEntity(PayRunLine payRunLine) {
        return new PayRunLineResponse(
                payRunLine.getId(),
                payRunLine.getPayRun().getId(),
                payRunLine.getEmployee().getId(),
                payRunLine.getNormalHours(),
                payRunLine.getOvertimeHours(),
                payRunLine.getTotalHours(),
                payRunLine.getPayRate(),
                payRunLine.getGrossPay(),
                payRunLine.getTaxablePay(),
                payRunLine.getFnpfEmployee(),
                payRunLine.getFnpfEmployer(),
                payRunLine.getPaye(),
                payRunLine.getNetPay(),
                payRunLine.getTotalEmployerCost()
        );
    }
}

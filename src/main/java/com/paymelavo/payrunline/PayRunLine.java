package com.paymelavo.payrunline;

import com.paymelavo.employee.Employee;
import com.paymelavo.payrun.PayRun;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(
        name = "pay_run_lines"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayRunLine {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pay_run_id", nullable = false)
    private PayRun payRun;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id",  nullable = false)
    private Employee employee;

    @NotNull
    @Column(name = "normal_hours", nullable = false)
    private BigDecimal normalHours;

    @NotNull
    @Column(name = "overtime_hours", nullable = false)
    private BigDecimal overtimeHours;

    @NotNull
    @Column(name = "pay_rate", nullable = false, precision = 12, scale = 2)
    private BigDecimal payRate;

    @NotNull
    @Column(name = "gross_pay", nullable = false, precision = 12, scale = 2)
    private BigDecimal grossPay;

    @NotNull
    @Column(name = "taxable_pay", nullable = false, precision = 12, scale = 2)
    private BigDecimal taxablePay;

    @NotNull
    @Column(name = "fnpf_employee", nullable = false, precision = 12, scale = 2)
    private BigDecimal fnpfEmployee;

    @NotNull
    @Column(name = "fnpf_employer", nullable = false, precision = 12, scale = 2)
    private BigDecimal fnpfEmployer;

    @NotNull
    @Column(name = "paye", nullable = false, precision = 12, scale = 2)
    private BigDecimal paye;

    @NotNull
    @Column(name = "net_pay", nullable = false, precision = 12, scale = 2)
    private BigDecimal netPay;

    @NotNull
    @Column(name = "total_employer_cost", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalEmployerCost;
}

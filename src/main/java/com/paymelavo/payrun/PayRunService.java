package com.paymelavo.payrun;

import com.paymelavo.employee.Employee;
import com.paymelavo.employee.EmployeeRepository;
import com.paymelavo.employee.EmploymentStatus;
import com.paymelavo.exception_handler.PayRunNotEditableException;
import com.paymelavo.exception_handler.ResourceNotFoundException;
import com.paymelavo.exception_handler.UpdateErrorException;
import com.paymelavo.payrun.dto.CreatePayRun;
import com.paymelavo.payrun.dto.PayRunResponse;
import com.paymelavo.payrunline.PayRunLine;
import com.paymelavo.payrunline.PayRunLineRepository;
import com.paymelavo.payrunline.dto.PayRunLineResponse;
import com.paymelavo.payrunline.dto.UpdateHoursRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PayRunService {

    private final PayRunRepository payRunRepo;
    private final EmployeeRepository employeeRepo;
    private final PayRunLineRepository payRunLineRepo;

    @Transactional
    public PayRunResponse createPayRun(CreatePayRun payRun) {

        PayRun createRun = PayRun.builder()
                .startDate(payRun.startDate())
                .endDate(payRun.endDate())
                .status(PayRunStatus.OPEN)
                .lines(new ArrayList<>())
                .build();

        List<Employee> employees = employeeRepo.findByEmploymentStatus(EmploymentStatus.ACTIVE);

        for(Employee employee : employees){
            PayRunLine line = PayRunLine.builder()
                    .payRun(createRun)
                    .employee(employee)
                    .normalHours(BigDecimal.ZERO)
                    .overtimeHours(BigDecimal.ZERO)
                    .totalHours(BigDecimal.ZERO)
                    .payRate(BigDecimal.ZERO)
                    .grossPay(BigDecimal.ZERO)
                    .taxablePay(BigDecimal.ZERO)
                    .fnpfEmployee(BigDecimal.ZERO)
                    .fnpfEmployer(BigDecimal.ZERO)
                    .paye(BigDecimal.ZERO)
                    .netPay(BigDecimal.ZERO)
                    .totalEmployerCost(BigDecimal.ZERO)
                    .build();

            createRun.addLine(line);
        }

        PayRun saved = payRunRepo.save(createRun);

        return PayRunResponse.fromEntity(saved);
    }

    @Transactional
    public PayRunLineResponse updatePayRunHours(String payRunId, String lineId, BigDecimal hours) {
        PayRun payRun = payRunRepo.findById(Long.parseLong(payRunId)).get();

        if(payRun == null){
            throw new ResourceNotFoundException("Pay Run not found");
        }

        if(!payRun.getStatus().equals(PayRunStatus.OPEN)){
            throw new PayRunNotEditableException("Pay Run Status is not Open");
        }

        PayRunLine payRunLine = payRunLineRepo.findByPayRunIdAndId(Long.parseLong(payRunId), Long.parseLong(lineId));

        if(!payRunLine.getPayRun().getId().equals(payRun.getId())){
            throw new ResourceNotFoundException("Employee pay run line does not match the pay run");
        }

        payRunLine.setTotalHours(hours);

        PayRunLine updated = payRunLineRepo.save(payRunLine);

        if(updated == null){
            throw new UpdateErrorException("Pay Run Line not updated");
        }

        return PayRunLineResponse.fromEntity(updated);
    }

    @Transactional
    public List<PayRunResponse> getAllPayRuns() {
        List<PayRun> payRuns = payRunRepo.findAll();
        List<PayRunLine> payRunLines = payRunLineRepo.findAll();

        List<PayRunResponse> payRunResponses = new ArrayList<>();
        for(PayRun payRun : payRuns){
            PayRunResponse payRunResponse = PayRunResponse.fromEntity(payRun);
            payRunResponses.add(payRunResponse);
        }

        return payRunResponses;
    }
}

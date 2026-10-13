package com.paymelavo.payrun;

import com.paymelavo.payrun.dto.CreatePayRun;
import com.paymelavo.payrun.dto.PayRunResponse;
import com.paymelavo.payrunline.dto.PayRunLineResponse;
import com.paymelavo.payrunline.dto.UpdateHoursRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payruns")
@RequiredArgsConstructor
public class PayRunController {

    private final PayRunService service;

    @PostMapping
    public ResponseEntity<PayRunResponse> createPayRun(@Valid @RequestBody CreatePayRun request) {
        PayRunResponse response = service.createPayRun(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{payRunId}/lines/{lineId}")
    public ResponseEntity<PayRunLineResponse> updateHours(@PathVariable String payRunId, @PathVariable String lineId, @RequestBody UpdateHoursRequest hours) {
        PayRunLineResponse response = service.updatePayRunHours(payRunId, lineId, hours.hours());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PayRunResponse>> getAllPayRuns(){
        List<PayRunResponse> payRunResponse = service.getAllPayRuns();

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(payRunResponse);
    }

}

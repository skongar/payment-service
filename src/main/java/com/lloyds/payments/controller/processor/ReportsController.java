package com.lloyds.payments.controller.processor;

import com.lloyds.payments.model.ActivityResponse;
import com.lloyds.payments.model.SummaryResponse;
import com.lloyds.payments.service.MetricsService;
import com.lloyds.payments.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportsController {

    private final MetricsService metricsService;

    private final ReportService reportService;

    @GetMapping("/summary")
    public SummaryResponse summary() {

        return metricsService.summary();
    }

    @GetMapping("/activity")
    public ActivityResponse activity(
            @RequestParam Instant from,
            @RequestParam Instant to) {

        return reportService.activity(from, to);
    }
}

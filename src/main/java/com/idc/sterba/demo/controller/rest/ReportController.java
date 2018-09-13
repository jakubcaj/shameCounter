package com.idc.sterba.demo.controller.rest;

import com.idc.sterba.demo.dto.JSONResponse;
import com.idc.sterba.demo.service.ReportService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    private ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @RequestMapping(value = "/matchWinCountReport", method = RequestMethod.POST)
    public JSONResponse getMatchWinCountReport() {
        return new JSONResponse(reportService.getMatchWinCountReport());
    }

    @RequestMapping(value = "/employeeMatchesReport", method = RequestMethod.POST)
    public JSONResponse getEmployeeMatchesReport(@RequestBody Long employeeId) {
        return new JSONResponse(reportService.getEmployeeMatchesReport(employeeId));
    }
}

package com.idc.sterba.demo.controller.rest;

import com.idc.sterba.demo.dto.FilterDTO;
import com.idc.sterba.demo.dto.JSONResponse;
import com.idc.sterba.demo.exception.EmptyFilterException;
import com.idc.sterba.demo.service.ReportService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    private ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @RequestMapping(value = "/matchWinCountReport", method = RequestMethod.POST)
    public JSONResponse getMatchWinCountReport(@RequestBody FilterDTO filterDTO) {
        JSONResponse jsonResponse = new JSONResponse();
        try {
            jsonResponse.setObject(reportService.getMatchWinCountReport(filterDTO));
            jsonResponse.setSuccess(true);
        } catch (EmptyFilterException e) {
            jsonResponse.setSuccess(false);
            jsonResponse.setErrorMessage("Filter is empty.");
        }
        return jsonResponse;
    }

    @RequestMapping(value = "/employeeMatchesReport/{employeeId}", method = RequestMethod.POST)
    public JSONResponse getEmployeeMatchesReport(@PathVariable("employeeId") Long employeeId, @RequestBody FilterDTO filterDTO) {

        JSONResponse jsonResponse = new JSONResponse();
        try {
            jsonResponse.setObject(reportService.getEmployeeMatchesReport(filterDTO, employeeId));
            jsonResponse.setSuccess(true);
        } catch (EmptyFilterException e) {
            jsonResponse.setSuccess(false);
            jsonResponse.setErrorMessage("Filter is empty.");
        }
        return jsonResponse;
    }

    @RequestMapping(value = "/roundWinCountReport", method = RequestMethod.POST)
    public JSONResponse getRoundWinCountReport(@RequestBody FilterDTO filterDTO) {
        JSONResponse jsonResponse = new JSONResponse();
        try {
            jsonResponse.setObject(reportService.getRoundWinCountReport(filterDTO));
            jsonResponse.setSuccess(true);
        } catch (EmptyFilterException e) {
            jsonResponse.setSuccess(false);
            jsonResponse.setErrorMessage("Filter is empty.");
        }
        return jsonResponse;
    }

    @RequestMapping(value = "/goalDidGetReport", method = RequestMethod.POST)
    public JSONResponse getGoalDidGetReport(@RequestBody FilterDTO filterDTO) {
        JSONResponse jsonResponse = new JSONResponse();
        try {
            jsonResponse.setObject(reportService.getGoalDidGetReport(filterDTO));
            jsonResponse.setSuccess(true);
        } catch (EmptyFilterException e) {
            jsonResponse.setSuccess(false);
            jsonResponse.setErrorMessage("Filter is empty.");
        }
        return jsonResponse;
    }

    @RequestMapping(value = "/matchPositionReport", method = RequestMethod.POST)
    public JSONResponse getMatchPositionReport(@RequestBody FilterDTO filterDTO) {
        JSONResponse jsonResponse = new JSONResponse();
        try {
            jsonResponse.setObject(reportService.getMatchPositionReport(filterDTO));
            jsonResponse.setSuccess(true);
        } catch (EmptyFilterException e) {
            jsonResponse.setSuccess(false);
            jsonResponse.setErrorMessage("Filter is empty.");
        }
        return jsonResponse;
    }
}

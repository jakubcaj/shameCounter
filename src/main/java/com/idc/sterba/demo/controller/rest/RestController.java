package com.idc.sterba.demo.controller.rest;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.idc.sterba.demo.dto.JSONResponse;
import com.idc.sterba.demo.dto.MatchDTO;
import com.idc.sterba.demo.dto.RegisterFormDTO;
import com.idc.sterba.demo.entity.Match;
import com.idc.sterba.demo.service.EmployeeService;
import com.idc.sterba.demo.service.MatchService;
import com.idc.sterba.demo.service.SecurityService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    private final EmployeeService employeeService;
    private final MatchService matchService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final SecurityService securityService;

    public RestController(EmployeeService employeeService, MatchService matchService, SimpMessagingTemplate simpMessagingTemplate, SecurityService securityService) {
        this.employeeService = employeeService;
        this.matchService = matchService;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.securityService = securityService;
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public JSONResponse test(@RequestBody String term) {
        JSONResponse jsonResponse = new JSONResponse();

        jsonResponse.setObject(employeeService.getAllEmployees());
        jsonResponse.setSuccess(true);

        return jsonResponse;
    }

    @RequestMapping(value = "/search/employees", method = RequestMethod.POST)
    public JSONResponse searchEmployees(@RequestBody String term) {
        JSONResponse jsonResponse = new JSONResponse();

        jsonResponse.setObject(employeeService.getEmployeesByTerm(term));
        jsonResponse.setSuccess(true);

        return jsonResponse;
    }

    @RequestMapping(value = "/create/match", method = RequestMethod.POST)
    public JSONResponse createMatch(@RequestBody MatchDTO matchDTO) {

        JSONResponse jsonResponse = new JSONResponse();
        Match match = matchService.saveMatch(matchDTO);
        jsonResponse.setObject(match);
        this.simpMessagingTemplate.convertAndSend("/chat", match);
        return jsonResponse;
    }

    @RequestMapping(value = "/authenticated", method = RequestMethod.POST)
    public JSONResponse isAuthenticated() {
        return new JSONResponse(securityService.isUserAuthenticated());
    }

    @RequestMapping(value = "/loggedUser", method = RequestMethod.POST)
    public JSONResponse loggedUser() {
        return new JSONResponse(securityService.getLoggedUser());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public JSONResponse register(@RequestBody RegisterFormDTO registerFormDTO) {
        employeeService.registerEmployee(registerFormDTO);
        JSONResponse jsonResponse = new JSONResponse();
        jsonResponse.setSuccess(true);
        return jsonResponse;
    }

    @RequestMapping(value = "/test/test", method = RequestMethod.POST)
    public void sendTest() {
        this.simpMessagingTemplate.convertAndSend("/team/1");
    }

}
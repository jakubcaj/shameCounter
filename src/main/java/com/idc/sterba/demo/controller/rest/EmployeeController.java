package com.idc.sterba.demo.controller.rest;

import com.idc.sterba.demo.dto.JSONResponse;
import com.idc.sterba.demo.dto.PasswordDTO;
import com.idc.sterba.demo.exception.PasswordNotMatchingException;
import com.idc.sterba.demo.service.EmployeeMetadataService;
import com.idc.sterba.demo.service.EmployeeService;
import com.idc.sterba.demo.service.PlayerGroupService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/employee")
public class EmployeeController {

    private PlayerGroupService teamService;
    private EmployeeService employeeService;
    private EmployeeMetadataService employeeMetadataService;


    public EmployeeController(PlayerGroupService teamService, EmployeeService employeeService, EmployeeMetadataService employeeMetadataService) {
        this.teamService = teamService;
        this.employeeService = employeeService;
        this.employeeMetadataService = employeeMetadataService;
    }

    @RequestMapping(value = "/groups", method = RequestMethod.POST)
    public JSONResponse getUserTeams() {
        JSONResponse jsonResponse = new JSONResponse();
        jsonResponse.setObject(teamService.getLoggedUserGroups());
        jsonResponse.setSuccess(true);
        return jsonResponse;
    }

    @RequestMapping(value = "/searchByGroup", method = RequestMethod.POST)
    public JSONResponse getUsersByGroup(@RequestBody Long groupId) {
        JSONResponse jsonResponse = new JSONResponse();
        jsonResponse.setObject(employeeService.getEmployeesByGroup(groupId));
        return jsonResponse;
    }

    @RequestMapping(value = "/upload/picture", method = RequestMethod.POST)
    public JSONResponse uploadPicture(@RequestParam MultipartFile file, @RequestParam Long user) {
        this.employeeMetadataService.savePicture(file, user);
        return new JSONResponse();
    }

    @RequestMapping(value = "/get/picture", method = RequestMethod.POST)
    public JSONResponse getPicture(@RequestBody Long userId) {
        return new JSONResponse(this.employeeMetadataService.getPicture(userId));
    }

    @RequestMapping(value = "/change/password", method = RequestMethod.POST)
    public JSONResponse getPicture(@RequestBody PasswordDTO passwordDTO) {
        JSONResponse jsonResponse = new JSONResponse();

        try {
            this.employeeService.changePassword(passwordDTO);
            jsonResponse.setSuccess(true);
        } catch (PasswordNotMatchingException e) {
            jsonResponse.setSuccess(false);
            jsonResponse.setErrorMessage("Bad current password.");
        }
        return jsonResponse;
    }

}

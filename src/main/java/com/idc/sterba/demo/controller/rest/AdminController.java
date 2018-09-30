package com.idc.sterba.demo.controller.rest;

import com.idc.sterba.demo.dto.JSONResponse;
import com.idc.sterba.demo.service.SeasonService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private SeasonService seasonService;

    public AdminController(SeasonService seasonService) {
        this.seasonService = seasonService;
    }

    @RequestMapping(value = "/create/season")
    public JSONResponse createSeason() {
        this.seasonService.createNewSeason();

        return new JSONResponse(null, true);
    }
}

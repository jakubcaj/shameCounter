package com.idc.sterba.demo.controller.rest;

import com.idc.sterba.demo.dto.JSONResponse;
import com.idc.sterba.demo.entity.Season;
import com.idc.sterba.demo.service.SeasonService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/season")
public class SeasonController {

    private SeasonService seasonService;

    public SeasonController(SeasonService seasonService) {
        this.seasonService = seasonService;
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.POST)
    public JSONResponse getAllSeasons() {
        List<Season> seasons = this.seasonService.getAllSeasons();
        return new JSONResponse(seasons, true);
    }
}

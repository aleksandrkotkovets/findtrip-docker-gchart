package com.sam_solutions.findtrip.controller;

import com.sam_solutions.findtrip.controller.dto.ChartDto;
import com.sam_solutions.findtrip.service.InfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/info")
public class InfoController {

    private final InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping("/chart")
    public List<ChartDto> getChart() {
        return infoService.getDataForChart();
    }
}

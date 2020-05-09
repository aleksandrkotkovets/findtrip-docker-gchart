package com.sam_solutions.findtrip.controller;

import com.sam_solutions.findtrip.controller.dto.ChartDto;
import com.sam_solutions.findtrip.service.InfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/info")
public class InfoController {

    private final InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping("/chart")
    public ModelAndView getChartView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("chart/chart");
        return modelAndView;
    }

    @GetMapping("/chart/data")
    public List<ChartDto> getChartData() {
        return infoService.getDataForChart();
    }
}

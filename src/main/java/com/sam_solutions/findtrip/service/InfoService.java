package com.sam_solutions.findtrip.service;

import com.sam_solutions.findtrip.controller.dto.ChartDto;

import java.util.List;

public interface InfoService {
    List<ChartDto> getDataForChart();
}

package com.sam_solutions.findtrip.controller.dto;

public class ChartDto {

    private String flightName;
    private Long countFlight;

    public ChartDto(String flightName, Long countFlight) {
        this.flightName = flightName;
        this.countFlight = countFlight;
    }

    public ChartDto() {
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public Long getCountFlight() {
        return countFlight;
    }

    public void setCountFlight(Long countFlight) {
        this.countFlight = countFlight;
    }
}

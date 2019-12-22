package by.sam_solutions.findtrip.controller.dto;

public class TicketCreateUpdateDTO {

    private Long id;
    private Long idFlight;
    private Long idClient;
    private Integer countSeats;
    private Double finalCost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(Long idFlight) {
        this.idFlight = idFlight;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public Integer getCountSeats() {
        return countSeats;
    }

    public void setCountSeats(Integer countSeats) {
        this.countSeats = countSeats;
    }

    public Double getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(Double finalCost) {
        this.finalCost = finalCost;
    }

    @Override
    public String toString() {
        return "TicketCreateUpdateDTO{" +
                "id=" + id +
                ", idFlight=" + idFlight +
                ", idClient=" + idClient +
                ", countSeats=" + countSeats +
                ", finalCost=" + finalCost +
                '}';
    }
}

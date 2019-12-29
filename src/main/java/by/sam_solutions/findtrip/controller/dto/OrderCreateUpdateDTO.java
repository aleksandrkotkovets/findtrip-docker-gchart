package by.sam_solutions.findtrip.controller.dto;

public class OrderCreateUpdateDTO {

    private Long id;
    private Long idFlight;
    private Integer countSeats;
    private Long idClient;
    private Double priceOneSeat;
    private Double finalCost;
    private Double returnMoney;
    private Integer returnTickets;

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

    public Integer getCountSeats() {
        return countSeats;
    }

    public void setCountSeats(Integer countSeats) {
        this.countSeats = countSeats;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public Double getPriceOneSeat() {
        return priceOneSeat;
    }

    public void setPriceOneSeat(Double priceOneSeat) {
        this.priceOneSeat = priceOneSeat;
    }

    public Double getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(Double finalCost) {
        this.finalCost = finalCost;
    }

    public Double getReturnMoney() {
        return returnMoney;
    }

    public void setReturnMoney(Double returnMoney) {
        this.returnMoney = returnMoney;
    }

    public Integer getReturnTickets() {
        return returnTickets;
    }

    public void setReturnTickets(Integer returnTickets) {
        this.returnTickets = returnTickets;
    }

    @Override
    public String toString() {
        return "OrderCreateUpdateDTO{" +
                "id=" + id +
                ", idFlight=" + idFlight +
                ", countSeats=" + countSeats +
                ", idClient=" + idClient +
                ", priceOneSeat=" + priceOneSeat +
                ", finalCost=" + finalCost +
                ", returnMoney=" + returnMoney +
                ", returnTickets=" + returnTickets +
                '}';
    }
}

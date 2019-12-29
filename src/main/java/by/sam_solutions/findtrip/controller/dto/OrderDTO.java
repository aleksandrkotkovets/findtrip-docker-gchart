package by.sam_solutions.findtrip.controller.dto;

import java.sql.Timestamp;
import java.util.List;

public class OrderDTO {

    private Long id;
    private Double finalCost;
    private Timestamp orderDate;
    private UserDTO userDTO;
    private FlightDTO flightDTO;
    private List<TicketDTO> ticketDTOList;

    public OrderDTO() {
    }

    public OrderDTO(Long id, Double finalCost, Timestamp orderDate, UserDTO userDTO, FlightDTO flightDTO, List<TicketDTO> ticketDTOList) {
        this.id = id;
        this.finalCost = finalCost;
        this.orderDate = orderDate;
        this.userDTO = userDTO;
        this.flightDTO = flightDTO;
        this.ticketDTOList = ticketDTOList;
    }

    public OrderDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(Double finalCost) {
        this.finalCost = finalCost;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public FlightDTO getFlightDTO() {
        return flightDTO;
    }

    public void setFlightDTO(FlightDTO flightDTO) {
        this.flightDTO = flightDTO;
    }

    public List<TicketDTO> getTicketDTOList() {
        return ticketDTOList;
    }

    public void setTicketDTOList(List<TicketDTO> ticketDTOList) {
        this.ticketDTOList = ticketDTOList;
    }
}

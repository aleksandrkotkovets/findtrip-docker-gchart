/*
package com.sam_solutions.findtrip.controller;

import com.sam_solutions.findtrip.controller.dto.CompanyDTO;
import com.sam_solutions.findtrip.controller.dto.FlightCreateUpdateDTO;
import com.sam_solutions.findtrip.controller.dto.FlightDTO;
import com.sam_solutions.findtrip.controller.dto.PlaneDTO;
import com.sam_solutions.findtrip.repository.entity.Rating;
import com.sam_solutions.findtrip.service.impl.CompanyServiceImpl;
import com.sam_solutions.findtrip.service.impl.FlightServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("worker")
public class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightServiceImpl flightService;

    @MockBean
    private CompanyServiceImpl companyService;

    @Autowired
    private FlightController flightController;

    @Test
    public void getPlanes() throws Exception {
        List<PlaneDTO> planeDTOList = new ArrayList<>();
        PlaneDTO planeDTOf = new PlaneDTO(2L, "Boeng", "B3434", new CompanyDTO(1L, "Fake", Rating.TWO_STARS));
        PlaneDTO planeDTOs = new PlaneDTO(3L, "Boeng", "B3444", new CompanyDTO(1L, "Fake", Rating.TWO_STARS));
        planeDTOList.add(planeDTOf);
        planeDTOList.add(planeDTOs);
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setPlaneDTOList(planeDTOList);
        when(companyService.findOne(1L)).thenReturn(companyDTO);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/flights/companies/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());


        assertEquals(planeDTOList.size(), flightController.getPlanes(1L).size());
    }

    @Test
    public void addFlight() throws Exception {
        FlightCreateUpdateDTO flightCreateUpdateDTO = new FlightCreateUpdateDTO();
        flightCreateUpdateDTO.setFreeSeats(100);
        flightCreateUpdateDTO.setTicketPrice(1.1);
        flightCreateUpdateDTO.setCompanyId(1L);
        flightCreateUpdateDTO.setAllSeats(124);
        flightCreateUpdateDTO.setAirportToId(2L);
        flightCreateUpdateDTO.setAirportFromId(3L);
        flightCreateUpdateDTO.setPlaneId(4L);
        flightCreateUpdateDTO.setDateArrival(12131231231L);
        flightCreateUpdateDTO.setDateDeparture(12321334234L);
        flightCreateUpdateDTO.setCityFromId(7L);
        flightCreateUpdateDTO.setCityToId(8L);

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/flights").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(flightCreateUpdateDTO)))
                .andExpect(status().isCreated());

    }

    @Test
    public void getShowFlightView() throws Exception {
        FlightDTO flightDTOf = new FlightDTO();
        FlightDTO flightDTOs = new FlightDTO();
        when(flightService.findAll()).thenReturn(Arrays.asList(flightDTOf, flightDTOs));

        mockMvc.perform(get("/flights"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(model().attributeExists("countries"))
                .andExpect(model().attributeExists("companies"))
                .andExpect(view().name("withrole/showFlights"))
                .andDo(print());

    }


}*/

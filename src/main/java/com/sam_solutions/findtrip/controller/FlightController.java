package com.sam_solutions.findtrip.controller;

import com.sam_solutions.findtrip.controller.dto.AirportDTO;
import com.sam_solutions.findtrip.controller.dto.CityDTO;
import com.sam_solutions.findtrip.controller.dto.CompanyDTO;
import com.sam_solutions.findtrip.controller.dto.CountryDTO;
import com.sam_solutions.findtrip.controller.dto.FlightCreateUpdateDTO;
import com.sam_solutions.findtrip.controller.dto.FlightCriteriaDTO;
import com.sam_solutions.findtrip.controller.dto.FlightDTO;
import com.sam_solutions.findtrip.controller.dto.OrderDTO;
import com.sam_solutions.findtrip.controller.dto.PlaneDTO;
import com.sam_solutions.findtrip.repository.entity.FlightStatus;
import com.sam_solutions.findtrip.repository.entity.Rating;
import com.sam_solutions.findtrip.service.CityService;
import com.sam_solutions.findtrip.service.CompanyService;
import com.sam_solutions.findtrip.service.CountryService;
import com.sam_solutions.findtrip.service.EmailSender;
import com.sam_solutions.findtrip.service.FlightService;
import com.sam_solutions.findtrip.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/flights")
public class FlightController {

    private final static Logger LOGGER = LogManager.getLogger();

    private FlightService flightService;
    private CountryService countryService;
    private CityService cityService;
    private CompanyService companyService;
    private EmailSender emailSender;
    private OrderService orderService;

    private List<CountryDTO> countryDTOList;
    private List<CompanyDTO> companyDTOList;

    public FlightController(FlightService flightService,
                            CountryService countryService,
                            CityService cityService,
                            CompanyService companyService,
                            EmailSender emailSender,
                            OrderService orderService) {
        this.flightService = flightService;
        this.countryService = countryService;
        this.cityService = cityService;
        this.companyService = companyService;
        this.emailSender = emailSender;
        this.orderService = orderService;
    }

    @ModelAttribute("countries")
    public List<CountryDTO> getCountries() {
        countryDTOList = countryService.findAll(Sort.by("name").ascending());
        return countryDTOList;
    }

    @GetMapping("/countries")
    @ResponseBody
    public List<CountryDTO> getCountriesJson() {
        return countryDTOList;
    }

    @ModelAttribute("companies")
    public List<CompanyDTO> getCompanies() {
        companyDTOList = companyService.findAll();
        return companyDTOList;
    }

    @GetMapping("/companies")
    @ResponseBody
    public List<CompanyDTO> getCompaniesJson() {
        return companyDTOList;
    }

    @GetMapping("/create")
    public String getAddFlightView(Model model) {
        model.addAttribute("flight", new FlightDTO());
        return "flight/addFlight";
    }

    @GetMapping("/edit/{id}")
    public String getEditFlightView(@PathVariable Long id, Model model) {
        FlightDTO flightDTO = flightService.getById(id);

        String dDeparture = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(flightDTO.getDepartureDate().getTime()));
        String dArrival = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(flightDTO.getArrivalDate().getTime()));

        model.addAttribute("id", flightDTO.getId());
        model.addAttribute("countries_fr", flightDTO.getAirportDeparture().getCityDTO().getCountryDTO());
        model.addAttribute("countries_to", flightDTO.getAirportArrival().getCityDTO().getCountryDTO());
        model.addAttribute("cities_fr", flightDTO.getAirportDeparture().getCityDTO());
        model.addAttribute("cities_to", flightDTO.getAirportArrival().getCityDTO());
        model.addAttribute("airports_fr", flightDTO.getAirportDeparture());
        model.addAttribute("airports_to", flightDTO.getAirportArrival());
        model.addAttribute("picker1", dDeparture);
        model.addAttribute("picker2", dArrival);
        model.addAttribute("companies", flightDTO.getPlane().getCompanyDTO());
        model.addAttribute("planes", flightDTO.getPlane());
        model.addAttribute("allSeats", flightDTO.getAllSeats());
        model.addAttribute("freeSeats", flightDTO.getFreeSeats());
        model.addAttribute("price", flightDTO.getPrice());
        model.addAttribute("soldTickets", flightService.getNumberSoldTicketById(flightDTO.getId()));
        return "flight/editFlight";
    }

    @GetMapping(value = "/countries/{id}")
    @ResponseBody
    public List<CityDTO> getCities(@PathVariable Long id) {
        return cityService.getCityListByCountry(id);
    }

    @GetMapping(value = "/cities/{id}")
    @ResponseBody
    public List<AirportDTO> getAirports(@PathVariable Long id) {
        return cityService.findOne(id).getAirportDTOList();
    }

    @GetMapping(value = "/companies/{id}")
    @ResponseBody
    public List<PlaneDTO> getPlanes(@PathVariable Long id) {
        return companyService.findOne(id).getPlaneDTOList();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public FlightCreateUpdateDTO addFlight(@RequestBody FlightCreateUpdateDTO flightDTO) {
        LOGGER.info("Create flight where flightCreateUpdateDTO: " + flightDTO);
        flightService.addFlight(flightDTO);
        return flightDTO;
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public FlightCreateUpdateDTO editFlight(@PathVariable Long id, @RequestBody FlightCreateUpdateDTO flightDTO) {
        LOGGER.info("Edit flight where new flightCreateUpdateDTO: " + flightDTO);
        flightService.edit(flightDTO);
        return flightDTO;
    }


    @PreAuthorize("hasAnyRole('WORKER','ADMIN')")
    @GetMapping()
    public String getShowFlightView(@RequestParam(value = "status", required = false, defaultValue = "ACTIVE") FlightStatus status, Model model) {
        List<FlightDTO> flightDTOList = flightService.findAllByStatus(status);
        model.addAttribute("flights", flightDTOList.size() != 0 ? flightDTOList : null);
        return "withrole/showFlights";
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @GetMapping("/show/{id}")
    public String getShowFlightViewByClient(@PathVariable Long id, Model model) {
        model.addAttribute("flight", flightService.getById(id));
        return "flight/checkoutOrder";
    }

    @GetMapping("/canceled/{id}")
    public String canceledFlight(@PathVariable(name = "id") Long idFlight) {
        LOGGER.info("Cancel flight where flight id: " + idFlight);
        flightService.canceledFlight(idFlight);
        sendСancellationСonfirmToEmail(idFlight);
        return "redirect:/flights";
    }

    @GetMapping("/{id}/orders")
    public String getFlightTicketsSold(@PathVariable Long id, Model model) {
        model.addAttribute("orders", orderService.findAllByFlightId(id));
        return "order/showOrdersOnFlight";
    }

    private void sendСancellationСonfirmToEmail(Long idFlight) {
        LOGGER.info("Send a cancellation email to all users");
        List<OrderDTO> orderDTOList = orderService.findAllByFlightId(idFlight);
        orderDTOList.stream().forEach(a -> emailSender.sendСancellationСonfirmToEmail(a));
    }

    @GetMapping("/findFlights")
    public ModelAndView getFlightByCriteria() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("flight/showFlights");
        modelAndView.addObject("flights", null);
        modelAndView.addObject("rating", Rating.values());
        modelAndView.addObject("flightCriteriaDTO", new FlightCriteriaDTO());
        return modelAndView;
    }

    @PostMapping("/findFlights")
    public ModelAndView getFlightByCriteria(@ModelAttribute("flightCriteriaDTO") FlightCriteriaDTO flightCriteriaDTO,
                                            @RequestParam(value = "companyChoice", required = false) Long companyChoice) {
        LOGGER.info("Get flights by criteria: " + flightCriteriaDTO);
        ModelAndView modelAndView = new ModelAndView("flight/showFlights");
        modelAndView.addObject("picker1", flightCriteriaDTO.getDepartureDate());
        modelAndView.addObject("city_from", cityService.findOne(flightCriteriaDTO.getIdCityDeparture()));
        modelAndView.addObject("city_to", cityService.findOne(flightCriteriaDTO.getIdCityArrival()));
        modelAndView.addObject("companyChoice", flightCriteriaDTO.getIdCompany() == null ? null : companyService.findOne(flightCriteriaDTO.getIdCompany()).getName());
        modelAndView.addObject("flightCriteriaDTO", flightCriteriaDTO);

        List<FlightDTO> flightDTOList = null;
        try {
            flightDTOList = flightService.findFlightsByCriteria(flightCriteriaDTO);
        } catch (ParseException e) {
            LOGGER.error("Parse dates error");
            e.printStackTrace();
        }
        modelAndView.addObject("flights", flightDTOList.size() == 0 ? null : flightDTOList);
        return modelAndView;
    }

}

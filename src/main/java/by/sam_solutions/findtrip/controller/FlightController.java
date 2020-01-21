package by.sam_solutions.findtrip.controller;

import by.sam_solutions.findtrip.controller.dto.*;
import by.sam_solutions.findtrip.repository.entity.FlightStatus;
import by.sam_solutions.findtrip.repository.entity.Rating;
import by.sam_solutions.findtrip.security.CustomUserDetail;
import by.sam_solutions.findtrip.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/flights")
public class FlightController {


    private FlightService flightService;
    private CountryService countryService;
    private CityService cityService;
    private CompanyService companyService;
    private EmailSender emailSender;
    private OrderService orderService;

    private List<CountryDTO> countryDTOList;
    private List<CompanyDTO> companyDTOList;
    private List<FlightDTO> flightDTOList;

    @Autowired
    public FlightController(FlightService flightService, CountryService countryService, CityService cityService, CompanyService companyService, EmailSender emailSender, OrderService orderService) {
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

    @ModelAttribute("flights")
    public List<FlightDTO> getFlights() {
        flightDTOList = flightService.findAll();
        return flightDTOList;
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
        flightService.addFlight(flightDTO);
        return flightDTO;
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public FlightCreateUpdateDTO editFlight(@PathVariable Long id, @RequestBody FlightCreateUpdateDTO flightDTO) {
        flightService.edit(flightDTO);
        return flightDTO;
    }


    @PreAuthorize("hasAnyRole('WORKER','ADMIN')")
    @GetMapping()
    public String getShowFlightView(Model model) {
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
        ModelAndView modelAndView = new ModelAndView("flight/showFlights");
        modelAndView.addObject("picker1", flightCriteriaDTO.getDepartureDate());
        modelAndView.addObject("city_from", cityService.findOne(flightCriteriaDTO.getIdCityDeparture()));
        modelAndView.addObject("city_to", cityService.findOne(flightCriteriaDTO.getIdCityArrival()));
        modelAndView.addObject("companyChoice", flightCriteriaDTO.getIdCompany()==null? null : companyService.findOne(flightCriteriaDTO.getIdCompany()).getName());
        modelAndView.addObject("flightCriteriaDTO", flightCriteriaDTO);

        List<FlightDTO> flightDTOList = null;
        try {
            flightDTOList = flightService.findFlightsByCriteria(flightCriteriaDTO);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        modelAndView.addObject("flights", flightDTOList.size() == 0 ? null : flightDTOList);
        return modelAndView;
    }

}

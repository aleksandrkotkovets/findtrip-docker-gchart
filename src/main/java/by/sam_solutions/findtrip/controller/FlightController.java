package by.sam_solutions.findtrip.controller;

import by.sam_solutions.findtrip.controller.dto.*;
import by.sam_solutions.findtrip.security.CustomUserDetail;
import by.sam_solutions.findtrip.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private CityService cityService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private OrderService orderService;

    private List<CountryDTO> countryDTOList;
    private List<CompanyDTO> companyDTOList;
    private List<FlightDTO> flightDTOList;

    @ModelAttribute("countries")
    public List<CountryDTO> getCountries() {
        countryDTOList = countryService.findAll();
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
        List<CityDTO> list = cityService.getCityListByCountry(id);
        return list;
    }

    @GetMapping(value = "/cities/{id}")
    @ResponseBody
    public List<AirportDTO> getAirports(@PathVariable Long id) {
        CityDTO cityDTO = cityService.findOne(id);
        return cityDTO.getAirportDTOList();
    }

    @GetMapping(value = "/companies/{id}")
    @ResponseBody
    public List<PlaneDTO> getPlanes(@PathVariable Long id) {
        CompanyDTO companyDTO = companyService.findOne(id);
        return companyDTO.getPlaneDTOList();
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
    public String getShowFlightViewByClient(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetail currentUser, Model model) {
        model.addAttribute("flight", flightService.getById(id));
        return "flight/checkoutOrder";
    }

    @GetMapping("/canceled/{id}")
    public String canceledFlight(@PathVariable(name = "id") Long idFlight){
        flightService.canceledFlight(idFlight);
        sendСancellationСonfirmToEmail(idFlight);
        return "redirect:/flights";
    }


    private void sendСancellationСonfirmToEmail(Long idFlight) {
        List<OrderDTO> orderDTOList = orderService.findAllByFlightId(idFlight);
        orderDTOList.stream().forEach(a->emailSender.sendСancellationСonfirmToEmail(a));
    }

}

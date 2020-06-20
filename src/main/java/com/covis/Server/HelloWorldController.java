package com.covis.Server;


import com.covis.Server.DAO.MainRepository;
import com.covis.Server.DAO.PopulationRepository;
import com.covis.Server.Entities.DatabaseRecord;
import com.covis.Server.Entities.SIRDModel;
import com.covis.Server.country.controller.CountryCovidInformationController;
import com.covis.Server.world.controller.WorldCovidInformationController;
import com.covis.api.CovidCaseDto;
import com.covis.api.HelloWorldResource;
import com.covis.api.country.dto.CovidDailyCasesDto;
import com.covis.api.covid.CovidCasesType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

@RestController
public class HelloWorldController implements HelloWorldResource{

    @Autowired
    CountryCovidInformationController countryCovidInformationController;

    @Autowired
    WorldCovidInformationController worldCovidInformationController;

    @Autowired
    PopulationRepository populationRepository;


    public HelloWorldController(){
        //empty public contructor
    }
    @Autowired
    private MainRepository mainRepository;
    @GetMapping("/helloworld")
    public String helloWorldEndPoint(){

        SIRDModel model = new SIRDModel(0.5, 0.0714, 0.053);
        List<CovidDailyCasesDto> testAcitve = model.resolve(BigDecimal.valueOf(30000),BigDecimal.valueOf(2), BigDecimal.valueOf(0), BigDecimal.valueOf(0), LocalDate.of(2020, 1,1), LocalDate.of(2020, 1, 20), CovidCasesType.ACTIVE);
        List<CovidDailyCasesDto> testNew = model.resolve(BigDecimal.valueOf(30000),BigDecimal.valueOf(2), BigDecimal.valueOf(0), BigDecimal.valueOf(0), LocalDate.of(2020, 1,1), LocalDate.of(2020, 1, 20), CovidCasesType.NEW);
        List<CovidDailyCasesDto> testRecovered = model.resolve(BigDecimal.valueOf(30000),BigDecimal.valueOf(2), BigDecimal.valueOf(0), BigDecimal.valueOf(0), LocalDate.of(2020, 1,1), LocalDate.of(2020, 1, 20), CovidCasesType.RECOVERED);
        List<CovidDailyCasesDto> testDeath = model.resolve(BigDecimal.valueOf(30000),BigDecimal.valueOf(2), BigDecimal.valueOf(0), BigDecimal.valueOf(0), LocalDate.of(2020, 1,1), LocalDate.of(2020, 1, 20), CovidCasesType.DEATH);

        

        return "Hello Covis v0.2";
    }

}

package com.foxelyss.transportbooking;

import com.foxelyss.transportbooking.model.Company;
import com.foxelyss.transportbooking.model.Transporting;
import com.foxelyss.transportbooking.model.TransportingResult;
import com.foxelyss.transportbooking.repos.CompanyRepo;
import com.foxelyss.transportbooking.service.TransportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/about")
public class DataController {
    @Autowired
    TransportService transportService;
    @Autowired
    CompanyRepo comService;

    @GetMapping("/transport")
    public TransportingResult GetTransportInfo(@RequestParam(value = "id") long id) {
        return transportService.getItemById(id);
    }

    @GetMapping("/company")
    public Company GetCompanyInfo(@RequestParam(value = "id") long id) {
        return comService.findById(id);
    }
}

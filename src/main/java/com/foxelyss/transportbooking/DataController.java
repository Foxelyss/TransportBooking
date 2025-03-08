package com.foxelyss.transportbooking;

import com.foxelyss.transportbooking.model.Company;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/about")
public class DataController {
    @GetMapping("/transport")
    public Company GetTransportInfo(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Company(1, "123", "123", "123");
    }

    @GetMapping("/company")
    public Company GetCompanyInfo(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Company(1, "123", "123", "123");
    }
}

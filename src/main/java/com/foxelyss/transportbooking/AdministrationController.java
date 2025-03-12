package com.foxelyss.transportbooking;

import com.foxelyss.transportbooking.model.Book;
import com.foxelyss.transportbooking.model.Company;
import com.foxelyss.transportbooking.model.Point;
import com.foxelyss.transportbooking.model.TransportingResult;
import com.foxelyss.transportbooking.service.BookService;
import com.foxelyss.transportbooking.service.CompanyService;
import com.foxelyss.transportbooking.service.PointService;
import com.foxelyss.transportbooking.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/admin")
public class AdministrationController {
    @Autowired
    TransportService transportService;
    @Autowired
    PointService pointService;
    @Autowired
    CompanyService companyService;

    @GetMapping("/add_point")
    public String AddPoint(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "region") String region,
            @RequestParam(value = "city") String town) {

        pointService.createItem(new Point(-1, name, region, town));
        return "";
    }

    @GetMapping("/create_company")
    public String CreateCompany(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "address") String address,
            @RequestParam(value = "inn") String INN) {
        companyService.createItem(new Company(-1, name, address, INN));
        return "";
    }
}

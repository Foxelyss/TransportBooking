package com.foxelyss.transportbooking;

import com.foxelyss.transportbooking.model.Book;
import com.foxelyss.transportbooking.model.Point;
import com.foxelyss.transportbooking.model.Transporting;
import com.foxelyss.transportbooking.service.BookService;
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
    BookService traasdnsportService;

    @GetMapping("/add_point")
    public String AddPoint(@RequestParam(value = "name", defaultValue = "World") String name) {

        pointService.createItem(new Point(0, null, "Омская область", "фывф"));
        return "";
    }

    @GetMapping("/create_company")
    public String CreateCompany(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "";
    }

    @GetMapping("/delete_company")
    public String DeleteCompany(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "";
    }
}

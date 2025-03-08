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
@RequestMapping("/api/business")
public class BusinessController {
    @Autowired
    TransportService transportService;
    @Autowired
    PointService pointService;
    @Autowired
    BookService traasdnsportService;

    @GetMapping("/add_transport")
    public String AddTransporting(@RequestParam(value = "name", defaultValue = "World") String name) {

        // transportService.createItem(new Transporting(0, "", new Timestamp(123123123),
        // new Timestamp(1233231223123123L), "", "", 1, 2, 2, "", ""));
        return "";
    }
}

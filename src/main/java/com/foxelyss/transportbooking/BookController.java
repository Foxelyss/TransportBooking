package com.foxelyss.transportbooking;

import com.foxelyss.transportbooking.model.Book;
import com.foxelyss.transportbooking.model.Passenger;
import com.foxelyss.transportbooking.model.Point;
import com.foxelyss.transportbooking.model.TransportingResult;
import com.foxelyss.transportbooking.service.BookService;
import com.foxelyss.transportbooking.service.PointService;
import com.foxelyss.transportbooking.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/booking")
public class BookController {
    @Autowired
    TransportService transportService;
    @Autowired
    PointService pointService;
    @Autowired
    BookService bookService;

    @GetMapping("/books")
    public ArrayList<TransportingResult> GetTicketsForUser(@RequestParam(value = "transporting") long passport) {
        return null;
    }

    @PostMapping("/book")
    public String BuyTicket(@RequestParam(value = "transporting") int transporting,
            @RequestParam(value = "transporting") String name,
            @RequestParam(value = "transporting") String surname,
            @RequestParam(value = "transporting") String middle_name,
            @RequestParam(value = "transporting") String email,
            @RequestParam(value = "transporting") long passport,
            @RequestParam(value = "transporting") long phone) {
        bookService.createItem(
                new Passenger(phone, email, surname, name, middle_name, passport),
                new Book(0, 1, 1, "", 1, transporting));
        return "400";

    }

    @PostMapping("/return")
    public String ReturnTicket(@RequestParam(value = "transporting") int transporting,
            @RequestParam(value = "transporting") String name,
            @RequestParam(value = "transporting") String surname,
            @RequestParam(value = "transporting") String middle_name,
            @RequestParam(value = "transporting") String email,
            @RequestParam(value = "transporting") long passport,
            @RequestParam(value = "transporting") long phone) {
        bookService.createItem(
                new Passenger(phone, email, surname, name, middle_name, passport),
                new Book(0, 1, 1, "", 1, transporting));
        return "400";

    }
}

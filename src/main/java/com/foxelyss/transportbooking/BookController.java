package com.foxelyss.transportbooking;

import com.foxelyss.transportbooking.model.Passenger;
import com.foxelyss.transportbooking.repos.BookRepo;
import com.foxelyss.transportbooking.service.BookService;
import com.foxelyss.transportbooking.service.PointService;
import com.foxelyss.transportbooking.service.TransportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<BookRepo.Ticket> GetTicketsForUser(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "passport") long passport) {
        return bookService.findAllByDetails(email, passport);
    }

    @PostMapping("/book")
    public String BuyTicket(@RequestParam(value = "transporting") int transporting,
                            @RequestParam(value = "name") String name,
                            @RequestParam(value = "surname") String surname,
                            @RequestParam(value = "middle_name") String middle_name,
                            @RequestParam(value = "email") String email,
                            @RequestParam(value = "passport") long passport,
                            @RequestParam(value = "phone") long phone) {

        bookService.createItem(new Passenger(phone, email, surname, name, middle_name, passport), transporting);

        return "Success";
    }

    @PostMapping("/return")
    public String ReturnTicket(@RequestParam(value = "email") String email,
                               @RequestParam(value = "passport") long passport,
                               @RequestParam(value = "id") long id) {

        bookService.deleteItem(email, passport, id);

        return "Success";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public String handleException(Exception ex) {
        return ex.getMessage();
    }
}

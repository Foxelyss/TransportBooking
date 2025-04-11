package com.foxelyss.transportbooking.service;

import com.foxelyss.transportbooking.model.Book;
import com.foxelyss.transportbooking.model.Passenger;
import com.foxelyss.transportbooking.repos.BookRepo;
import com.foxelyss.transportbooking.repos.PassengerRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private PassengerRepo passengerRepo;

    public void createItem(Passenger passenger, int transporting) {
        int rows = 0;

        try {
            rows = bookRepo.allocatePlace(transporting);
        } catch (UncategorizedSQLException e) {
            throw new BookingException(BookingException.ErrorType.SpaceAllocation);
        }

        if (rows == 0) {
            throw new BookingException(BookingException.ErrorType.CantBeBooked);
        }

        Number a;

        a = passengerRepo.save(passenger);

        bookRepo.save((Integer) a, transporting);
    }

    public void deleteItem(String email, long passport, long id) {
        Number a;

        try {
            a = bookRepo.getRecordForBook(email, passport, id);
        } catch (EmptyResultDataAccessException e) {
            throw new BookingException(BookingException.ErrorType.CantBeReturned);
        }

        bookRepo.deleteById(email, passport, id);
        passengerRepo.deleteById((Integer) a);
    }

    public List<BookRepo.Ticket> findAllByDetails(String email, long passport) {
        return bookRepo.findAllByDetails(email, passport);
    }
}

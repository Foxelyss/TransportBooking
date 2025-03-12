package com.foxelyss.transportbooking.service;

import com.foxelyss.transportbooking.model.Book;
import com.foxelyss.transportbooking.model.Passenger;
import com.foxelyss.transportbooking.repos.BookRepo;
import com.foxelyss.transportbooking.repos.PassengerRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private PassengerRepo passengerRepo;


    public List<Book> getAllItems() {
        return bookRepo.findAll();
    }

    public Book getItemById(Long id) {
        return bookRepo.findById(id);
    }

    public Book createItem(Passenger passenger, Book book) {
        bookRepo.allocatePlace(book.transporting());

        Number a = passengerRepo.save(passenger);

        bookRepo.save((Integer) a, book);
        return book;
    }

    public void deleteItem(String email, long passport, long id) {
        Number a = bookRepo.getRecordForBook(email, passport, id);
        bookRepo.deleteById(email, passport, id);
        passengerRepo.deleteById((Integer) a);
    }

    public List<HashMap<String, String>> findAllByDetails(String email, long passport) {
        return bookRepo.findAllByDetails(email, passport);
    }
}

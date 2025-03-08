package com.foxelyss.transportbooking.service;

import com.foxelyss.transportbooking.model.Book;
import com.foxelyss.transportbooking.model.Passenger;
import com.foxelyss.transportbooking.repos.BookRepo;
import com.foxelyss.transportbooking.repos.PointsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepo itemRepository;

    public List<Book> getAllItems() {
        return itemRepository.findAll();
    }

    public Book getItemById(Long id) {
        return itemRepository.findById(id);
    }

    public Book createItem(Passenger passenger, Book item) {
        itemRepository.save(passenger, item);
        return item; // Возвращаем созданный объект
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    public Book findByName(String name) {
        return itemRepository.findByName(name);
    }
}

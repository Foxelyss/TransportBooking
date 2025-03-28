package com.foxelyss.transportbooking.service;

import com.foxelyss.transportbooking.model.Passenger;
import com.foxelyss.transportbooking.repos.BookRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ComponentScan({"com.foxelyss.transportbooking.model"})
@ActiveProfiles("test")
@Sql("/data-booking-test.sql")
class BookServiceTest {
    @Autowired
    BookService bookService;

    @Autowired
    TransportService transportService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    final Passenger testificate = new Passenger(8_900_920_07_23L, "ivanovich@sibmail.ru", "Иванов", "Иван", "Иванович", 123213_1231);

    @Test
    void shouldBook() {
        bookService.createItem(testificate, 1);

        List<BookRepo.Ticket> tickets = bookService.findAllByDetails(testificate.Email(), testificate.passport());

        assertEquals(1, tickets.size());
        assertEquals(1, tickets.getFirst().transporting());
    }

    @Test
    void shouldReturnBook() {
        bookService.createItem(testificate, 1);
        List<BookRepo.Ticket> tickets = bookService.findAllByDetails(testificate.Email(), testificate.passport());

        bookService.deleteItem(testificate.Email(), testificate.passport(), tickets.getFirst().id());
        tickets = bookService.findAllByDetails(testificate.Email(), testificate.passport());

        assertEquals(0, tickets.size());
    }

    @Test
    void shouldNotReturnBook() {
        bookService.createItem(testificate, 2);
        List<BookRepo.Ticket> tickets = bookService.findAllByDetails(testificate.Email(), testificate.passport());

        assertThrows(Exception.class, () -> {
            bookService.deleteItem(testificate.Email(), testificate.passport(), tickets.getFirst().id());
        });
    }

    @AfterEach
    void tearDown() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "Passenger", "book");
    }
}

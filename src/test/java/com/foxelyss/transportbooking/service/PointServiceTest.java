package com.foxelyss.transportbooking.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan({"com.foxelyss.transportbooking.model"})
class PointServiceTest {
    @Autowired
    BookService passengerRepo;

    @Test
    void saveAndRequest() {
//        passengerRepo.save(new Passenger(8_900_920_07_23L, "ivanovich@sibmail.ru", "Иванов", "Иван", "Иванович", 123213_1231));

    }

}

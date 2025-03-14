package com.foxelyss.transportbooking.repos;

import com.foxelyss.transportbooking.model.Passenger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan({"com.foxelyss.transportbooking.model"})
class TransportbookingApplicationTests {
    @Autowired
    PassengerRepo passangerRepo;

    @Test
    void contextLoads() {
        passangerRepo.save(new Passenger(0, "", "asdasd", "123", "sd", 123213));
    }

}

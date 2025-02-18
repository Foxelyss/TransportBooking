package com.foxelyss.transportbooking;

import com.foxelyss.transportbooking.model.Point;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @GetMapping("/register_company")
    public String searchForPoint(@RequestParam(value = "point", defaultValue = "Томск") String point) {


        return "";
    }

    @GetMapping("/register")
    public String p(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/validate_token")
    public String p3(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/login")
    public String p23(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

}
;
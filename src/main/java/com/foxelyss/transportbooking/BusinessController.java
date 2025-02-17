package com.foxelyss.transportbooking;

import com.foxelyss.transportbooking.model.Transporting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/business")
public class BusinessController{
    @GetMapping("/checks")
    public ArrayList<Transporting> searchForTransport(@RequestParam(value = "point_a") int point_a, @RequestParam(value = "point_b") int point_b,
                                                      @RequestParam(value = "quantity", defaultValue = "1") int quantity) {
        return null;
    }



    @GetMapping("/add_transport")
    public String pa(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "";
    }

    @GetMapping("/add_point")
    public String pa2(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "";
    }

    @GetMapping("/buy_check")
    public String pa22(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "";
    }

    @GetMapping("/add_official")
    public String pa12(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "";
    }
}

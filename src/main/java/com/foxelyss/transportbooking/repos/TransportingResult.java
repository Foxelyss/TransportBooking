package com.foxelyss.transportbooking.repos;

import org.springframework.boot.context.properties.bind.DefaultValue;

import java.sql.Timestamp;

public record TransportingResult(int id, String name, Timestamp start, Timestamp end, String start_point,
                                 String end_point,
                                 @DefaultValue("0") int arr, @DefaultValue("0") int dep, double price, String mean,
                                 String company, int places, int free_place_quantity) {
}

;
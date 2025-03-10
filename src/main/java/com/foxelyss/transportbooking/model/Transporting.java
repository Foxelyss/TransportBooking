package com.foxelyss.transportbooking.model;

import java.sql.Timestamp;

public record Transporting(
        int id,
        String name,
        Timestamp departure,
        Timestamp arrival,
        int departure_point,
        int arrival_point,
        int transporting_mean,
        int company,
        float price,
        int place_count,
        int free_place_count) {

}

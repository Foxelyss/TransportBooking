package com.foxelyss.transportbooking.model;

import java.sql.Timestamp;

public record Transporting(
        int id,
        String name,
        Timestamp departure,
        Timestamp arrival,
        int departurePoint,
        int arrivalPoint,
        int transportingMean,
        int company,
        float price,
        int placeCount,
        int freePlaceCount) {

}

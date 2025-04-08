package com.foxelyss.transportbooking.repos;

import java.sql.Timestamp;

public record TransportingResult(int id, String name, Timestamp start, Timestamp end,
                                 String startPoint, String endPoint,
                                 int arr, int dep, float price, String mean,
                                 String company, int places, int freePlaceQuantity) {
}

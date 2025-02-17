package com.foxelyss.transportbooking.model;

import java.sql.Timestamp;

public record Transporting(int id, String name, Timestamp start, Timestamp end, String start_point, String end_point){}

;
package com.foxelyss.transportbooking.model;

// insert into Passenger values (null,123123,"smirnoff@mail.su","Олегов","Виктор","Олегрович",3123123123)
public record Passenger(long phone, String Email, String Surname, String name, String middleName, long passport) {

}

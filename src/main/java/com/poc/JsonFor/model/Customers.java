package com.poc.JsonFor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customers {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String dateOfBirth;
    private String address;
}

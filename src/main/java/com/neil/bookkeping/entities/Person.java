package com.neil.bookkeping.entities;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Person {
    private int id;

    @NotNull(message = "This field must not be empty")
    @Size(min = 2, max = 30, message = "Name should be 2 - 30 symbols")
    private String fullName;

    @NotNull(message = "This field must not be empty")
    @Min(value = 1900, message = "Year of birth is not valid")
    private Integer yearOfBirth;

    private Book book;
}

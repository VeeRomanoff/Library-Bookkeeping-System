package com.neil.bookkeping.entities;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Book {
    private int id;

    @NotNull(message = "This field must not be empty")
    @Size(min = 2, max = 100, message = "Title should be 2 - 30 symbols")
    private String title;

    @NotNull(message = "This field must not be empty")
    @Size(min = 2, max = 255, message = "Author name is not valid")
    private String author;

    @NotNull(message = "This field must not be empty")
    @Min(value = 1900)
    private Integer yearOfPublishing;


    private Person owner;
}

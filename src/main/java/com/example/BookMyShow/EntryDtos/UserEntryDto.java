package com.example.BookMyShow.EntryDtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
@Data

public class UserEntryDto {
    private String name;
    private int age;

    private String email;
    private String mobNo;
    private String address;
}

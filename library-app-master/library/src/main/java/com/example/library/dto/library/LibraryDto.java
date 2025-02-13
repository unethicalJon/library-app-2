package com.example.library.dto.library;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LibraryDto {

    private Long id;

    @NotBlank(message = "name is required!")
    private String name;

    @NotBlank(message = "address is required!")
    private String address;

}

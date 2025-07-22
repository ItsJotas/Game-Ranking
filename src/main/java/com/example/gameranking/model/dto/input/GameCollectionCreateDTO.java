package com.example.gameranking.model.dto.input;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameCollectionCreateDTO {

    @NotNull(message = "The field Name cannot be null.")
    @Size(max = 50, message = "The field Name cannot exceed 50 characters.")
    private String name;

    private List<Long> gameIds;

    @NotNull(message = "The field Color cannot be null.")
    @Size(max = 7, message = "The field Color must be a valid hex color code (e.g., #FFFFFF).")
    private String color;

}
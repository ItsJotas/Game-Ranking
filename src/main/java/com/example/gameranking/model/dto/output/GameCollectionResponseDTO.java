package com.example.gameranking.model.dto.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameCollectionResponseDTO {

    private Long id;
    private String name;
    private Double averageRating;
    private List<GameForCollectionResponseDTO> games;
}
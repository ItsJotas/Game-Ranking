package com.example.gameranking.model.dto.output;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GameToFilterResponseDTO {

    private Long id;
    private String name;

    public GameToFilterResponseDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
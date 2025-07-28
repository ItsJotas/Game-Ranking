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
    private String imageUrl;

    public GameToFilterResponseDTO(Long id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }
}
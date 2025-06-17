package com.example.gameranking.model.dto.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameForCollectionResponseDTO {

    private Long id;
    private String name;
    private String imageUrl;
    private Integer ranking;
    private BigDecimal totalRating;

}
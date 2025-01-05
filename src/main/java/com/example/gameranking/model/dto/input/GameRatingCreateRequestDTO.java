package com.example.gameranking.model.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameRatingCreateRequestDTO {

    @NotNull(message = "The field Gameplay cannot be null.")
    private BigDecimal gameplay;

    @NotNull(message = "The field Graphics cannot be null.")
    private BigDecimal graphics;

    @NotNull(message = "The field Difficulty cannot be null.")
    private BigDecimal difficulty;

    @NotNull(message = "The field Performance cannot be null.")
    private BigDecimal performance;

    @NotNull(message = "The field Sound cannot be null.")
    private BigDecimal sound;

    @NotNull(message = "The field Immersion cannot be null.")
    private BigDecimal immersion;

    private BigDecimal story;
    private BigDecimal matchMaking;
    private BigDecimal competitiveBalance;
    private BigDecimal coop;
}
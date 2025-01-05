package com.example.gameranking.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "game_rating")
public class GameRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "GAMEPLAY", nullable = false, scale = 2, precision = 4)
    private BigDecimal gameplay;

    @Column(name = "GRAPHICS", nullable = false, scale = 2, precision = 4)
    private BigDecimal graphics;

    @Column(name = "DIFFICULTY", nullable = false, scale = 2, precision = 4)
    private BigDecimal difficulty;

    @Column(name = "PERFORMANCE", nullable = false, scale = 2, precision = 4)
    private BigDecimal performance;

    @Column(name = "STORY", scale = 2, precision = 4)
    private BigDecimal story;

    @Column(name = "SOUND", nullable = false, scale = 2, precision = 4)
    private BigDecimal sound;

    @Column(name = "IMMERSION", nullable = false, scale = 2, precision = 4)
    private BigDecimal immersion;

    @Column(name = "MATCH_MAKING", scale = 2, precision = 4)
    private BigDecimal matchMaking;

    @Column(name = "COMPETITIVE_BALANCE", scale = 2, precision = 4)
    private BigDecimal competitiveBalance;

    @Column(name = "COOP", scale = 2, precision = 4)
    private BigDecimal coop;
}
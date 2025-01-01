package com.example.gameranking.model;

import com.example.gameranking.model.enums.AchievementsStatusEnum;
import com.example.gameranking.model.enums.MultiplayerStatusEnum;
import com.example.gameranking.model.enums.StoryModeStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "STORY_MODE_STATUS", nullable = false)
    private StoryModeStatusEnum storyModeStatusEnum;

    @Enumerated(EnumType.STRING)
    @Column(name = "MULTIPLAYER_STATUS", nullable = false)
    private MultiplayerStatusEnum multiplayerStatusEnum;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACHIEVEMENTS_STATUS", nullable = false)
    private AchievementsStatusEnum achievementsStatusEnum;

    @Column(name = "FINISH_DATE")
    private LocalDate finishDate;

    @Column(name = "ONE_HUNDRED_PERCENT_DATE")
    private LocalDate oneHundredPercentDate;

    @Column(name = "ALL_ACHIEVEMENTS_DATE")
    private LocalDate allAchievementsDate;

    @Column(name = "LAUNCHER")
    private String launcher;
}
package com.example.gameranking.model.dto.output;

import com.example.gameranking.model.enums.AchievementsStatusEnum;
import com.example.gameranking.model.enums.MultiplayerStatusEnum;
import com.example.gameranking.model.enums.StoryModeStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GamePagedResponseDTO {

    private Long id;
    private String name;
    private StoryModeStatusEnum storyModeStatusEnum;
    private MultiplayerStatusEnum multiplayerStatusEnum;
    private AchievementsStatusEnum achievementsStatusEnum;
    private LocalDate finishDate;
    private LocalDate oneHundredPercentDate;
    private LocalDate allAchievementsDate;
    private String launcher;
    private BigDecimal totalRating;
}

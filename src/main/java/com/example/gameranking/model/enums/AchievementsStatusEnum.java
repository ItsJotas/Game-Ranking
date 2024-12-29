package com.example.gameranking.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AchievementsStatusEnum {

    NO_ACHIEVEMENTS(0, "The Game doesn't have Achievements."),
    PLAYER_HAS_NO_ACHIEVEMENTS(1, "The Player doesn't have any Achievements."),
    PLAYER_HAS_SOME_ACHIEVEMENTS(2, "The Player has some Achievements."),
    ALL_ACHIEVEMENTS(3, "The Player got all Achievements.");

    private final Integer id;
    private final String description;
}

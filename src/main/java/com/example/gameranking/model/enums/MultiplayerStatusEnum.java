package com.example.gameranking.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MultiplayerStatusEnum {

    NO_MULTIPLAYER(0, "The Game doesn't have Multiplayer."),
    HAS_MULTIPLAYER(1, "The Game has Multiplayer."),
    COMPETITIVE(1, "The Game is Multiplayer Competitive."),
    COOP_STORY_MODE(2, "The Game has COOP Story Mode");

    private final Integer id;
    private final String description;
}
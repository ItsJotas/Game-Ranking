package com.example.gameranking.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MultiplayerStatusEnum {

    NO_MULTIPLAYER(0, "No Multiplayer"),
    HAS_MULTIPLAYER(1, "Has Multiplayer"),
    COMPETITIVE(1, "Competitive"),
    COOP_STORY_MODE(2, "COOP Story Mode");

    private final Integer id;
    private final String description;
}
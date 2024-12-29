package com.example.gameranking.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StoryModeStatusEnum {

    NO_STORY_MODE(0, "The Game doesn't have Story Mode."),
    NOT_FINISHED(1, "The Player has not finished the Story Mode."),
    FINISHED(2, "The Player has finished the Story Mode."),
    ONE_HUNDRED_PERCENT(3, "The Player got 100% in the Story Mode.");

    private final Integer id;
    private final String description;
}
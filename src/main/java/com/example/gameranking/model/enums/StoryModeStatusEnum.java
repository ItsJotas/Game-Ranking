package com.example.gameranking.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StoryModeStatusEnum {

    NO_STORY_MODE(0, "No Story Mode"),
    NOT_FINISHED(1, "Not Finished"),
    FINISHED(2, "Finished"),
    ONE_HUNDRED_PERCENT(3, "One Hundred Percent");

    private final Integer id;
    private final String description;
}
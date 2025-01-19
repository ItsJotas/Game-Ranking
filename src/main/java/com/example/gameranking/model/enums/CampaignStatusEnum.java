package com.example.gameranking.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CampaignStatusEnum {

    NO_CAMPAIGN(0, "The Game doesn't have Campaign."),
    NOT_FINISHED(1, "The Player has not finished the Campaign."),
    FINISHED(2, "The Player has finished the Campaign."),
    ONE_HUNDRED_PERCENT(3, "The Player got 100% in the Campaign."),
    NOT_PLAYED(4, "The Game has a Campaign but the Player hasn't played it."),
    JUST_LORE(5, "The Game doesn't have a Story Mode, but has Lore.");

    private final Integer id;
    private final String description;
}
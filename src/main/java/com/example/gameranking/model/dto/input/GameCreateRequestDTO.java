package com.example.gameranking.model.dto.input;

import com.example.gameranking.model.enums.AchievementsStatusEnum;
import com.example.gameranking.model.enums.CampaignStatusEnum;
import com.example.gameranking.model.enums.MultiplayerStatusEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameCreateRequestDTO {

    @NotNull(message = "The field Name cannot be null.")
    @Size(max = 50, message = "The field Name cannot exceed 50 characters.")
    private String name;

    @NotNull(message = "The field Campaign Status cannot be null.")
    private CampaignStatusEnum campaignStatusEnum;

    @NotNull(message = "The field Multiplayer cannot be null.")
    private MultiplayerStatusEnum multiplayerStatusEnum;

    @NotNull(message = "The field Achievements cannot be null.")
    private AchievementsStatusEnum achievementsStatusEnum;

    private LocalDate finishDate;

    private LocalDate oneHundredPercentDate;

    private LocalDate allAchievementsDate;

    @NotNull(message = "The field Launcher cannot be null.")
    private String launcher;

    private MultipartFile image;
}
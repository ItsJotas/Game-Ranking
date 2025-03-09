package com.example.gameranking.model.dto.input;

import com.example.gameranking.model.enums.AchievementsStatusEnum;
import com.example.gameranking.model.enums.CampaignStatusEnum;
import com.example.gameranking.model.enums.MultiplayerStatusEnum;
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
public class GameUpdateRequestDTO {

    @Size(max = 50, message = "The field Name cannot exceed 50 characters.")
    private String name;

    private CampaignStatusEnum campaignStatusEnum;
    private MultiplayerStatusEnum multiplayerStatusEnum;
    private AchievementsStatusEnum achievementsStatusEnum;
    private LocalDate finishDate;
    private LocalDate oneHundredPercentDate;
    private LocalDate allAchievementsDate;
    private String launcher;
    private MultipartFile image;
}

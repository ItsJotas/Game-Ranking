package com.example.gameranking.service;

import com.example.gameranking.exception.customized.BadRequestException;
import com.example.gameranking.model.GameCollection;
import com.example.gameranking.model.dto.input.GameCollectionCreateDTO;
import com.example.gameranking.repository.GameCollectionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GameCollectionService {

    private final GameCollectionRepository repository;
    private final GameService gameService;

    @Transactional
    public void createGameCollection(GameCollectionCreateDTO gameCollectionCreateDTO) {
        GameCollection gameCollection = new GameCollection();

        verifyIfNameExists(gameCollectionCreateDTO.getName());
        BigDecimal averageRating = gameService.calculateAverageRating(gameCollectionCreateDTO.getGameIds());

        gameCollection.setName(gameCollectionCreateDTO.getName());
        gameCollection.setAverageRating(averageRating);

        save(gameCollection);
        gameService.associateGames(gameCollectionCreateDTO.getGameIds(), gameCollection);
    }

    public void save(GameCollection gameCollection) {
        repository.save(gameCollection);
    }

    private void verifyIfNameExists(String collectionName) {
        GameCollection gameCollection = repository.findByName(collectionName);
        if(Objects.nonNull(gameCollection)){
            throw new BadRequestException("There is already a Game Collection with this name: " + collectionName);
        }
    }
}
package com.example.gameranking.service;

import com.example.gameranking.model.Game;
import com.example.gameranking.model.GameCollection;
import com.example.gameranking.model.GameCollectionRelationship;
import com.example.gameranking.repository.GameCollectionRelationshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameCollectionRelationshipService {

    private final GameCollectionRelationshipRepository repository;
    private final GameService gameService;

    public void associateGames(List<Long> gameIds, GameCollection gameCollection) {
        List<Game> games = gameService.findAllGamesById(gameIds);

        List<GameCollectionRelationship> gameCollections = games.stream()
                .map(game -> {
                    GameCollectionRelationship relationship = new GameCollectionRelationship();
                    relationship.setGame(game);
                    relationship.setGameCollection(gameCollection);
                    return relationship;
                })
                .toList();

        repository.saveAll(gameCollections);
    }
}
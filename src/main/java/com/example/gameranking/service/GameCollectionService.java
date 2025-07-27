package com.example.gameranking.service;

import com.example.gameranking.exception.customized.BadRequestException;
import com.example.gameranking.model.Game;
import com.example.gameranking.model.GameCollection;
import com.example.gameranking.model.dto.input.GameCollectionCreateDTO;
import com.example.gameranking.model.dto.output.GameCollectionResponseDTO;
import com.example.gameranking.model.dto.output.GameForCollectionResponseDTO;
import com.example.gameranking.repository.GameCollectionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GameCollectionService {

    private final GameCollectionRepository repository;
    private final GameService gameService;
    private final GameCollectionRelationshipService gameCollectionRelationshipService;
    private final ModelMapper mapper;

    @Transactional
    public void createGameCollection(GameCollectionCreateDTO gameCollectionCreateDTO) {
        GameCollection gameCollection = new GameCollection();

        verifyIfNameExists(gameCollectionCreateDTO.getName());

        gameCollection.setName(gameCollectionCreateDTO.getName());
        gameCollection.setColor(gameCollectionCreateDTO.getColor());

        save(gameCollection);

        if(Objects.nonNull(gameCollectionCreateDTO.getGameIds()) && !gameCollectionCreateDTO.getGameIds().isEmpty()) {
            BigDecimal averageRating = gameService.calculateAverageRating(gameCollectionCreateDTO.getGameIds());
            gameCollection.setAverageRating(averageRating);
            gameCollectionRelationshipService.associateGames(gameCollectionCreateDTO.getGameIds(), gameCollection);
        }
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

    public Page<GameCollectionResponseDTO> getAllGameCollections(String name, Integer pageNumber, Integer pageSize,
                                                                 String orderBy) {

        String direction = orderBy.equalsIgnoreCase("desc") ? "desc" : "asc";
        Pageable paging = PageRequest.of(pageNumber, pageSize);

        Page<GameCollection> collections = repository.findAllPaged(paging, name, direction);

        return getGameCollectionResponseDTOS(collections);
    }

    private Page<GameCollectionResponseDTO> getGameCollectionResponseDTOS(Page<GameCollection> collections) {
        return collections.map(
                c -> {
                    GameCollectionResponseDTO collectionDTO = new GameCollectionResponseDTO();
                    mapper.map(c, collectionDTO);

                    List<Game> games = gameService.findAllGamesByCollectionId(c.getId());
                    List<GameForCollectionResponseDTO> gamesDTO = games.stream()
                            .map(g -> mapper.map(g, GameForCollectionResponseDTO.class)).toList();

                    collectionDTO.setGames(gamesDTO);

                    return collectionDTO;
                });
    }
}
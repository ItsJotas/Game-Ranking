package com.example.gameranking.service;

import com.example.gameranking.exception.customized.BadRequestException;
import com.example.gameranking.model.Game;
import com.example.gameranking.model.dto.input.GameCreateRequestDTO;
import com.example.gameranking.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository repository;
    private final ModelMapper mapper;

    public void create(GameCreateRequestDTO gameCreateRequestDTO) {
        verifyIfNameExists(gameCreateRequestDTO.getName());
        Game game = mapper.map(gameCreateRequestDTO, Game.class);
        repository.save(game);
    }

    private void verifyIfNameExists(String gameName) {
        Game game = repository.findByName(gameName);
        if(Objects.nonNull(game)){
            throw new BadRequestException("There is already a Game with this name: " + gameName);
        }
    }
}

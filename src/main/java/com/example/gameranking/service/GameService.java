package com.example.gameranking.service;

import com.example.gameranking.model.Game;
import com.example.gameranking.model.dto.input.GameCreateRequestDTO;
import com.example.gameranking.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository repository;
    private final ModelMapper mapper;

    public void create(GameCreateRequestDTO gameCreateRequestDTO) {
        Game game = mapper.map(gameCreateRequestDTO, Game.class);
        repository.save(game);
    }
}

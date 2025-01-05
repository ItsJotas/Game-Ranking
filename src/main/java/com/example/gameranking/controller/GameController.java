package com.example.gameranking.controller;

import com.example.gameranking.model.dto.input.GameCreateRequestDTO;
import com.example.gameranking.model.dto.input.GameRatingCreateRequestDTO;
import com.example.gameranking.service.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService service;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid GameCreateRequestDTO gameCreateRequestDTO){
        service.create(gameCreateRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{gameId}/rating")
    public ResponseEntity<Void> rateGame(@RequestBody @Valid GameRatingCreateRequestDTO gameRatingCreateRequestDTO,
                                         @PathVariable("gameId") Long gameId){
        service.rateGame(gameRatingCreateRequestDTO, gameId);
        return ResponseEntity.ok().build();
    }
}

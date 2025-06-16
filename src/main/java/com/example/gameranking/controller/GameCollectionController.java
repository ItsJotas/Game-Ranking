package com.example.gameranking.controller;

import com.example.gameranking.model.dto.input.GameCollectionCreateDTO;
import com.example.gameranking.service.GameCollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game-collection")
@RequiredArgsConstructor
public class GameCollectionController {

    private final GameCollectionService service;

    @PostMapping
    public ResponseEntity<Void> createGameCollection(@RequestBody GameCollectionCreateDTO gameCollectionCreateDTO) {
        service.createGameCollection(gameCollectionCreateDTO);
        return ResponseEntity.ok().build();
    }
}
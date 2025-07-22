package com.example.gameranking.controller;

import com.example.gameranking.model.dto.input.GameCollectionCreateDTO;
import com.example.gameranking.model.dto.input.GameCollectionFilterDTO;
import com.example.gameranking.model.dto.output.GameCollectionResponseDTO;
import com.example.gameranking.service.GameCollectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game-collection")
@RequiredArgsConstructor
public class GameCollectionController {

    private final GameCollectionService service;

    @PostMapping
    public ResponseEntity<Void> createGameCollection(@RequestBody @Valid GameCollectionCreateDTO gameCollectionCreateDTO) {
        service.createGameCollection(gameCollectionCreateDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/all")
    public ResponseEntity<Page<GameCollectionResponseDTO>> getAllGameCollections(
            @RequestBody(required = false) GameCollectionFilterDTO gameCollectionFilterDTO,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "asc") String orderBy) {

        Page<GameCollectionResponseDTO> gameCollections = service.getAllGameCollections(gameCollectionFilterDTO.getName(),
                pageNumber, pageSize, orderBy);
        return ResponseEntity.ok(gameCollections);
    }
}
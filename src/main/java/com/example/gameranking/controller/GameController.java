package com.example.gameranking.controller;

import com.example.gameranking.model.dto.input.GameCreateRequestDTO;
import com.example.gameranking.model.dto.input.GameRatingCreateRequestDTO;
import com.example.gameranking.model.dto.output.GamePagedResponseDTO;
import com.example.gameranking.service.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService service;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Void> create(@ModelAttribute @Valid GameCreateRequestDTO gameCreateRequestDTO) throws IOException {
        service.create(gameCreateRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{gameId}/rating")
    public ResponseEntity<Void> rateGame(@RequestBody @Valid GameRatingCreateRequestDTO gameRatingCreateRequestDTO,
                                         @PathVariable("gameId") Long gameId){
        service.rateGame(gameRatingCreateRequestDTO, gameId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<GamePagedResponseDTO>> getAllPaged(@RequestParam(required = false) String gameName,
                                                                  @RequestParam(defaultValue = "0") Integer pageNumber,
                                                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                                                  @RequestParam(defaultValue = "asc") String orderBy){

        Page<GamePagedResponseDTO> gamePagedResponseDTOPage = service.getAllPaged(gameName, pageNumber, pageSize, orderBy);
        return ResponseEntity.ok().body(gamePagedResponseDTOPage);
    }
}

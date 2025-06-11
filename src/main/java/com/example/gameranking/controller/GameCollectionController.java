package com.example.gameranking.controller;

import com.example.gameranking.service.GameCollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game-collection")
@RequiredArgsConstructor
public class GameCollectionController {

    private final GameCollectionService gameCollectionService;
}
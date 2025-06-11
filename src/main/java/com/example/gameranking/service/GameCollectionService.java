package com.example.gameranking.service;

import com.example.gameranking.repository.GameCollectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameCollectionService {
    private final GameCollectionRepository gameCollectionRepository;
}
package com.example.gameranking.repository;

import com.example.gameranking.model.GameCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameCollectionRepository extends JpaRepository<GameCollection, Long> {
    GameCollection findByName(String name);
}
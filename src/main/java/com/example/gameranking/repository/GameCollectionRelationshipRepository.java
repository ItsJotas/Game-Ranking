package com.example.gameranking.repository;

import com.example.gameranking.model.GameCollectionRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameCollectionRelationshipRepository extends JpaRepository<GameCollectionRelationship, Long> {
}
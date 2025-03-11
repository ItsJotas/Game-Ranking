package com.example.gameranking.repository;

import com.example.gameranking.model.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    Game findByName(String name);

    @Query(value = "SELECT * FROM game WHERE (:gameName IS NULL OR LOWER(NAME) LIKE LOWER(CONCAT('%', :gameName, '%'))) " +
            "AND TOTAL_RATING IS NOT NULL", nativeQuery = true)
    Page<Game> findAllPaged(Pageable paging, @Param("gameName") String gameName);

    @Query(value = "SELECT * FROM game WHERE TOTAL_RATING IS NOT NULL", nativeQuery = true)
    List<Game> findAllGamesTotalRating();

    @Query(value = "SELECT * FROM game WHERE (:gameName IS NULL OR LOWER(NAME) " +
            "LIKE LOWER(CONCAT('%', :gameName, '%'))) " +
            "AND TOTAL_RATING IS NULL", nativeQuery = true)
    Page<Game> getUnratedGames(Pageable paging, @Param("gameName") String gameName);

    @Query(value = "SELECT * FROM game WHERE (:gameName IS NULL OR LOWER(NAME) " +
            "LIKE LOWER(CONCAT('%', :gameName, '%')))", nativeQuery = true)
    Page<Game> getAllGames(Pageable paging, @Param("gameName") String gameName);
}
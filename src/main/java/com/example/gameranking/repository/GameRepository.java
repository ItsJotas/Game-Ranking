package com.example.gameranking.repository;

import com.example.gameranking.model.Game;
import com.example.gameranking.model.dto.output.GameToFilterResponseDTO;
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

    @Query(value = "SELECT * FROM game " +
            "WHERE (:gameName IS NULL OR LOWER(NAME) LIKE LOWER(CONCAT('%', :gameName, '%'))) " +
            "ORDER BY CASE WHEN RANKING IS NULL THEN 1 ELSE 0 END ASC, " +
            "         CASE WHEN :orderBy = 'desc' THEN RANKING END DESC, " +
            "         CASE WHEN :orderBy = 'asc' THEN RANKING END ASC ", nativeQuery = true)
    Page<Game> findAllPaged(Pageable paging, @Param("gameName") String gameName, @Param("orderBy") String orderBy);

    @Query(value = "SELECT * FROM game WHERE TOTAL_RATING IS NOT NULL", nativeQuery = true)
    List<Game> findAllGamesTotalRating();

    @Query(value = "SELECT * FROM game WHERE (:gameName IS NULL OR LOWER(NAME) " +
            "LIKE LOWER(CONCAT('%', :gameName, '%'))) " +
            "AND TOTAL_RATING IS NULL", nativeQuery = true)
    Page<Game> getUnratedGames(Pageable paging, @Param("gameName") String gameName);

    @Query(value = "SELECT * FROM game WHERE (:gameName IS NULL OR LOWER(NAME) " +
            "LIKE LOWER(CONCAT('%', :gameName, '%')))", nativeQuery = true)
    Page<Game> getAllGames(Pageable paging, @Param("gameName") String gameName);

    @Query(value = """
            SELECT g FROM Game g
            JOIN GameCollectionRelationship gcr ON gcr.game.id = g.id
            WHERE gcr.gameCollection.id = :collectionId
            """)
    List<Game> findAllGamesByCollectionId(@Param("collectionId") Long collectionId);

    @Query(value = """
            SELECT new com.example.gameranking.model.dto.output.GameToFilterResponseDTO(g.id, g.name, g.imageUrl)
            FROM Game g
            WHERE (:name IS NULL OR LOWER(g.name) LIKE LOWER(CONCAT('%', :name, '%')))
            ORDER BY g.name
            """)
    List<GameToFilterResponseDTO> getGamesToFilter(@Param("name") String name);
}
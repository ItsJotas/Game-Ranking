package com.example.gameranking.repository;

import com.example.gameranking.model.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    Game findByName(String name);

    @Query(value = "SELECT * FROM game WHERE (:gameName IS NULL OR LOWER(NAME) LIKE LOWER(CONCAT('%', :gameName, '%')))",
            nativeQuery = true)
    Page<Game> findAllPaged(Pageable paging, @Param("gameName") String gameName);
}

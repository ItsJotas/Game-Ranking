package com.example.gameranking.repository;

import com.example.gameranking.model.GameCollection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GameCollectionRepository extends JpaRepository<GameCollection, Long> {
    GameCollection findByName(String name);

    @Query(value = """
            SELECT gc FROM GameCollection gc
            WHERE (:name IS NULL OR LOWER(gc.name) LIKE CONCAT('%', LOWER(:name), '%'))
            ORDER BY
                CASE WHEN name IS NULL THEN 1 ELSE 0 END ASC,
                CASE WHEN :orderBy = 'desc' THEN name END DESC,
                CASE WHEN :orderBy = 'asc' THEN name END ASC
            """)
    Page<GameCollection> findAllPaged(Pageable paging, @Param("name") String name, String orderBy);
}
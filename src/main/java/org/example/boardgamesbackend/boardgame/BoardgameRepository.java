package org.example.boardgamesbackend.boardgame;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardgameRepository extends JpaRepository<Boardgame, Long> {
    Optional<Object> findByName(String name);
}

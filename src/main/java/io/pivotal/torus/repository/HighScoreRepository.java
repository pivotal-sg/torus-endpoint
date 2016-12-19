package io.pivotal.torus.repository;

import io.pivotal.torus.data.HighScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HighScoreRepository extends JpaRepository<HighScore, Long> {
    List<HighScore> findAll();
}

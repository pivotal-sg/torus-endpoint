package io.pivotal.torus.repository;

import io.pivotal.torus.data.HighScore;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HighScoreRepository extends CrudRepository<HighScore, Long> {
    List<HighScore> findAll();
}

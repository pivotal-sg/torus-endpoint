package io.pivotal.torus.service;

import io.pivotal.torus.data.HighScore;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HighScoreService {

    public List<HighScore> sortScores(List<HighScore> highScores) {
        Collections.sort(highScores);
        return highScores.stream().limit(10).collect(Collectors.toList());
    }
}

package io.pivotal.torus;

import io.pivotal.torus.data.HighScore;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestHelper {

    public static List<HighScore> createHighScores(Integer... scoreValues) {
        return Arrays.stream(scoreValues).
                map(value -> new HighScore("default", value)).
                collect(Collectors.toList());
    }

}

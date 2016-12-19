package io.pivotal.torus.service;

import io.pivotal.torus.data.HighScore;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Collections.singletonList;

public class HighScoreService {

    public List<HighScore> sortScores(List<HighScore> highScores) {
        Collections.sort(highScores);
        return highScores.stream().limit(10).collect(Collectors.toList());
    }

    public List<HighScore> seedData() {
        String[] names = {"Buzz", "John", "Yuri", "Neil", "Alan", "Eugene", "Kalpana", "Haipeng", "Claude", "Sally"};

        return IntStream.range(0, 10).mapToObj(value -> {
            String name = names[value];
            int score = (value + 1) * 10;
            return new HighScore(name, score);
        }).collect(Collectors.toList());
    }

    public List<HighScore> mergeScores(List<HighScore> scores) {
        return Stream.concat(scores.stream(), seedData().stream())
                .limit(10)
                .sorted()
                .collect(Collectors.toList());
    }
}

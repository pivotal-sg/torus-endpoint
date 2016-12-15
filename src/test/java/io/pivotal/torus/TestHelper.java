package io.pivotal.torus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.pivotal.torus.data.HighScore;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestHelper {

    public static List<HighScore> createHighScores(Integer... scoreValues) {
        return Arrays.stream(scoreValues).
                map(value -> new HighScore("default", value)).
                collect(Collectors.toList());
    }

    public static String json(Object o) throws IOException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(o);
    }
}

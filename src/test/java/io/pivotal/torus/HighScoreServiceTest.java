package io.pivotal.torus;


import io.pivotal.torus.service.HighScoreService;
import org.junit.Test;

import java.util.Arrays;

import static io.pivotal.torus.TestHelper.createHighScores;
import static org.junit.Assert.assertEquals;

public class HighScoreServiceTest {

    private HighScoreService service = new HighScoreService();

    @Test
    public void testHighScoresAreSorted() {
        assertEquals(createHighScores(2, 1), service.sortScores(createHighScores(1, 2)));
    }

    @Test
    public void testHighScoresAreLimitedTo10() {
        Integer[] scores = {5, 6, 3, 1, 3, 5, 6, 10, 19, 1, 20, 9};
        Integer[] expectedScores = scores.clone();
        Arrays.sort(expectedScores, (i, j) -> j - i);
        expectedScores = Arrays.copyOf(expectedScores, 10);

        assertEquals(createHighScores(expectedScores), service.sortScores(createHighScores(scores)));
    }

}

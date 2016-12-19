package io.pivotal.torus;


import io.pivotal.torus.data.HighScore;
import io.pivotal.torus.service.HighScoreService;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @Test
    public void testSeedDataHasTenEntries() {
        List<HighScore> seedData = service.seedData();

        assertEquals(seedData.size(), 10);
    }

    @Test
    public void testSeedDataHasTenDistinctNames() {
        List<HighScore> seedData = service.seedData();
        int numDistinctNames = seedData.stream()
                .map(HighScore::getName)
                .distinct()
                .collect(Collectors.toList())
                .size();

        assertEquals(numDistinctNames, 10);
    }

    @Test
    public void testSeedDataHasTenDistinctScores() {
        List<HighScore> seedData = service.seedData();
        int numDistinctScores = seedData.stream()
                .map(HighScore::getScore)
                .distinct()
                .collect(Collectors.toList())
                .size();

        assertEquals(numDistinctScores, 10);
    }

    @Test
    public void testHighScoresContainSeedDataWhenThereAreNoPlayers() {
        List<HighScore> expectedScores = service.seedData();
        Collections.sort(expectedScores);

        assertEquals(expectedScores, service.mergeScores(Collections.emptyList()));
    }

    @Test
    public void testHighScoresContainSeedDataWhenThereAreLessThan10Players() {
        List<HighScore> scores = Collections.singletonList(new HighScore("Gam", Integer.MAX_VALUE));

        assertEquals(service.mergeScores(scores).size(), 10);
    }

    @Test
    public void testHighScoresDoesntContainSeedDataWhenThereAre10Players() {
        List<HighScore> scores = createHighScores(1, 2, 3, 1, 2, 3, 1, 2, 3, 1);
        Collections.sort(scores);
        List<HighScore> mergedScores = service.mergeScores(scores);

        assertEquals(mergedScores, scores);
    }
}

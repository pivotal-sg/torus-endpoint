package io.pivotal.torus;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.pivotal.torus.data.HighScore;
import io.pivotal.torus.repository.HighScoreRepository;
import io.pivotal.torus.web.HighScoreController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static io.pivotal.torus.TestHelper.json;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class HighScoreControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        HighScoreRepository highScoreRepository = mock(HighScoreRepository.class);
        when(highScoreRepository.findAll()).thenReturn(
                TestHelper.createHighScores(10, 1, 20));

        HighScoreController highScoreController = new HighScoreController(highScoreRepository);
        this.mockMvc = MockMvcBuilders.standaloneSetup(highScoreController).build();
    }

    @Test
    public void testGetHighscores() throws Exception {
        ObjectMapper jsonOM = new ObjectMapper();

        MvcResult mvcResult = mockMvc.perform(get("/torus/highscores")).
                andExpect(status().isOk()).andReturn();

        assertEquals(jsonOM.writeValueAsString(TestHelper.createHighScores(20, 10, 1)),
                mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testSubmitScores() throws Exception {
        mockMvc.perform(post("/torus/highscores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(new HighScore("Grace", 30))))
                .andExpect(status().isCreated());
    }
}

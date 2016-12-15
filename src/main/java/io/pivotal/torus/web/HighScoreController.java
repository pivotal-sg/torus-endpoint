package io.pivotal.torus.web;

import io.pivotal.torus.data.HighScore;
import io.pivotal.torus.repository.HighScoreRepository;
import io.pivotal.torus.service.HighScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/torus")
public class HighScoreController {

    HighScoreRepository highScoreRepository;

    HighScoreService highScoreService;

    @Autowired
    public HighScoreController(HighScoreRepository highScoreRepository) {
        this.highScoreRepository = highScoreRepository;
        this.highScoreService = new HighScoreService();
    }


    @GetMapping("/highscores")
    public List<HighScore> highScores() {
        return highScoreService.sortScores(highScoreRepository.findAll());
    }
}

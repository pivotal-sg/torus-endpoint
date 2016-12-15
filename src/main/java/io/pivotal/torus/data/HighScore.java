package io.pivotal.torus.data;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class HighScore implements Comparable<HighScore> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    int id;
    String name;
    int score;

    public HighScore() {
    }

    public HighScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(HighScore o) {
        return o.score - this.score;
    }
}

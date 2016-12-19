package io.pivotal.torus;

import io.pivotal.torus.repository.HighScoreRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpaHighScoreRepositoryTest {

    @Autowired
    HighScoreRepository hr;

    @Test
    public void testFindAll() {
        Assert.assertEquals(Collections.emptyList(), hr.findAll());
    }

}

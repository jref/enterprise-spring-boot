package com.ua.codespace;

import com.ua.codespace.model.User;
import com.ua.codespace.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BambooSpringBootApplication.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class BambooApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Test
    public void textStartingApp() {

    }

    @Test
    public void testUserSave() {
        User user = userRepository.save(new User("test-user", "test-user@gmail.com", "test-pass"));
        assertTrue(userRepository.findByEmailEndsWithOrderByUsername("gmail.com", new PageRequest(0, 20)).getContent().contains(user));
    }
}

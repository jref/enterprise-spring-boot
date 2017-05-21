package com.ua.codespace;

import com.ua.codespace.exception.UserNotFoundException;
import com.ua.codespace.model.User;
import com.ua.codespace.repository.UserRepository;
import com.ua.codespace.service.UserService;
import com.ua.codespace.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService = new UserServiceImpl();

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUserFriendsPositive() {
        long bobId = 222L;

        User bob = new User("Bob", "bob@gmail.com", "bobbob");
        User sarah = new User("Sarah", "sarah@gmail.com", "sarahsarah");
        User john = new User("John", "john@gmail.com", "johnjohn");
        bob.addToFriends(sarah);
        bob.addToFriends(john);

        when(userRepository.findOne(bobId)).thenReturn(bob);

        List<User> userFriends = userService.getUserFriends(bobId);

        assertEquals(userFriends.size(), 2);
        assertTrue(userFriends.contains(sarah));
        assertTrue(userFriends.contains(john));
    }

    @Test(expected = UserNotFoundException.class)
    public void getUserFriendsNegative() {
        long noneId = 666L;

        when(userRepository.findOne(noneId)).thenReturn(null);

        List<User> userFriends = userService.getUserFriends(noneId);
    }


}

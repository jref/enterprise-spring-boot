package com.ua.codespace.service.impl;


import com.ua.codespace.model.User;
import com.ua.codespace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Profile({"stage", "prod"})
@Transactional
public class RemoteUserServiceImpl implements UserService {
    @Autowired
    RestTemplate restTemplate;

    @Value("${bamboo.service.url}")
    private String bambooServiceUrl;


    @Override
    public List<User> getUserFriends(Long id) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public User save(User user, MultipartFile image) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public User get(Long id) {
        return restTemplate.getForObject(bambooServiceUrl + "/users/" + id, User.class);
    }
}

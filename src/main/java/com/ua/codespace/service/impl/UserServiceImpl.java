package com.ua.codespace.service.impl;


import com.ua.codespace.exception.UserNotFoundException;
import com.ua.codespace.model.User;
import com.ua.codespace.repository.UserRepository;
import com.ua.codespace.service.PhotoService;
import com.ua.codespace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Service
@Profile({"dev", "test"})
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PhotoService photoService;

    @Override
    @Transactional(readOnly = true)
//    @PreAuthorize("hasRole('ROLE_USER')")
    public List<User> getUserFriends(Long id) {
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new UserNotFoundException(id);
        } else {
            return user.getFriends().stream()
                    .sorted(comparing(User::getUsername))
                    .collect(toList());
        }
    }

    @Override
    public User save(User user, MultipartFile image) {
        String photoUrl = photoService.saveUserPhoto(user.getUsername(), image);
        user.setPhotoUrl(photoUrl);
        return userRepository.save(user);
    }

    @Override
    @Transactional
//    @PreAuthorize("hasRole('ROLE_USER')")
    public User get(Long id) {
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new UserNotFoundException(id);
        }
        return user;
    }
}

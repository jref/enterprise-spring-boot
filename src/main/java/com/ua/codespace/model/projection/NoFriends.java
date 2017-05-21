package com.ua.codespace.model.projection;


import com.ua.codespace.model.User;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "noFriends", types = User.class)
public interface NoFriends {
    String getUsername();

    String getEmail();

    String getPhotoUrl();

}

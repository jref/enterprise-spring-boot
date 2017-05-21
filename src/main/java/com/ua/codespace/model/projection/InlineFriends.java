package com.ua.codespace.model.projection;

import com.ua.codespace.model.User;
import org.springframework.data.rest.core.config.Projection;

import java.util.Set;

@Projection(name = "inlineFriends", types = User.class)
public interface InlineFriends extends NoFriends {

    Set<NoFriends> getFriends();

}

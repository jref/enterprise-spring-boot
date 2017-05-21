package com.ua.codespace.repository;

import com.ua.codespace.model.User;
import com.ua.codespace.model.projection.NoFriends;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(excerptProjection = NoFriends.class)
public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {

    @RestResource(path = "/autoEmailProvider", rel = "emailProvider")
    Page<User> findByEmailEndsWithOrderByUsername(@Param("emailProvider") String emailProvider, Pageable pageable);

}

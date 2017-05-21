package com.ua.codespace.repository.impl;


import com.ua.codespace.model.User;
import com.ua.codespace.repository.CustomUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
public class UserRepositoryImpl implements CustomUserRepository {
    @PersistenceContext
    EntityManager em;

    private static String countQueryPrefix = "select count(*) ";
    private static String usersByEmailProviderQuery = "from User u where u.email like concat('%',:emailProvider)";


    @Override
    public Page<User> findUsersByEmailProvider(String emailProvider, Pageable pageable) {
        List<User> userList = em.createQuery(usersByEmailProviderQuery, User.class)
                .setParameter("emailProvider", emailProvider)
                .setFirstResult(pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        long totalElements = performCountQuery(usersByEmailProviderQuery, emailProvider);

        return new PageImpl<>(userList, pageable, totalElements);
    }

    private long performCountQuery(String query, String emailProvider) {
        return (long) em.createQuery(countQueryPrefix + query)
                .setParameter("emailProvider",emailProvider)
                .getSingleResult();
    }

}

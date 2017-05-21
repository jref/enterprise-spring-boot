package com.ua.codespace.rest.controller;

import com.ua.codespace.model.User;
import com.ua.codespace.model.projection.NoFriends;
import com.ua.codespace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RepositoryRestController
@RequestMapping("/api/users")
public class UserRestController {
    @Autowired
    ProjectionFactory projectionFactory;

    @Autowired
    RepositoryEntityLinks repositoryEntityLinks;

    @Autowired
    PagedResourcesAssembler<NoFriends> pagedResourcesAssembler;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/search/customEmailProvider", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getUsersByEmailProvider(@RequestParam String emailProvider, Pageable pageable) {
        Page<User> userPage = userRepository.findUsersByEmailProvider(emailProvider, pageable);



        Page<NoFriends> noFriendsPage = projectPage(userPage, pageable);

        PagedResources<Resource<NoFriends>> pagedResources = pagedResourcesAssembler.toResource(noFriendsPage);
        pagedResources.add(repositoryEntityLinks.linkToCollectionResource(UserRepository.class));

        return ResponseEntity.ok(pagedResources);
    }

    private Page<NoFriends> projectPage(Page<User> userPage, Pageable pageable) {
        List<NoFriends> noFriendsList = userPage.getContent()
                .stream()
                .map(u -> projectionFactory.createProjection(NoFriends.class, u))
                .collect(toList());

        return new PageImpl<>(noFriendsList, pageable, userPage.getTotalElements());
    }

}

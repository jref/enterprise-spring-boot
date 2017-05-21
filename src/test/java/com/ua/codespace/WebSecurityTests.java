package com.ua.codespace;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
// if you want to test running app, you can use webEnvironment property
@SpringBootTest(classes = BambooSpringBootApplication.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class WebSecurityTests {
    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }


    @Test
    public void testAddUserPage() throws Exception {
        mockMvc.perform(get("/users/add"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "http://localhost/login"));
    }

    @Test
    public void testAddUserPageWithBasicAuthorization() throws Exception {
        mockMvc.perform(get("/users/add")
                .header("Authorization", "Basic dGVzdC11c2VyOnRlc3QtcGFzc3dvcmQ="))
                .andExpect(status().isOk())
                .andExpect(view().name("userForm"));
    }

    @Test
    @WithMockUser(username = "john", password = "johnjohn", roles = "USER")
    public void testArticleAddPage() throws Exception {
        mockMvc.perform(get("/articles/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("articleForm"));
    }
}

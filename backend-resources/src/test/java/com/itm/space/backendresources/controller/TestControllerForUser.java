package com.itm.space.backendresources.controller;

import com.itm.space.backendresources.BaseIntegrationTest;
import com.itm.space.backendresources.api.request.UserRequest;
import com.itm.space.backendresources.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TestControllerForUser extends BaseIntegrationTest {
    @MockBean
    private UserService userService;

    @Test
    @WithMockUser(roles = "MODERATOR")
    public void testCreateUser() throws Exception {
        UserRequest userRequest = new UserRequest("Andr", "Andrei@bk.ru", "1234",
                "Andrei", "Prishchepa");
        mvc.perform(requestWithContent(post("/api/users"), userRequest)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "MODERATOR")
    public void testUsernameNotBeBlank() throws Exception {
        UserRequest userRequest = new UserRequest("", "Andrei@bk.ru", "1234",
                "Andrei", "Prishchepa");
        mvc.perform(requestWithContent(post("/api/users"), userRequest)).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "MODERATOR")
    public void testUsernameForSize() throws Exception {
        UserRequest userRequestSize = new UserRequest("A", "Andrei@bk.ru", "1234",
                "Andrei", "Prishchepa");
        mvc.perform(requestWithContent(post("/api/users"), userRequestSize)).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "MODERATOR")
    public void testForEmailNotBlank() throws Exception {
        UserRequest userRequest = new UserRequest("Andr", "", "1234",
                "Andrei", "Prishchepa");
        mvc.perform(requestWithContent(post("/api/users"), userRequest)).andDo(print())
                .andExpect(status().isBadRequest());
    }


    @Test
    @WithMockUser(roles = "MODERATOR")
    public void testEmailNotBeValid() throws Exception {
        UserRequest userRequest = new UserRequest("Andr", "Not_Be_Valid",
                "1234", "Andrei", "Prishchepa");
        mvc.perform(requestWithContent(post("/api/users"), userRequest)).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "MODERATOR")
    public void testPasswordNotBeBlank() throws Exception {
        UserRequest userRequest = new UserRequest("Andr", "Andrei@bk.ru",
                "", "Andrei", "Prishchepa");
        mvc.perform(requestWithContent(post("/api/users"), userRequest)).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "MODERATOR")
    public void testPasswordForSize() throws Exception {
        UserRequest userRequest = new UserRequest("Andr", "Andrei@bk.ru", "111",
                "Andrei", "Prishchepa");
        mvc.perform(requestWithContent(post("/api/users"), userRequest)).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "MODERATOR")
    public void testFirstNameNotBlank() throws Exception {
        UserRequest userRequest = new UserRequest("Andr", "Andrei@bk.ru", "1234",
                "", "Prishchepa");
        mvc.perform(requestWithContent(post("/api/users"), userRequest)).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "MODERATOR")
    public void testLastNameNotBlank() throws Exception {
        UserRequest userRequest = new UserRequest("Andr", "Andrei@bk.ru", "1234",
                "Andrei", "");
        mvc.perform(requestWithContent(post("/api/users"), userRequest)).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "MODERATOR")
    public void getTestUserById() throws Exception {
        UUID id = UUID.fromString("872c11a0-2b48-4c97-a653-6a88a046f488");
        mvc.perform(get("/api/users/" + id)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "MODERATOR")
    public void testUserForHello() throws Exception {
        mvc.perform(get("/api/users/hello")).andDo(print())
                .andExpect(status().isOk());
    }
}


package com.flamexander.book.store;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void securityAccessAllowedTest() throws Exception {
        mockMvc.perform(get("/api/v1/books"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    public void securityAccessDeniedTest() throws Exception {
        mockMvc.perform(get("/api/v1/orders"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "Bob", roles = "ADMIN")
    public void securityCheckUserTest() throws Exception {
        mockMvc.perform(get("/api/v1/orders"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void securityTokenTest() throws Exception {
        String jsonRequest = "{\n" +
                "\t\"username\": \"bob\",\n" +
                "\t\"password\": \"100\"\n" +
                "}";
        MvcResult result = mockMvc.perform(
                post("/auth")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String token = result.getResponse().getContentAsString();
        token = token.replace("{\"token\":\"", "").replace("\"}", "");

        mockMvc.perform(get("/api/v1/orders")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }
}

package com.flamexander.book.store;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "Bob", roles = "USER")
    public void securityAccessAllowedTest() throws Exception {
        mockMvc.perform(get("/api/v1/books"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    public void securityAccessDeniedTest() throws Exception {
        mockMvc.perform(get("/api/v1/books"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

//    @Test
//    @WithMockUser(username = "+79380000000", roles = "ADMIN")
//    // @WithAnonymousUser
//    public void securityAccessAllowedTest() throws Exception {
//        mockMvc.perform(get("/admin"))
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful());
//    }

//    Deprecated - Thymeleaf

//    @Test
//    public void securityAccessDeniedTest() throws Exception {
//        mockMvc.perform(get("/admin"))
//                .andDo(print())
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("http://localhost/login"));
//    }

//    @Test
//    public void correctLogin() throws Exception {
//        mockMvc.perform(formLogin("/authenticateTheUser").user("11111111").password("100"))
//                .andDo(print())
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/"));
//    }

//    @Test
//    public void badCredentials() throws Exception {
//        mockMvc.perform(formLogin("/authenticateTheUser").user("admin").password("100"))
//                .andDo(print())
//                .andExpect(status().is3xxRedirection());
//    }
}

package com.rentalhive;

import com.rentalhive.web.rest.RoleController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = RoleController.class)
@ExtendWith(SpringExtension.class)
class TestRole {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllRolesTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/roles"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                // Add more assertions based on your API response if needed
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }
}

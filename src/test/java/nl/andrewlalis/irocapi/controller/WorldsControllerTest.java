package nl.andrewlalis.irocapi.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class WorldsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testPost() throws Exception {
        final String name = "TestingWorld";
        this.mockMvc.perform(
                post("/worlds")
                        .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"" + name + "\"}")
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString(name)))
                .andExpect(content().string(containsString("token")));
    }

    @Test
    public void testGet() throws Exception {
        this.mockMvc.perform(
                get("/worlds")
        )
                .andDo(print());
    }
}

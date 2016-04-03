package com.example;

import com.example.service.UserService;


import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringRestBootApplication.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class SpringRestBootApplicationTests {

    @Autowired
    protected UserService userService;
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    @Sql("/schema.sql")
    public void serviceGetAllTest() {
        Assert.assertNotNull(userService.getAll());
        Assert.assertEquals("checking", 2, userService.getAll().size());
    }

    @Test
    @Sql("/schema.sql")
    public void serviceGetByNameTest() {
        Assert.assertEquals("VASYA", userService.getByName("VASYA").getName());
    }

    @Test
    @Sql("/schema.sql")
    public void deleteTest(){
        userService.delete(userService.getByName("VASYA").getId());
        Assert.assertEquals(1,userService.getAll().size());
    }
    @Test
    @Sql("/schema.sql")
    public void cotrollerGetAllTest() throws Exception {
        mockMvc.perform(get("/api/users")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$", Matchers.notNullValue()))
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is("VASYA")))
                .andReturn();
    }

    @Test
    @Sql("/schema.sql")
    public void cotrollerGetByNameTest() throws Exception {
        mockMvc.perform(get("/api/user").param("name", "VASYA"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.name", Matchers.is("VASYA")))
                .andReturn();
    }

    @Test
    @Sql("/schema.sql")
    public void addUserTest() throws Exception {
        mockMvc.perform(post("/api/user").param("name", "SENYA"))
                .andExpect(status().isOk())
                .andDo(print());

        mockMvc.perform(get("/api/users")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", Matchers.notNullValue()))
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[2].name", Matchers.is("SENYA")))
                .andReturn();
        //clear
        userService.delete(userService.getByName("SENYA").getId());
    }

    @Test
    @Sql("/schema.sql")
    public void deleteUserTest() throws Exception {
        mockMvc.perform(delete("/api/user/1"))
                .andExpect(status().isOk())
                .andDo(print());

        mockMvc.perform(get("/api/users")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", Matchers.notNullValue()))
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andReturn();
    }

    @Test
    @Sql("/schema.sql")
    public void getByIdTest() throws Exception {
        mockMvc.perform(get("/api/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",Matchers.is("VASYA")))
                .andReturn();
    }
}

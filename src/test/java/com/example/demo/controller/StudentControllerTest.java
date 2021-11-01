package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getById() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/students/{studentsId}", 3);

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
            .andDo(print())
            .andExpect(jsonPath("$.id", equalTo(3)))
            .andExpect(jsonPath("$.name").value("Judy"))
            .andExpect(jsonPath("$.score", notNullValue()))
            .andExpect(status().isOk())
            .andReturn();

        System.out.println("mvcResult = " + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void create() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
            .post("/students")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n"
                + "\t\"name\": \"Hank\",\n"
                + "\t\"score\": 14.6,\n"
                + "\t\"graduate\": false\n"
                + "}");

        mockMvc.perform(requestBuilder)
            .andExpect(status().isCreated());
    }


}
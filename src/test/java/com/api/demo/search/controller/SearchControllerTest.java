package com.api.demo.search.controller;

import com.api.demo.search.service.SearchService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(SearchController.class)
public class SearchControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private MockMvc mockMvc;

    @MockBean
    private SearchService searchService;

    @MockBean
    private SearchController searchController;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(searchController).build();
        this.mockMvc = MockMvcBuilders.standaloneSetup(searchService).build();
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void searchBlog() throws Exception{

        mockMvc.perform(post("/search/blog/v1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"query\":\"이효리\", \"sort\":\"recency\", \"page\":1, \"size\":10}"))
                .andExpect(status().isOk())
                .andDo(print());

    }


    @Test
    public void popularKeyword() throws Exception {

        mockMvc.perform(post("/search/popular/keyword/v1")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print());

    }
}

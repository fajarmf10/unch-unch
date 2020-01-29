package com.fajarmf.demoaja.controller;

import com.fajarmf.demoaja.entity.Post;
import com.fajarmf.demoaja.service.PostServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    PostServiceImpl postService;

    private List<Post> postList;
    private Post firstPost;
    private Post secondPost;

    @BeforeEach
    public void setUp() {
        postList = new ArrayList<>();
        firstPost = new Post();
        firstPost.setTitle("Test");
        firstPost.setSlug("test-post");
        firstPost.setBody("Hari ini aku senang sekali bisa bertemu denganmu</br>Karena kamu ada di sisiku <3</br>Kamu adalah matahariku <3</br>");
        secondPost = new Post();
        secondPost.setTitle("Second Post");
        secondPost.setSlug("second-post-incoming");
        secondPost.setBody("This is just an example</br>Just open your laptop and do your way");
        postList.add(firstPost);
        postList.add(secondPost);
    }

    @Test
    @DisplayName("It should return all the posts")
    void getAllPosts_shouldReturnAllPosts_whenThereIsAtLeastOnePost() throws Exception {
        when(postService.getAllPosts()).thenReturn(postList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/posts");
        MvcResult requestResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = requestResult.getResponse();
        String responseString = response.getContentAsString();
        Post[] posts = new ObjectMapper().readValue(responseString, Post[].class);
        assertEquals(2, posts.length);
        assertEquals(secondPost, posts[1]);
    }

    @Test
    @DisplayName("It should return no content when there is no post")
    void getAllPosts_shouldReturn204HTTPStatus_whenThereIsNoPost() throws Exception {
        when(postService.getAllPosts()).thenThrow(new EmptyResultDataAccessException("No post!", 1));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/posts");
        mockMvc.perform(requestBuilder).andExpect(status().isNoContent()).andReturn();
    }

    @Test
    @DisplayName("It should return the Post object response when the request is correct")
    void getPostBySlugUsingPath_shouldReturnThePostObject_whenTheRequestIsCorrect() throws Exception {
        when(postService.getPostUsingSlug(any(String.class))).thenReturn(firstPost);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/posts/1");
        MvcResult requestResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = requestResult.getResponse();
        String responseString = response.getContentAsString();
        Post result = new ObjectMapper().readValue(responseString, Post.class);
        assertEquals(firstPost.getTitle(), result.getTitle());
    }

    @Test
    @DisplayName("It should return the slug when the post is being created")
    void createNewPost_shouldReturn200HttpAndReturnTheNewPostSlug_whenTheRequestIsCorrect() throws Exception {
        String lalala = new ObjectMapper().writeValueAsString(firstPost);
        when(postService.createNewPost(any(Post.class))).thenReturn("slug-baru-nih");
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(lalala);
        MvcResult requestResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = requestResult.getResponse();
        String responseString = response.getContentAsString();
        assertEquals("slug-baru-nih", responseString);
    }

    // Sori gaes, yg throw2 bikin sendiri ya. W sibuk nge-sprint
}

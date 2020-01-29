package com.fajarmf.demoaja.service;

import com.fajarmf.demoaja.entity.Post;
import com.fajarmf.demoaja.repository.PostRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
class PostServiceImplTest {
    @Mock
    PostRepository repository;

    @InjectMocks
    PostServiceImpl service;

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
    @DisplayName("Should get all Posts")
    void getAllPosts_shouldSuccess_whenThereIsAtLeastOnePost() {
        when(repository.findAll()).thenReturn(postList);
        List<Post> result = service.getAllPosts();
        assertEquals(postList, result);
    }

    @Test
    @DisplayName("Should throw the error")
    void getAllPosts_shouldThrow_whenThereIsNoPost() {
        when(repository.findAll()).thenThrow(new EmptyResultDataAccessException("Alah ngethow mulu", 1));
        assertThrows(EmptyResultDataAccessException.class, () -> {
            service.getAllPosts();
        });
    }

    @Test
    @DisplayName("Should return the wanted post")
    void getPostUsingSlug_shouldReturnTheWantedPost_whenTheSlugIsCorrect() {
        when(repository.getPostBySlug("second-post-incoming")).thenReturn(secondPost);
        Post result = service.getPostUsingSlug("second-post-incoming");
        assertEquals(secondPost, result);
    }

    @Test
    @DisplayName("Should successfully created the post and returns the slug")
    void createNewPost_shouldSuccessfullyCreatedThePost_whenTheRequestIsCorrect() {
        when(repository.save(any(Post.class))).thenReturn(firstPost);
        String result = service.createNewPost(firstPost);
        assertNotNull(result);
        assertEquals("test-post", result);
    }

    @Test
    void updatePost() {
    }

    @Test
    void deletePost() {
    }
}

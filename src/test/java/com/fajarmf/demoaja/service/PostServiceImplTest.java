package com.fajarmf.demoaja.service;

import com.fajarmf.demoaja.entity.Post;
import com.fajarmf.demoaja.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

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

    @Test
    void getAllPosts_shouldSuccess_whenThereIsAtLeastOnePost() {
        List<Post> postList = new ArrayList<>();
        Post firstPost = new Post();
        firstPost.setTitle("Test");
        firstPost.setSlug("/test-post");
        firstPost.setBody("Hari ini aku senang sekali bisa bertemu denganmu</br>Karena kamu ada di sisiku <3</br>Kamu adalah matahariku <3</br>");
        postList.add(firstPost);
        when(repository.findAll()).thenReturn(postList);
        List<Post> result = service.getAllPosts();
        assertEquals(postList, result);
    }

    @Test
    void getAllPosts_shouldThrow_whenThereIsNoPost() {
        when(repository.findAll()).thenThrow(new EmptyResultDataAccessException("Alah ngethow mulu", 1));
        assertThrows(EmptyResultDataAccessException.class, () -> {
            service.getAllPosts();
        });
    }

    @Test
    void getPostUsingSlug() {
    }

    @Test
    void createNewPost() {
    }

    @Test
    void updatePost() {
    }

    @Test
    void deletePost() {
    }
}

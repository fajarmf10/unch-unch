package com.fajarmf.demoaja.service;

import com.fajarmf.demoaja.entity.Post;
import com.fajarmf.demoaja.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository repository;

    @Override
    public List<Post> getAllPosts() {
        return repository.findAll();
    }

    @Override
    public Post getPostUsingSlug(String slug) {
        return null;
    }

    @Override
    public Post createNewPost(Post post) {
        return null;
    }

    @Override
    public Post updatePost(Long postId, Post post) {
        return null;
    }

    @Override
    public String deletePost(String postId) {
        return null;
    }
}

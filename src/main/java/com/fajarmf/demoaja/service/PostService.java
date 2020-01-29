package com.fajarmf.demoaja.service;

import com.fajarmf.demoaja.entity.Post;

import java.util.List;
import java.util.Map;

public interface PostService {
    List<Post> getAllPosts();
    Post getPostUsingSlug(String slug);
    String createNewPost(Post post);
    Post updatePost(Long postId, Post post);
    String deletePost(String postId);
}

package com.fajarmf.demoaja.service;

import com.fajarmf.demoaja.entity.Post;
import com.fajarmf.demoaja.repository.PostRepository;
import com.fajarmf.demoaja.SlugAndTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        return repository.getPostBySlug(slug);
    }

    @Override
    public String createNewPost(Post post) {
        Post resultPost = repository.save(post);
        return resultPost.getSlug();
    }

    @Override
    public Post updatePost(Long postId, Post post) {
        return null;
    }

    @Override
    public String deletePost(String postId) {
        return null;
    }

    public List<Post> getAllPostsBasedOnSlugAndTitles(List<SlugAndTitle> slugAndTitles) {
        return slugAndTitles.stream()
                .map(slugAndTitle -> repository.getPostBySlugAndTitle(slugAndTitle.getSlug(), slugAndTitle.getTitle()))
                .collect(Collectors.toList());
    }
}

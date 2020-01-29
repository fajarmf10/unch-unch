package com.fajarmf.demoaja.controller;

import com.fajarmf.demoaja.SlugAndTitle;
import com.fajarmf.demoaja.entity.Post;
import com.fajarmf.demoaja.service.PostServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RestController
@RequestMapping(value = "/posts")
public class PostController {
    @Autowired
    PostServiceImpl service;

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(){
        List<Post> postList;
        try{
            postList = service.getAllPosts();
            return new ResponseEntity<>(postList, HttpStatus.OK);
        }
        catch (Exception e){
             return new ResponseEntity("NO data found", HttpStatus.NO_CONTENT);
        }
    }

    // Just other example of endpoint, no test needed
    @GetMapping("/anotherWayToGetAllPosts")
    public ResponseEntity<List<Post>> getPost(@RequestBody List<SlugAndTitle> slugAndTitles){
        return new ResponseEntity(service.getAllPostsBasedOnSlugAndTitles(slugAndTitles), HttpStatus.OK);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<Post> getPostBySlugUsingPath(@PathVariable("slug") String slug){
        return new ResponseEntity(service.getPostUsingSlug(slug), HttpStatus.OK);
    }

    // Just other example of endpoint, no test needed
    @GetMapping("/usingParam")
    public ResponseEntity<Post> getPostBySlug(@RequestParam("slug") String slug){
        return new ResponseEntity(service.getPostUsingSlug(slug), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Post> createNewPost(@RequestBody Map<String, String> postMap){
        Post post = new ObjectMapper().convertValue(postMap, Post.class);
        return new ResponseEntity(service.createNewPost(post), HttpStatus.CREATED);
    }
}

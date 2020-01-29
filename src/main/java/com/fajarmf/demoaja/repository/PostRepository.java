package com.fajarmf.demoaja.repository;

import com.fajarmf.demoaja.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Post getPostBySlug(String slug);
    Post getPostBySlugAndTitle(String slug, String title);
}

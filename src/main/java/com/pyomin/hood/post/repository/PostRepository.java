package com.pyomin.hood.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pyomin.hood.post.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}

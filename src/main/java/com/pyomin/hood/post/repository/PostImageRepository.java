package com.pyomin.hood.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pyomin.hood.post.entity.PostImage;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {

}

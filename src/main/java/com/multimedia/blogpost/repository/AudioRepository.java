package com.multimedia.blogpost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multimedia.blogpost.model.Image;

@Repository
public interface AudioRepository extends JpaRepository<Image, Long> {
    
}

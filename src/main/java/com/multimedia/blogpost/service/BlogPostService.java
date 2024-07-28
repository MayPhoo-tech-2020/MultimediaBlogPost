package com.multimedia.blogpost.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multimedia.blogpost.model.BlogPost;
import com.multimedia.blogpost.repository.BlogPostRepository;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    public List<BlogPost> findAll() {
        return blogPostRepository.findAll();
    }

    public Optional<BlogPost> findById(Long id) {
        return blogPostRepository.findById(id);
    }

    public BlogPost save(BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

    public void deleteById(Long id) {
        blogPostRepository.deleteById(id);
    }
}
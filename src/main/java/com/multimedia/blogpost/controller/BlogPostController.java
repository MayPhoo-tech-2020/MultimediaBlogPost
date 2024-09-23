package com.multimedia.blogpost.controller;

import com.multimedia.blogpost.model.BlogPost;
import com.multimedia.blogpost.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blogposts")
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    @GetMapping
    public List<BlogPost> getAllBlogPosts() {
        return blogPostService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> getBlogPostById(@PathVariable Long id) {
        Optional<BlogPost> blogPost = blogPostService.findById(id);
        return blogPost.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BlogPost> createBlogPost(@RequestParam("title") String title,
                                                   @RequestParam("content") String content,
                                                   @RequestParam("audio") MultipartFile audio,
                                                   @RequestParam("image") MultipartFile image,
                                                   @RequestParam("video") MultipartFile video) {
        try {
            BlogPost blogPost = new BlogPost();
            blogPost.setTitle(title);
            blogPost.setContent(content);
            blogPost.setAudio(blogPostService.convertToBase64(audio));
            blogPost.setImage(blogPostService.convertToBase64(image));
            blogPost.setVideo(blogPostService.convertToBase64(video));
            BlogPost savedBlogPost = blogPostService.save(blogPost);
            return ResponseEntity.ok(savedBlogPost);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogPost> updateBlogPost(@PathVariable Long id,
                                                   @RequestParam("title") String title,
                                                   @RequestParam("content") String content,
                                                   @RequestParam("audio") MultipartFile audio,
                                                   @RequestParam("image") MultipartFile image,
                                                   @RequestParam("video") MultipartFile video) {
        Optional<BlogPost> optionalBlogPost = blogPostService.findById(id);
        if (optionalBlogPost.isPresent()) {
            try {
                BlogPost blogPost = optionalBlogPost.get();
                blogPost.setTitle(title);
                blogPost.setContent(content);
                blogPost.setAudio(blogPostService.convertToBase64(audio));
                blogPost.setImage(blogPostService.convertToBase64(image));
                blogPost.setVideo(blogPostService.convertToBase64(video));
                BlogPost updatedBlogPost = blogPostService.save(blogPost);
                return ResponseEntity.ok(updatedBlogPost);
            } catch (IOException e) {
                return ResponseEntity.status(500).body(null);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBlogPost(@PathVariable Long id) {
        Optional<BlogPost> optionalBlogPost = blogPostService.findById(id);
        if (optionalBlogPost.isPresent()) {
            blogPostService.deleteById(id);
            // Explicitly return 200 OK with a custom message
            return ResponseEntity.ok("Blog post with ID " + id + " was deleted successfully.");
        } else {
            // If blog post does not exist, return 404 with a custom message
            return ResponseEntity.status(404).body("Blog post with ID " + id + " not found.");
        }
    }
    
}

package com.multimedia.blogpost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.multimedia.blogpost.model.Image;
import com.multimedia.blogpost.model.News;
import com.multimedia.blogpost.service.NewsService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<News> createNews(
            @RequestParam("title") String title,
            @RequestParam("images") MultipartFile[] images) {
        
        List<Image> imageList = new ArrayList<>();
        for (MultipartFile file : images) {
            try {
                byte[] bytes = file.getBytes();
                String base64Image = java.util.Base64.getEncoder().encodeToString(bytes);
                imageList.add(Image.builder().url(base64Image).build());
            } catch (IOException e) {
                return ResponseEntity.badRequest().build();
            }
        }
        
        News news = News.builder()
                        .title(title)
                        .images(imageList)
                        .build();

        News savedNews = newsService.saveNews(news);
        return ResponseEntity.ok(savedNews);
    }

    @GetMapping
    public ResponseEntity<List<News>> getAllNews() {
        List<News> newsList = newsService.getAllNews();
        return ResponseEntity.ok(newsList);
    }
}
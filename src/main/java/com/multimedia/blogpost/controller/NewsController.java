package com.multimedia.blogpost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.multimedia.blogpost.model.Audio;
import com.multimedia.blogpost.model.Image;
import com.multimedia.blogpost.model.News;
import com.multimedia.blogpost.model.Video;
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
            @RequestParam("description") String description,            
            @RequestParam(value = "images", required = false) MultipartFile[] images,
            @RequestParam(value = "audios", required = false) MultipartFile[] audios,
            @RequestParam(value = "videos", required = false) MultipartFile[] videos) {

        List<Image> imageList = new ArrayList<>();
        if (images != null) {
            for (MultipartFile file : images) {
                try {
                    byte[] bytes = file.getBytes();
                    String base64Image = java.util.Base64.getEncoder().encodeToString(bytes);
                    imageList.add(Image.builder().url(base64Image).build());
                } catch (IOException e) {
                    return ResponseEntity.badRequest().build();
                }
            }
        }

        List<Audio> audioList = new ArrayList<>();
        if (audios != null) {
            for (MultipartFile file : audios) {
                try {
                    byte[] bytes = file.getBytes();
                    String base64Audio = java.util.Base64.getEncoder().encodeToString(bytes);
                    audioList.add(Audio.builder().url(base64Audio).build());
                } catch (IOException e) {
                    return ResponseEntity.badRequest().build();
                }
            }
        }

        List<Video> videoList = new ArrayList<>();
        if (videos != null) {
            for (MultipartFile file : videos) {
                try {
                    byte[] bytes = file.getBytes();
                    String base64Video = java.util.Base64.getEncoder().encodeToString(bytes);
                    videoList.add(Video.builder().url(base64Video).build());
                } catch (IOException e) {
                    return ResponseEntity.badRequest().build();
                }
            }
        }

        News news = News.builder()
                        .title(title)
                        .description(description)
                        .images(imageList)
                        .audios(audioList)
                        .videos(videoList)
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

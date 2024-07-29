package com.multimedia.blogpost.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multimedia.blogpost.model.Audio;
import com.multimedia.blogpost.model.Image;
import com.multimedia.blogpost.model.News;
import com.multimedia.blogpost.model.Video;
import com.multimedia.blogpost.repository.NewsRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public News saveNews(News news) {
        if (news.getImages() != null) {
            for (Image image : news.getImages()) {
                image.setNews(news);
            }
        }

        if (news.getAudios() != null) {
            for (Audio audio : news.getAudios()) {
                audio.setNews(news);
            }
        }

        if (news.getVideos() != null) {
            for (Video video : news.getVideos()) {
                video.setNews(news);
            }
        }

        return newsRepository.save(news);
    }

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }
}

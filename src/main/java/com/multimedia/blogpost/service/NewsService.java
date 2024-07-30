package com.multimedia.blogpost.service;

import java.util.List;
import java.util.Optional;

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

    public News updateNews(Long id, News updatedNews) {
        Optional<News> existingNewsOpt = newsRepository.findById(id);
        if (existingNewsOpt.isPresent()) {
            News existingNews = existingNewsOpt.get();

            // Update fields of existingNews with data from updatedNews
            existingNews.setTitle(updatedNews.getTitle());
            existingNews.setDescription(updatedNews.getDescription());

            // Clear and update images
            if (updatedNews.getImages() != null) {
                existingNews.getImages().clear();
                for (Image image : updatedNews.getImages()) {
                    image.setNews(existingNews);
                    existingNews.getImages().add(image);
                }
            }

            // Clear and update audios
            if (updatedNews.getAudios() != null) {
                existingNews.getAudios().clear();
                for (Audio audio : updatedNews.getAudios()) {
                    audio.setNews(existingNews);
                    existingNews.getAudios().add(audio);
                }
            }

            // Clear and update videos
            if (updatedNews.getVideos() != null) {
                existingNews.getVideos().clear();
                for (Video video : updatedNews.getVideos()) {
                    video.setNews(existingNews);
                    existingNews.getVideos().add(video);
                }
            }

            return newsRepository.save(existingNews);
        } else {
            // Handle the case where the News entity with the given ID is not found
            throw new RuntimeException("News with ID " + id + " not found");
        }
    }

    public void deleteNews(Long id) {
        if (newsRepository.existsById(id)) {
            newsRepository.deleteById(id);
        } else {
            // Handle the case where the News entity with the given ID is not found
            throw new RuntimeException("News with ID " + id + " not found");
        }
    }
}

package com.multimedia.blogpost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.multimedia.blogpost.service.NewsService;

@Controller
public class ViewNewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/news")
    public String viewNews(Model model) {
        model.addAttribute("newsList", newsService.getAllNews());
        return "news";
    }
}
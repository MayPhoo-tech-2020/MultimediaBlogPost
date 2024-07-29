package com.multimedia.blogpost.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/api/blogposts/upload")
public class UploadPostController {
    @GetMapping
    public String getUploadForm() {
        return "upload";
    }

}

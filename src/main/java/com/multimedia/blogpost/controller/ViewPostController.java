package com.multimedia.blogpost.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/blogposts/view")
public class ViewPostController {

    @GetMapping
    public String getUploadForm(@RequestParam(name = "id", required = false, defaultValue = "0") String id, Model model) {
        // Add the parameter to the model
        model.addAttribute("id", id);
        
        return "view";
    }
}

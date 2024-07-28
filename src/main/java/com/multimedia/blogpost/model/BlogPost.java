package com.multimedia.blogpost.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@Entity
@JsonPropertyOrder({"id", "title", "content", "image", "audio", "video"})
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @Column(columnDefinition = "LONGTEXT")
    private String audio;

    @Column(columnDefinition = "LONGTEXT")
    private String image;

    @Column(columnDefinition = "LONGTEXT")
    private String video;
}

package com.multimedia.blogpost.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    // One-to-Many relationship with Image
    // Cascade all operations (persist, merge, remove, etc.) and remove orphaned images
    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Image> images = new ArrayList<>();

    // One-to-Many relationship with Audio
    // Cascade all operations and remove orphaned audios
    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Audio> audios = new ArrayList<>();

    // One-to-Many relationship with Video
    // Cascade all operations and remove orphaned videos
    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Video> videos = new ArrayList<>();
}

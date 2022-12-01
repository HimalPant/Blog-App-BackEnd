package com.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(name = "post_title")
    private String title;

    @Column(name="content")
    private String content;

    @Column(name="image_name")
    private  String imageName;

    @Column(name = "post_added_date")
    private Date addedDate;

    // One category can have multiple posts
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // One User can have multiple posts
    @ManyToOne
    private User user;

}

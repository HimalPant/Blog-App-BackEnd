package com.blog.services;

import com.blog.payloads.PostDTO;

import java.util.List;

public interface PostService {
    // create Post
    PostDTO createPost(PostDTO postDTO, Integer categoryId, Integer userId);

    // update Post
    PostDTO updatePost(PostDTO postDTO, Integer postId);

    // get Post by Id
    PostDTO getPostById(Integer postId);

    // get all post
    List<PostDTO> getAllPost(Integer pageNo, Integer pageSize);

    // get all post by User
    List<PostDTO> getAllPostByUser(Integer userId);

    // get all post by category
    List<PostDTO> getAllPostByCategory(Integer categoryId);

    // delete post
    void deletePost(Integer postId);

    // search post
    List<PostDTO> searchPost(String keyword);
}

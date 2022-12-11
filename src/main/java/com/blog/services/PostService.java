package com.blog.services;

import com.blog.payloads.PostDTO;
import com.blog.payloads.PostResponse;

import java.util.List;

public interface PostService {
    // create Post
    PostDTO createPost(PostDTO postDTO, Integer categoryId, Integer userId);

    // update Post
    PostDTO updatePost(PostDTO postDTO, Integer postId);

    // get Post by Id
    PostDTO getPostById(Integer postId);

    // get all post
   PostResponse getAllPost(Integer pageNo, Integer pageSize,String sortBy,String sortDir);

    // get all post by User
    public List<PostDTO> getAllPostByUser(Integer userId, Integer pageNo, Integer pageSize);

    // get all post by category
    List<PostDTO> getAllPostByCategory(Integer categoryId);

    // delete post
    void deletePost(Integer postId);

    // search post
    List<PostDTO> searchPost(String keyword);
}

package com.blog.controller;

import com.blog.payloads.APIResponse;
import com.blog.payloads.PostDTO;
import com.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    // Create Post
    @PostMapping("user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPosts(@RequestBody PostDTO postDTO,
                                               @PathVariable Integer userId,
                                               @PathVariable Integer categoryId){
        PostDTO post = this.postService.createPost(postDTO,userId,categoryId);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    // update post by postId
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> updatePosts(@RequestBody PostDTO postDTO,
                                               @PathVariable Integer postId){
        PostDTO postDTO1 = this.postService.updatePost(postDTO,postId);
        return new ResponseEntity<>(postDTO1,HttpStatus.OK);
    }

    // get post by postId
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId){
        PostDTO postById = this.postService.getPostById(postId);
        return new ResponseEntity<>(postById, HttpStatus.OK);
    }

    // get all posts
    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> getAllPosts(
            @RequestParam(value = "pageNo",defaultValue = "1",required = false) Integer pageNo,
            @RequestParam(value = "pageSize",defaultValue = "2",required = false) Integer pageSize
    ){
        List<PostDTO> allPost = postService.getAllPost(pageNo,pageSize);
        return new ResponseEntity<>(allPost, HttpStatus.OK);
    }

    // get posts by userId
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDTO>> getPostByUserId(@PathVariable Integer userId){
        List<PostDTO> allPostByUser = this.postService.getAllPostByUser(userId);
        return new ResponseEntity<>(allPostByUser,HttpStatus.OK);
    }

    // get posts by categoryId
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDTO>> getPostByCategoryId(@PathVariable Integer categoryId){
        List<PostDTO> allPostByCategory = this.postService.getAllPostByCategory(categoryId);
        return new ResponseEntity<>(allPostByCategory,HttpStatus.OK);
    }

    // delete post by id
    @DeleteMapping("/posts/{postId}")
    public APIResponse deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new APIResponse("Post with postId : "+postId +"deleted successfully",true);
    }
}

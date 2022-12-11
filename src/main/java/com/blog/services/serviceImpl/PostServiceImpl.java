package com.blog.services.serviceImpl;

import com.blog.exception.ResourceNotFoundException;
import com.blog.model.Category;
import com.blog.model.Post;
import com.blog.model.User;
import com.blog.payloads.PostDTO;
import com.blog.payloads.PostResponse;
import com.blog.repositories.CategoryRepository;
import com.blog.repositories.PostRepository;
import com.blog.repositories.UserRepository;
import com.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDTO createPost(PostDTO postDTO, Integer categoryId, Integer userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
        Post post = this.modelMapper.map(postDTO, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post newPost = this.postRepository.save(post);
        PostDTO newDTO = this.modelMapper.map(newPost, PostDTO.class);
        return newDTO;
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO,Integer postId) {
        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
        post.setContent(postDTO.getContent());
        post.setTitle(postDTO.getTitle());
        post.setImageName(postDTO.getImageName());
        Post savedPost = this.postRepository.save(post);
        PostDTO postDTOs = this.modelMapper.map(savedPost, PostDTO.class);
        return postDTOs;
    }

    @Override
    public PostDTO getPostById(Integer postId) {
        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
        PostDTO postDTO = this.modelMapper.map(post, PostDTO.class);
        return postDTO;
    }

    @Override
    public PostResponse getAllPost(Integer pageNo, Integer pageSize,String sortBy,String sortDir) {
        Sort sort =(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        /*if(sortDir.equalsIgnoreCase("asc")){
            sort = Sort.by(sortBy).ascending();
        }else{
            sort = Sort.by(sortBy).descending();
        }*/
        Pageable p = PageRequest.of(pageNo,pageSize, sort);
        Page<Post> postPage = postRepository.findAll(p); // It will return post of only one page.
        List<Post> postList = postPage.getContent();
        List<PostDTO> postDTOList = postList.stream().
                map((post) -> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setPageNo(postPage.getNumber());
        postResponse.setTotalPages(postPage.getTotalPages());
        postResponse.setTotalElements(postPage.getNumberOfElements());
        postResponse.setContent(postDTOList);
        postResponse.setPageSize(postPage.getSize());
        postResponse.setLastPage(postPage.isLast());
        return postResponse;
    }

    @Override
    public List<PostDTO> getAllPostByUser(Integer userId, Integer pageNo, Integer pageSize) {
        Pageable p = PageRequest.of(pageNo,pageSize);
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));
        Page<Post> postPage = postRepository.findAll(p);
        List<Post> posts = postPage.getContent();
        // List<Post> posts = this.postRepository.findByUser(user);
        List<PostDTO> postDTOS = posts.stream()
                .map((post) -> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
        return postDTOS;
    }

    @Override
    public List<PostDTO> getAllPostByCategory(Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        List<Post> posts = this.postRepository.findByCategory(category);
        List<PostDTO> postDTOS = posts.stream().
                map((post) -> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
        return postDTOS;
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
        this.postRepository.delete(post);
    }

    @Override
    public List<PostDTO> searchPost(String keyword) {
        return null;
    }
}

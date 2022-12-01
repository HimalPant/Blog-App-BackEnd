package com.blog.services.serviceImpl;

import com.blog.exception.ResourceNotFoundException;
import com.blog.model.User;
import com.blog.payloads.UserDto;
import com.blog.repositories.UserRepository;
import com.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
       User user = this.dtoToUser(userDto);
        User savedUser = this.userRepository.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {

        // getting id of the user to be updated
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setEmail(userDto.getEmail());
        User updatedUser = this.userRepository.save(user);
        // so here we need to convert this updatedUser to UserDto as return type is UserDto so, we need to convert it to UserDto as
        UserDto userDto1 = this.userToDto(updatedUser);
        return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        UserDto userDto = this.userToDto(user);
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> listOfUsers = this.userRepository.findAll();
        // Here we need to convert this List of Users to Dto. So for this we need to use stream api concept.
        List<UserDto> listOfDtos = listOfUsers.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return listOfDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        this.userRepository.delete(user);
    }

    // converting userDto to user normally
    private User dtoToUser(UserDto userDto){
        // converting userDto to User using model mapper class.
        User user = this.modelMapper.map(userDto, User.class);
       /* User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());*/
        return user;
    }

    // similarly converting user to userDto using model mapper class.
    private UserDto userToDto(User user){
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        return userDto;
    }

}

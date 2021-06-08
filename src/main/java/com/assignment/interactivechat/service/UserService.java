package com.assignment.interactivechat.service;

import com.assignment.interactivechat.dto.UserData;
import com.assignment.interactivechat.exception.ApiException;
import com.assignment.interactivechat.exception.ResponseStatus;
import com.assignment.interactivechat.mapper.UserMapper;
import com.assignment.interactivechat.model.User;
import com.assignment.interactivechat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String createUser(UserData userData) throws ApiException {
        Optional<User> existingUser = userRepository.findByUsername(userData.getUsername());
        if(existingUser.isPresent()){
            throw new ApiException("User with this username already exists", ResponseStatus.USER_BY_THIS_ID_NOT_FOUND.getValue());
        }
        User newUser = UserMapper.INSTANCE.userDataToUser(userData);
        userRepository.save(newUser);

        return  newUser.getId();
    }
}

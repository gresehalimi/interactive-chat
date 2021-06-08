package com.assignment.interactivechat.mapper;


import com.assignment.interactivechat.dto.UserData;
import com.assignment.interactivechat.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = UserMapper.class)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userDataToUser(UserData userData);

}

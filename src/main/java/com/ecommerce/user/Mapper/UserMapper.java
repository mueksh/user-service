package com.ecommerce.user.Mapper;

import com.ecommerce.user.dto.request.UserRequest;
import com.ecommerce.user.dto.response.UserResponse;
import com.ecommerce.user.model.User;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.ecommerce.user.Mapper.AddressMapper.toAddressEntityList;
import static com.ecommerce.user.Mapper.AddressMapper.toAddressResponseList;

@Slf4j
public class UserMapper {

    // CREATE: Request → Entity
    public static User toEntity(UserRequest userRequest){

        if (userRequest==null){
            log.warn("UserRequest is Null");
            return null;
        }

        log.info("Converting UserRequest to UserEntity");
        return User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword()) // hash in service
                .phone(userRequest.getPhone())
                .role(userRequest.getRole())
                .createdAt(LocalDateTime.now())
                .addresses(toAddressEntityList(userRequest.getAddresses()))
                .build();
    }

    // RESPONSE: Entity → Response
    public static UserResponse toUserResponse(User user) {

        if (user == null) {
            log.warn("User is Null");
            return null;
        }

        log.info("Converting User to UserResponse");
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .addresses(toAddressResponseList(user.getAddresses()))
                .build();
    }

    // RESPONSE: Entity → Response
    public static List<UserResponse> toUserResponseList(List<User> userList) {

        if (userList == null) {
            log.warn("Their is no User in list");
            return null;
        }

        log.info("Converting User list into UserResponse list");
        return userList.stream()
                .filter(Objects::nonNull)
                .map(UserMapper::toUserResponse)
                .collect(Collectors.toList());
    }
}

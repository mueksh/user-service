package com.ecommerce.user.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class UserResponse {

    private String id;
    private String name;
    private String email;
    private String phone;
    private String role;
    private LocalDateTime createdAt;

    private List<AddressResponse> addresses;
}

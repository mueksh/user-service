package com.ecommerce.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import java.util.List;

@Data
public class UserRequest {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 6)
    private String password;

    @NotBlank
    private String phone;

    @NotBlank
    // Regex "^[0-9]+$" ensures the string starts and ends with one or more digits
    @Pattern(regexp = "^[0-9]+$", message = "Phone number must contain only digits")
    private String role;

    private List<AddressRequest> addresses;
}

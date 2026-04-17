package com.ecommerce.user.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.aot.generate.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
@Document(collection = "users")
public class User {

    @Id
    private String Id;
    private String name;

    @Indexed(unique = true)
    @Email
    private String email;

    @Size(min = 6)
    private String password;

    private String phone;
    private String role;
    private LocalDateTime createdAt;

    private List<Address> addresses; // Embedded




}

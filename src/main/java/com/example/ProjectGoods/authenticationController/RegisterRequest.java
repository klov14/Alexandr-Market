package com.example.ProjectGoods.authenticationController;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @Pattern(regexp = "^[A-Za-z]{1,20}$",
             message = "Name must contain only characters")
    private String firstName;
    @Pattern(regexp = "^[A-Za-z]{1,20}$",
             message = "Surname must contain only characters")
    private String lastName;
    @Pattern(regexp="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",
             message="Enter the valid email")
    private String email;
    @Pattern(regexp = "^.{3,}$",
             message = "Password is not long enough")
    private String password;
}

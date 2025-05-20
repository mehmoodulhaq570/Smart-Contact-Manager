package com.scm.forms;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForm {

    @NotBlank(message = "Name is required")
    @Size(min =3, message = "Name must be at least 3 characters long")
    private String name;

    @Email(message = "Email is not valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be minimum 6 characters long")
    private String password;

    @Size(min = 8, max = 12, message = "Phone number must be between 8 and 12 characters long")
    private String phoneNumber;

    @NotBlank(message = "About is required")
    private String about;

    // Add any other fields you need for the user registration form

}

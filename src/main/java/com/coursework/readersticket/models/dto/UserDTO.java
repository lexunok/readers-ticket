package com.coursework.readersticket.models.dto;

import com.coursework.readersticket.models.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private User.Role role;
    private String password;
}

package com.coursework.readersticket.models.dto;

import com.coursework.readersticket.models.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private User.Role role;
    private String password;
}

package com.Licenta.QuizSurprise.DTO;

import com.Licenta.QuizSurprise.Entity.UserPoints;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Integer userGroup;
    private UserPoints userPoints;
}

package pl.edu.agh.skyhunt.api.user.dto;

import lombok.Data;

@Data
public class UserAuthentication {

    private String email;

    private String password;
}

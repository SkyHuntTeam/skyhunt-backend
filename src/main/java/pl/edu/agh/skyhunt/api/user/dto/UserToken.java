package pl.edu.agh.skyhunt.api.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserToken {

    private String token;

    public UserToken() {}
}

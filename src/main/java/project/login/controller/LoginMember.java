package project.login.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class LoginMember {

    @NotBlank
    private String userId;

    @NotBlank
    private String password;

}

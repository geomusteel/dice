package project.member.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;

@Getter @Setter
@ToString
public class JoinMember {

    @NotBlank
    @Pattern(regexp="[a-zA-Z0-9]{2,10}")
    private String userId;

    @NotBlank
    @Pattern(regexp="[a-zA-Z0-9]{6,12}")
    private String password;

    @NotBlank
    @Pattern(regexp="[a-zA-Z0-9]{6,12}")
    private String passwordVerification;

    @NotBlank
    private String name;

    @NotNull
    @Positive
    @Min(value = 6)
    @Max(value = 100)
    private Integer age;

    @NotBlank
    @Email
    private String email;

}

package project.member.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;

@Getter @Setter
@ToString
public class Member {

    private Long id;

    @NotBlank
    @Pattern(regexp="[a-zA-Z0-9]{2,10}")
    private String userId;

    @NotBlank
    @Pattern(regexp="[a-zA-Z0-9]{6,12}")
    private String password;

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

    public Member(){

    }

    public Member(String userId, String password, String name, int age, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.age = age;
        this.email = email;
    }
}

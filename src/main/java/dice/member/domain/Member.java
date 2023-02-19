package dice.member.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {

    private Long id;
    private String userId;
    private String password;
    private String name;
    private int age;
    private String email;

    public Member(String userId, String password, String name, int age, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.age = age;
        this.email = email;
    }
}

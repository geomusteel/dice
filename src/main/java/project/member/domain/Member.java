package project.member.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import project.member.controller.JoinMember;

@Getter
@Setter
@ToString
public class Member {

    private Long id;
    private String userId;
    private String password;
    private String name;
    private Integer age;
    private String email;


    public Member(JoinMember joinMember){
        this.userId = joinMember.getUserId();
        this.password = joinMember.getPassword();
        this.name = joinMember.getName();
        this.age = joinMember.getAge();
        this.email = joinMember.getEmail();
    }
}

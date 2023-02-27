package project.login.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import project.login.service.LoginService;
import project.member.repository.MemberRepository;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberRepository memberRepository;
    private final LoginService loginService;

    @GetMapping("/login")
    public String loginView(@ModelAttribute("loginMember") LoginMember loginMember) {
        return "login/login-index";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("loginMember") LoginMember loginMember, BindingResult bindingResult) {
        log.info("에러 {}",bindingResult.getAllErrors());
        log.info("로그인 멤버 {}",loginMember);

        if (memberRepository.findUserId(loginMember.getUserId()) == null) {
            bindingResult.addError(new FieldError("loginMember", "userId", "찾을 수 없는 아이디 입니다."));
            return "login/login-index";
        }

        if (loginService.login(loginMember.getUserId(), loginMember.getPassword()) == null) {
            bindingResult.addError(new FieldError("loginMember", "password", "비밀번호가 틀렸습니다."));
            return "login/login-index";
        }

        if (bindingResult.hasErrors()) {
            return "login/login-index";
        }

        return "redirect:/";

    }

}

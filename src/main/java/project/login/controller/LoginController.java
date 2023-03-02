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
import org.springframework.web.bind.annotation.RequestParam;
import project.login.service.LoginService;
import project.member.domain.Member;
import project.member.repository.MemberRepository;
import project.session.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberRepository memberRepository;

    @GetMapping("/login")
    public String loginView(@ModelAttribute("loginMember") LoginMember loginMember) {
        return "login/login-index";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("loginMember") LoginMember loginMember,
                        BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURL,
                        HttpServletRequest request) {

        Member member = memberRepository.findUserId(loginMember.getUserId());
        log.info("로그인 멤버 {}", member);

        if (member == null) {
            bindingResult.addError(new FieldError("loginMember", "userId", "찾을 수 없는 아이디 입니다."));
            return "login/login-index";
        }

        if (!member.getPassword().equals(loginMember.getPassword())) {
            bindingResult.addError(new FieldError("loginMember", "password", "비밀번호가 틀렸습니다."));
            return "login/login-index";
        }

        if (bindingResult.hasErrors()) {
            return "login/login-index";
        }

        //성공로직
        log.info("redirectURL [{}]",redirectURL);
        HttpSession session = request.getSession(true);
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember.getUserId());

        return "redirect:" + redirectURL;

    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {

        // 세션 삭제
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}

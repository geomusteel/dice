package project.member.controller;


import project.member.domain.Member;
import project.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/member")
    public String pageView(@ModelAttribute("joinMember") JoinMember joinMember) {
        return "join/join-index";
    }

    @PostMapping("/member")
    public String join(@Validated @ModelAttribute("joinMember") JoinMember joinMember, BindingResult bindingResult) {

        // 실패로직 start
        // 1차 비밀번호 검증
        if (!joinMember.getPassword().equals(joinMember.getPasswordVerification()) && !joinMember.getPassword().equals("")) {
            bindingResult.addError(new FieldError("joinMember", "passwordVerification", "비밀번호가 다릅니다."));
        }

        // 아이디 중복 검증
        if (memberRepository.findUserId(joinMember.getUserId()) != null) {
            bindingResult.addError(new FieldError("joinMember", "userId", "중복 된 아이디 입니다."));
        }

        // 에러가 있는지 검증
        if (bindingResult.hasErrors()) {
            return "join/join-index";
        }
        // 실패로직 end


        // 성공 로직
        Member member = new Member(joinMember);
        memberRepository.save(member);
        log.info("저장 멤버 {}",member);
        return "redirect:/";
    }
}

package project.member.controller;


import project.member.domain.Member;
import project.member.repository.MemberRepository;
import project.member.service.MemberService;
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

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @GetMapping("/member")
    public String pageView(@ModelAttribute("member") Member member) {
        return "/member/memberIndex";
    }

    @PostMapping("/member/save")
    public String join(@Validated @ModelAttribute("member") Member member, BindingResult bindingResult) {

        // 실패 로직

        if (memberRepository.findUserId(member.getUserId()).isPresent()) {
            bindingResult.addError(new FieldError("member", "userId", "중복 된 아이디 입니다."));
        }

        log.info("에러 {}", bindingResult.getAllErrors());

        if (bindingResult.hasErrors()) {
            return "/member/memberIndex";
        }
        log.info("member = {}", member);
        // 성공 로직
        memberRepository.save(member);
        return "redirect:/";
    }
}

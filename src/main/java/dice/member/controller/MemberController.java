package dice.member.controller;


import dice.member.domain.Member;
import dice.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/member")
    public String pageView(){
        return "/member/memberIndex";
    }

    @GetMapping("/member/save")
    public String join(@ModelAttribute Member member, BindingResult bindingResult){
        memberRepository.save(member);
        return "/";
    }
}

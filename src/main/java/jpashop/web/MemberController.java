package jpashop.web;

import jakarta.validation.Valid;
import jpashop.domain.Address;
import jpashop.domain.Member;
import jpashop.service.MemberService;
import jpashop.web.form.MemberForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "/members/createMemberForm";
    }

    @PostMapping("/new")
    public String create(@Valid MemberForm memberForm, BindingResult result) {
        if (result.hasErrors()) {
            return "/members/createMemberForm";
        }

        Address address = new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode());

        Member member = new Member();
        member.setName(memberForm.getName());
        member.setAddress(address);

        memberService.join(member);

        return "redirect:/";
    }

    /**
     * 엔티티 객체를 그대로 반환하고 있지만
     * 실무적으로는 DTO 로 변환하여 화면에 필요한 데이터만 반환하는 것이 이상적
     */
    @GetMapping
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "/members/memberList";
    }
}

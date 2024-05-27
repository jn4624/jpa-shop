package jpashop.api;

import jakarta.validation.Valid;
import jpashop.domain.Member;
import jpashop.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    /**
     * 등록 V1:
     * - 요청 값으로 Member 엔티티를 직접 받는다.
     *
     * 문제점:
     * - 엔티티에 프레젠테이션 계층을 위한 로직이 추가된다.
     * - 엔티티에 API 검증을 위한 로직이 추가된다. (@NotEmpty 등)
     * - 실무에서는 회원 엔티티를 위한 API 가 다양하게 만들어지는데,
     *   한 엔티티에 각각의 API 를 위한 모든 요청 요구사항을 담기는 힘들다.
     * - 엔티티가 변하면 API 스펙이 변한다.
     *
     * 결론:
     * - API 요청 스펙에 맞추어 별도의 DTO 를 파라미터로 받는다.
     */
    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @Data
    public static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }
}

package hello.core.member;

import hello.core.AppConfig;
import hello.core.Member.Grade;
import hello.core.Member.Member;
import hello.core.Member.MemberService;
import hello.core.Member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    //각 테스트 실행 전 appconfig 만들고 멤버서비스 할당, 테스트 실행
    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join(){
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member); //조인했을때
        Member findMember = memberService.findMember(1L);
        //then 으로 검증(내가 조인한거랑 찾은거랑 같으면 테스트성공
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}

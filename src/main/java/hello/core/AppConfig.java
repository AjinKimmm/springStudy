package hello.core;

import hello.core.Member.MemberRepository;
import hello.core.Member.MemberService;
import hello.core.Member.MemberServiceImpl;
import hello.core.Member.MemoryMemberRepository;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    //역할이 다 드러나도록 리팩토링하였다.
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository()); //new해서 메모리멤버리포객체를 생성해서 참조값을 멤버서비스임플에 주입
    }
    //나중에 만약 db로 바뀌면 이 코드만 바꾸면 됨
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy()); //아래 discountPolicy를 통해 픽스어쩌구 가져옴
    }
    //픽스디스카운트폴리시는 이걸로 가져옴 -> rate로변경
    @Bean
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
    }
}

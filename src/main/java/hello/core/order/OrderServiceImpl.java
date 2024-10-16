package hello.core.order;

import hello.core.Member.Member;
import hello.core.Member.MemberRepository;
import hello.core.Member.MemoryMemberRepository;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy; //고정할인정책 구현체로 생성

    //아래 생성자를 통해 메모리멤버리포 넘어가고 디스카운트폴리시도 픽스디스카운트 폴리시 넘어가서 값이 할당됨. 철저히 DIP를 지키는중
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    //private DiscountPolicy discountPolicy; //인터페이스에 의존

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);  //할인에 대한 변경 필요하면 discountPolicy쪽만 고치면 됨. 단일책임원칙잘지킴

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
//주문 들어오면 회원정보 먼저 조회, 후 할인정책에 회원 넘겨서 할인여부 판단, 최종가격을 받는다.(discounntPrice), 최종 생성된 주문 리턴
package hello.core.discount;

import hello.core.Member.Member;

public interface DiscountPolicy {
    //@return 할인 대상 금액, 얼마 할인됐는지 리턴하는것. 구현체 있어야함
    int discount(Member member, int price);
}

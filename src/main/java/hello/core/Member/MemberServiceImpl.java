package hello.core.Member;

public class MemberServiceImpl implements MemberService {
                  //인터페이스 의존                          //구현체 의존 => DIP위반
    private final MemberRepository memberRepository = new MemoryMemberRepository(); //가입,회원조회하려면 멤버리포, 구현객체 필요
    //가입,회원조회하려면 멤버리포 인터페이스가 필요, 인터페이스만 갖고있으면 null point exception 남. 구현체가 없이 널이면 터짐. 구현객체 넣기.
    @Override
    public void join(Member member) {
        memberRepository.save(member); //save는 다형성에 의해 메모리멤버피로에있는 세이브가 호출됨.
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

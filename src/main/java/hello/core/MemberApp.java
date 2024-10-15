package hello.core;

import hello.core.Member.Grade;
import hello.core.Member.Member;
import hello.core.Member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MemberApp

{
    public static void main(String[] args) {
        //AppConfig appConfig = new AppConfig(); //appconfig에서 멤버서비스 요청하면 멤버서비스 인터페이스 주고, 그 안에 멤버서비스 인플이 들어가있고 나머지 로직 똑같음
        //MemberService memberService = appConfig.memberService();
        //MemberService memberService = new MemberServiceImpl(); 원래는 이렇게 구현체를 직접 메인메서드에서 생성했었음,

        //스프링의 시작은 항상 이렇게. new로 스프링컨테이너 생성. appconfig 환경설정 정보로 스프링이 Bean에 객체생성한걸 넣어서 관리해줌
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //스프링 컨테이너를 통해서 파일을 찾아와야함. config에서 아래 객체를 찾을거야. 타입은 멤버서비스야.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new Member = " + member.getName());
        System.out.println("find member = " + findMember.getName());
    }
}

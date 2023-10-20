package models.member;

import commons.BadRequestException;
import commons.Validator;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Objects;

public class JoinService {
    // JoinValidator 객체 생성
    private Validator validator;
    private MemberDAO memberdao;

    public JoinService(Validator validator, MemberDAO memberdao){
        this.validator = validator;
        this.memberdao = memberdao;
    }
    public void join(Member member){
        validator.check(member);

        memberdao.register(member);
    }

    public void join(HttpServletRequest request) {
        String _agree = Objects.requireNonNullElse(request.getParameter("agree"), "false");
        boolean agree = _agree.equals("true") ? true : false;

        Member member = Member.builder()
                .userId(request.getParameter("userId"))
                .userPw(request.getParameter("userPw"))
                .confirmUserPw(request.getParameter("confirmUserPw"))
                .email(request.getParameter("email"))
                .userName(request.getParameter("userName"))
                .agree(agree)
                .build();
        join(member);
    }
}

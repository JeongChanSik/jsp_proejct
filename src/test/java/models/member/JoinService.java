package models.member;

import commons.BadRequestException;
import commons.Validator;

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
}

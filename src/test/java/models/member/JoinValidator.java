package models.member;

import commons.BadRequestException;
import commons.LengthValidator;
import commons.RequiredValidator;
import commons.Validator;

public class JoinValidator implements Validator<Member>, RequiredValidator, LengthValidator {

    @Override
    public void check(Member member) {
        String userId = member.getUserId();
        String userPw = member.getUserPw();
        String confirmUserPw = member.getConfirmUserPw();

        // 가독성이 좀 더 효율적인 필수 항목 검증 사항 Start
        requiredCheck(userId, new BadRequestException("아이디를 입력하세요."));
        requiredCheck(userPw, new BadRequestException("비밀번호를 입력하세요."));
        requiredCheck(confirmUserPw, new BadRequestException("비밀번호를 확인하세요."));
        requiredCheck(member.getUserName(), new BadRequestException("회원명을 입력하세요."));
        requiredCheck(member.getEmail(), new BadRequestException("이메일을 입력하세요."));
        requiredTrue(member.isAgree(), new BadRequestException("회원가입 약관에 동의하세요"));
        // 필수 항목 검증 사항 Start

        /* 가독성을 위해 사용 안 함
        String userId = member.getUserId();
        if(userId == null || userId.isBlank()) {
            throw new BadRequestException("아이디를 입력하세요");
        } */

        // 아이디, 비밀번호 자릿수 체크 Start
        lengthCheck(userId, 6, new BadRequestException("아이디는 6자리 이상 입력하세요."));
        lengthCheck(userPw, 8, new BadRequestException("비밀번호는 8자리 이상 입력하세요"));
        // 아이디, 비밀번호 자릿수 체크 End

        // 비밀번호, 비밀번호 확인 일치 여부 체크
        requiredTrue(userPw.equals(confirmUserPw), new BadRequestException("비밀번호가 일치하지 않습니다."));

    }
}

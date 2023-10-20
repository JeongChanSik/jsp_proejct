package models.member;

import commons.BadRequestException;
import commons.RequiredValidator;
import commons.Validator;
import jakarta.servlet.http.HttpServletRequest;


public class LoginValidator implements Validator<HttpServletRequest>, RequiredValidator { // RequiredValidator는 길이 체크 X

    @Override
    public void check(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String userPw = request.getParameter("userPw");

        // 로그인 실패 시 뜨는 알림창(필수 입력 항목 검사)
        requiredCheck(userId, new BadRequestException("아이디를 입력하세요."));
        requiredCheck(userPw, new BadRequestException("비밀번호를 입력하세요"));
    }
}

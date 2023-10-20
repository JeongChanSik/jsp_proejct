package models.member;

import commons.Validator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LoginService {

    private Validator<HttpServletRequest> validator;
    private  MemberDAO memberDAO;

    public LoginService(Validator<HttpServletRequest> validator, MemberDAO memberDAO) {
        this.validator = validator;
        this.memberDAO = memberDAO;
    }
    public void login(HttpServletRequest request) {
        validator.check(request);

        // 로그인 처리
        String userId = request.getParameter("userId");
        Member member = memberDAO.get(userId);
        HttpSession session = request.getSession();
        session.setAttribute("member", member);
    }
}

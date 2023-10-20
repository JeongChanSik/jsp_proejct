package tests;

import commons.BadRequestException;
import models.member.JoinService;
import models.member.JoinValidator;
import models.member.Member;
import models.member.MemberDAO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("회원가입 기능 단위 테스트")
public class JoinServiceTest {

    private JoinService joinService;

    void init(){
        joinService = new JoinService(new JoinValidator(), new MemberDAO());
    }

    private Member getMember(){
        return Member.builder()
                        .userId("user" + System.currentTimeMillis())
                        .userPw("12345678")
                        .confirmUserPw("12345678")
                        .userName("사용자")
                        .email("user@test.org")
                        .agree(true)
                        .build();
    }
    @Test
    @DisplayName("회원가입 성공 시 예외가 발생하지 않음")
    void joinSuccess(){
        assertDoesNotThrow(() ->{
            joinService.join(getMember());
        });
    }
    @Test
    @DisplayName("필수 항목 검증(아이디, 비밀번호, 비밀번호 확인, 회원명, 이메일, 회원가입 약관 동의)" +
            " 검증 실패 시 BadRequestException 발생")
    void requiredFieldCheck(){
        // 아이디가 null값 또는 빈 값일 때
        assertThrows(BadRequestException.class, () -> {
            Member member = getMember();

            member.setUserId(null);
            joinService.join(member);

            member.setUserId("  ");
            joinService.join(member);

        });
    }
}

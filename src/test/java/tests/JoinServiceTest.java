package tests;

import models.member.JoinService;
import models.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("회원가입 기능 단위 테스트")
public class JoinServiceTest {

    private JoinService joinService;

    void init(){
        joinService = new JoinService();
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
}

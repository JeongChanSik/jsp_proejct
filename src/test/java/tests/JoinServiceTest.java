package tests;

import commons.BadRequestException;
import models.member.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("회원가입 기능 단위 테스트")
public class JoinServiceTest {

    private JoinService joinService;

    void init(){
        joinService = ServiceManager.getInstance().joinService();
    }

    private Member getMember() {
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
    void requiredFieldCheck() {
        // 가독성이 좀 더 효율적으로
        assertAll(
                () -> {
                    // 아이디 검증(userId)
                    Member member = getMember();
                    member.setUserId(null);
                    requiredFieldEachCheck(member, "아이디");

                    member.setUserId("  ");
                    requiredFieldEachCheck(member, "아이디");
                },
                () -> {
                    // 비밀번호 검증(userPw)
                    Member member = getMember();
                    member.setUserId(null);
                    requiredFieldEachCheck(member, "비밀번호");

                    member.setUserPw("  ");
                    requiredFieldEachCheck(member, "비밀번호");
                },
                () -> {
                    // 비밀번호 확인(userId)
                    Member member = getMember();
                    member.setConfirmUserPw(null);
                    requiredFieldEachCheck(member, "비밀번호를 확인");

                    member.setConfirmUserPw("  ");
                    requiredFieldEachCheck(member, "비밀번호를 확인");
                },
                () -> {
                    // 이름 검증(userId)
                    Member member = getMember();
                    member.setUserName(null);
                    requiredFieldEachCheck(member, "이름");

                    member.setUserName("  ");
                    requiredFieldEachCheck(member, "이름");
                },
                () -> {
                    // 이메일 검증(userId)
                    Member member = getMember();
                    member.setEmail(null);
                    requiredFieldEachCheck(member, "이메일");

                    member.setEmail("  ");
                    requiredFieldEachCheck(member, "이메일");
                },
                () -> {
                    // 약관 동의 여부 검증(userId)
                    Member member = getMember();
                    member.setAgree(false);
                    requiredFieldEachCheck(member, "약관");

                }
        );
    }
        // 아이디가 null값 또는 빈 값일 때
        /*BadRequestException thrown = assertThrows(BadRequestException.class, () -> {
            Member member = getMember();

            member.setUserId(null);
            joinService.join(member);

            member.setUserId("  ");
            joinService.join(member);

        });*/

       /* String message = thrown.getMessage();
        // assertEquals("아이디를 입력하세요", message);
        assertTrue(message.contains("아이디"));

        // 비밀번호가 null값 또는 빈 값일 때
        assertThrows(BadRequestException.class, () -> {
            Member member = getMember();

            member.setUserPw(null);
            joinService.join(member);

            member.setUserPw("  ");
            joinService.join(member);
        });*/


    private void requiredFieldEachCheck(Member member, String word){
        BadRequestException thrown = assertThrows(BadRequestException.class, () ->{
            joinService.join(member);
        });
        assertTrue(thrown.getMessage().contains(word));
    }
}

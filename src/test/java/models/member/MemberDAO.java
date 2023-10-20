package models.member;

import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.Map;

public class MemberDAO {
    private static Map<String, Member> members = new HashMap<String, Member>();
    public void register(Member member) { // 회원가입, 암호화
        String userPw = BCrypt.hashpw(member.getUserPw(), BCrypt.gensalt(12)); // 비밀번호 암호화 설정
        member.setUserPw(userPw);
        members.put(member.getUserId(), member);
    }

    public Member get(String userId) {
        return members.get(userId);
    }

    public boolean exists(String userId) {
        return members.containsKey(userId);
    }

    public static void clearData() { // 기존에 데이터가 있기 때문에 clear메소드로 데이터 정리해주기
        members.clear();
    }
}

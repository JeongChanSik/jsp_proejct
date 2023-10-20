package models.member;

import java.util.HashMap;
import java.util.Map;

public class MemberDAO {
    private static Map<String, Member> members = new HashMap<String, Member>();

    public void register(Member member) { // 회원가입
        members.put(member.getUserId(), member);
    }

    public Member get(String userId) {
        return members.get(userId);
    }

    public boolean exists(String userId) {
        return members.containsKey(userId);
    }
}

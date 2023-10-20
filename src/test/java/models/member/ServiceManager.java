package models.member;

public class ServiceManager {

    private static ServiceManager instance;
    private ServiceManager() {}
    public static ServiceManager getInstance() {
        if (instance == null) {
            instance = new ServiceManager();
        }
        return  instance;
    }
    public MemberDAO memberDAO(){
        return new MemberDAO();
    }

    public JoinValidator joinValidator(){
        JoinValidator validator = new JoinValidator();
        validator.setMemberDAO(memberDAO());
        return validator;
    }

    public JoinService joinService() {
        return new JoinService(joinValidator(), memberDAO());
    }
}

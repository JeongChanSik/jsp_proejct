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
        return new JoinValidator();
    }

    public JoinService joinService() {
        return new JoinService(joinValidator(), memberDAO());
    }
}

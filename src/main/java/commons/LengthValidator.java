package commons;

public interface LengthValidator {
    // 아이디 자릿수 검증
    default void lengthCheck(String str, int min, int max, RuntimeException e){
        if(min > 0 && str.length() < min) {
            throw e;
        }
        if(max > 0 && str.length() > max) {
            throw e;
        }
    }

    default void lengthCheck(String str, int min, RuntimeException e) {
        lengthCheck(str, min, 0, e);
    }
}

package commons;

public interface RequiredValidator {

    default void requiredCheck(String str, RuntimeException e) {
        if (str == null || str.isBlank()) {
            throw e;
        }
    }
    // 약관 동의 여부 체크가 되어있지 않으면 RuntimeException 발생
    default void requiredTrue(boolean result, RuntimeException e){
        if(!result){
            throw e;
        }
    }
}

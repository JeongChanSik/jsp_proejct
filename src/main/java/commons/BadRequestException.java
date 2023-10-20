package commons;

// 예외 발생 시 RuntimeException 발생하도록 하는 역할
public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message); // super()를 꼭 정의해줘야 한다.
    }
}

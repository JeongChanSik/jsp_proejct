package commons;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class ScriptUtil {
    public static void alertError(HttpServletResponse resp, Exception e) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter(); // IOException이 발생하니 throws IOException을 넣어준다.
        out.printf("<script>alert('%s');</script>", e.getMessage());

        e.printStackTrace();
    }
    public static void go(HttpServletResponse resp, String url, String target) throws IOException {
        target = Objects.requireNonNullElse(target, "self");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter(); // IOException이 발생하니 throws IOException을 넣어준다.
        // 스크립트로 보내면서 target.location.replace(url)을 해주면서 데이터가 두 번 기록되지 않도록 방지해준다.
        out.printf("<script>%s.location.replace('%s');</script>", target, url);
    }

    public static void go(HttpServletResponse resp, String url) throws IOException {
        go(resp, url, null); // IOException이 발생하니 throws IOException을 넣어준다.
    }
}

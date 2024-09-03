package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //서블릿이 자동으로 http메시지를 생성

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);    //자동으로 생성된 요청 인스턴스 확인
        System.out.println("response = " + response);   //자동으로 생성된 응답 인스턴스 확인

        String username = request.getParameter("username");     //url 요청 값을 파라미터로 받아와서 변수에 저장
        System.out.println("username = " + username);       //url에서 요청한 값이 저장된걸 확인

        response.setContentType("text/plain");      //응답 데이터를 문자로 보냄
        response.setCharacterEncoding("utf-8");     //문자 인코딩
        response.getWriter().write("hello " + username); //html 메시지 바디에 데이터를 넣음
    }
}

package pangyo.makeat.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pangyo.makeat.dto.Users;
import pangyo.makeat.service.KakaoService;
import pangyo.makeat.service.UserInfoService;


import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    KakaoService ks;
    @Autowired
    UserInfoService us;

    /**
     * users/info
     * @param kakaoId
     * @return
     */
    //회원 정보 GET
    @GetMapping("/info/{kakaoId}")
    public Users getUserInfo(@PathVariable String kakaoId) throws IOException {
        log.info("mappingPath userId={}", kakaoId);
        Users users = us.findUserInfo(kakaoId);
        return users; //user info json 보내기
    }

    //회원 정보 수정 란에서 최초 회원 정보 저장
    //getParameter()는 GET의 쿼리파라미터, POST Form 파라미터 모두 꺼낼 수 있음
    @PostMapping("/info/{kakaoId}")
    public void postUserInfo(@PathVariable String kakaoId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        int height = Integer.parseInt(request.getParameter("height"));
        int weight = Integer.parseInt(request.getParameter("weight"));
        float bmi = Float.parseFloat(request.getParameter("bmi"));
        us.saveUserInfo(kakaoId, age, gender, height, weight, bmi);
    }

    //회원 정보 수정
    @PutMapping("/info/{kakaoId}")
    public void putUserInfo(@PathVariable String kakaoId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        int height = Integer.parseInt(request.getParameter("height"));
        int weight = Integer.parseInt(request.getParameter("weight"));
        float bmi = Float.parseFloat(request.getParameter("bmi"));
        us.saveUserInfo(kakaoId, age, gender, height, weight, bmi);
    }

    //Login
    @GetMapping("/login")
    public String loginPage(){
        return "kakaoCI/login";
    }

    @GetMapping("/kakao")
    public String getCI(@RequestParam String code, Model model) throws IOException, IOException {
        System.out.println("code = " + code);
        String access_token = ks.getToken(code);
        Map<String, Object> userInfo = ks.getUserInfo(access_token);
        model.addAttribute("code", code);
        model.addAttribute("access_token", access_token);
        model.addAttribute("userInfo", userInfo);

        //ci는 비즈니스 전환후 검수신청 -> 허락받아야 수집 가능
        return "index"; //userInfo의 id값 바로 넘겨주기
    }

    //QnA
    @GetMapping("/info/qna")
    public String getQna() {
        return "qna";
    }
}

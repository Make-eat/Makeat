package pangyo.makeat.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pangyo.makeat.dto.UserInfo;
import pangyo.makeat.dto.Users;
import pangyo.makeat.service.KakaoService;
import pangyo.makeat.service.UserInfoService;
import pangyo.makeat.service.UsersService;


import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    KakaoService ks;
    @Autowired
    UserInfoService uis;
    @Autowired
    UsersService us;

    /**
     * users/info
     * @param kakaoId
     * @return
     */
    //회원 정보 GET
    @GetMapping("/info/{kakaoId}")
    public UserInfo getUserInfo(@PathVariable String kakaoId) throws IOException {
        Optional<UserInfo> userInfo = uis.findUserInfo(kakaoId);
        log.info("userinfo: {}", userInfo.get());
        return userInfo.get(); //user info json 보내기
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

        uis.saveUserInfo(kakaoId, age, gender, height, weight, bmi);
    }

    //회원 정보 수정
    @PutMapping("/info/{kakaoId}")
    public void putUserInfo(@PathVariable String kakaoId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        int height = Integer.parseInt(request.getParameter("height"));
        int weight = Integer.parseInt(request.getParameter("weight"));
        float bmi = Float.parseFloat(request.getParameter("bmi"));
        uis.saveUserInfo(kakaoId, age, gender, height, weight, bmi);
    }

    //회원 탈퇴
    //수정중
    @DeleteMapping("/info/{kakaoId}")
    public void deleteUserInfo(@PathVariable String kakaoId) throws IOException {
        log.info("mappingPath userId={}", kakaoId);
        Optional<UserInfo> users = uis.findUserInfo(kakaoId);
    }

    /**
     * Login
     * @return
     */
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

    /**
     * Register
     */
    @PostMapping("/register/{kakaoId}")
    public String makeUser(@PathVariable String kakaoId) throws IOException {
        us.saveUser(kakaoId);
        us.findAllUsers();
        return "ok";


    }

    /**
     * QnA
     * @return
     */
    @GetMapping("/info/qna")
    public String getQna() {
        return "qna";
    }
}

package pangyo.makeat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pangyo.makeat.service.KakaoService;
import pangyo.makeat.service.UserDataService;


import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    KakaoService ks;
    @Autowired
    UserDataService us;

    //Users
    @GetMapping("/info/{kakaoId}")
    public String getUserData(@PathVariable String kakaoId) {
        log.info("mappingPath userId={}", kakaoId);
        return "userdata"; //유저 정보 페이지
    }

    @PostMapping("/info/{kakaoId}")
    public void postUserData(@PathVariable String kakaoId) throws IOException {
        us.saveUserData(kakaoId);
        log.info("mappingPath userId={}", kakaoId);
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
        return "index";
    }

    //QnA
    @GetMapping("/info/qna")
    public String getQna() {
        return "qna";
    }
}

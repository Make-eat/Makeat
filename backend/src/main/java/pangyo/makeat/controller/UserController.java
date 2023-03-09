package pangyo.makeat.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pangyo.makeat.entity.UserInfo;
import pangyo.makeat.service.UserInfoService;
import pangyo.makeat.service.UsersService;


import java.io.IOException;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
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

    @PostMapping("/abc")
    public void abc(HttpServletRequest request, HttpServletResponse response){
        log.info(request.getParameter("bmi"));
    }

    //회원 정보 수정 란에서 최초 회원 정보 저장
    //getParameter()는 GET의 쿼리파라미터, POST Form 파라미터 모두 꺼낼 수 있음
    @PostMapping("/info/{kakaoId}")
    public void postUserInfo(@PathVariable String kakaoId, @RequestBody UserInfo userInfo) throws IOException {
        int age = userInfo.getAge();
        String gender = String.valueOf(userInfo.getGender());
        int height = userInfo.getHeight();
        int weight = userInfo.getWeight();
        float bmi = userInfo.getBmi();

        uis.saveUserInfo(kakaoId, age, gender, height, weight, bmi);
        log.info("{} {} {} {} {} {}", kakaoId, age, gender, height, weight, bmi);
    }

    //회원 정보 수정
    @PutMapping("/info/{kakaoId}")
    public void putUserInfo(@PathVariable String kakaoId, @RequestBody UserInfo userInfo) throws IOException {
        int age = userInfo.getAge();
        String gender = String.valueOf(userInfo.getGender());
        int height = userInfo.getHeight();
        int weight = userInfo.getWeight();
        float bmi = userInfo.getBmi();

        uis.saveUserInfo(kakaoId, age, gender, height, weight, bmi);
        log.info("{} {} {} {} {} {}", kakaoId, age, gender, height, weight, bmi);
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
    @GetMapping("/login/{kakaoId}")
    public boolean loginPage(@PathVariable String kakaoId){
        return us.checkExistUsers(kakaoId);
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

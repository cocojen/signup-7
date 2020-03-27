package com.example.group7;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
@RestController
public class ApiUserController {
    @Autowired
    UserRepository userRepository;
    ///회원가입
    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<ApiResponseMessage> createUser(User user) {
        ///아이디 중복 체크
        if (userRepository.isUserExists(user.getUserId())) {
            return new ResponseEntity<>(new ApiResponseMessage(HttpStatus.BAD_REQUEST, "ID ALREADY IN USE",404), HttpStatus.BAD_REQUEST);
        }
        if (userRepository.isNumberExists(user.getPhoneNumber())) {
            return new ResponseEntity<>(new ApiResponseMessage(HttpStatus.BAD_REQUEST, "PHONE NUMBER ALREADY IN USE",404), HttpStatus.BAD_REQUEST);
        }
        if (userRepository.isEmailExists(user.getEmail())) {
            return new ResponseEntity<>(new ApiResponseMessage(HttpStatus.BAD_REQUEST, "EMAIL ALREADY IN USE",404), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ApiResponseMessage(HttpStatus.OK, "REGISTERED SUCCESSFULLY",200), HttpStatus.OK);
    }
    ///아이디 중복검사
    @CrossOrigin
    @PostMapping("/validate/id")
    public ResponseEntity<ApiResponseMessage> isValidateId(@RequestBody HashMap<String, String> map) {
        String userId = map.get("userId");
        if (!userRepository.isUserExists(userId)) {
            return new ResponseEntity<>(new ApiResponseMessage(HttpStatus.OK, "ID AVAILABLE",200), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponseMessage(HttpStatus.BAD_REQUEST, "ID ALREADY IN USE",404), HttpStatus.BAD_REQUEST);
    }
    ///핸드폰 번호 중복검사
    @CrossOrigin
    @PostMapping("/validate/phoneNumber")
    public ResponseEntity<ApiResponseMessage> isValidateNumber(@RequestBody HashMap<String, String> map) {
        String phoneNumber = map.get("phoneNumber");
        if (!userRepository.isNumberExists(phoneNumber)) {
            return new ResponseEntity<>(new ApiResponseMessage(HttpStatus.OK, "PHONE NUMBER AVAILABLE",200), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponseMessage(HttpStatus.BAD_REQUEST, "PHONE NUMBER ALREADY IN USE",404), HttpStatus.BAD_REQUEST);
    }
    ///이메일 중복검사
    @CrossOrigin
    @PostMapping("/validate/email")
    public ResponseEntity<ApiResponseMessage> isValidateEmail(@RequestBody HashMap<String, String> map) {
        String email = map.get("email");
        if (!userRepository.isEmailExists(email)) {
            return new ResponseEntity<>(new ApiResponseMessage(HttpStatus.OK, "EMAIL AVAILABLE",200), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponseMessage(HttpStatus.BAD_REQUEST, "EMAIL ALREADY IN USE",404), HttpStatus.BAD_REQUEST);
    }
}
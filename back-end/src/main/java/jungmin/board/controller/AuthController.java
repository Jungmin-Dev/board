package jungmin.board.controller;


import jungmin.board.domain.Info;
import jungmin.board.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping(value = "/auth")
@Controller
@CrossOrigin(origins="*")
public class AuthController {

    private final AuthService authService;

    // 아이디(이메일) 중복 체크
    @RequestMapping(value="/duplicate", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> duplicate(@RequestBody Info param) throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("userEmail",authService.duplicate(param.getUserEmail()));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    // 회원가입
    @RequestMapping(value="/join", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> join(@RequestBody Info param) throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("user", authService.join(param));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    // 로그인
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> login(@RequestBody Info param) throws Exception{
        Map<String, Object> map = new HashMap<>();
        Optional info = authService.login(param);
        if(info.isPresent()){
            map.put("login", info.get());
        }
        else if(info == Optional.empty()){
            map.put("login", 0);
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    // 인증번호 보내기
    @RequestMapping(value="/emailcheck", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> emailCheck(@RequestBody Info param) throws Exception{
        Map<String, Object> map = new HashMap<>();
        String info =  authService.mailCheck(param);
        if(info=="1"){
            map.put("userEmail", 1);
        }
        else if(info!="1"){
            map.put("userEmail", 0);
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    // 인증번호 일치하는지 확인
    @RequestMapping(value="/certificationcheck", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> certificationcheck(@RequestBody Info param) throws Exception{
        Map<String, Object> map = new HashMap<>();
        int info = authService.Certification(param);
        if(info>0)
            map.put("selfAuth", 1);
        else{
            map.put("selfAuth", 0);
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    // 비밀번호 찾기(아이디 유효 검사)
    @RequestMapping(value="/emailfind", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> findPassword(@RequestBody Info param) throws Exception{
        Map<String, Object> map = new HashMap<>();
        Optional info =  authService.findPassword(param.getUserEmail());
        if(info.isPresent()){
            map.put("userEmail", 1);
        }
        else if(info == Optional.empty()){
            map.put("userEmail", 0);
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    // 비밀번호 찾기(비밀번호 변경)
    @RequestMapping(value="/changepw", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> changePw(@RequestBody Info param) throws Exception{
        Map<String, Object> map = new HashMap<>();
        int info =  authService.changePassword(param);
        if(info>=1){
            map.put("changePw", 1);
        }
        else if(info<=0){
            map.put("changePw", 0);
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}

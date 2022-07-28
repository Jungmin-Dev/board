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
@RequestMapping("/auth")
@Controller
@CrossOrigin(origins="*")
public class AuthController {

    private final AuthService authService;

    @RequestMapping(value="/duplicate", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> duplicate(@RequestBody Info param) throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("userId",authService.duplicate(param.getUserId()));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @RequestMapping(value="/join", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> emailCheck(@RequestBody Info param) throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("user", authService.join(param));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> login(@RequestBody Info param) throws Exception{
        Map<String, Object> map = new HashMap<>();
        Optional info = authService.login(param);
        if(info!= Optional.empty()){
            map.put("login", info.get());
        }
        else if(info == Optional.empty()){
            map.put("login", 0);
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @RequestMapping(value="/emailcheck", method = RequestMethod.POST)
    @ResponseBody
    public String join() throws Exception{

        return "하이";
    }
}

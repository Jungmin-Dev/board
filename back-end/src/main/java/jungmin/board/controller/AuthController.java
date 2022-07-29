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
        map.put("userEmail",authService.duplicate(param.getUserEmail()));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @RequestMapping(value="/join", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> join(@RequestBody Info param) throws Exception{
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
    public ResponseEntity<Map<String, Object>> emailcheck(@RequestBody Info param) throws Exception{
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

    @RequestMapping(value="/certificationcheck", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> certificationcheck(@RequestBody Info param) throws Exception{
        Map<String, Object> map = new HashMap<>();
        int info =  authService.Certification(param);
        if(info>0)
            map.put("selfAuth", 1);
        else{
            map.put("selfAuth", 0);
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}

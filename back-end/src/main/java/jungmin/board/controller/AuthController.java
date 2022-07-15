package jungmin.board.controller;


import ch.qos.logback.classic.Logger;
import jungmin.board.domain.login;
import jungmin.board.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/auth")
@Controller
@CrossOrigin(origins="*")
public class AuthController {

    private final AuthService authService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> login(@RequestBody login param) throws Exception{
        Map<String, Object> map = new HashMap<>();
        login info = authService.login(param);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @RequestMapping(value="/test", method = RequestMethod.GET)
    @ResponseBody
    public String test() throws Exception{
        return "abc";
    }
}

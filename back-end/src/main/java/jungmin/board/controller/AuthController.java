package jungmin.board.controller;


import ch.qos.logback.classic.Logger;
import jungmin.board.domain.Info;
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

    @RequestMapping(value="/duplicate", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> duplicate() throws Exception{
        return null;
    }

    @RequestMapping(value="/join", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> join() throws Exception{
        return null;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> login(@RequestBody Info param) throws Exception{
        Map<String, Object> map = new HashMap<>();
        authService.login(param);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }




}

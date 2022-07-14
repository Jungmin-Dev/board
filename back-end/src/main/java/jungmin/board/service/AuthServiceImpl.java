package jungmin.board.service;

import jungmin.board.domain.login;
import jungmin.board.mapper.AuthMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@RequiredArgsConstructor
@Log
@Service
public class AuthServiceImpl implements AuthService{

    private final AuthMapper authMapper;

    @Override
    public login login(login param) throws Exception {
        login result = authMapper.userInfo(param);

        return result;
    }
}

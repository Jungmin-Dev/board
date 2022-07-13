package jungmin.board.service;

import jungmin.board.domain.login;

import java.util.HashMap;

public interface AuthService {
    // 로그인 성공 유무
    public login login(login param) throws Exception;
}

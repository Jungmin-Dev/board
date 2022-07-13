package jungmin.board.mapper;

import jungmin.board.domain.login;

import java.util.HashMap;


public interface AuthMapper {
    // 로그인 확인
    public login userInfo(login param) throws Exception;

}

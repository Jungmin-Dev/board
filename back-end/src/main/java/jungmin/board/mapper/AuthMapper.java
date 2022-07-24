package jungmin.board.mapper;

import jungmin.board.domain.Info;

import java.util.HashMap;


public interface AuthMapper {
    // 로그인 확인
    public Info userInfo(Info userId) throws Exception;

}

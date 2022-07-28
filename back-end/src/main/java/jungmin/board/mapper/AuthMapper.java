package jungmin.board.mapper;

import jungmin.board.domain.Info;

import java.util.HashMap;
import java.util.Optional;


public interface AuthMapper {
    // 로그인 확인
    public Info userInfo(Info info) throws Exception;
    public Info userDuplicate(String info) throws Exception;
    // 회원가입
    public int userJoin(Info info) throws Exception;
}

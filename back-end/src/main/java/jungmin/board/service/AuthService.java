package jungmin.board.service;

import jungmin.board.domain.Info;


import java.util.HashMap;
import java.util.Optional;

public interface AuthService {
    // 로그인 성공 유무
    public Optional login(Info param) throws Exception;
    // 회원 가입
    public int join(Info param) throws Exception;
    // Id 중복 체크 검사
    public Optional duplicate(String param) throws Exception;

}

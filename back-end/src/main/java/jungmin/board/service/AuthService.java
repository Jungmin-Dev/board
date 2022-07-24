package jungmin.board.service;

import jungmin.board.domain.Info;


import java.util.HashMap;

public interface AuthService {
    // 로그인 성공 유무
    public Info login(Info param) throws Exception;
    public Info join(Info param) throws Exception;
}

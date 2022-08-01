package jungmin.board.service;

import jungmin.board.domain.Info;


import java.util.HashMap;
import java.util.Optional;

public interface AuthService {
    // 로그인
    public Optional login(Info param) throws Exception;
    // 회원 가입
    public int join(Info param) throws Exception;
    // Id 중복 체크 검사
    public Optional duplicate(String param) throws Exception;
    // 이메일 보내기
    public String mailCheck(Info info) throws Exception;
    // 인증번호 체크
    public int Certification(Info info) throws Exception;

    public Optional findPassword(String param) throws Exception;
    public int changePassword(Info param) throws Exception;
}

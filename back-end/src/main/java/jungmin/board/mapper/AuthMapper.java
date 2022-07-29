package jungmin.board.mapper;

import jungmin.board.domain.Info;

import java.util.HashMap;
import java.util.Optional;


public interface AuthMapper {
    // 로그인
    public Info userLogin(Info info) throws Exception;
    public Info userDuplicate(String info) throws Exception;
    // 회원가입
    public int userJoin(Info info) throws Exception;
    // 인증번호 저장 (비교를 위한 DB 저장)
    public int userEmailCertification(Info info) throws Exception;
    // 인증번호 확인 (인증번호 일치 시 인증번호 DB 삭제) -- DB 과부하 예방
    public int userEmailCertificationCheck(Info info) throws Exception;
}

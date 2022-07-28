package jungmin.board.service;

import jungmin.board.domain.Info;
import jungmin.board.mapper.AuthMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.stereotype.Service;
import java.util.Optional;

@RequiredArgsConstructor
@Log
@Service
public class AuthServiceImpl implements AuthService{

    private final AuthMapper authMapper;

    @Override
    public int join(Info param) throws Exception {
        // 회원가입
        return authMapper.userJoin(param);
    }

    @Override
    public Optional duplicate(String param) throws Exception {
        // 중복 체크
        return Optional.ofNullable(authMapper.userDuplicate(param));
    }


    @Override
    public Info login(Info param) throws Exception {
//        Info result = authMapper.userInfo(param);

        return null;
    }
}

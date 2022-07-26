package jungmin.board.service;

import jungmin.board.domain.Info;
import jungmin.board.mapper.AuthMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Optional;

@RequiredArgsConstructor
@Log
@Service
public class AuthServiceImpl implements AuthService{

    private final AuthMapper authMapper;

    @Override
    public Info join(Info param) throws Exception {


        // 회원기ㅏ입

        return null;
    }

    @Override
    public Optional<Info> duplicate(Info param) throws Exception {
        // 중복 체크
        Optional<Info> optional = Optional.ofNullable(authMapper.userDuplicate(param));
        System.out.println(optional);
        return optional;

    }

    @Override
    public Info login(Info param) throws Exception {
//        Info result = authMapper.userInfo(param);

        return null;
    }
}

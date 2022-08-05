package jungmin.board.service;

import jungmin.board.domain.Content;
import jungmin.board.mapper.AuthMapper;
import jungmin.board.mapper.ContentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@RequiredArgsConstructor
@Log
@Service
public class ContentServiceImpl implements ContentService{

    private final ContentMapper contentMapper;
    @Override
    public List<Content> contentList() throws Exception {
        return contentMapper.contentList();
    }

    @Override
    public Content contentDetail(String param) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("contentId", param);
        return contentMapper.contentDetail(map);
    }
}

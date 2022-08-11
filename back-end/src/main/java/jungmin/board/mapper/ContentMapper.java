package jungmin.board.mapper;

import jungmin.board.domain.Content;
import jungmin.board.domain.Info;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface ContentMapper {

    // 게시글 목록 가져오기
    public List<Content> contentList() throws Exception;

    public Content contentDetail(HashMap<String, Object> param) throws Exception;

    public List<Content> contentComment(HashMap<String, Object> param) throws Exception;

    public List<Content> contentSubComment(HashMap<String, Object> param) throws Exception;

    public int contentInsert(HashMap<String, Object> param) throws Exception;
}

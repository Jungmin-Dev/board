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
    @Override
    public int commentDelete(String param) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("commentId", param);
        return contentMapper.commentDelete(map);
    }

    @Override
    public int commentUpdate(Content param) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userEmail", param.getUserEmail());
        map.put("context", param.getContext());
        map.put("contentId", param.getContentId());
        map.put("commentId", param.getCommentId());
        return contentMapper.commentUpdate(map);
    }

    @Override
    public int subCommentDelete(String param) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("subCommentId", param);
        return contentMapper.subCommentDelete(map);
    }

    @Override
    public int subCommentUpdate(Content param) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userEmail", param.getUserEmail());
        map.put("context", param.getContext());
        map.put("contentId", param.getContentId());
        map.put("commentId", param.getCommentId());
        map.put("subCommentId", param.getSubCommentId());
        return contentMapper.subCommentUpdate(map);
    }

    @Override
    public int commentCreate(Content param) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userEmail", param.getUserEmail());
        map.put("context", param.getContext());
        map.put("contentId", param.getContentId());
        return contentMapper.commentCreate(map);
    }

    @Override
    public int commentSubCreate(Content param) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userEmail", param.getUserEmail());
        map.put("context", param.getContext());
        map.put("contentId", param.getContentId());
        map.put("commentId", param.getCommentId());
        return contentMapper.commentSubCreate(map);
    }

    @Override
    public int contentUpdate(Content param) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userEmail", param.getUserEmail());
        map.put("title", param.getTitle());
        map.put("context", param.getContext());
        map.put("contentId", param.getContentId());
        return contentMapper.contentUpdate(map);
    }

    private final ContentMapper contentMapper;

    @Override
    public int contentInsert(Content param) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userEmail", param.getUserEmail());
        map.put("title", param.getTitle());
        map.put("context", param.getContext());
        return contentMapper.contentInsert(map);
    }

    @Override
    public int contentDelete(String param) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("contentId", param);
        return contentMapper.contentDelete(map);
    }

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

    @Override
    public List<Content> contentComment(String param) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("contentId", param);
        return contentMapper.contentComment(map);
    }

    @Override
    public List<Content> contentCommentSub(String param) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("contentId", param);
        return contentMapper.contentSubComment(map);
    }
}

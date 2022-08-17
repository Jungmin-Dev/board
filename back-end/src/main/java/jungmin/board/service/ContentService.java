package jungmin.board.service;

import jungmin.board.domain.Content;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface ContentService {
    public List<Content> contentList() throws Exception;
    public Content contentDetail(String param) throws Exception;
    public List<Content> contentComment(String param) throws Exception;
    public List<Content> contentCommentSub(String param) throws Exception;
    public int contentInsert(Content param) throws Exception;
    public int contentDelete(String param) throws Exception;
    public int contentUpdate(Content param) throws Exception;
    public int commentCreate(Content param) throws Exception;
    public int commentSubCreate(Content param) throws Exception;
    public int commentDelete(String param) throws Exception;
    public int commentUpdate(Content param) throws Exception;
    public int subCommentDelete(String param) throws Exception;
    public int subCommentUpdate(Content param) throws Exception;

}

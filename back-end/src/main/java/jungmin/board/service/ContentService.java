package jungmin.board.service;

import jungmin.board.domain.Content;
import jungmin.board.domain.FileDownLoad;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ContentService {
    public List<Content> contentList() throws Exception;
    public List<Content> contentDetail(String param) throws Exception;
    public List<Content> contentComment(String param) throws Exception;
    public List<Content> contentCommentSub(String param) throws Exception;
    public int contentInsert(MultipartHttpServletRequest request, Map<String, Object> param) throws Exception;
    public int contentDelete(String param) throws Exception;
    public int contentUpdate(Content param) throws Exception;
    public int commentCreate(Content param) throws Exception;
    public int commentSubCreate(Content param) throws Exception;
    public int commentDelete(String param) throws Exception;
    public int commentUpdate(Content param) throws Exception;
    public int subCommentDelete(String param) throws Exception;
    public int subCommentUpdate(Content param) throws Exception;
    public ResponseEntity<byte[]> fileDownLoad(FileDownLoad param) throws Exception;

}

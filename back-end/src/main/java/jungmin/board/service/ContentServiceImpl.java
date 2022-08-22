package jungmin.board.service;

import jungmin.board.domain.Content;
import jungmin.board.mapper.AuthMapper;
import jungmin.board.mapper.ContentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.*;

@RequiredArgsConstructor
@Log
@Service
public class ContentServiceImpl implements ContentService{

    private final ContentMapper contentMapper;
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



    @Override
    public int contentInsert(MultipartHttpServletRequest request, Map<String, Object> param) throws Exception {
        Iterator<String> fileNames = request.getFileNames();
        String path = "C://Users//kjm//Desktop//test//";
        int contentId = contentMapper.fileInfoContentId();

        while(fileNames.hasNext()){
            String fileName = fileNames.next();
            List<MultipartFile> mFile = request.getFiles(fileName);
            for (MultipartFile mf : mFile){
                Map<String ,Object> fileUpLoad = new HashMap<>();
                UUID uuid = UUID.randomUUID();
                String originalFilename = mf.getOriginalFilename();
                fileUpLoad.put("contentId", contentId);
                fileUpLoad.put("uuid", uuid.toString());
                fileUpLoad.put("fileName", originalFilename);
                fileUpLoad.put("fileSize", mf.getSize());
                contentMapper.fileInfo(fileUpLoad);
                String safeFile = path + uuid;
                try {
                    mf.transferTo(new File(safeFile));
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        contentMapper.contentInsert(param);
        return 1;
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

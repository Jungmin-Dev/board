package jungmin.board.service;

import jungmin.board.domain.Content;
import jungmin.board.domain.FileDownLoad;
import jungmin.board.mapper.AuthMapper;
import jungmin.board.mapper.ContentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public ResponseEntity<byte[]> fileDownLoad(String param) throws Exception {
//        String fileRoot = "C:/Users/kjm/Desktop/test/"; // 회사
        String fileRoot = "C:\\test\\"; // 집
        // 확장자 찾기
        Path path = Paths.get(fileRoot + param);
        String contentType = Files.probeContentType(path);

        //서버의 파일을 다운로드하기 위한 스트림
        InputStream in = null; // java.io
        ResponseEntity<byte[]> entity = null;
        try {
            // 헤더 구성성 객체
            HttpHeaders headers = new HttpHeaders();
            // InputStream 생성
            in = new FileInputStream(fileRoot + param);

            // 다운로드용 컨텐트 타입
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            // 파일 타입
            headers.add(HttpHeaders.CONTENT_TYPE, contentType);

            // 큰 따옴표 내부에 " \"
            // 바이트배열을 스트링으로
            // iso-8859-1 서유럽언어
            // new String(fileName.getBytes("utf-8"), "iso-8859-1")
            headers.add("Content-Disposition", "attachment; filename=\"" + param + "\"");

            // 바이트 배열, 헤더
            entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
        } finally {
            if (in != null)
                in.close(); // 스트림 닫기
        }
        return entity;
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
//        String path = "C://Users//kjm//Desktop//test//";
        String path = "C:\\test\\"; // 집
        int contentId = contentMapper.fileInfoContentId();
        contentMapper.contentInsert(param);
        while(fileNames.hasNext()){
            String fileName = fileNames.next();
            List<MultipartFile> mFile = request.getFiles(fileName);
            for (MultipartFile mf : mFile){
                Map<String ,Object> fileUpLoad = new HashMap<>();
                String prefix = mf.getOriginalFilename().substring(mf.getOriginalFilename().lastIndexOf(".") +1 , mf.getOriginalFilename().length());
                UUID uuid = UUID.randomUUID();
                String originalFilename = mf.getOriginalFilename();
                fileUpLoad.put("contentId", contentId);
                fileUpLoad.put("uuid", uuid + "." + prefix);
                fileUpLoad.put("fileName", originalFilename);
                fileUpLoad.put("fileSize", mf.getSize());
                contentMapper.fileInfo(fileUpLoad);
                String safeFile = path + uuid + "." + prefix;
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
    public List<Content> contentDetail(String param) throws Exception {
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

package jungmin.board.controller;

import jungmin.board.domain.Content;
import jungmin.board.domain.Info;
import jungmin.board.service.AuthService;
import jungmin.board.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping(value = "/content")
@Controller
@CrossOrigin(origins="*")
public class ContentController {

    private final ContentService contentService;

    // 게시글 목록 가져오기
    @RequestMapping(value="/list", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> list() throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("contentList",contentService.contentList());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    // 게시글 내용 자세히 가져오기(게시글 클릭 시)
    @RequestMapping(value="/detail/{contentId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> detail(@PathVariable String contentId) throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("contentList",contentService.contentDetail(contentId));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    // 게시글 댓글 가져오기
    @RequestMapping(value="/comment/{contentId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> comment(@PathVariable String contentId) throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("contentCommentList",contentService.contentComment(contentId));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    // 게시글 대댓글 가져오기
    @RequestMapping(value="/commentsub/{contentId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> commentSub(@PathVariable String contentId) throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("contentCommentSubList",contentService.contentCommentSub(contentId));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    // 게시글 작성
    @RequestMapping(value="/insert", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> insert(@RequestBody Content param) throws Exception{
        contentService.contentInsert(param);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 게시글 삭제
    @RequestMapping(value="/delete/{contentId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> delete(@PathVariable String contentId) throws Exception{
        contentService.contentContentDelete(contentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    // 게시글 작성
    @RequestMapping(value="/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> update(@RequestBody Content param) throws Exception{
        contentService.contentUpdate(param);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

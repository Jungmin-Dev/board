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

    // 게시글 작성
    @RequestMapping(value="/insert", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> insert(@RequestBody Content param) throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("contentInsert",contentService.contentInsert(param));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}

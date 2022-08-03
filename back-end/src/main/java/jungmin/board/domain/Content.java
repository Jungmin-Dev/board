package jungmin.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Content {
    private String contentId; // 게시판 번호
    private String userEmail; // 이메일(아이디)
    private String Title; // 게시판 제목
    private String createdAt; // 작성일
    private String updatedAt; // 수정일
    private String Context; // 게시글 내용
    private String commentId; // 댓글 번호
    private String subCommentId; // 대댓글 번호
}

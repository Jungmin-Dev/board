package jungmin.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Info {
    private String userId;
    private String userPassword;
    private String Name;
    private String createdAt;
    private String Email;
}

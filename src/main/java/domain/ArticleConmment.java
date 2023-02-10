package domain;

import java.time.LocalDateTime;

public class ArticleConmment {
    private long id;
    private Article article; // 게시글 (id)
    private String content; // 내용 (본문)

    private LocalDateTime createdAt; // 생성일시
    private String createBy; //생성자
    private LocalDateTime modifiedAt; //수정일시
    private String modifiedBy; //수정자
}

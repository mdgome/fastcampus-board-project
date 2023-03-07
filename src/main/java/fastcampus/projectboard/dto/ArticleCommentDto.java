package fastcampus.projectboard.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link fastcampus.projectboard.domain.ArticleComment} entity
 */
public record ArticleCommentDto(
        Long articleId,
        Long id,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy,
        String content
){
    public static ArticleCommentDto of(Long articleId, Long id, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy, String content) {
        return new ArticleCommentDto(articleId, id, createdAt, createdBy, modifiedAt, modifiedBy, content);
    }

    public Long articleId() {
        return articleId;
    }

    public Long id() {
        return id;
    }
}
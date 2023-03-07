package fastcampus.projectboard.service;

import fastcampus.projectboard.domain.Article;
import fastcampus.projectboard.domain.ArticleComment;
import fastcampus.projectboard.dto.ArticleCommentDto;
import fastcampus.projectboard.repository.ArticleCommentRepository;
import fastcampus.projectboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;


@DisplayName("비즈니스 로직 - 댓글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {

    @InjectMocks private ArticleCommentService sut;

    @Mock private ArticleCommentRepository articleCommentRepository;
    @Mock private ArticleRepository articleRepository;

    @DisplayName("게시글 ID 조회, 해당 댓글 리스트 반환")
    @Test
    void givenArticleId_whenSearchingComments_thenReturnsComments() {
        // Given
        Long articleId = 1L;
        given(articleRepository.findById(articleId)).willReturn(Optional.of(
                Article.of("title","content","#java")));

        // When
        List<ArticleCommentDto> articleComments =  sut.searchArticleComment(articleId);

        // Then
        assertThat(articleComments).isNotNull();
        then(articleRepository).should().findById(articleId);
    }

    @DisplayName("댓글 정보 입력, 댓글 저장")
    @Test
    void givenCommentsInfo_whenSavingComment_thenReturnsComments() {
        // Given
        ArticleCommentDto dto = createArticleCommentDto("댓글");
        given(articleRepository.getReferenceById(dto.articleId())).willReturn(createArticle());
        given(articleCommentRepository.save(any(ArticleComment.class))).willReturn(null);

        // When
        sut.saveArticleComment(dto);

        // Then
        then(articleRepository).should().getReferenceById(dto.articleId());
        then(articleCommentRepository).should().save(any(ArticleComment.class));
    }

    @DisplayName("댓글 저장 시도 실패, 경고 로그 이후 행동 없음")
    @Test
    void givenNonExistentArticle_whenSavingArticleComment_thenLogsSituationAndDoesNothing() {
        // Given
        ArticleCommentDto dto = createArticleCommentDto("댓글");
        given(articleRepository.getReferenceById(dto.articleId())).willThrow(EntityNotFoundException.class);

        // When
        sut.saveArticleComment(dto);

        // Then
        then(articleRepository).should().getReferenceById(dto.articleId());
        then(articleCommentRepository).shouldHaveNoInteractions();
    }

    @DisplayName("댓글 정보 입력, 댓굴 수정")
    @Test
    void givenArticleCommentInfo_whenUpdateArticleComment_thenUpdatesArticleComment() {
        // Given
        String oldContent = "오래된 댓글";
        String updateContent = "댓글";
        ArticleComment articleComment = createArticleComment(oldContent);
        ArticleCommentDto dto = createArticleCommentDto(updateContent);
        given(articleCommentRepository.getReferenceById(dto.id())).willReturn(articleComment);
        // When
        sut.updateArticleComment(dto);

        // Then
        assertThat(articleComment.getContent())
                .isNotEqualTo(oldContent)
                .isEqualTo(updateContent);
        then(articleCommentRepository).should().getReferenceById(dto.id());
    }

    @DisplayName("없은 댓글 수정 시, 경고 로그 이후 행동 없음")
    @Test
    void givenNonExistentArticleComment_whenUpdatingArticleComment_thenLogsWarningAndDoesNothing() {
        // Given
        ArticleCommentDto dto = createArticleCommentDto("댓글");
        given(articleCommentRepository.getReferenceById(dto.id())).willThrow(EntityNotFoundException.class);
        // When
        sut.updateArticleComment(dto);
        // Then
        then(articleCommentRepository).should().getReferenceById(dto.id());
    }

    @DisplayName("댓글 ID 입력, 댓글 삭제")
    @Test
    void givenArticleCommentId_whenDeletingArticleComment_thenDeletesArticleComment() {
        // Given
        Long articleCommentId = 1L;
        willDoNothing().given(articleCommentRepository).deleteById(articleCommentId);

        // When
        sut.deleteArticleComment(articleCommentId);

        // Then
        then(articleCommentRepository).should().deleteById(articleCommentId);
    }

    private ArticleComment createArticleComment(String oldContent) {
        return ArticleComment.of(
                Article.of("title","content","hashtag")
                , oldContent
        );
    }

    private Article createArticle() {
        return Article.of(
                "title"
                ,"content"
                ,"#java"
        );
    }

    private ArticleCommentDto createArticleCommentDto(String content) {
        return ArticleCommentDto.of(1L,1L,LocalDateTime.now(),"mdgome",null,"mdgome",content);
    }


}
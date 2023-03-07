package fastcampus.projectboard.service;

import fastcampus.projectboard.domain.Article;
import fastcampus.projectboard.domain.type.SearchType;
import fastcampus.projectboard.dto.ArticleDto;
import fastcampus.projectboard.dto.ArticleUpdateDto;
import fastcampus.projectboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks private ArticleService sut;                     //system under test
    @Mock private ArticleRepository articleRepository;

    @DisplayName("게시글 검색: 게시글 리스트 반환")
    @Test
    void givenSearchParameters_whenSearchingArticles_thenReturnArticleList() {
        // Given

        // When
        Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "search keyword");   // 제목, 본문, Id, 닉네임, 해시태그
        // Then
        assertThat(articles).isNotNull();
    }

    @DisplayName("게시글 조회: 게시글 반환")
    @Test
    void givenId_whenSearchingArticle_thenReturnArticle() {
        // Given

        // When
        ArticleDto articles = sut.searchArticle(1L);   // 제목, 본문, Id, 닉네임, 해시태그
        // Then
        assertThat(articles).isNotNull();
    }

    @DisplayName("게시글 정보 입력, 게시글 생성")
    @Test
    void givenArticleInfo_whenSavingArticle_thenSavesArticle() {
        // Given
        given(articleRepository.save(any(Article.class))).willReturn(null);

        // When
        sut.saveArticle(ArticleDto.of(LocalDateTime.now(), "mdgome", "title", "content","#java"));

        // Then
        then(articleRepository).should().save(any(Article.class));

    }

    @DisplayName("게시글 ID와 수정 정보 입력, 게시글 수정")
    @Test
    void givenArticleIdAndInfo_whenUpdatingArticle_thenUpdatesArticle() {
        // Given
        given(articleRepository.save(any(Article.class))).willReturn(null);

        // When
        sut.updateArticle(1L, ArticleUpdateDto.of("title", "content","#java"));

        // Then
        then(articleRepository).should().save(any(Article.class));

    }

    @DisplayName("게시글 ID 입력, 게시글 삭제")
    @Test
    void givenArticleId_whenDeletingArticle_thenDeletesArticle() {
        // Given
        willDoNothing().given(articleRepository).delete(any(Article.class));

        // When
        sut.deleteArticle(1L);

        // Then
        then(articleRepository).should().delete(any(Article.class));

    }

}

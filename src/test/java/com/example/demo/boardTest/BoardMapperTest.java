package com.example.demo.boardTest;

import com.demo.domain.board.dto.BoardRequest;
import com.demo.domain.board.entity.Board;
import com.demo.domain.board.mapper.BoardMapper;
import com.demo.domain.board.model.BoardPageData;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
@DisplayName("Mapper 테스트")
public class BoardMapperTest {

    @Mock
    private BoardMapper boardMapper;

    private static List<Board> boards;
    private static List<BoardPageData> boardPageData;

    @BeforeAll
    static void setUp() {
        boards = Arrays.asList(
                Board.builder().id(1L).title("제목1").content("내용1").build(),
                Board.builder().id(2L).title("제목2").content("내용2").build()
        );

        boardPageData = Arrays.asList(
                new BoardPageData(1L, "제목1", "내용1"),
                new BoardPageData(2L, "제목2", "내용2")
        );
    }

    @Test
    @DisplayName("게시글 저장 테스트")
    public void testSaveBoard() {
        // given
        Long id = 1L;
        String title = "제목1";
        String content = "내용1";

        Board board = Board.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();

        // when
        boardMapper.save(board);

        // then
        verify(boardMapper, times(1)).save(board);

        log.info("** 게시글 저장 **");
        log.info(board.getTitle());
        log.info(board.getContent());
        log.info("--------------------------------------------");
    }

    @Test
    @DisplayName("게시글 전체 조회 테스트")
    void testFindAllBoards() {
        // given
        when(boardMapper.findAllBoards()).thenReturn(boardPageData);

        // when
        List<BoardPageData> result = boardMapper.findAllBoards();

        // then
        verify(boardMapper).findAllBoards();

        log.info("** 게시글 전체 조회 **");
        for (BoardPageData board : result) {
            log.info("게시글 제목: {}", board.getTitle());
            log.info("게시글 내용: {}", board.getContent());
        }
        log.info("--------------------------------------------");
    }

    @Test
    @DisplayName("검색어로 조회 테스트")
    void testFindBySearch() {
        // given
        String searchKeyword = "제목2";

        when(boardMapper.findBoardsBySearch(eq(searchKeyword))).thenReturn(Collections.singletonList(boardPageData.get(1)));

        // when
        List<BoardPageData> result = boardMapper.findBoardsBySearch(searchKeyword);

        // then
        verify(boardMapper).findBoardsBySearch(searchKeyword);

        log.info("** 검색어로 페이징 조회 **");
        for (BoardPageData board : result) {
            log.info("게시글 제목: {}", board.getTitle());
            log.info("게시글 내용: {}", board.getContent());
        }
        log.info("--------------------------------------------");
    }

    @Test
    @DisplayName("게시글 번호별 조회 테스트")
    void testFindBoardById() {
        // given
        Long boardId = 1L;
        Map<String, Object> boardMap = new HashMap<>();
        boardMap.put("id", boardId);
        boardMap.put("title", "제목1");
        boardMap.put("content", "내용1");

        // when
        Map<String, Object> result = boardMapper.findBoardById(boardId);

        // then
        verify(boardMapper).findBoardById(boardId);

        log.info("** 게시글 번호별 조회 **");
        log.info("게시글 제목: {}", result.get("title"));
        log.info("게시글 내용: {}", result.get("content"));
        log.info("--------------------------------------------");
    }

    @Test
    @DisplayName("게시글 수정 테스트")
    void testUpdateBoard() {
        // given
        Long boardId = 1L;
        BoardRequest boardRequest = BoardRequest.builder()
                .title("수정된 제목")
                .content("수정된 내용")
                .build();

        when(boardMapper.updateBoard(eq(boardId), eq(boardRequest))).thenReturn(1);

        // when
        int result = boardMapper.updateBoard(boardId, boardRequest);

        // then
        verify(boardMapper).updateBoard(eq(boardId), eq(boardRequest));

        log.info("** 게시글 수정 **");
        log.info("수정 결과: {}", result);

        assertEquals(1, result);
    }

    @Test
    @DisplayName("게시글 삭제 테스트")
    void testDeleteBoard() {
        // given
        Long boardId = 1L;

        when(boardMapper.changeUseYnWhenDeleted(eq(boardId))).thenReturn(1);

        // when
        int result = boardMapper.changeUseYnWhenDeleted(boardId);

        // then
        verify(boardMapper).changeUseYnWhenDeleted(eq(boardId));

        log.info("** 게시글 삭제 **");
        log.info("삭제 결과: {}", result);
        log.info("--------------------------------------------");
    }
}
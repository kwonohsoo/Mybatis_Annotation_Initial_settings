package com.example.demo.boardTest;

import com.demo.domain.board.dto.BoardPageRequest;
import com.demo.domain.board.dto.BoardRequest;
import com.demo.domain.board.entity.Board;
import com.demo.domain.board.mapper.BoardMapper;
import com.demo.domain.board.model.BoardPageData;
import com.demo.domain.board.service.BoardService;
import com.demo.global.error.exception.InvalidValueException;
import com.demo.global.values.CommonValue;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@Slf4j
@DisplayName("Service 테스트")
public class BoardServiceTest {

    @InjectMocks
    private BoardService boardService;

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
                new BoardPageData(2L, "제목2", "내용2"),
                new BoardPageData(3L, "제목3", "내용3"),
                new BoardPageData(4L, "제목4", "내용4"),
                new BoardPageData(5L, "제목5", "내용5"),
                new BoardPageData(6L, "제목6", "내용6"),
                new BoardPageData(7L, "제목7", "내용7"),
                new BoardPageData(8L, "제목8", "내용8"),
                new BoardPageData(9L, "제목9", "내용9"),
                new BoardPageData(10L, "제목10", "내용10"),
                new BoardPageData(11L, "제목11", "내용11"),
                new BoardPageData(12L, "제목12", "내용12"),
                new BoardPageData(13L, "제목13", "내용13")
        );
    }

    @Test
    @DisplayName("게시글 저장 테스트")
    public void testSaveBoard() {
        // given
        BoardRequest boardRequest = new BoardRequest("새로운 제목", "새로운 내용");
        Board board = boardRequest.createBoardEntity();

        ArgumentCaptor<Board> boardCaptor = ArgumentCaptor.forClass(Board.class);

        // when
        boardService.save(boardRequest);

        // then
        verify(boardMapper, times(1)).save(boardCaptor.capture());

        Board capturedBoard = boardCaptor.getValue();

        assertNotNull(capturedBoard);
        assertEquals(board.getId(), capturedBoard.getId());
        assertEquals(board.getTitle(), capturedBoard.getTitle());
        assertEquals(board.getContent(), capturedBoard.getContent());
        assertNotNull(capturedBoard.getCreatedDt());

        log.info("** 게시글 저장 **");
        log.info("ID: {}", capturedBoard.getId());
        log.info("Title: {}", capturedBoard.getTitle());
        log.info("Content: {}", capturedBoard.getContent());
        log.info("CreatedDt: {}", capturedBoard.getCreatedDt());
    }

    @Test
    @DisplayName("게시글 전체 조회 테스트")
    void testFindAllBoards() {
        // given
        when(boardMapper.findAllBoards()).thenReturn(boardPageData);

        // when
        List<Map<String, Object>> result = boardService.getAll();

        // then
        assertEquals(boardPageData.size(), result.size());

        // 각 항목별로 일치하는지 확인
        for (int i = 0; i < boardPageData.size(); i++) {
            BoardPageData mockBoard = boardPageData.get(i);
            Map<String, Object> resultMap = result.get(i);

            assertEquals(mockBoard.getId(), resultMap.get("id"));
            assertEquals(mockBoard.getTitle(), resultMap.get("title"));
            assertEquals(mockBoard.getContent(), resultMap.get("content"));

            // 예상 시간 <-> 실제 시간 차이가 1초 이내인지 확인
            LocalDateTime expectedCreatedDt = mockBoard.getCreatedDt();
            LocalDateTime actualCreatedDt = (LocalDateTime) resultMap.get("createdDt");
            if (expectedCreatedDt != null && actualCreatedDt != null) {
                assertTrue(Duration.between(expectedCreatedDt, actualCreatedDt).getSeconds() <= 1);
            }

            LocalDateTime expectedModifiedDt = mockBoard.getModifiedDt();
            LocalDateTime actualModifiedDt = (LocalDateTime) resultMap.get("modifiedDt");
            if (expectedModifiedDt != null && actualModifiedDt != null) {
                assertTrue(Duration.between(expectedModifiedDt, actualModifiedDt).getSeconds() <= 1);
            }

            log.info("** 게시글 조회 **");
            log.info("ID: {}", resultMap.get("id"));
            log.info("Title: {}", resultMap.get("title"));
            log.info("Content: {}", resultMap.get("content"));
            log.info("CreatedDt: {}", resultMap.get("createdDt"));
            log.info("ModifiedDt: {}", resultMap.get("modifiedDt"));
        }
    }

    @Test
    @DisplayName("검색어 페이징 조회 테스트")
    void testBoardPageWithSearch() {
        // given
        BoardPageRequest request = new BoardPageRequest("제목", "id DESC");
        List<BoardPageData> boardPageDataList = boardPageData;

        // Mock 데이터 설정
        when(boardMapper.findBoardsBySearch(anyString())).thenReturn(boardPageDataList);

        // when
        List<PageInfo<BoardPageData>> result = boardService.boardPageWithSearch(request);

        // then
        assertNotNull(result);
        assertEquals(1, result.size());

        verify(boardMapper, times(1)).findBoardsBySearch(anyString());

        log.info("Mock Data: {}", boardPageDataList);
        log.info("Result Data: {}", result);
    }

    @Test
    @DisplayName("게시글 번호별 조회 테스트")
    void testFindBoardById() {
        // given
        Long id = 1L;

        Map<String, Object> boardData = new HashMap<>();
        boardData.put("id", id);

        when(boardMapper.findBoardById(id)).thenReturn(boardData);
        // existsBooleanId 호출 시 항상 true를 반환
        when(boardMapper.existsBooleanId(id)).thenReturn(true);

        // when
        Map<String, Object> result = boardService.findBoardById(id);

        // then
        assertNotNull(result);
        assertEquals(boardData, result);

        // 예외를 던지지 않아야 하는 경우
        verify(boardMapper, times(1)).existsBooleanId(id);

        log.info(result.toString());
    }

    @Test
    @DisplayName("게시글 번호별 조회 - 게시글이 존재하지 않는 경우")
    void testFindBoardByIdNotFound() {
        // given
        Long id = 1L;

        // existsBooleanId 호출 시 항상 false를 반환
        when(boardMapper.existsBooleanId(id)).thenReturn(false);

        // when & then
        assertThrows(InvalidValueException.class, () -> boardService.findBoardById(id));

        verify(boardMapper, times(1)).existsBooleanId(id);
    }

    @Test
    @DisplayName("게시글 수정 테스트 - 정상적인 경우")
    void testUpdateBoard() {
        // given
        Long id = 1L;
        BoardRequest request = BoardRequest.builder()
                .title("Updated Title")
                .content("Updated Content")
                .build();

        // existsBooleanId 호출 시 항상 true를 반환
        when(boardMapper.existsBooleanId(id)).thenReturn(true);

        // boardMapper.updateBoard 호출 시 항상 1을 반환
        when(boardMapper.updateBoard(id, request)).thenReturn(1);

        // when
        int updatedCnt = boardService.updateBoard(id, request);

        // then
        assertEquals(1, updatedCnt);
        verify(boardMapper, times(1)).existsBooleanId(id);
        verify(boardMapper, times(1)).updateBoard(id, request);
    }

    @Test
    @DisplayName("게시글 수정 테스트 - 게시글이 존재하지 않는 경우")
    void testUpdateBoardNotFound() {
        // given
        Long id = 1L;
        BoardRequest request = BoardRequest.builder()
                .title("Updated Title")
                .content("Updated Content")
                .build();

        // existsBooleanId 호출 시 항상 false를 반환하도록 스텁
        when(boardMapper.existsBooleanId(id)).thenReturn(false);

        // when & then
        assertThrows(InvalidValueException.class, () -> boardService.updateBoard(id, request));

        // 예외를 던져야 하는 경우
        verify(boardMapper, times(1)).existsBooleanId(id);
    }

    @Test
    @DisplayName("게시글 수정 테스트 - 업데이트 실패")
    void testUpdateBoardFail() {
        // given
        Long id = 1L;
        BoardRequest request = BoardRequest.builder()
                .title("Updated Title")
                .content("Updated Content")
                .build();

        // existsBooleanId 호출 시 항상 true를 반환하도록 스텁
        when(boardMapper.existsBooleanId(id)).thenReturn(true);

        // boardMapper.updateBoard 호출 시 항상 0을 반환하도록 스텁
        when(boardMapper.updateBoard(id, request)).thenReturn(0);

        // when & then
        assertThrows(InvalidValueException.class, () -> boardService.updateBoard(id, request));

        verify(boardMapper, times(1)).existsBooleanId(id);
        verify(boardMapper, times(1)).updateBoard(id, request);
    }

    @Test
    @DisplayName("게시글 삭제 테스트")
    void testChangeUseYnWhenDeleted() {
        // given
        Long id = 1L;

        // existsBooleanId 호출 시 항상 true를 반환
        when(boardMapper.existsBooleanId(id)).thenReturn(true);

        // findById 호출 시 항상 삭제된 상태의 게시글을 나타내는 Optional<Board>를 반환
        Board boardWithDeletedStatus = new Board();
        boardWithDeletedStatus.setUseYn(CommonValue.SEARCH_USE_YN);
        when(boardMapper.findById(id)).thenReturn(Optional.of(boardWithDeletedStatus));

        // boardMapper.changeUseYnWhenDeleted 호출 시 항상 1을 반환
        when(boardMapper.changeUseYnWhenDeleted(id)).thenReturn(1);

        log.info(String.valueOf(boardWithDeletedStatus.getUseYn()));

        // when
        assertDoesNotThrow(() -> {
            Board board = Board.builder().build();
            board.setUseYn(CommonValue.DELETE_USE_YN);
            boardService.changeUseYnWhenDeleted(id);
            assertEquals(CommonValue.DELETE_USE_YN, board.getUseYn());

            log.info(String.valueOf(board.getUseYn()));
        });

        // then
        verify(boardMapper, times(1)).existsBooleanId(id);
        verify(boardMapper, times(1)).findById(id);
        verify(boardMapper, times(1)).changeUseYnWhenDeleted(id);

    }
}

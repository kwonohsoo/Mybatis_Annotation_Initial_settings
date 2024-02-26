package com.demo.domain.board.service;

import com.demo.domain.board.dto.BoardPageRequest;
import com.demo.domain.board.dto.BoardRequest;
import com.demo.domain.board.dto.BoardResponse;
import com.demo.domain.board.entity.Board;
import com.demo.domain.board.mapper.BoardMapper;
import com.demo.domain.board.model.BoardPageData;
import com.demo.global.error.code.CommonErrorCode;
import com.demo.global.error.exception.InvalidValueException;
import com.demo.global.values.CommonValue;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    // 게시글 작성
    public void save(BoardRequest boardRequest) {
        // 제목 및 내용이 비어있는지 확인
        if (!StringUtils.hasText(boardRequest.getTitle())) {
            throw new InvalidValueException(CommonErrorCode.EMPTY_TITLE_ERROR);
        }

        if (!StringUtils.hasText(boardRequest.getContent())) {
            throw new InvalidValueException(CommonErrorCode.EMPTY_CONTENT_ERROR);
        }

        Board board = boardRequest.createBoardEntity();
        boardMapper.save(board);

        BoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .createdDt(board.getCreatedDt())
                .build();
    }

    // 게시글 전체 조회
    public List<Map<String, Object>> getAll() {
        return boardMapper.findAllBoards().stream()
                .map(board -> {
                    Map<String, Object> boardMap = new HashMap<>();
                    boardMap.put("id", board.getId());
                    boardMap.put("title", board.getTitle());
                    boardMap.put("content", board.getContent());
                    boardMap.put("createdDt", board.getCreatedDt());
                    boardMap.put("modifiedDt", board.getModifiedDt());
                    return boardMap;
                })
                .collect(Collectors.toList());
    }

    // 검색어로 페이징
    public List<PageInfo<BoardPageData>> boardPageWithSearch(BoardPageRequest boardPageRequest) {
        // 페이지 및 검색 조건 설정
        int pageNum = boardPageRequest.getPageNum();
        int pageSize = boardPageRequest.getPageSize();
        String search = boardPageRequest.getSearch();
        String orderBy = boardPageRequest.getOrderBy();

        // 페이징 설정 및 비동기 카운트 비활성화
        PageHelper.startPage(pageNum, pageSize).disableAsyncCount();

        // 정렬 조건이 있는 경우 적용
        if (StringUtils.hasText(orderBy)) {
            PageHelper.orderBy(orderBy);
        }

        // 검색어에 따라 게시글 조회
        List<BoardPageData> boardPageData = StringUtils.hasText(search) ?
                boardMapper.findBoardsBySearch(search) :
                boardMapper.findAllBoards();

        // 페이지 정보 포함하여 반환
        return Collections.singletonList(new PageInfo<>(boardPageData));
    }

    // 게시글 번호별 조회(상세조회)
    public Map<String, Object> findBoardById(Long id) {
        // 존재하지 않는 게시글인지 확인
        if (!boardMapper.existsBooleanId(id)) {
            throw new InvalidValueException(CommonErrorCode.POST_NOT_FOUND);
        }

        // 게시글 조회
        return boardMapper.findBoardById(id);
    }

    // 게시글 수정
    public int updateBoard(Long id, BoardRequest boardRequest) {
        // 게시글 조회
        Board existingBoard = boardMapper.findById(id)
                .orElseThrow(() -> new InvalidValueException(CommonErrorCode.POST_NOT_FOUND));

        // 삭제된 게시글인 경우 예외 처리
        if (existingBoard.getUseYn() == CommonValue.DELETE_USE_YN) {
            throw new InvalidValueException(CommonErrorCode.CANNOT_UPDATE_DELETED_POST);
        }

        // 제목이 없는 경우 예외 처리
        if (!StringUtils.hasText(boardRequest.getTitle())) {
            throw new InvalidValueException(CommonErrorCode.EMPTY_TITLE_ERROR);
        }

        // 내용이 없는 경우 예외 처리
        if (!StringUtils.hasText(boardRequest.getContent())) {
            throw new InvalidValueException(CommonErrorCode.EMPTY_CONTENT_ERROR);
        }

        // 게시글 수정
        int updatedCnt = boardMapper.updateBoard(id, boardRequest);

        // 업데이트가 실패한 경우 예외 처리
        if (updatedCnt <= 0) {
            throw new InvalidValueException(CommonErrorCode.UPDATE_FAIL);
        }

        return updatedCnt;
    }

    // 삭제 시 UseYn 0(사용) -> 9(사용 안함) 변경
    public void changeUseYnWhenDeleted(Long id) {
        // 존재하지 않는 게시글인 경우 예외 처리
        if (!boardMapper.existsBooleanId(id)) {
            throw new InvalidValueException(CommonErrorCode.POST_NOT_FOUND);
        }

        // 삭제된 게시글인 경우 예외 처리
        boardMapper.findById(id).ifPresent(board -> {
            if (board.getUseYn() == CommonValue.DELETE_USE_YN) {
                throw new InvalidValueException(CommonErrorCode.ALREADY_DELETED);
            }
        });

        // 게시글 삭제 여부 업데이트
        int updatedCnt = boardMapper.changeUseYnWhenDeleted(id);

        // 업데이트가 실패한 경우 예외 처리
        if (updatedCnt <= 0) {
            throw new InvalidValueException(CommonErrorCode.UPDATE_FAIL);
        }
    }
}

package com.demo.domain.board.controller;

import com.demo.domain.board.dto.BoardPageRequest;
import com.demo.domain.board.dto.BoardRequest;
import com.demo.domain.board.model.BoardPageData;
import com.demo.domain.board.service.BoardService;
import com.demo.global.dto.CommonResult;
import com.demo.global.dto.ResponseService;
import com.demo.global.dto.SingleResult;
import com.demo.global.message.CommonMessage;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@Tag(name = "게시판", description = "swagger Test 게시판")
public class BoardController {

    private final ResponseService responseService;
    private final BoardService boardService;

    @PostMapping(value = "/create")
    @Operation(summary = "등록", description = "swagger Test 게시글 등록")
    public CommonResult create(@RequestBody BoardRequest boardRequest) {
        boardService.save(boardRequest);
        return responseService.getSuccessResult(CommonMessage.CREATE_SUCCESS);
    }

    @PostMapping("/view/read")
    @Operation(summary = "전체 조회", description = "Swagger Test 게시판 전체 조회")
    public SingleResult<List<Map<String, Object>>> getAll() {
        return responseService.getSingleResult(boardService.getAll(), CommonMessage.SELECT_SUCCESS);
    }

    @PostMapping("/view/page")
    @Operation(summary = "게시판 페이징", description = "Swagger Test 게시판 페이징")
    public SingleResult<List<PageInfo<BoardPageData>>> page(@RequestBody BoardPageRequest boardPageRequest) {
        return responseService.getSingleResult(boardService.boardPageWithSearch(boardPageRequest), CommonMessage.SELECT_SUCCESS);
    }

    @PostMapping("/view/read/{id}")
    @Operation(summary = "게시글 번호별 조회", description = "swagger Test 게시판 번호별 조회")
    public SingleResult<Map<String, Object>> findBoardById(@PathVariable("id") Long id) {
        return responseService.getSingleResult(boardService.findBoardById(id), CommonMessage.SELECT_SUCCESS);
    }

    @PostMapping("/update/{id}")
    @Operation(summary = "수정", description = "swagger Test 게시글 수정")
    public CommonResult update(@PathVariable("id") Long id, @RequestBody BoardRequest boardRequest) {
        boardService.updateBoard(id, boardRequest);
        return responseService.getSuccessResult(CommonMessage.UPDATE_SUCCESS);
    }

    @PostMapping("/delete/UseYnChange/{id}")
    @Operation(summary = "삭제", description = "swagger Test 게시글 삭제(삭제 시 UseYn: 0(사용) -> 9(사용 안함)로 변경)")
    public CommonResult changeUseYnWhenDeleted(@PathVariable("id") Long id) {
        boardService.changeUseYnWhenDeleted(id);
        return responseService.getSuccessResult(CommonMessage.DELETE_SUCCESS);
    }
}



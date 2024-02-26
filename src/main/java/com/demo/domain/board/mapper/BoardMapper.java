package com.demo.domain.board.mapper;

import com.demo.domain.board.dto.BoardRequest;
import com.demo.domain.board.entity.Board;
import com.demo.domain.board.model.BoardPageData;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface BoardMapper {

    // 게시글 작성
    @Insert("""
            INSERT INTO board (id, title, content, createdDt)
            VALUES (#{id}, #{title}, #{content}, #{createdDt})
            """)
    void save(Board board);

    // 게시글 전체 조회
    @Select("""
            SELECT *
            FROM BOARD
            """)
    List<BoardPageData> findAllBoards();

    // 검색어로 페이징
    @Select("""
            SELECT *
            FROM BOARD
            WHERE title LIKE CONCAT('%', #{search}, '%') OR
                  content LIKE CONCAT('%', #{search}, '%') OR
                  createdDt = #{search} OR
                  modifiedDt = #{search}
                    """)
    List<BoardPageData> findBoardsBySearch(@Param("search") String search);

    // 게시글 번호별 조회
    @Select("""
            SELECT *
            FROM BOARD
            WHERE id = #{id}
            """)
    Map<String, Object> findBoardById(Long id);

    // 게시글 수정
    @Update("""
            UPDATE BOARD
            SET title = #{request.title}, content = #{request.content}, modifiedDt = now()
            WHERE id = #{id} AND useYn != 9
            """)
    int updateBoard(@Param("id") Long id, @Param("request") BoardRequest request);

    // 삭제 시 UseYn 0(사용 ) -> 9(사용 안함) 변경
    @Update("""
            UPDATE BOARD
            SET useYn = 9,
            modifiedDt = now()
            WHERE id = #{id}
            """)
    int changeUseYnWhenDeleted(Long id);

    // ID 존재 여부
    @Select("""
            SELECT COUNT(*)
            FROM BOARD
            WHERE id = #{id}
            """)
    boolean existsBooleanId(Long id);

    // Optional findById
    @Select("""
            SELECT *
            FROM BOARD
            WHERE id = #{id}
            """)
    Optional<Board> findById(Long id);

}

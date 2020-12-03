package com.example.site.board.dto;

import com.example.site.board.entity.Board;
import lombok.Getter;

@Getter
public class BoardResponseDto {
    private Long boardNum;
    private String boardTitle;
    private String boardWriter;
    private String boardContent;

    public BoardResponseDto(Board board) {
        this.boardNum = board.getBoardNum();
        this.boardTitle = board.getBoardTitle();
        this.boardWriter = board.getBoardWriter();
        this.boardContent = board.getBoardContent();
    }
}

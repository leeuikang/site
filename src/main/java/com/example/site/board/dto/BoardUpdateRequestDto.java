package com.example.site.board.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardUpdateRequestDto {
    private String boardTitle;
    private String boardContent;

    @Builder
    public BoardUpdateRequestDto(String boardTitle, String boardContent) {
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
    }
}

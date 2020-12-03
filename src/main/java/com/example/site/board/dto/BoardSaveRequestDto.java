package com.example.site.board.dto;

import com.example.site.board.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardSaveRequestDto {

    private String boardTitle;
    private String boardWriter;
    private String boardContent;

    @Builder
    public BoardSaveRequestDto(String boardTitle, String boardWriter, String boardContent) {
        this.boardTitle = boardTitle;
        this.boardWriter = boardWriter;
        this.boardContent = boardContent;
    }

    public Board toEntity(){
        return Board.builder()
                .boardTitle(boardTitle)
                .boardWriter(boardWriter)
                .boardContent(boardContent)
                .build();
    }
}

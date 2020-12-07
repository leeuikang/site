package com.example.site.board.service;

import com.example.site.board.dto.BoardResponseDto;
import com.example.site.board.dto.BoardSaveRequestDto;
import com.example.site.board.dto.BoardUpdateRequestDto;
import com.example.site.board.entity.Board;


import java.util.List;

public interface BoardService {

    public List<BoardResponseDto> findAll();

    public BoardResponseDto findById(Long boardNum);

    public Long save(BoardSaveRequestDto boardSaveRequestDto);

    public Long update(Long boardNum, BoardUpdateRequestDto boardUpdateRequestDto);

    public void delete(Long boardNum);

    public void hitUpdate(Board board);
}

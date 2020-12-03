package com.example.site.board.service;

import com.example.site.board.dto.BoardResponseDto;
import com.example.site.board.dto.BoardSaveRequestDto;
import com.example.site.board.dto.BoardUpdateRequestDto;
import com.example.site.board.entity.Board;
import com.example.site.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BoardResponseDto> findAll() {
        return boardRepository.findAll()
                .stream()
                .map(BoardResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BoardResponseDto findById(Long boardNum) {
        Board board = boardRepository.findById(boardNum)
                .orElseThrow(() -> new IllegalArgumentException("[boardnum = " + boardNum +"] 해당 게시글이 존재하지 않습니다."));

        return new BoardResponseDto(board);
    }

    @Override
    @Transactional
    public Long save(BoardSaveRequestDto boardSaveRequestDto) {
        return boardRepository.save(boardSaveRequestDto.toEntity())
                .getBoardNum();
    }

    @Override
    @Transactional
    public Long update(Long boardNum, BoardUpdateRequestDto boardUpdateRequestDto) {
        Board board = boardRepository.findById(boardNum)
                .orElseThrow(() -> new IllegalArgumentException("[boardnum = " + boardNum +"] 해당 게시글이 존재하지 않습니다."));

        board.update(boardUpdateRequestDto.getBoardTitle(), boardUpdateRequestDto.getBoardContent());

        return boardNum;
    }

    @Override
    @Transactional
    public void delete(Long boardNum) {
        Board board = boardRepository.findById(boardNum)
                .orElseThrow(() -> new IllegalArgumentException("[boardnum = " + boardNum +"] 해당 게시글이 존재하지 않습니다."));

        boardRepository.delete(board);
    }
}

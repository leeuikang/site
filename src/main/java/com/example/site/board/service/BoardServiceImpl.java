package com.example.site.board.service;

import com.example.site.board.dto.BoardResponseDto;
import com.example.site.board.dto.BoardSaveRequestDto;
import com.example.site.board.dto.BoardUpdateRequestDto;
import com.example.site.board.entity.Board;
import com.example.site.board.repository.BoardRepository;
import com.example.site.error.ErrorCode;
import com.example.site.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
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
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public BoardResponseDto findById(Long boardNum) {
        Board board = boardRepository.findById(boardNum)
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_IS_NOT_EXIST));

        hitUpdate(board);

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
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_IS_NOT_EXIST));

        board.update(boardUpdateRequestDto.getBoardTitle(), boardUpdateRequestDto.getBoardContent());

        return boardNum;
    }

    @Override
    @Transactional
    public void delete(Long boardNum) {
        Board board = boardRepository.findById(boardNum)
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_IS_NOT_EXIST));

        boardRepository.delete(board);
    }

    @Override
    public void hitUpdate(Board board) {
        board.hitUpdate();
        boardRepository.save(board);
    }
}

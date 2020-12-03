package com.example.site.board.controller;

import com.example.site.board.dto.BoardResponseDto;
import com.example.site.board.dto.BoardSaveRequestDto;
import com.example.site.board.dto.BoardUpdateRequestDto;
import com.example.site.board.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/board")
@RestController
public class BoardController {

    public final BoardServiceImpl boardService;

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<BoardResponseDto>> findAll(){

        List<BoardResponseDto> boardResponseDtoList = boardService.findAll();

        return new ResponseEntity<>(boardResponseDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "/{boardNum}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BoardResponseDto> findById(@PathVariable("boardNum") Long boardNum){

        BoardResponseDto boardResponseDto = boardService.findById(boardNum);

        return new ResponseEntity<>(boardResponseDto, HttpStatus.OK);
    }

    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Long> save(@RequestBody BoardSaveRequestDto boardSaveRequestDto){

        Long savedBoardNum = boardService.save(boardSaveRequestDto);

        return new ResponseEntity<>(savedBoardNum, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{boardNum}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Long> update(@PathVariable("boardNum") Long boardNum, @RequestBody BoardUpdateRequestDto boardUpdateRequestDto){

        Long updateBoardNum = boardService.update(boardNum, boardUpdateRequestDto);

        return new ResponseEntity<>(updateBoardNum, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{boardNum}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Long> delete(@PathVariable("boardNum") Long boardNum){

        boardService.delete(boardNum);

        return new ResponseEntity<>(boardNum, HttpStatus.NO_CONTENT);
    }

}

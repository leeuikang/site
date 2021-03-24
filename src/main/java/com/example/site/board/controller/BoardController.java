package com.example.site.board.controller;

import com.example.site.board.dto.BoardResponseDto;
import com.example.site.board.dto.BoardSaveRequestDto;
import com.example.site.board.dto.BoardUpdateRequestDto;
import com.example.site.board.dto.FileUploadDto;
import com.example.site.board.service.BoardServiceImpl;

import com.example.site.board.service.FileServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.io.File;

@RequiredArgsConstructor
@RequestMapping("/board")
@RestController
public class BoardController {

    private final BoardServiceImpl boardService;
    private final FileServiceImpl fileService;

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
    public ResponseEntity<Long> save(@RequestParam("boardTitle") String boardTitle,
                                     @RequestParam("boardContent") String boardContent,
                                     @RequestParam("boardWriter") String boardWriter,
                                     @RequestParam("file") List<MultipartFile> files){
        String path = "C:/Users/jaeyoung/IdeaProjects/site/uploads/";
        long savedBoardNum = boardService.save(BoardSaveRequestDto.builder()
                .boardTitle(boardTitle)
                .boardContent(boardContent)
                .boardWriter(boardWriter)
                .build());

        try {
            if(!new File(path).exists()){
                try{
                    new File(path).mkdir();
                }
                catch (Exception e){
                    //e.getStackTrace();
                }
            }
            if (!files.isEmpty()) {
                for(MultipartFile file : files){
                    String fileUUID = UUID.randomUUID().toString();
                    fileService.saveFile(FileUploadDto.builder()
                        .fileName(fileUUID)
                        .fileOriginName(file.getOriginalFilename())
                        .boardNum(savedBoardNum)
                        .filePath(path+fileUUID)
                        .build());
                    file.transferTo(new File(path+fileUUID));
                }
            }
        }
        catch (Exception e){
            //e.printStackTrace();
        }
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

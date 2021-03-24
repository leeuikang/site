package com.example.site.board.dto;

import com.example.site.board.entity.FileStorage;
import lombok.Builder;
import lombok.Setter;

@Setter
public class FileUploadDto {

    private String fileName;
    private String fileOriginName;
    private String filePath;
    private long boardNum;

    public FileStorage toEntity(){
        return FileStorage.builder()
                .fileName(fileName)
                .fileOriginName(fileOriginName)
                .filePath(filePath)
                .board_num(boardNum)
                .build();
    }

    @Builder
    public FileUploadDto(String fileName, String fileOriginName, String filePath, long boardNum) {
        this.fileName = fileName;
        this.fileOriginName = fileOriginName;
        this.filePath = filePath;
        this.boardNum = boardNum;
    }
}

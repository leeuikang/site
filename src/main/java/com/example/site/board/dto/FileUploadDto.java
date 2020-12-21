package com.example.site.board.dto;

import com.example.site.board.entity.File;
import lombok.Setter;

@Setter
public class FileUploadDto {

    private String fileName;
    private String fileOriginName;
    private String filePath;

    public File toEntity(){
        return File.builder()
                .fileName(fileName)
                .fileOriginName(fileOriginName)
                .filePath(filePath)
                .build();
    }

}

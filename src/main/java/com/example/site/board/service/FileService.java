package com.example.site.board.service;

import com.example.site.board.dto.FileUploadDto;

public interface FileService {

    public Long saveFile(FileUploadDto fileUploadDto);

}

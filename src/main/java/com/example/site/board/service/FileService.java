package com.example.site.board.service;

import com.example.site.board.dto.FileUploadDto;
import com.example.site.board.entity.FileStorage;

import java.util.List;

public interface FileService {

    public Long saveFile(FileUploadDto fileUploadDto);

    public Long updateFile(FileStorage fileStorage);

    public List<FileStorage> getFile(long boardNum);

}

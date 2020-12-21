package com.example.site.board.service;

import com.example.site.board.dto.FileUploadDto;
import com.example.site.board.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    @Override
    public Long saveFile(FileUploadDto fileUploadDto) {
        return fileRepository.save(fileUploadDto.toEntity()).getId();
    }
}

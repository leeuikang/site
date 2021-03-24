package com.example.site.board.service;

import com.example.site.board.dto.FileUploadDto;
import com.example.site.board.entity.FileStorage;
import com.example.site.board.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    @Transactional
    @Override
    public Long saveFile(FileUploadDto fileUploadDto) {

        return fileRepository.save(fileUploadDto.toEntity()).getId();

    }

    @Override
    public FileUploadDto getFile(long id) {

        FileStorage file = fileRepository.findById(id).get();

        return FileUploadDto.builder()
                .fileName(file.getFileName())
                .fileOriginName(file.getFileOriginName())
                .filePath(file.getFilePath())
                .build();
    }
}

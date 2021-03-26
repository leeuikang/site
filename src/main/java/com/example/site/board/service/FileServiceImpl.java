package com.example.site.board.service;

import com.example.site.board.dto.FileUploadDto;
import com.example.site.board.entity.FileStorage;
import com.example.site.board.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    @Transactional
    @Override
    public Long saveFile(FileUploadDto fileUploadDto) {
        return fileRepository.save(fileUploadDto.toEntity()).getId();

    }

    @Transactional
    @Override
    public Long updateFile(FileStorage fileStorage){
        return fileRepository.save(fileStorage).getId();
    }
    @Override
    public List<FileStorage> getFile(long boardNum) {

        return fileRepository.findByBoardNum(boardNum);

    }
}

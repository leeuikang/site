package com.example.site.board.repository;

import com.example.site.board.dto.FileUploadDto;
import com.example.site.board.entity.FileStorage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<FileStorage,Long> {
    List<FileStorage> findByBoardNum(long boardNum);
}

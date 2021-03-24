package com.example.site.board.repository;

import com.example.site.board.entity.FileStorage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileStorage,Long> {
}

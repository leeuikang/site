package com.example.site.board.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "file")
public class FileStorage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private long id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_origin_name")
    private String fileOriginName;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "board_num")
    private long board_num;

    @Builder
    public FileStorage(String fileName, String fileOriginName, String filePath, long board_num) {
        this.fileName = fileName;
        this.fileOriginName = fileOriginName;
        this.filePath = filePath;
        this.board_num = board_num;
    }
}

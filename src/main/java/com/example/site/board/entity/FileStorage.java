package com.example.site.board.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Setter
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

    @Column(name = "file_delete")
    @ColumnDefault("false")
    private boolean fileDelete;

    @Column(name = "board_num")
    private long boardNum;

    @Builder
    public FileStorage(String fileName, String fileOriginName, String filePath, long boardNum) {
        this.fileName = fileName;
        this.fileOriginName = fileOriginName;
        this.filePath = filePath;
        this.fileDelete = false;
        this.boardNum = boardNum;
    }


}

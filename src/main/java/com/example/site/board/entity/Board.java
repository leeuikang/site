package com.example.site.board.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board")
public class Board extends TimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_num")
    private long boardNum;

    @Column(name = "board_writer")
    private String boardWriter;

    @Column(name = "board_title")
    private String boardTitle;

    @Lob
    @Column(name = "board_content")
    private String boardContent;

}

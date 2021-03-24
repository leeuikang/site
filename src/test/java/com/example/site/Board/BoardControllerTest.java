package com.example.site.Board;

import com.example.site.board.dto.BoardUpdateRequestDto;
import com.example.site.board.entity.Board;
import com.example.site.board.repository.BoardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoardControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void findAll(){
        String url = "http://localhost:" + port + "/board";

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).contains("boardNum");
        assertThat(responseEntity.getBody()).contains("boardTitle");
        assertThat(responseEntity.getBody()).contains("boardWriter");
        assertThat(responseEntity.getBody()).contains("boardContent");
    }

    @Test
    public void findById() {
        Long boardNum = 1L;

        String url = "http://localhost:" + port + "/board/" + boardNum;

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).contains("boardNum");
        assertThat(responseEntity.getBody()).contains("boardTitle");
        assertThat(responseEntity.getBody()).contains("boardWriter");
        assertThat(responseEntity.getBody()).contains("boardContent");
    }

    @Test
    public void save() {

        String boardTitle = "게시글 제목 저장 01";
        String boardWriter = "게시글 작성자 저장 01";
        String boardContent = "게시글 내용 저장 01";

        Board saveBoard = boardRepository.save(Board.builder()
                .boardTitle(boardTitle)
                .boardWriter(boardWriter)
                .boardContent(boardContent)
                .build());

        Long boardNum = saveBoard.getBoardNum();
        String updateBoardTitle = "게시글 제목 수정 01";
        String updateBoardContent = "게시글 내용 수정 01";

        BoardUpdateRequestDto boardUpdateRequestDto = BoardUpdateRequestDto.builder()
                .boardTitle(updateBoardTitle)
                .boardContent(updateBoardContent)
                .build();

        String url = "http://localhost:" + port + "/board/" + boardNum;

        HttpEntity<BoardUpdateRequestDto> requestDtoHttpEntity = new HttpEntity<>(boardUpdateRequestDto);

        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestDtoHttpEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Board> boardList = boardRepository.findAll();
        Board board = boardList.get(boardList.size() - 1);
        assertThat(board.getBoardTitle()).isEqualTo(updateBoardTitle);
        assertThat(board.getBoardContent()).isEqualTo(updateBoardContent);
    }

    @Test
    public void delete(){

        String boardTitle = "게시글 제목 저장 01";
        String boardWriter = "게시글 작성자 저장 01";
        String boardContent = "게시글 내용 저장 01";

        Board saveBoard = boardRepository.save(Board.builder()
                            .boardTitle(boardTitle)
                            .boardWriter(boardWriter)
                            .boardContent(boardContent)
                            .build());

        Long boardNum = saveBoard.getBoardNum();

        String url = "http://localhost:" + port + "/board/" + boardNum;

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, httpEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}

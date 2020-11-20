package com.example.site.board.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface BoardService {

    Map<String, Object> selectBoardList(Map<String, Object> map) throws Exception;

    Map<String, Object> selectBoardSearchList(Map<String, Object> map) throws Exception;

    Map<String, Object> selectBoardDetails(Map<String, Object> map) throws Exception;

    void insertBoard(Map<String, Object> map, HttpServletRequest request) throws Exception;

    boolean updateBoard(Map<String, Object> map, HttpServletRequest request) throws Exception;

    boolean deleteBoard(Map<String, Object> map, HttpServletRequest request) throws Exception;

}

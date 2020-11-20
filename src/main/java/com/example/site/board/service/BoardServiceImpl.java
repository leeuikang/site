package com.example.site.board.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService{

    @Override
    public Map<String, Object> selectBoardList(Map<String, Object> map) throws Exception {
        return null;
    }

    @Override
    public Map<String, Object> selectBoardSearchList(Map<String, Object> map) throws Exception {
        return null;
    }

    @Override
    public Map<String, Object> selectBoardDetails(Map<String, Object> map) throws Exception {
        return null;
    }

    @Override
    public void insertBoard(Map<String, Object> map, HttpServletRequest request) throws Exception {

    }

    @Override
    public boolean updateBoard(Map<String, Object> map, HttpServletRequest request) throws Exception {
        return false;
    }

    @Override
    public boolean deleteBoard(Map<String, Object> map, HttpServletRequest request) throws Exception {
        return false;
    }

}

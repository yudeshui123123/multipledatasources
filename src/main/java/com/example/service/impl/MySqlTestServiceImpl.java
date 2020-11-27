package com.example.service.impl;

import com.example.mapper.MySqlTestMapper;
import com.example.service.MySqlTestService;
import com.example.service.OracleTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO
 *
 * @author yds
 * @version 1.0
 * @date 2020/11/27 20:38
 * @description:
 */
@Service
@Transactional
public class MySqlTestServiceImpl implements MySqlTestService {

    @Autowired
    private MySqlTestMapper mySqlTestMapper;

    @Autowired
    private OracleTestService oracleTestService;

    @Override
    public Integer insert() {
        Integer insert = oracleTestService.insert();
        System.out.println(insert);
        return mySqlTestMapper.insertTest();
    }
}

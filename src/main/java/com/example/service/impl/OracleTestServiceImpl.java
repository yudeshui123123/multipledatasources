package com.example.service.impl;

import com.example.mapper.OracleTestMapper;
import com.example.service.OracleTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO
 *
 * @author yds
 * @version 1.0
 * @date 2020/11/27 20:40
 * @description:
 */
@Service
@Transactional
public class OracleTestServiceImpl implements OracleTestService {

    @Autowired
    private OracleTestMapper oracleTestMapper;

    @Override
    public Integer insert() {
        return oracleTestMapper.insertTest();
    }
}

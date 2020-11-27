package com.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

/**
 * TODO
 *
 * @author yds
 * @version 1.0
 * @date 2020/11/27 20:21
 * @description:
 */
@Repository
public interface OracleTestMapper {

    @Insert("insert into student values('1','小明','18')")
    Integer insertTest();
}

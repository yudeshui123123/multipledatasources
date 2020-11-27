package com.example.controller;


import com.example.service.MySqlTestService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author yds
 * @version 1.0
 * @date 2020/11/27 20:41
 * @description:
 */
@RestController
@RequestMapping("mysql")
public class MySqlTestController {

    @Autowired
    private MySqlTestService mySqlTestService;

    @GetMapping("/insert")
    public Integer insertByStudent(){
        return mySqlTestService.insert();
    }
}

package com.zhangbao.controller;

import com.zhangbao.entiy.Student;
import com.zhangbao.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhangbao
 * @date 2021/3/14 23:05
 **/
@RestController
@RequestMapping("/feign")
public class FeignController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/findAll")
    public List<Student> findAll(){
        return studentService.findAll();
    }

    @GetMapping("/index")
    public String indec(){
        return studentService.index();
    }
}

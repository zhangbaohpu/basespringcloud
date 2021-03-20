package com.zhangbao.service.impl;

import com.zhangbao.entiy.Student;
import com.zhangbao.service.StudentService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhangbao
 * @date 2021/3/14 23:23
 **/
@Component
public class StudentError implements StudentService {
    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public String index() {
        return "服务器维护中......";
    }
}

package com.zhangbao.service;

import com.zhangbao.entity.Student;

import java.util.List;

/**
 * @author zhangbao
 * @date 2021/3/14 15:03
 **/
public interface StudentService {
    List<Student> findAll();
    void saveOrUpdate(Student student);
    Student fingById(Long id);
    void deleteById(Long id);
}

package com.zhangbao.service.impl;

import com.zhangbao.entity.Student;
import com.zhangbao.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author zhangbao
 * @date 2021/3/14 15:05
 **/
@Service
public class StudentServiceImpl implements StudentService {

    private static Map<Long,Student> studentMapper;
    static {
        studentMapper = new HashMap<>();
        studentMapper.put(1L,new Student(1L,"张三",21));
        studentMapper.put(2L,new Student(2L,"李四",26));
        studentMapper.put(3L,new Student(3L,"小明",18));
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(studentMapper.values());
    }

    @Override
    public void saveOrUpdate(Student student) {
        studentMapper.put(student.getId(),student);
    }

    @Override
    public Student fingById(Long id) {
        return studentMapper.get(id);
    }

    @Override
    public void deleteById(Long id) {
        studentMapper.remove(id);
    }
}

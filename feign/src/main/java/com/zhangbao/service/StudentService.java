package com.zhangbao.service;

import com.zhangbao.entiy.Student;
import com.zhangbao.service.impl.StudentError;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author zhangbao
 * @date 2021/3/14 23:03
 **/
@FeignClient(value = "provider",fallback = StudentError.class)
public interface StudentService {

    @GetMapping("/student/findAll")
    List<Student> findAll();
    @GetMapping("/student/index")
    String index();
}

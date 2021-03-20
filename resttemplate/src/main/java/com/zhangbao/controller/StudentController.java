package com.zhangbao.controller;

import com.zhangbao.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author zhangbao
 * @date 2021/3/14 15:31
 **/
@RestController
@RequestMapping("/rest")
public class StudentController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/findAll")
    public List<Student> findAll(){
        return restTemplate.getForEntity("http://localhost:8010/student/findAll",List.class).getBody();
    }

    @GetMapping("/findAll2")
    public List<Student> findAll2(){
        return restTemplate.getForObject("http://localhost:8010/student/findAll",List.class);
    }

    @GetMapping("/findById/{id}")
    public Student findById(@PathVariable("id") Long id){
        return restTemplate.getForEntity("http://localhost:8010/student/findById/{id}",Student.class,id).getBody();
    }
}

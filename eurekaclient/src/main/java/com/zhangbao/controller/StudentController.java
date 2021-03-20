package com.zhangbao.controller;

import com.zhangbao.entity.Student;
import com.zhangbao.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangbao
 * @date 2021/3/14 15:20
 **/
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Value("${server.port}")
    private String port;

    @GetMapping("/findAll")
    public List<Student> findAll(){
        return studentService.findAll();
    }

    @PostMapping("/findById/{id}")
    public Student findById(@PathVariable("id") Long id){
        return studentService.fingById(id);
    }

    @PostMapping("/save")
    public void saveOrUpdate(@RequestBody Student student){
        studentService.saveOrUpdate(student);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") Long id){
        studentService.deleteById(id);
    }

    @GetMapping("/index")
    public String index(){
        return "当前端口："+this.port;
    }
}

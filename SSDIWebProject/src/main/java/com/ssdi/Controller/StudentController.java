package com.ssdi.Controller;

import com.ssdi.Entity.Student;
import com.ssdi.Service.StudentSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Collection;

/**
 * Created by praya on 2/18/2017.
 */

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentSevice studentSevice;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Student> getAllStudents(){
        return studentSevice.getAllStudents();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Student getStudentById(@PathVariable("id") int id){
        return studentSevice.getStudentById(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void deleteStudentById(@PathVariable("id") int id){
        this.studentSevice.removeStudentByid(id);
    }

    @RequestMapping(method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateStudent(@RequestBody Student student){
        this.studentSevice.updateStudent(student);
    }
}

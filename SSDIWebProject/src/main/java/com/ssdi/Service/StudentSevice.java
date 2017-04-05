package com.ssdi.Service;

import com.ssdi.Dao.StudentDao;
import com.ssdi.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by praya on 2/18/2017.
 */

@Service
public class StudentSevice {

    @Autowired
    @Qualifier("mysql")
    private StudentDao studentDao;

    public Collection<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    public Student getStudentById(int id) {
        return this.studentDao.getStudentById(id);
    }

    public void removeStudentByid(int id) {
        this.studentDao.removeStudentByid(id);
    }

    public void updateStudent(Student student) {
        this.studentDao.updateStudent(student);
    }
}
package com.ssdi.Dao;

import com.ssdi.Entity.Student;

import java.util.Collection;

/**
 * Created by praya on 2/20/2017.
 */
public interface StudentDao {
    Collection<Student> getAllStudents();

    Student getStudentById(int id);

    void removeStudentByid(int id);

    void updateStudent(Student student);
}

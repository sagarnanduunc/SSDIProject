package com.ssdi.Dao;

import com.ssdi.Entity.Student;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by prayas on 2/18/2017.
 */

@Repository
@Qualifier("SampleData")
public class StudentDaoImpl implements StudentDao {
    private static Map<Integer,Student> students;
    static {
        students = new HashMap<Integer,Student>(){
            {
                put(1, new Student(1,"abc","cs"));
                put(2, new Student(2,"asd","cs1"));
                put(3, new Student(3,"qwe","cs2"));
            }
        };
    }

    @Override
    public Collection<Student> getAllStudents(){
        return this.students.values();
    }

    @Override
    public Student getStudentById(int id){
        return this.students.get(id);
    }

    @Override
    public void removeStudentByid(int id) {
        this.students.remove(id);
    }

    @Override
    public void updateStudent(Student student){
        Student s = students.get(student.getId());
        s.setCourse(student.getCourse());
        s.setName(student.getName());
        students.put(student.getId(),student);
    }
}

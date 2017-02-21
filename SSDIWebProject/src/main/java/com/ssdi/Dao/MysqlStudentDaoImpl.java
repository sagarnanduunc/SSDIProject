package com.ssdi.Dao;

import com.ssdi.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by praya on 2/20/2017.
 */
@Repository("mysql")
public class MysqlStudentDaoImpl implements StudentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Collection<Student> getAllStudents() {
        final String sql = "SELECT id, name, course FROM student";
        List<Student> students = jdbcTemplate.query(sql, new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet resultSet, int i) throws SQLException {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setCourse(resultSet.getString("course"));
                return student;
            }
        });
        return students;
    }

    @Override
    public Student getStudentById(int id) {
        return null;
    }

    @Override
    public void removeStudentByid(int id) {

    }

    @Override
    public void updateStudent(Student student) {

    }
}

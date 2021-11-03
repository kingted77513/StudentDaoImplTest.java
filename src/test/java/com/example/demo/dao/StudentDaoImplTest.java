package com.example.demo.dao;

import com.example.demo.model.Student;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentDaoImplTest {

    @Autowired
    private StudentDao studentDao;

    @Test
    public void getById() {
        Student student = studentDao.getById(1);
        assertNotNull(student);
        assertEquals("Amy", student.getName());
        assertEquals(90.3, student.getScore());
        assertTrue(student.isGraduate());
        assertNotNull(student.getCreateDate());
    }

    @Transactional
    @Test
    public void deleteById() {
        studentDao.deleteById(1);
        assertNull(studentDao.getById(1));
    }

    @Transactional
    @Test
    public void insert() {
        Student student = new Student();
        student.setName("Kevin");
        student.setScore(66.2);
        student.setGraduate(true);
        student.setCreateDate(new Date());

        Integer studentId = studentDao.insert(student);

        Student result = studentDao.getById(studentId);
        assertNotNull(result);
        assertEquals("Kevin", result.getName());
        assertEquals(66.2, result.getScore());
        assertTrue(result.isGraduate());
        assertNotNull(result.getCreateDate());
    }

    @Transactional
    @Test
    public void update() {
        Student student = studentDao.getById(3);
        student.setName("John");

        studentDao.update(student);

        Student result = studentDao.getById(3);
        assertNotNull(result);
        assertEquals("John", result.getName());
    }
}
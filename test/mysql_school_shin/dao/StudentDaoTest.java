package mysql_school_shin.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Test;

import mysql_school_shin.dao.impl.StudentDaoImpl;
import mysql_school_shin.dto.Student;

public class StudentDaoTest {
	private static StudentDao dao = StudentDaoImpl.getInstance();
	
	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void testSelectStudentByAll() {
		List<Student> list = dao.selectStudentByAll();

		list.stream().forEach(System.out::println);
	}

	@Test
	public void testSelectStudentByNo() {
		Student select = new Student(1);
		Student str = dao.selectStudentByNo(select);
		System.out.println(str);
	}

	@Test
	public void testInsertStudent() {
		Student select = new Student(7,"호호호");
		int res = dao.insertStudent(select);
		System.out.println(dao.selectStudentByNo(select));
	}

}

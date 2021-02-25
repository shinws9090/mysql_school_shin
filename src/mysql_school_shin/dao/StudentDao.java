package mysql_school_shin.dao;

import java.util.List;

import mysql_school_shin.dto.Student;


public interface StudentDao {
	
	List<Student> selectStudentByAll();
	Student selectStudentByNo(Student student);
	int insertStudent(Student student);
	int deleteStudent(int no);

}

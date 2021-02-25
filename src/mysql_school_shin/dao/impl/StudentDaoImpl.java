package mysql_school_shin.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mysql_school_shin.dao.StudentDao;
import mysql_school_shin.dto.Score;
import mysql_school_shin.dto.Student;
import native_jdbc_programing.util.JdbcUtil;

public class StudentDaoImpl implements StudentDao {
	private static final StudentDaoImpl instance = new StudentDaoImpl();

	private StudentDaoImpl() {
	}

	public static StudentDaoImpl getInstance() {
		return instance;
	}

	private Student getStudent(ResultSet rs) throws SQLException {
		int no = rs.getInt("no");
		String name = rs.getString("name");
		
		return new Student(no, name);
	}
	
	@Override
	public List<Student> selectStudentByAll() {
		String sql = "select * from student";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			if (rs.next()) {
				ArrayList<Student> list = new ArrayList<Student>();
				do {
					list.add(getStudent(rs));
				} while (rs.next());
				return list;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	

	@Override
	public Student selectStudentByNo(Student student) {
		String sql = "select * from student where no = ?";
		try (Connection con = JdbcUtil.getConnection(); 
			PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, student.getNo());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return getStudent(rs);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public int insertStudent(Student student) {
		String sql = "insert into student values (?,?)";
		try (Connection con = JdbcUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, student.getNo());
			pstmt.setString(2, student.getName());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int deleteStudent(int no) {
		String sql = "delete from student where no=?";
		try (Connection con = JdbcUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, no);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

}

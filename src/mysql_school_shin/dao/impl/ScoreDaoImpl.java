package mysql_school_shin.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mysql_school_shin.dao.ScoreDao;
import mysql_school_shin.dto.Score;
import mysql_school_shin.dto.Student;
import native_jdbc_programing.util.JdbcUtil;

public class ScoreDaoImpl implements ScoreDao {
	private static final ScoreDaoImpl instance = new ScoreDaoImpl();

	private ScoreDaoImpl() {
	}

	public static ScoreDaoImpl getInstance() {
		return instance;
	}

	private Score getScore(ResultSet rs) throws SQLException {
		Student no = new Student(rs.getInt("no"));
		int kor = rs.getInt("kor");
		int eng = rs.getInt("eng");
		int math = rs.getInt("math");

		return new Score(no, kor, eng, math);
	}

	@Override
	public List<Score> selectScoreByAll() {
		String sql = "select * from score";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			if (rs.next()) {
				ArrayList<Score> list = new ArrayList<Score>();
				do {
					list.add(getScore(rs));
				} while (rs.next());
				return list;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Score selectScoreByNo(Score score) {
		String sql = "select * from score where no = ?";
		try (Connection con = JdbcUtil.getConnection(); 
			PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, score.getNo().getNo());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return getScore(rs);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public int insertScore(Score score) {
		String sql = "insert into score values (?,?,?,?)";
		try (Connection con = JdbcUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, score.getNo().getNo());
			pstmt.setInt(2, score.getKor());
			pstmt.setInt(3, score.getEng());
			pstmt.setInt(4, score.getMath());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int deleteScore(int no) {
		String sql = "delete from score where no=?";
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

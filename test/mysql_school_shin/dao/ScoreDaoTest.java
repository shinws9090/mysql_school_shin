package mysql_school_shin.dao;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Test;

import mysql_school_shin.dao.impl.ScoreDaoImpl;
import mysql_school_shin.dto.Score;
import mysql_school_shin.dto.Student;

public class ScoreDaoTest {
	private static ScoreDao dao = ScoreDaoImpl.getInstance();

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void testSelectScoreByAll() {
		List<Score> list = dao.selectScoreByAll();

		list.stream().forEach(System.out::println);

	}

	@Test
	public void testSelectScoreByNo() {
		Score select = new Score(new Student(1));
		Score score = dao.selectScoreByNo(select);
		System.out.println(score);
	}

	@Test
	public void testInsertScore() {
		Score select = new Score(new Student(6),50,50,50);
		int res = dao.insertScore(select);
		System.out.println(dao.selectScoreByNo(select));
		
		
	}

}

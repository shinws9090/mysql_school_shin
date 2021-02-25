package mysql_school_shin.dao;

import java.util.List;

import mysql_school_shin.dto.Score;

public interface ScoreDao {
	
	List<Score> selectScoreByAll();
	Score selectScoreByNo(Score score);
	int insertScore(Score score);
	int updateScore(Score score);
	int deleteScore(int no);
	
	

}

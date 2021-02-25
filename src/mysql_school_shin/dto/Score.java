package mysql_school_shin.dto;

public class Score {
	private Student no;
	private int kor;
	private int eng;
	private int math;

	public Score() {
	}

	public Score(Student no) {
		this.no = no;
	}

	public Score(Student no, int kor, int eng, int math) {
		this.no = no;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}

	@Override
	public String toString() {
		return String.format("Score [no=%s, kor=%s, eng=%s, math=%s]", no, kor, eng, math);
	}

	public Student getNo() {
		return no;
	}

	public void setNo(Student no) {
		this.no = no;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

}

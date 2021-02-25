package mysql_school_shin.dto;

public class Student {
	private int no;
	private String name;

	public Student(int no) {
		this.no = no;
	}

	public Student(int no, String name) {
		this.no = no;
		this.name = name;
	}

	public Student() {
	}

	@Override
	public String toString() {
		return String.format("Student [no=%s, name=%s]", no, name);
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

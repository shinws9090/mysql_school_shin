package mysql_school_shin.jswing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import mysql_school_shin.dao.StudentDao;
import mysql_school_shin.dao.impl.ScoreDaoImpl;
import mysql_school_shin.dao.impl.StudentDaoImpl;
import mysql_school_shin.dto.Score;
import mysql_school_shin.dto.Student;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentMain extends JFrame implements ActionListener{
	private JPanel contentPane;
	private JTable table;
	private JTextField tfno;
	private JTextField tfname;
	private JTextField tfKor;
	private JTextField ftEng;
	private JTextField tfMath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentMain frame = new StudentMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StudentMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 604);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 238, 410, 317);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setToolTipText("");
		table.setModel(getModel());
		scrollPane.setViewportView(table);
		
		tfno = new JTextField();
		tfno.setBounds(241, 10, 116, 21);
		contentPane.add(tfno);
		tfno.setColumns(10);
		
		tfname = new JTextField();
		tfname.setColumns(10);
		tfname.setBounds(241, 33, 116, 21);
		contentPane.add(tfname);
		
		tfKor = new JTextField();
		tfKor.setColumns(10);
		tfKor.setBounds(241, 57, 116, 21);
		contentPane.add(tfKor);
		
		ftEng = new JTextField();
		ftEng.setColumns(10);
		ftEng.setBounds(241, 82, 116, 21);
		contentPane.add(ftEng);
		
		tfMath = new JTextField();
		tfMath.setColumns(10);
		tfMath.setBounds(241, 106, 116, 21);
		contentPane.add(tfMath);
		
		JLabel lblNewLabel = new JLabel("학번");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(172, 13, 57, 15);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("이름");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(172, 36, 57, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("국어");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(172, 60, 57, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("영어");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(172, 85, 57, 15);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("수학");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(172, 109, 57, 15);
		contentPane.add(label_3);
		
		JButton btnNewButton = new JButton("추가");
		btnNewButton.addActionListener(this);
			
		btnNewButton.setBounds(87, 187, 97, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("삭제");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int no = Integer.parseInt(tfno.getText().trim());
				ScoreDaoImpl.getInstance().deleteScore(no);
				StudentDaoImpl.getInstance().deleteStudent(no);
				table.setModel(getModel());
			}
		});
		btnNewButton_1.setBounds(230, 187, 97, 23);
		contentPane.add(btnNewButton_1);
	}
	
	public DefaultTableModel getModel() {
		return new DefaultTableModel(getStudent(), getColumnNames());
	}

	private String[] getColumnNames() {
		
		return new String[]{"번호","학생이름","국어","영어","수학"};
	}

	private Object[][] getStudent() {
		List<Student> stlist = StudentDaoImpl.getInstance().selectStudentByAll();
		List<Score> sclist = ScoreDaoImpl.getInstance().selectScoreByAll();
		Object[][] obj = new Object[stlist.size()][];
		try {
		for(int i=0 ; i < stlist.size(); i++) {
			obj[i] = new Object[] {
				stlist.get(i).getNo(),
				stlist.get(i).getName(),
				sclist.get(i).getKor(),	
				sclist.get(i).getEng(),	
				sclist.get(i).getMath()
				
			};
		}
		}catch(Exception e) {
			System.out.println("중복인듯?");
		}
		
		return obj;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int no = Integer.parseInt(tfno.getText().trim());
		String name = tfname.getText().trim();
		
		Student student = new Student(no, name);
		StudentDaoImpl.getInstance().insertStudent(student);
		
		int kor =Integer.parseInt(tfKor.getText().trim());
		int eng =Integer.parseInt(ftEng.getText().trim());
		int math =Integer.parseInt(tfMath.getText().trim());
		
		Score score = new Score(student, kor, eng, math);
		ScoreDaoImpl.getInstance().insertScore(score);
		table.setModel(getModel());
		
		
	}

	
}

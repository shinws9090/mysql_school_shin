-- 학교
DROP SCHEMA IF EXISTS school;

-- 학교
CREATE SCHEMA school;

-- 학생
CREATE TABLE school.student (
	no   INT         NOT NULL COMMENT '학번', -- 학번
	name VARCHAR(20) NOT NULL COMMENT '학생명' -- 학생명
)
COMMENT '학생';

-- 학생
ALTER TABLE school.student
	ADD CONSTRAINT PK_student -- 학생 기본키
		PRIMARY KEY (
			no -- 학번
		);

-- 점수
CREATE TABLE school.score (
	no   INT NOT NULL COMMENT '학번', -- 학번
	kor  INT NULL     COMMENT '국어', -- 국어
	eng  INT NULL     COMMENT '영어', -- 영어
	math INT NULL     COMMENT '수학' -- 수학
)
COMMENT '점수';

-- 점수
ALTER TABLE school.score
	ADD CONSTRAINT PK_score -- 점수 기본키
		PRIMARY KEY (
			no -- 학번
		);

-- 점수
ALTER TABLE school.score
	ADD CONSTRAINT FK_student_TO_score -- 학생 -> 점수
		FOREIGN KEY (
			no -- 학번
		)
		REFERENCES school.student ( -- 학생
			no -- 학번
		);
		
	

grant all
	on school.*
	to 'user_school'@'localhost' identified by 'rootroot';



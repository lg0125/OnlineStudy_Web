CREATE TABLE course
(
    id VARCHAR(20) PRIMARY KEY ,
    name VARCHAR(20) NOT NULL DEFAULT '',
    introduction VARCHAR(100) NOT NULL DEFAULT '',
    dept_name VARCHAR(15),
    FOREIGN KEY (dept_name) REFERENCES department(name)
)ENGINE=InnoDB;

CREATE TABLE student
(
    id VARCHAR(20) PRIMARY KEY ,
    password VARCHAR(20) NOT NULL DEFAULT '',
    name VARCHAR(20) NOT NULL DEFAULT '',
    introduction VARCHAR(100) NOT NULL DEFAULT '',
    photo VARCHAR(255) NOT NULL DEFAULT '',
    type VARCHAR(20) NOT NULL DEFAULT 'student',
    dept_name VARCHAR(20),
    FOREIGN KEY (dept_name) REFERENCES department(name)
)ENGINE = InnoDB;

CREATE TABLE teacher
(
    id VARCHAR(20) PRIMARY KEY ,
    password VARCHAR(20) NOT NULL DEFAULT '',
    name VARCHAR(20) NOT NULL DEFAULT '',
    introduction VARCHAR(100) NOT NULL DEFAULT '',
    photo VARCHAR(255) NOT NULL DEFAULT '',
    type VARCHAR(20) NOT NULL DEFAULT 'teacher',
    dept_name VARCHAR(20),
    rank_name VARCHAR(20),
    FOREIGN KEY (dept_name) REFERENCES department(name),
    FOREIGN KEY (rank_name) REFERENCES teacher_rank(rank_name)
)ENGINE = InnoDB;

CREATE TABLE teaches
(
    teacher_id VARCHAR(20),
    course_id VARCHAR(20),
    PRIMARY KEY (teacher_id,course_id),
    FOREIGN KEY (teacher_id) REFERENCES teacher(id),
    FOREIGN KEY (course_id) REFERENCES course(id)
)ENGINE = InnoDB;

CREATE TABLE takes
(
    student_id VARCHAR(20),
    course_id VARCHAR(20),
    teacher_id VARCHAR(20),
    PRIMARY KEY (student_id,teacher_id,course_id),
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (teacher_id,course_id) REFERENCES teaches(teacher_id, course_id)
)ENGINE = InnoDB;

CREATE TABLE message
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    status INT NOT NULL DEFAULT 0,
    title VARCHAR(20) NOT NULL DEFAULT '',
    content VARCHAR(100) NOT NULL DEFAULT '',
    time DATE,
    student_id VARCHAR(20),
    course_id VARCHAR(20),
    teacher_id VARCHAR(20),
)ENGINE = InnoDB;
ALTER TABLE message
ADD FOREIGN KEY (student_id,teacher_id,course_id) REFERENCES takes(student_id,teacher_id,course_id);

CREATE TABLE reply
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    message_id INT,
    content VARCHAR(100) NOT NULL DEFAULT '',
    time DATE,
    teacher_id VARCHAR(20),
    teacher_name VARCHAR(20) NOT NULL DEFAULT '',
    FOREIGN KEY (message_id) REFERENCES message(id)
)ENGINE = InnoDB;

CREATE VIEW courseStudent(student_id,student_name,course_id,course_name,teacher_id,teacher_name,status)
AS
    (
        SELECT t1.id,t1.name,t4.id,t4.name,t3.id,t3.name,t2.status
        FROM student AS t1
        RIGHT JOIN takes AS t2
        ON t1.id = t2.student_id
        LEFT JOIN teacher AS t3
        ON t2.teacher_id = t3.id
        LEFT JOIN course AS t4
        ON t2.course_id = t4.id
    );

CREATE VIEW messageShow(id,status,title,content,time,student_id,student_name,photo,course_id,course_name,course_dept,teacher_id,teacher_name)
AS
    (
        SELECT t1.id,t1.status,t1.title,t1.content,t1.time,t1.student_id,t2.name,t2.photo,t1.course_id,t4.name,t4.dept_name,t1.teacher_id,t3.name
        FROM message AS t1
        LEFT JOIN student AS t2
        ON t1.student_id = t2.id
        LEFT JOIN teacher AS t3
        ON t1.teacher_id = t3.id
        LEFT JOIN course AS t4
        ON t1.course_id = t4.id

    );
SELECT * FROM messageShow;

CREATE VIEW replyShow(id,message_id,content,time,teacher_id,teacher_name,rank_name,photo)
AS
    (
        SELECT t1.id,t1.message_id,t1.content,t1.time,t2.teacher_id,t3.name,t3.rank_name,t3.photo
        FROM reply AS t1
        LEFT JOIN message AS t2
        ON  t1.message_id = t2.id
        LEFT JOIN teacher AS t3
        ON t2.teacher_id = t3.id
    );

CREATE VIEW studentCourse(student_id,student_name,teacher_id,teacher_name,rank_name,course_id,course_name,course_dept,status)
AS
    (
        SELECT t1.id,t1.name,t2.teacher_id,t3.name,t3.rank_name,t2.course_id,t4.name,t4.dept_name,t2.status
        FROM student AS t1
        RIGHT JOIN takes AS t2
        ON t1.id = t2.student_id
        LEFT JOIN teacher AS t3
        ON t2.teacher_id = t3.id
        LEFT JOIN course AS t4
        ON t2.course_id = t4.id
    );

CREATE VIEW teacherCourse(teacher_id,teacher_name,rank_name,course_id,course_name,course_dept)
AS
    (
        SELECT t2.teacher_id,t1.name,t1.rank_name,t2.course_id,t3.name,t3.dept_name
        FROM teacher AS t1
        RIGHT JOIN teaches AS t2
        ON t1.id = t2.teacher_id
        LEFT JOIN course AS t3
        ON t2.course_id = t3.id
    );
SELECT * FROM teacherCourse;


INSERT INTO teacher (id,password,name,dept_name,rank_name) VALUES ('10101','10101','Srini','Comp.Sci.','lecturer');
INSERT INTO teacher (id,password,name,dept_name,rank_name) VALUES ('12121','12121','Wu','Finance','professor');
INSERT INTO teacher (id,password,name,dept_name,rank_name) VALUES ('15151','15151','Mozart','Music','adj_professor');
INSERT INTO teacher (id,password,name,dept_name,rank_name) VALUES ('22222','22222','Einstein','Physics','professor');
INSERT INTO teacher (id,password,name,dept_name,rank_name) VALUES ('32343','32343','El Said','History','adj_professor');
INSERT INTO teacher (id,password,name,dept_name,rank_name) VALUES ('33456','33456','Gold','Physics','professor');
INSERT INTO teacher (id,password,name,dept_name,rank_name) VALUES ('45565','45565','Katz','Comp.Sci.','lecturer');
INSERT INTO teacher (id,password,name,dept_name,rank_name) VALUES ('58583','58583','Califi','History','assistant');
INSERT INTO teacher (id,password,name,dept_name,rank_name) VALUES ('76543','76543','Singh','Finance','professor');
INSERT INTO teacher (id,password,name,dept_name,rank_name) VALUES ('76766','76766','Crick','Biology','assistant');
INSERT INTO teacher (id,password,name,dept_name,rank_name) VALUES ('83821','83821','Brandt','Comp.Sci.','professor');
INSERT INTO teacher (id,password,name,dept_name,rank_name) VALUES ('98345','98345','Kim','Elec.Eng.','professor');

INSERT INTO student (id,password,name,dept_name) VALUES ('00128','00128','Zhang','Comp.Sci.');
INSERT INTO student (id,password,name,dept_name) VALUES ('12345','12345','Shankar','Comp.Sci.');
INSERT INTO student (id,password,name,dept_name) VALUES ('19991','19991','Brandt','History');
INSERT INTO student (id,password,name,dept_name) VALUES ('23121','23121','Chavez','Finance');
INSERT INTO student (id,password,name,dept_name) VALUES ('44553','44553','Peltier','Physics');
INSERT INTO student (id,password,name,dept_name) VALUES ('45678','45678','Levy','Comp.Sci.');
INSERT INTO student (id,password,name,dept_name) VALUES ('54321','54321','William','Biology');
INSERT INTO student (id,password,name,dept_name) VALUES ('70557','70557','Snow','Elec.Eng.');
INSERT INTO student (id,password,name,dept_name) VALUES ('76653','76653','Aoi','Comp.Sci.');
INSERT INTO student (id,password,name,dept_name) VALUES ('98765','98765','Bourikas','Music');
INSERT INTO student (id,password,name,dept_name) VALUES ('98988','98988','Tanaka','Biology');

INSERT INTO student (id,password,name) VALUES ('00128','00128','Zhang');
INSERT INTO student (id,password,name) VALUES ('12345','12345','Shankar');
INSERT INTO student (id,password,name) VALUES ('19991','19991','Brandt');
INSERT INTO student (id,password,name) VALUES ('23121','23121','Chavez');
INSERT INTO student (id,password,name) VALUES ('44553','44553','Peltier');
INSERT INTO student (id,password,name) VALUES ('45678','45678','Levy');
INSERT INTO student (id,password,name) VALUES ('54321','54321','William');
INSERT INTO student (id,password,name) VALUES ('70557','70557','Snow');
INSERT INTO student (id,password,name) VALUES ('76653','76653','Aoi');
INSERT INTO student (id,password,name) VALUES ('98765','98765','Bourikas');
INSERT INTO student (id,password,name) VALUES ('98988','98988','Tanaka');

INSERT INTO user (id,password,type) VALUES ('10101','10101','teacher');
INSERT INTO user (id,password,type) VALUES ('12121','12121','teacher');
INSERT INTO user (id,password,type) VALUES ('15151','15151','teacher');
INSERT INTO user (id,password,type) VALUES ('22222','22222','teacher');
INSERT INTO user (id,password,type) VALUES ('32343','32343','teacher');
INSERT INTO user (id,password,type) VALUES ('33456','33456','teacher');
INSERT INTO user (id,password,type) VALUES ('45565','45565','teacher');
INSERT INTO user (id,password,type) VALUES ('58583','58583','teacher');
INSERT INTO user (id,password,type) VALUES ('76543','76543','teacher');
INSERT INTO user (id,password,type) VALUES ('76766','76766','teacher');
INSERT INTO user (id,password,type) VALUES ('83821','83821','teacher');
INSERT INTO user (id,password,type) VALUES ('98345','98345','teacher');
INSERT INTO user (id,password,type) VALUES ('00128','00128','student');
INSERT INTO user (id,password,type) VALUES ('12345','12345','student');
INSERT INTO user (id,password,type) VALUES ('19991','19991','student');
INSERT INTO user (id,password,type) VALUES ('23121','23121','student');
INSERT INTO user (id,password,type) VALUES ('44553','44553','student');
INSERT INTO user (id,password,type) VALUES ('45678','45678','student');
INSERT INTO user (id,password,type) VALUES ('54321','54321','student');
INSERT INTO user (id,password,type) VALUES ('70557','70557','student');
INSERT INTO user (id,password,type) VALUES ('76653','76653','student');
INSERT INTO user (id,password,type) VALUES ('98765','98765','student');
INSERT INTO user (id,password,type) VALUES ('98988','98988','student');

INSERT INTO course(id,introduction,name,dept_name) VALUES ('BIO-101','Intro. to Biology','BioIntro','Biology');
INSERT INTO course(id,introduction,name,dept_name) VALUES ('BIO-301','Genetics','BioGen','Biology');
INSERT INTO course(id,introduction,name,dept_name) VALUES ('BIO-399','Computational BioCompute','Biology','Biology');
INSERT INTO course(id,introduction,name,dept_name) VALUES ('CS-101','Intro. to Computer Science','CompIntro','Comp.Sci.');
INSERT INTO course(id,introduction,name,dept_name) VALUES ('CS-190','Game Design','CompGame','Comp.Sci.');
INSERT INTO course(id,introduction,name,dept_name) VALUES ('CS-319','Imag Processing','CompImage','Comp.Sci.');
INSERT INTO course(id,introduction,name,dept_name) VALUES ('CS-347','Database System Concepts','CompDatabase','Comp.Sci.');
INSERT INTO course(id,introduction,name,dept_name) VALUES ('EE-181','Intro. to Digital Systems','DigitalSystem','Elec.Eng.');
INSERT INTO course(id,introduction,name,dept_name) VALUES ('FIN-201','Investment Blanking','Invest','Finance');
INSERT INTO course(id,introduction,name,dept_name) VALUES ('HIS-351','World History','HisWorld','History');
INSERT INTO course(id,introduction,name,dept_name) VALUES ('MU-199','Music Video Production','MusicVideo','Music');
INSERT INTO course(id,introduction,name,dept_name) VALUES ('PHY-101','Physical Principles','PhyPrinciple','Physics');

INSERT INTO teaches(teacher_id,course_id) VALUES ('10101','CS-101');
INSERT INTO teaches(teacher_id,course_id) VALUES ('10101','CS-347');
INSERT INTO teaches(teacher_id,course_id) VALUES ('12121','FIN-201');
INSERT INTO teaches(teacher_id,course_id) VALUES ('15151','MU-199');
INSERT INTO teaches(teacher_id,course_id) VALUES ('22222','PHY-101');
INSERT INTO teaches(teacher_id,course_id) VALUES ('32343','HIS-351');
INSERT INTO teaches(teacher_id,course_id) VALUES ('45565','CS-101');
INSERT INTO teaches(teacher_id,course_id) VALUES ('45565','CS-319');
INSERT INTO teaches(teacher_id,course_id) VALUES ('76766','BIO-101');
INSERT INTO teaches(teacher_id,course_id) VALUES ('76766','BIO-301');
INSERT INTO teaches(teacher_id,course_id) VALUES ('83821','CS-190');
INSERT INTO teaches(teacher_id,course_id) VALUES ('83821','CS-319');
INSERT INTO teaches(teacher_id,course_id) VALUES ('98345','EE-181');

INSERT INTO takes (student_id,teacher_id,course_id) VALUES ('12345','83821','CS-190');
INSERT INTO takes (student_id,teacher_id,course_id) VALUES ('12345','10101','CS-347');
INSERT INTO takes (student_id,teacher_id,course_id) VALUES ('19991','32343','HIS-351');
INSERT INTO takes (student_id,teacher_id,course_id) VALUES ('23121','12121','FIN-201');
INSERT INTO takes (student_id,teacher_id,course_id) VALUES ('44553','22222','PHY-101');
INSERT INTO takes (student_id,teacher_id,course_id) VALUES ('45678','45565','CS-101');
INSERT INTO takes (student_id,teacher_id,course_id) VALUES ('45678','83821','CS-319');
INSERT INTO takes (student_id,teacher_id,course_id) VALUES ('54321','83821','CS-190');
INSERT INTO takes (student_id,teacher_id,course_id) VALUES ('54321','76766','BIO-101');
INSERT INTO takes (student_id,teacher_id,course_id) VALUES ('76653','45565','CS-319');
INSERT INTO takes (student_id,teacher_id,course_id) VALUES ('98765','10101','CS-101');
INSERT INTO takes (student_id,teacher_id,course_id) VALUES ('98988','76766','BIO-101');
INSERT INTO takes (student_id,teacher_id,course_id) VALUES ('98988','76766','BIO-301');

INSERT INTO message (student_id,teacher_id,course_id,title,content,time) VALUES ('12345','83821','CS-190','test','test','2019-12-16');
INSERT INTO message (student_id,teacher_id,course_id,title,content,time) VALUES ('12345','10101','CS-347','test','test','2019-12-16');
INSERT INTO message (student_id,teacher_id,course_id,title,content,time) VALUES ('19991','32343','HIS-351','test','test','2019-12-16');
INSERT INTO message (student_id,teacher_id,course_id,title,content,time) VALUES ('23121','12121','FIN-201','test','test','2019-12-16');
INSERT INTO message (student_id,teacher_id,course_id,title,content,time) VALUES ('44553','22222','PHY-101','test','test','2019-12-16');
INSERT INTO message (student_id,teacher_id,course_id,title,content,time) VALUES ('45678','45565','CS-101','test','test','2019-12-16');
INSERT INTO message (student_id,teacher_id,course_id,title,content,time) VALUES ('45678','83821','CS-319','test','test','2019-12-16');
INSERT INTO message (student_id,teacher_id,course_id,title,content,time) VALUES ('54321','83821','CS-190','test','test','2019-12-16');
INSERT INTO message (student_id,teacher_id,course_id,title,content,time) VALUES ('54321','76766','BIO-101','test','test','2019-12-16');
INSERT INTO message (student_id,teacher_id,course_id,title,content,time) VALUES ('76653','45565','CS-319','test','test','2019-12-16');
INSERT INTO message (student_id,teacher_id,course_id,title,content,time) VALUES ('98765','10101','CS-101','test','test','2019-12-16');
INSERT INTO message (student_id,teacher_id,course_id,title,content,time) VALUES ('98988','76766','BIO-101','test','test','2019-12-16');
INSERT INTO message (student_id,teacher_id,course_id,title,content,time) VALUES ('98988','76766','BIO-301','test','test','2019-12-16');

SELECT photo FROM messageShow WHERE student_id='12345';

SELECT * FROM message;

UPDATE message SET status = 1
WHERE id in (SELECT message_id FROM reply);

SELECT * FROM takes WHERE teacher_id='76766';

SELECT * FROM message;

SELECT * FROM teacher;

SELECT * FROM course;

SELECT * FROM department;

SELECT * FROM student;

SELECT * FROM user;

INSERT INTO takes (student_id,teacher_id,course_id) VALUES ('32504','77777','SP-101');
INSERT INTO message (student_id,teacher_id,course_id,title,content,time) VALUES ('32503','77777','SP-101','test','test','2019-12-16');

SELECT * FROM teachercourse;
SELECT * FROM studentCourse;
SELECT * FROm department;
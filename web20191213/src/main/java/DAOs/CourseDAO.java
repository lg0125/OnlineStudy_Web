package DAOs;

import Beans.*;
import Views.CourseStudent;
import Views.StudentCourse;
import Views.TeacherCourse;

import java.util.ArrayList;
import java.util.HashSet;

public class CourseDAO extends BaseDAO {

    public ArrayList<Course> query(){
        Class<Course> clazz = Course.class;
        String sql = "SELECT * FROM course ";
        Object[] param = {};
        return executeQuery(clazz,sql,param.length,param);
    }

    public ArrayList<TeacherCourse> queryTeacherCourseOrderCourse(){
        Class<TeacherCourse> clazz = TeacherCourse.class;
        String sql = "SELECT * FROM teacherCourse ORDER BY course_id ";
        Object[] param = {};
        return executeQuery(clazz,sql,param.length,param);
    }

    public ArrayList<TeacherCourse> queryTeacherCourseByCourse(String course_id){
        Class<TeacherCourse> clazz = TeacherCourse.class;
        String sql = "SELECT * FROM teacherCourse WHERE course_id=? ";
        Object[] param = {course_id};
        return executeQuery(clazz,sql,param.length,param);
    }

    public ArrayList<TeacherCourse> queryTeacherCourse(String teacher_id){
        Class<TeacherCourse> clazz = TeacherCourse.class;
        String sql = "SELECT * FROM teacherCourse WHERE teacher_id=? ";
        Object[] param = {teacher_id};
        return executeQuery(clazz,sql,param.length,param);
    }

    public ArrayList<Course> query(String course_id){
        Class<Course> clazz = Course.class;
        String sql = "SELECT * FROM course WHERE id=? ";
        Object[] param = {course_id};
        return  executeQuery(clazz,sql,param.length,param);
    }

    public ArrayList<CourseStudent> queryCourseStudentAvail(String course_id,String teacher_id){
        Class<CourseStudent> clazz = CourseStudent.class;
        String sql = "SELECT * FROM courseStudent WHERE course_id=? AND teacher_id=? AND status=1 ";
        Object[] param = {course_id,teacher_id};
        return executeQuery(clazz,sql,param.length,param);
    }

    public ArrayList<CourseStudent> queryCourseStudentNotAvail(String course_id,String teacher_id){
        Class<CourseStudent> clazz = CourseStudent.class;
        String sql = "SELECT * FROM courseStudent WHERE course_id=? AND teacher_id=? AND status=0 ";
        Object[] param = {course_id,teacher_id};
        return executeQuery(clazz,sql,param.length,param);
    }

    public Boolean changeAvail(String course_id,String teacher_id,String student_id,Integer status){
        String sql = "UPDATE takes SET status=? WHERE course_id=? AND teacher_id=? AND student_id=? ";
        Object[] param = {status,course_id,teacher_id,student_id};
        return executeStatement(sql,param);
    }

    public ArrayList<StudentCourse> queryStudentCourseByCourse(String course_id){
        Class<StudentCourse> clazz = StudentCourse.class;
        String sql = "SELECT * FROM studentCourse WHERE course_id=? ";
        Object[] param = {course_id};
        return executeQuery(clazz,sql,param.length,param);
    }

    public ArrayList<StudentCourse> queryStudentCourseByTeacher(String teacher_id){
        Class<StudentCourse> clazz = StudentCourse.class;
        String sql = "SELECT * FROM studentCourse WHERE teacher_id=? ";
        Object[] param = {teacher_id};
        return executeQuery(clazz,sql,param.length,param);
    }

    public ArrayList<StudentCourse> queryStudentCourse(String student_id){
        Class<StudentCourse> clazz = StudentCourse.class;
        String sql = "SELECT * FROM studentCourse WHERE student_id=? ";
        Object[] param = {student_id};
        return executeQuery(clazz,sql,param.length,param);
    }

    public ArrayList<StudentCourse> queryStudentCourseOrderBYDept(String student_id){
        Class<StudentCourse> clazz = StudentCourse.class;
        String sql = "SELECT * FROM studentCourse WHERE student_id=? ORDER BY course_dept";
        Object[] param = {student_id};
        return executeQuery(clazz,sql,param.length,param);
    }

    public ArrayList<StudentCourse> queryStudentCourseOrderBYTeacher(String student_id){
        Class<StudentCourse> clazz = StudentCourse.class;
        String sql = "SELECT * FROM studentCourse WHERE student_id=? ORDER BY teacher_name";
        Object[] param = {student_id};
        return executeQuery(clazz,sql,param.length,param);
    }

    public ArrayList<Department> queryDepartment(){
        Class<Department> clazz = Department.class;
        String sql = "SELECT * FROM department ";
        Object[] param = {};
        return executeQuery(clazz,sql,param.length,param);
    }

    public Boolean add(Course course){
        String sql = "INSERT INTO course (id,name,dept_name,introduction) "
                    +"VALUES (?,?,?,?) ";
        Object[] param = {course.getId(),course.getName(),course.getDept_name(),course.getIntroduction()};
        return executeStatement(sql,param);
    }

    public ArrayList<Teacher> queryTeacher(){
        Class<Teacher> clazz = Teacher.class;
        String sql = "SELECT * FROM teacher ";
        Object[] param = {};
        return executeQuery(clazz,sql,param.length,param);
    }

    public ArrayList<Teacher> queryTeacher(String id){
        Class<Teacher> clazz = Teacher.class;
        String sql = "SELECT * FROM teacher WHERE id=? ";
        Object[] param = {id};
        return executeQuery(clazz,sql,param.length,param);
    }

    public Boolean addTeacher(Teacher teacher){
        String sql = "INSERT INTO teacher (id,password,name,rank_name,dept_name,introduction) "
                    +"VALUES (?,?,?,?,?,?) ";

        String sqlUser = "INSERT INTO user (id,password,type) VALUES (?,?,'teacher')";

        Object[] param = {teacher.getId(),teacher.getPassword(),teacher.getName(),teacher.getRank_name(),teacher.getDept_name(),teacher.getIntroduction()};

        Object[] paramUser = {teacher.getId(),teacher.getPassword()};

        return executeStatement(sql,param) && executeStatement(sqlUser,paramUser);
    }

    public Boolean updateTeacher(Teacher teacher){
        String sql = "UPDATE teacher SET password=?,name=?,rank_name=?,dept_name=?,introduction=? WHERE id=? ";
        Object[] param = {teacher.getPassword(),teacher.getName(),teacher.getRank_name(),teacher.getDept_name(),teacher.getIntroduction(),teacher.getId()};
        return executeStatement(sql,param);
    }

    public Boolean deleteTeacher(String id){
        String sql = "DELETE FROM teacher WHERE id=? ";
        Object[] param = {id};
        return executeStatement(sql,param);
    }

    public Boolean addTeaches(String teacher_id,String course_id){
        String sql = "INSERT INTO teaches (teacher_id,course_id) "
                    +"VALUES (?,?) ";
        Object[] param = {teacher_id,course_id};
        return executeStatement(sql,param);
    }

    public Boolean deleteTeaches(String teacher_id){
        String sql = "DELETE FROM teaches WHERE teacher_id=? ";
        Object[] param = {teacher_id};
        return executeStatement(sql,param);
    }

    public Boolean deleteCourseByCourseTeacherId(String course_id,String teacher_id){
        MessageDAO message_dao = new MessageDAO();
        ArrayList<Message> message_list = message_dao.queryMessageByCourse(course_id, teacher_id);
        String sql_reply = "DELETE FROM reply WHERE message_id=? ";
        String sql_message = "DELETE FROM message WHERE course_id=? AND teacher_id=? ";
        for(Message message : message_list){
            Object[] paramReply = {message.getId()};
            executeStatement(sql_reply,paramReply);
        }
        String sql_takes = "DELETE FROM takes WHERE course_id=? AND teacher_id=? ";
        String sql_teaches = "DELETE FROM teaches WHERE course_id=? AND teacher_id=? ";
        Object[] param = {course_id,teacher_id};
        return executeStatement(sql_message,param) && executeStatement(sql_takes,param) && executeStatement(sql_teaches,param);
    }

    public ArrayList<Rank> queryRank(){
        Class<Rank> clazz = Rank.class;
        String sql = "SELECT * FROM teacher_Rank ";
        Object[] param = {};
        return executeQuery(clazz,sql,param.length,param);
    }

    public Boolean addDept(Department department){
        String sql = "INSERT INTO department (name,building,budget) "
                    +"VALUES (?,?,?) ";
        Object[] param = {department.getName(),department.getBuilding(),department.getBudget()};
        return executeStatement(sql,param);
    }

    public Boolean updateDept(Department department) {
        String sql = "UPDATE department SET building=?,budget=? WHERE name=? ";
        Object[] param = {department.getBuilding(), department.getBudget(), department.getName()};
        return executeStatement(sql, param);
    }

    public void deleteDeptAll(String dept_name){
        String sql_reply = "DELETE FROM reply WHERE message_id=? ";
        String sql_message = "DELETE FROM message WHERE course_id=? AND teacher_id=? ";
        String sql_takes = "DELETE FROM takes WHERE course_id=? AND teacher_id=? ";
        String sql_teaches = "DELETE FROM teaches WHERE course_id=? AND teacher_id=? ";

        Class<Student> clazzStudent = Student.class;
        Class<Course> clazzCourse = Course.class;
        Class<Teacher> clazzTeacher = Teacher.class;
        String sql_student = "SELECT *  FROM student WHERE dept_name=? ";
        String sql_course = "SELECT *  FROM course WHERE dept_name=? ";
        String sql_teacher = "SELECT *  FROM teacher WHERE dept_name=? ";
        Object[] paramDept = {dept_name};
        ArrayList<Student> student_list = executeQuery(clazzStudent,sql_student,paramDept.length,paramDept);
        ArrayList<Course> course_list = executeQuery(clazzCourse,sql_course,paramDept.length,paramDept);
        ArrayList<Teacher> teacher_list = executeQuery(clazzTeacher,sql_teacher,paramDept.length,paramDept);
        HashSet<String> courseIdSet = new HashSet<>();
        HashSet<String> teacherIdSet = new HashSet<>();
        HashSet<String> studentIdSet = new HashSet<>();

        for(Student student:student_list){studentIdSet.add(student.getId());}
        for(Course course:course_list){courseIdSet.add(course.getId());}
        for(Teacher teacher:teacher_list){teacherIdSet.add(teacher.getId());}

        HashSet<StudentCourse> studentCourseSet = new HashSet<>();
        HashSet<TeacherCourse> teacherCourseSet = new HashSet<>();

        for(String id:courseIdSet){
            ArrayList<StudentCourse> tempList = queryStudentCourseByCourse(id);
            studentCourseSet.addAll(tempList);
        }
        for(String id:teacherIdSet){
            ArrayList<StudentCourse> tempList = queryStudentCourseByTeacher(id);
            studentCourseSet.addAll(tempList);
        }
        for(String id:studentIdSet){
            ArrayList<StudentCourse> tempList = queryStudentCourse(id);
            studentCourseSet.addAll(tempList);
        }

        for(String id:teacherIdSet){
            ArrayList<TeacherCourse> tempList = queryTeacherCourse(id);
            teacherCourseSet.addAll(tempList);
        }
        for(String id:courseIdSet){
            ArrayList<TeacherCourse> tempList = queryTeacherCourseByCourse(id);
            teacherCourseSet.addAll(tempList);
        }

        MessageDAO message_dao = new MessageDAO();

        for(StudentCourse obj:studentCourseSet){
            ArrayList<Message> message_list = message_dao.queryMessageByCourse(obj.getCourse_id(),obj.getTeacher_id());
            for(Message message:message_list){
                Object[] paramReply = {message.getId()};
                executeStatement(sql_reply,paramReply);
            }
            Object[] param = {obj.getCourse_id(),obj.getTeacher_id()};
            executeStatement(sql_message,param);
            executeStatement(sql_takes,param);
            executeStatement(sql_teaches,param);
        }

        for(TeacherCourse obj:teacherCourseSet){
            Object[] param = {obj.getCourse_id(),obj.getTeacher_id()};
            executeStatement(sql_teaches,param);
        }

        String sql_courseDelete = "DELETE FROM course WHERE id=? ";
        String sql_studentDelete = "DELETE FROM student WHERE id=? ";
        String sql_teacherDelete = "DELETE FROM teacher WHERE id=? ";
        String sql_userDelete = "DELETE FROM user WHERE id=? ";
        String sql_deptDelete = "DELETE FROM department WHERE name=? ";

        for(String id:courseIdSet){
            Object[] param = {id};
            executeStatement(sql_courseDelete,param);
        }

        for(String id:studentIdSet){
            Object[] param = {id};
            executeStatement(sql_userDelete,param);
            executeStatement(sql_studentDelete,param);
        }

        for(String id:teacherIdSet){
            Object[] param = {id};
            executeStatement(sql_userDelete,param);
            executeStatement(sql_teacherDelete,param);
        }

        Object[] param = {dept_name};
        executeStatement(sql_deptDelete,param);
    }

    public ArrayList<Department> queryDept(String name){
        Class<Department> clazz = Department.class;
        String sql = "SELECT * FROM department WHERE name=? ";
        Object[] param = {name};
        return executeQuery(clazz,sql,param.length,param);
    }

    public Boolean delete(String id){
        String sql = "DELETE FROM course WHERE id=? ";
        Object[] param = {id};
        return executeStatement(sql,param);
    }

    public Boolean updateCourse(Course course){
        String sql = "UPDATE course SET name=?,dept_name=?,introduction=? WHERE id=? ";
        Object[] param = {course.getName(),course.getDept_name(),course.getIntroduction(),course.getId()};
        return executeStatement(sql,param);
    }

    public Boolean deleteTakesTeacherId(String teacher_id){
        String sql = "DELETE FROM takes WHERE teacher_id=? ";
        Object[] param = {teacher_id};
        return executeStatement(sql,param);
    }

}

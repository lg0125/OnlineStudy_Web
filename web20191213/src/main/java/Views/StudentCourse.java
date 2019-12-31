package Views;

public class StudentCourse {

    private String student_id;
    private String student_name;
    private Integer status;

    private String teacher_id;
    private String teacher_name;
    private String rank_name;
    private String course_id;
    private String course_name;
    private String course_dept;

    public String getCourse_dept() {
        return course_dept;
    }

    public void setCourse_dept(String course_dept) {
        this.course_dept = course_dept;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setRank_name(String rank_name) {
        this.rank_name = rank_name;
    }

    public String getRank_name() {
        return rank_name;
    }

}

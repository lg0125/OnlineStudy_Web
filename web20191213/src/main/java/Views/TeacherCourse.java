package Views;

public class TeacherCourse {

    private String teacher_id;
    private String teacher_name;
    private String rank_name;
    private String course_id;
    private String course_name;
    private String course_dept;

    public void setCourse_dept(String course_dept) {
        this.course_dept = course_dept;
    }

    public String getCourse_dept() {
        return course_dept;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public String getRank_name() {
        return rank_name;
    }

    public void setRank_name(String rank_name) {
        this.rank_name = rank_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

}

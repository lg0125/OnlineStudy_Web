package Beans;

public class ScanMsgReply {
    private String course_name;
    private String course_dept;
    private String teacher_name;
    private String title;
    private String content;

    public void setCourse_dept(String course_dept) {
        this.course_dept = course_dept;
    }

    public String getCourse_dept() {
        return course_dept;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

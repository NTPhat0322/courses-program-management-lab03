package data;

import util.Inputer;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.TreeSet;

public class Topic implements Comparable<Topic>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String code;
    private String name;
    private String type;
    private String title;
    private int duration;
    private TreeSet<Course> courses = new TreeSet<>();

    public Topic(String code, String name, String type, String title, int duration) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.title = title;
        this.duration = duration;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                '}';
    }

    /**
     * sort topic in tree by code
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Topic o) {
        return this.code.compareTo(o.code);
    }

    /**
     *
     */
    public void addCourse() {
//        phải yêu cầu nhập topic code bên ngoài main, sau đó kiểm tra
//        là topic nào và vào topic đó để dùng hàm addCourse() này

        String cCode, cName, cType, cTitle;
        LocalDate beginDate, endDate;
        double tuitionFee;

        boolean loop = false;
        do{
            loop = false;
            cCode = Inputer.inputAString("Input code of course", true);
            if(searchCourseByCode(code) != null) {
                System.out.println("Course already exists");
                loop = true;
            }
        }while(loop);
        cName = Inputer.inputAString("Input name of course", true);
        cType = Inputer.inputAString("Input type of course", true);
        cTitle = Inputer.inputAString("Input title of course", true);
        beginDate = Inputer.inputLocalDate("Input begin date of course (YYYY-MM-DD)");
        endDate = Inputer.inputLocalDate("Input end date of course (YYYY-MM-DD)");
        tuitionFee = Inputer.inputADouble("Input tuition fee of course");
        courses.add(new Course(cCode, cName, cType, cTitle, beginDate, endDate, tuitionFee, this.getCode()));
    }

    public Course searchCourseByCode(String code) {
        Iterator<Course> it = courses.iterator();
        while (it.hasNext()) {
            Course course = it.next();
            if (course.getCode().equalsIgnoreCase(code))
                return course;
        }
        return null;
    }
}

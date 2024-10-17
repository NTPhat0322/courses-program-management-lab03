package data;

import java.io.Serial;
import java.io.Serializable;
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
     * adding course belonging to this topic
     * @param course
     */
    public void addCourse(Course course) {
        courses.add(course);
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
}

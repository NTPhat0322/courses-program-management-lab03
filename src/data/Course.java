package data;

import java.util.Date;
import java.util.TreeSet;

public class Course implements Comparable<Course>{

    private String code;
    private String name;
    private String type;
    private String title;
    private Date beginDate;
    private Date endDate;
    private double tuitionFee;
    private String topicCode;
    private TreeSet<Learner> learners = new TreeSet<>();

    public Course(String code, String name, String type, String title, Date beginDate, Date endDate, double tuitionFee, String topicCode) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.title = title;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.tuitionFee = tuitionFee;
        this.topicCode = topicCode;
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

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getTuitionFee() {
        return tuitionFee;
    }

    public void setTuitionFee(double tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    public String getTopicCode() {
        return topicCode;
    }

    public void setTopicCode(String topicCode) {
        this.topicCode = topicCode;
    }

    @Override
    public String toString() {
        return "Course{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", tuitionFee=" + tuitionFee +
                ", topicCode='" + topicCode + '\'' +
                '}';
    }

    public void addLearner(Learner learner) {
        learners.add(learner);
    }

    @Override
    public int compareTo(Course o) {
        return this.code.compareTo(o.code);
    }
}

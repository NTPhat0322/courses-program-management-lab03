package data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.TreeSet;

public class Course implements Comparable<Course>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String code;
    private String name;
    private String type;
    private String title;
    private LocalDate beginDate;
    private LocalDate endDate;
    private double tuitionFee;
    private String topicCode;
    //private TreeSet<Learner> learners = new TreeSet<>();

    public Course(String code, String name, String type, String title, LocalDate beginDate, LocalDate endDate, double tuitionFee, String topicCode) {
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

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
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


    /**
     * sort course by code
     * @param o the object to be compared.
     * @return 1 to swap
     */
    @Override
    public int compareTo(Course o) {
        return this.code.compareTo(o.code);
    }

    /**
     * count number of pass learner in this course
     * @param learners the list all learners
     * @return the number of pass leaner
     */
    public int countNumOfLearnersPassInCourse(LearnerTree learners) {
        int count = 0;
        Iterator<Learner> it = learners.iterator();
        while (it.hasNext()) {
            Learner learner = it.next();
            if(learner.getCourseCode().equalsIgnoreCase(this.code) && learner.checkPass()) {
                count++;
            }
        }
        return count;
    }


    /**
     * count how many leaner is learning at course
     * @param learners is the list of learners
     * @return number of learner
     */
    public int countLearnersInCourse(LearnerTree learners) {
        Iterator<Learner> it = learners.iterator();
        int count = 0;
        while(it.hasNext()) {
            Learner learner = it.next();
            if(learner.getCourseCode().equalsIgnoreCase(this.code)) count++;
        }
        return count;
    }
}

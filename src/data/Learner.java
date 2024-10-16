package data;

import java.util.Date;

public class Learner implements Comparable<Learner>{


    private String code;
    private String name;
    private Date birthDay;
    private int score;
    private int courseCode;

    public Learner(String code, String name, Date birthDay, int score, int courseCode) {
        this.code = code;
        this.name = name;
        this.birthDay = birthDay;
        this.score = score;
        this.courseCode = courseCode;
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

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(int courseCode) {
        this.courseCode = courseCode;
    }

    @Override
    public String toString() {
        return "Learner{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", birthDay=" + birthDay +
                ", score=" + score +
                ", courseCode=" + courseCode +
                '}';
    }

    @Override
    public int compareTo(Learner o) {
        return this.code.compareTo(o.code);
    }
}

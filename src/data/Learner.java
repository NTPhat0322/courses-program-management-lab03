package data;

import util.FileName;

import java.time.LocalDate;
import java.util.Date;

public class Learner implements Comparable<Learner>{


    private String code;
    private String name;
    private LocalDate birthDay;
    private double score;
    private String courseCode;

    public Learner(String code, String name, LocalDate birthDay, double score, String courseCode) {
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

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
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

    public boolean checkPass() {
        if(this.getScore() >= FileName.SCORE_TO_PASS) {
            return true;
        }
        return false;
    }

    /**
     * sort learner in tree by code
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Learner o) {
        return this.code.compareTo(o.code);
    }
}

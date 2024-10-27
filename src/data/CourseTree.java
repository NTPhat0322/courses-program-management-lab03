package data;

import util.Inputer;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class CourseTree extends TreeSet<Course> {
    /**
     *  adding a course to topic
     */
    public void addCourse(TopicTree topics) {
        String cCode, cName, cType, cTitle, topicCode;
        LocalDate beginDate, endDate;
        double tuitionFee;

        boolean loop = false;
        do{
            loop = false;
            cCode = Inputer.inputAString("Input code of course", true);
            if(searchCourseByCode(cCode) != null) {
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

        do{
            loop = false;
            topicCode = Inputer.inputAString("Input topic code of course", true);
            if((topics.searchTopicByCode(topicCode) == null)) {
                System.out.println("Course does not exists");
                loop = true;
            }
        }while(loop);
        this.add(new Course(cCode, cName, cType, cTitle, beginDate, endDate, tuitionFee, topicCode));
    }
    /**
     * search course by its code
     * @param code is the code of Course to search
     * @return the course
     */
    public Course searchCourseByCode(String code) {
        //trước khi tìm thì mình cần phải biết được cái topic id của cái course đó
        Iterator<Course> it = this.iterator();
        while (it.hasNext()) {
            Course course = it.next();
            if (course.getCode().equalsIgnoreCase(code))
                return course;
        }
        return null;
    }

    /**
     * deleting a course
     * @param code is the code of course that you want to delete
     */
    public void deleteCourse(String code) {
        Course t = searchCourseByCode(code);
        if (t == null) {
            System.out.println("Course does not exist");
            return;
        }
        System.out.println("The information of course");
        System.out.println(t);
        int choice = Inputer.inputAnIntegerInRange("Are you sure you want to delete this code(0/1)", -1, 2);
        if (choice == 1) {
            this.remove(t);
            System.out.println("Deleted the course");
        }
    }

    //display
    public void printAllCourse(LearnerTree learners) {
        ArrayList<Course> tmp = new ArrayList<>();
        for(Course c : this) {
            tmp.add(c);
        }
        Comparator<Course> t = new Comparator<Course>() {
            @Override
            public int compare(Course o1, Course o2) {
                return o1.getBeginDate().compareTo(o2.getBeginDate());
            }
        };
        tmp.sort(t);
        for(Course c : tmp) {
            int numOStudent = c.countLearnersInCourse(learners);
            System.out.println(c +
                    ", Status: " + numOStudent +
                    ", Num of pass: " + c.countNumOfLearnersPassInCourse(learners) +
                    ", Incomes: " + (c.countLearnersInCourse(learners)*c.getTuitionFee()));
        }
    }



    public void saveToFile(String fileName) {
        File file = new File(fileName);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for(Course c : this) {
                oos.writeObject(c);
            }
            oos.close();
            fos.close();
            System.out.println("Save successfully");
        } catch (Exception e) {
            System.out.println("Save failed");
        }
    }

    public void loadFromFile(String fileName) {
        File file = new File(fileName);
        try{
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Course t = null;
            do {
                t = (Course) ois.readObject();
                if(t != null) {
                    this.add(t);
                }
            }while(t != null);
        } catch (Exception e) {
            System.out.println("Load failed");
        }
    }
}

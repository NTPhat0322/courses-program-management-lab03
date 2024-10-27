package data;

import util.Inputer;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class CourseTree extends TreeSet<Course> {
    /**
     * Display course with format "status, no pass, fee"
     * @param learners the list of learner to count No.Learner
     */
    public void displayCourseByName(LearnerTree learners) {
        List<Course> t = searchCourseByName();
        for(Course c : t) {
            System.out.println("Status: " + c.countLearnersInCourse(learners) +
                    ", Pass: " + c.countNumOfLearnersPassInCourse(learners) +
                    ", TuitionFee: " + c.getTuitionFee());

        }
    }

    /**
     * searching course by name
     * @return the list of course
     */
    public List<Course> searchCourseByName() {
        List<Course> courses = new ArrayList<>();
        String name = Inputer.inputAString("Input name of course", true);
        for(Course c: this) {
            if(c.getName().contains(name))
                courses.add(c);
        }
        return courses;
    }

    /**
     * Display course with format "status, no pass, fee"
     * @param learners the list of learner to count No.Learner
     */
    public void displayCourseByTopic(LearnerTree learners) {
        List<Course> t = searchCourseByTopic();
        for(Course c : t) {
            System.out.println("Status: " + c.countLearnersInCourse(learners) +
                               ", Pass: " + c.countNumOfLearnersPassInCourse(learners) +
                               ", TuitionFee: " + c.getTuitionFee());

        }
    }

    /**
     * searching course by topic code
     * @return the list of course
     */
    public List<Course> searchCourseByTopic() {
        List<Course> courses = new ArrayList<>();
        String topic = Inputer.inputAString("Input topic's code", true);
        for(Course c: this) {
            if(c.getTopicCode().equalsIgnoreCase(topic))
                courses.add(c);
        }
        return courses;
    }

    /**
     * updating the course
     */
    public void updateCourse() {
        String code = Inputer.inputAString("Input code of couse", true);
        Course s = searchCourseByCode(code);
        if (s == null) {
            System.out.println("Course does not exist");
            return;
        }
        System.out.println("Information before updating");
        System.out.println(s.toString());
        String name, type, title, topicCode;
        LocalDate beginDate, endDate;
        double tuitionFee;
        name = Inputer.inputAString("Input name of couse", false);
        if(!name.isEmpty()) s.setName(name);
        type = Inputer.inputAString("Input type of couse", false);
        if(!type.isEmpty()) s.setType(type);
        title = Inputer.inputAString("Input title of couse", false);
        if(!title.isEmpty()) s.setTitle(title);
        beginDate = Inputer.inputLocalDate("Input begin date of couse");
        s.setBeginDate(beginDate);
        endDate = Inputer.inputLocalDate("Input end date of couse");
        s.setEndDate(endDate);
        tuitionFee = Inputer.inputADouble("Input tuition fee of couse", 0);
        s.setTuitionFee(tuitionFee);
        System.out.println("The information after updating");
        System.out.println(s.toString());
    }

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
     */
    public void deleteCourse() {
        String code = Inputer.inputAString("Input a code of course", true);
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

    /**
     * saving datat to file
     * @param fileName is the file name
     */
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

    /**
     * loading daa from file
     * @param fileName is the file name
     */
    public void loadFromFile(String fileName) {
        File file = new File(fileName);
        try{
            if(!file.exists())
                file.createNewFile();
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Course t = null;
            do {
                try {
                    t = (Course) ois.readObject();
                    if(t != null) {
                        this.add(t);
                    }
                }catch(EOFException a) {
                    break;
                }
            } while(t != null);
        } catch (Exception e) {

        }
    }
}

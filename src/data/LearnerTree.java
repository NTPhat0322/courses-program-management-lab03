package data;

import util.FileName;
import util.Inputer;

import java.io.*;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.TreeSet;

public class LearnerTree extends TreeSet<Learner> {

    /**
     * adding new leaner to list
     * @param courses is the list of courses that training center educate
     */
    public void addLearner(CourseTree courses) {
        String code, name, courseCode;
        double score;
        LocalDate birthDay;
        boolean loop = false;
        do{
            loop = false;
            code = Inputer.inputAString("Input the code of leaner", true);
            if(searchLearnerByCode(code) != null){
                System.out.println("The learner already exists!");
                loop = true;
            }
        }while(loop);
        name = Inputer.inputAString("Input the name of leaner", true);
        birthDay = Inputer.inputLocalDate("Input the birth day of leaner");
        do{
            loop = false;
            courseCode = Inputer.inputAString("Input the course's code of leaner", true);
            Course t = courses.searchCourseByCode(courseCode);
            if(t == null){
                System.out.println("The course doesn't exist!");
                loop = true;
            }
            //cho tối đa 1 khóa học chỉ dc 30 sinh viên
            //chạy dc tới đây là đã tìm được course rồi
            else{
                if(t.countLearnersInCourse(this) >= FileName.MAXIMUM_SIZE_OF_COURSE) { //kiểm tra số lượng học sinh
                    //hiện tại của course
                    System.out.println("The course is already full");
                    loop = true; //tìm khóa khác
                }
            }
        }while(loop);

        score = Inputer.inputADouble("Input the score of leaner", 0);
        this.add(new Learner(code, name, birthDay, score, courseCode));
        System.out.println("Adding successfully!");
    }

    /**
     * print all leaner
     */
    public void printAllLeaner() {
        Iterator<Learner> it = this.iterator();
        while(it.hasNext()){
            Learner t = it.next();
            String status = t.checkPass() ? "Pass" : "Fail";
            System.out.println("Code: " + t.getCode() +
                               ", Name: " + t.getName() +
                               ", Score: " + t.getScore() +
                               ", Course: " + t.getCourseCode() +
                               ", Status: " + status);
        }
    }

    /**
     * update score for learner
     */
    public void updateScoreForLeaner() {
        String code = Inputer.inputAString("Input a code of learner",true);
        Learner t = searchLearnerByCode(code);
        if(t == null){
            System.out.println("The learner doesn't exist!");
            return;
        }
        double score = Inputer.inputADouble("Input new score of leaner", 0);
        t.setScore(score);
        System.out.println("The information after updating score");
        System.out.println(t);
    }

    /**
     * searching leaner
     * @param code of the leaner
     * @return a leaner
     */
    public Learner searchLearnerByCode(String code) {
        Iterator<Learner> it = iterator();
        while (it.hasNext()) {
            Learner l = it.next();
            if (l.getCode().equalsIgnoreCase(code)) return l;
        }
        return null;
    }

    /**
     * save data to file
     * @param fileName is the filename
     */
    public void saveToFile(String fileName) {
        File file = new File(fileName);
        try {
            if(!file.exists())
                file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for(Learner l : this)
                oos.writeObject(l);
            oos.close();
            fos.close();
            System.out.println("Successfully saved the file!");
        } catch (Exception e) {
            System.out.println("Save failed!");
        }
    }

    /**
     * loading data from file
     * @param fileName
     */
    public void loadFromFile(String fileName) {
        File file = new File(fileName);
        try {
            if(!file.exists())
                file.createNewFile();
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Learner l = null;
            do {
                try {
                    l = (Learner) ois.readObject();
                    if(l != null)
                        this.add(l);
                }catch(EOFException a) {
                    break;
                }
            } while(l != null);
            ois.close();
            fis.close();
        } catch (Exception e) {

        }
    }
}

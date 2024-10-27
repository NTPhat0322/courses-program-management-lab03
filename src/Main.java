import data.CourseTree;
import data.LearnerTree;
import data.Topic;
import data.TopicTree;
import ui.Menu;
import util.FileName;

import java.util.ArrayList;
import java.util.List;

public class Main {
//    public static void main(String[] args) {
//        TopicTree topicTree = new TopicTree();
//        topicTree.loadFromFile(FileName.TOPIC_TREE_BINARY_FILE_NAME);
//        topicTree.showAllTopics();
//    }

    public static void main(String[] args) {
        //load data
        TopicTree tTree = new TopicTree();
        CourseTree cTree = new CourseTree();
        LearnerTree lTree = new LearnerTree();
        tTree.loadFromFile(FileName.TOPIC_TREE_BINARY_FILE_NAME);
        cTree.loadFromFile(FileName.COURSE_TREE_BINARY_FILE_NAME);
        lTree.loadFromFile(FileName.LEARNER_TREE_BINARY_FILE_NAME);

        //tao menu
        Menu menu = new Menu("Welcome to training center management");
        menu.addNewOption("1. Manage the topics");
        menu.addNewOption("2. Manage the courses");
        menu.addNewOption("3. Manage the learners");
        menu.addNewOption("4. Search information");
        menu.addNewOption("5. Save data");
        menu.addNewOption("6. Exit");
        int choice;
        do{
            menu.printMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    Menu subMenuTopics = new Menu("Manage the topics");
                    subMenuTopics.addNewOption("1. Add topics to catalog");
                    subMenuTopics.addNewOption("2. Update topic");
                    subMenuTopics.addNewOption("3. Delete topic");
                    subMenuTopics.addNewOption("4. Display all Topics");
                    int subChoice;
                    subMenuTopics.printMenu();
                    subChoice = subMenuTopics.getChoice();
                    switch (subChoice) {
                        case 1:
                            tTree.addTopic();
                            break;
                        case 2:
                            tTree.updateTopic();
                            break;
                        case 3:
                            tTree.deleteTopic();
                            break;
                        default:
                            tTree.showAllTopics();
                    }
                    break;
                case 2:
                    Menu subMenuCourses = new Menu("Manage the courses");
                    subMenuCourses.addNewOption("1. Add courses to catalog");
                    subMenuCourses.addNewOption("2. Update course");
                    subMenuCourses.addNewOption("3. Delete course");
                    subMenuCourses.addNewOption("4. Display all Courses");
                    int subChoice2;
                    subMenuCourses.printMenu();
                    subChoice2 = subMenuCourses.getChoice();
                    switch (subChoice2) {
                        case 1:
                            cTree.addCourse(tTree);
                            break;
                        case 2:
                            cTree.updateCourse();
                            break;
                        case 3:
                            cTree.deleteCourse();
                            break;
                        default:
                            cTree.printAllCourse(lTree);
                    }
                    break;
                case 3:
                    Menu subMenuLearners = new Menu("Manage the learners");
                    subMenuLearners.addNewOption("1. Add learners to catalog");
                    subMenuLearners.addNewOption("2. Enter scores for learners");
                    subMenuLearners.addNewOption("3. Display learner information");
                    subMenuLearners.printMenu();
                    int subChoice3 = subMenuLearners.getChoice();
                    switch (subChoice3) {
                        case 1:
                            lTree.addLearner(cTree);
                            break;
                        case 2:
                            lTree.updateScoreForLeaner();
                            break;
                        default:
                            lTree.printAllLeaner();
                    }
                    break;
                case 4:
                    Menu subMenuSearch = new Menu("Search information");
                    subMenuSearch.addNewOption("1. Search topic by name");
                    subMenuSearch.addNewOption("2. Search course by topic code");
                    subMenuSearch.addNewOption("3. Search course by name");
                    subMenuSearch.printMenu();
                    int subChoice4 = subMenuSearch.getChoice();
                    switch (subChoice4) {
                        case 1:
                            tTree.displayTopicBySearchName();
                            break;
                        case 2:
                            cTree.displayCourseByTopic(lTree);
                            break;
                        case 3:
                            cTree.displayCourseByName(lTree);
                            break;
                    }
                    break;
                case 5:
                    tTree.saveToFile(FileName.TOPIC_TREE_BINARY_FILE_NAME);
                    cTree.saveToFile(FileName.COURSE_TREE_BINARY_FILE_NAME);
                    lTree.saveToFile(FileName.LEARNER_TREE_BINARY_FILE_NAME);
                    break;
                default:
                    System.out.println("See you again");
            }
        }while(choice != 6);
    }
}
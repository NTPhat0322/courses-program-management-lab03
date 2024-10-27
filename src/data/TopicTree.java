package data;

import util.Inputer;

import java.io.*;
import java.util.*;

public class TopicTree extends TreeSet<Topic> {

    public void deleteTopic(String code) {
        Topic t = searchTopicByCode(code);
        if (t == null) {
            System.out.println("Topic does not exist");
            return;
        }
        System.out.println("The information of the topic that you want to delete is");
        System.out.println(t);
        int choice = Inputer.inputAnIntegerInRange("Are you sure to delete (0-no/1-yes)", -1, 2);
        if(choice == 1) {
            this.remove(t);
            System.out.println("delete successfully");
        }
    }

    /**
     * update new information for topic
     * @param code is the code of the Topic that you want to update
     */
    public void updateTopic(String code) {
        Topic t = searchTopicByCode(code);
        //check whether topic exist or not
        if (t == null) {
            System.out.println("The topic does not exist");
            return;
        }
        //enter new information
        String name, type, title;
        int duration;
        //input name
        name = Inputer.inputAString("Input new name (press Enter to do not change)", false);
        if(!name.isEmpty()) t.setName(name);
        //input type
        type = Inputer.inputAString("Input new type (press Enter to do not change))", false);
        if(!type.isEmpty()) t.setType(type);
        //input title
        title = Inputer.inputAString("Input new title (press Enter to do not change)", false);
        if(!title.isEmpty()) t.setTitle(title);
        //input duration
        duration = Inputer.inputAnInteger("Input duration (how many month??) - press 0 to do not change", -1);
        if(duration != 0) t.setDuration(duration);

        System.out.println("The information of the Topic after updating");
        System.out.println(t.toString());
    }

    /**
     * show all of topics
     */
    public void showAllTopics() {
        Comparator<Topic> comparator = new Comparator<Topic>() {
            @Override
            public int compare(Topic o1, Topic o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        ArrayList<Topic> tmp = new ArrayList<>();
        Iterator it = this.iterator();
        while (it.hasNext()) {
            tmp.add((Topic) it.next());
        }
        Collections.sort(tmp, comparator);
        for (Topic t : tmp) {
            System.out.println(t.toString());
        }
    }

    /**
     * add new topic
     */
    public void addTopic() {
        String code, name, type, title;
        int duration;
        //input code
        Topic tmp = null;
        do {
            code = Inputer.inputAString("Input code of the topic", true).toUpperCase();
            tmp = searchTopicByCode(code);
            if(tmp != null)
                System.out.println("This code exists");
        } while (tmp != null);

        //input name
        name = Inputer.inputAString("Input name of the topic", true);
        //input type
        type = Inputer.inputAString("Input type of the topic(long/short)", true);
        //input title
        title = Inputer.inputAString("Input title of the topic", true);
        //input duration
        duration = Inputer.inputAnInteger("Input duration of the topic(how many month??)", 0);

        Topic t = new Topic(code, name, type, title, duration);
        this.add(t);
        System.out.println("Topic added");
    }

    /**
     * searching topic by code of the topic
     * @param code the code of the topic
     * @return the topic having your input code
     */
    public Topic searchTopicByCode(String code) {
        Iterator<Topic> it = this.iterator();
        while (it.hasNext()) {
            Topic t = it.next();
            if(t.getCode().equalsIgnoreCase(code))
                return t;
        }
        return null;
    }

    /**
     * save all topics to binary file
     * @param fileName is a name of file that you would like to save to
     */
    public void saveToFile(String fileName) {
        File file = new File(fileName);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for(Topic t : this) {
                oos.writeObject(t);
            }
            oos.close();
            fos.close();
            System.out.println("Save successfully");
        } catch (Exception e) {
            System.out.println("Save failed");
        }
    }

    /**
     * load data of topics from file
     * @param fileName is a name of file that you would like to load from
     */
    public void loadFromFile(String fileName) {
        File file = new File(fileName);
        try{
            FileInputStream fos = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fos);
            Topic t = null;
            do {
                t = (Topic) ois.readObject();
                if(t != null) {
                    this.add(t);
                }
            } while(t != null);
            fos.close();
            ois.close();
        } catch (Exception e) {
            System.out.println("Load failed");
        }
    }
}

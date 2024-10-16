package data;

import util.Inputer;

import java.util.*;

public class TopicTree extends TreeSet<Topic> {
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

    public void addTopic() {
        String code, name, type, title;
        int duration;
        //input code
        code = Inputer.inputAString("Input code of the topic", true);
        //input name
        name = Inputer.inputAString("Input name of the topic", true);
        //input type
        type = Inputer.inputAString("Input type of the topic", true);
        //input title
        title = Inputer.inputAString("Input title of the topic", true);
        //input duration
        duration = Inputer.inputAnInteger("Input duration of the topic", -1);


    }
}

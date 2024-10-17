import data.TopicTree;
import util.FileName;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        //test cac ham
        TopicTree tTree = new TopicTree();
        tTree.loadFromFile(FileName.TOPIC_TREE_BINARY_FILE_NAME);
        tTree.showAllTopics();
    }
}
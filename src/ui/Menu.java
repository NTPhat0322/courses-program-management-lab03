
package ui;

import java.util.ArrayList;
import util.Inputer;

public class Menu {
    private String menuTitle;
    private ArrayList<String> optionList = new ArrayList<>();

    /**
     * Constructor
     * @param menuTitle is a menu title
     */
    public Menu(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    /**
     * get a menu title
     * @return menu title
     */
    public String getMenuTitle() {
        return menuTitle;
    }

    /**
     * setter of menu title
     * @param menuTitle menu title you want to change
     */
    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    /**
     * getter option list of menu
     * @return
     */
    public ArrayList<String> getOptionList() {
        return optionList;
    }

    /**
     * setter option list of menu
     * @param optionList
     */
    public void setOptionList(ArrayList<String> optionList) {
        this.optionList = optionList;
    }

    /**
     * add new option to menu
     * @param newOption new option that you want to put into menu list
     */
    public void addNewOption(String newOption) {
        boolean exist = false;
        for (String str : optionList) {
            if(str.equalsIgnoreCase(newOption)) {
                exist = true;
                break;
            }
        }
        if(exist) {
            System.out.println("Your inputed option is exist");
        } else {
            optionList.add(newOption);
        }
    }

    /**
     * get the choice of user
     * @return a choice integer that is inputed by user
     */
    public int getChoice() {
        int quantity = optionList.size();
        int choice = 0;
        if (quantity == 0)
            System.out.println("your menu has no option!!!");
        else {
            String inputMsg = "Input your choice between " + 1 + " and " + quantity;
            choice = Inputer.inputAnIntegerInRange(inputMsg, 0, quantity + 1);
        }
        return choice;
    }

    /**
     * print menu list to screen for user
     */
    public void printMenu() {
        if (optionList.isEmpty()) {
            System.out.println("Menu has no option");
            return;
        }
        System.out.println("------------------------");
        System.out.println(menuTitle);
        for (String str : optionList) {
            System.out.println(str);
        }
    }
}

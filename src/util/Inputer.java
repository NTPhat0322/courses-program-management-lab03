package util;

import java.util.Scanner;
import java.util.TreeSet;

public class Inputer {
    private static Scanner sc = new Scanner(System.in);

    /**
     * check whether your input is an integer or not
     * @param inputMsg is the message will be printed to user
     * @return an integer
     */
    public static int inputAnInteger(String inputMsg) {
        int rs = 0;
        boolean loop;
        do {
            try {
                System.out.println(inputMsg);
                rs = Integer.parseInt(sc.nextLine());
                loop = false;
            } catch (NumberFormatException e) {
                System.out.println("Your input is not an integer");
                loop = true;
            }

        } while(loop);
        return rs;
    }

    /**
     * check whether input is an integer that is greater than lowerBound or not
     * @param inputMsg is the message will be printed to user
     * @param lowerBound the lower bound of your inputed integer
     * @return an integer > lowerBound
     */
    public static int inputAnInteger(String inputMsg, int lowerBound) {
        int rs = 0;
        boolean loop;
        do {
            loop = false;
            rs = inputAnInteger(inputMsg);
            if (rs <= lowerBound) {
                System.out.println("Your integer is not greater than " + lowerBound);
                loop = true;
            }
        } while(loop);
        return rs;
    }

    /**
     * input an integer that is limit in range
     * @param inputMsg: the message that you want to print
     * @param lowerBound: the minimum value of valid input
     * @param upperBound: the maximum vale of valid input
     * @return the valid input
     */
    public static int inputAnIntegerInRange(String inputMsg, int lowerBound, int upperBound) {
        int rs = 0;
        boolean isLoop = false;
        if(lowerBound > upperBound) {
            int tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }

        do {
            isLoop = false;
            rs = inputAnInteger(inputMsg, lowerBound); //rs hiện tại đang là 1 integer
            //và nó đang lớn hơn lowerBound
            //check điều kiện < upperBound
            if(rs >= upperBound) {
                System.out.println("Your input is not lower than " + upperBound);
                isLoop = true;
            }
        } while (isLoop);
        return rs;
    }


    /**
     * input a string that is not empty
     * @param inputMsg is the message will be printed to user
     * @param checkEmpty true: check empty, false: not check empty
     * @return a string is trim
     */
    public static String inputAString(String inputMsg, boolean checkEmpty) {
        String rs;
        boolean loop;
        do {
            loop = false;
            System.out.println(inputMsg);
            rs = sc.nextLine();
            rs = rs.trim();
            if(checkEmpty) {//nếu hàm có cần check empty thì vào check
                if(rs.isEmpty()) {
                    System.out.println("Your inputed string is empty");
                    loop = true;
                }
            }
        } while (loop);
        return rs;
    }

//    public static boolean checkDuplicated(TreeSet<Object> tree, String code) {
//
//        return true;
//    }

}

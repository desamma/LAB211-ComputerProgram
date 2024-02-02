package Common;

import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    Scanner sc = new Scanner((System.in));
    public int getInt(String td) {
        System.out.print(td);
        return Integer.parseInt(sc.nextLine());
    }

    public int getInt(String td, String errorMsg) {
        System.out.print(td);
        String s = sc.nextLine();
        if (s.isEmpty()) return -1;
        while (!s.matches("[0-9]+") && !isPositiveInteger(s)) {
            System.out.println("\u001B[31m" + errorMsg + "\u001B[0m");
            s = getString(td);
        }
        return Integer.parseInt(s);
    }

    public String getString(String td) {
        System.out.print(td);
        return sc.nextLine();
    }

    public String getString(String td, String errorMsg) {
        System.out.print(td);
        String s = sc.nextLine();
        if (s.isEmpty()) return "";
        while (!s.matches("[A-Za-z ]+")) {
            System.out.println("\u001B[31m" + errorMsg + "\u001B[0m");
            s = getString(td);
        }
        return s;
    }

    public Double getDouble(String td) {
        System.out.print(td);

        while (true) {
            try {
                return Double.valueOf(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please input number");
                System.out.print(td + ": ");
            }
        }
    }

    private boolean isPositiveInteger(String s) {
        try {
            int num = Integer.parseInt(s);
            return num > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
package transport;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Account {
    private String firstName;
    private String lastName;
    private Dept dept;
    private Comp comp;
    private String email;
    private int storage = Const.DEF_SIZE;
    private String password;

    public void createNewAcc() {
        final Scanner scan = new Scanner(System.in);
        System.out.println("**********************************");
        System.out.println("WELCOME TO BANK OF AMERICA");
        System.out.println("**********************************\n");
        System.out.print("Enter your first name: ");
        firstName = scan.nextLine();
        System.out.print("Enter your last name: ");
        lastName = scan.nextLine();

        System.out.println("Which department are you in?");
        System.out.println("\n1. " + Dept.Accounting.toString());
        System.out.println("2. " + Dept.Admin.toString());
        System.out.println("3. " + Dept.Economist.toString());
        System.out.println("4. " + Dept.IT.toString());
        System.out.print("\n>>> Department: ");

        int deptChoice = Integer.parseInt(scan.nextLine());
        while (deptChoice < 1 || deptChoice > 3) {
            System.out.print("Enter 1 or 2 or 3 or 4 only: ");
            deptChoice = Integer.parseInt(scan.nextLine());
        }

        switch(deptChoice) {
            case 1 -> dept = Dept.Accounting;
            case 2 -> dept = Dept.Admin;
            case 3 -> dept = Dept.Economist;
            case 4 -> dept = Dept.IT;
        }

        System.out.print("\nWhich company are you in?");

        System.out.println("\n1. " + Comp.Google.toString());
        System.out.println("2. " + Comp.Microsoft.toString());
        System.out.println("3. " + Comp.Sainsburys.toString());
        System.out.print("\n>>> Company: ");

        int compChoice = Integer.parseInt(scan.nextLine());
        while (compChoice < 1 || compChoice > 3) {
            System.out.print("Enter 1 or 2 or 3 only: ");
            compChoice = Integer.parseInt(scan.nextLine());
        }

        switch(compChoice) {
            case 1 -> comp = Comp.Google;
            case 2 -> comp = Comp.Microsoft;
            case 3 -> comp = Comp.Sainsburys;
        }

        System.out.print("Enter your password difficulty (1/2/3): ");
        int passDifficulty = Integer.parseInt(scan.nextLine());

        while (passDifficulty < 1 || passDifficulty > 3) {
            System.out.print("Enter 1 or 2 or 3 only: ");
            passDifficulty = Integer.parseInt(scan.nextLine());
        }

        scan.close();

        email = firstName + "." + lastName + "@" + dept + "." + comp + ".com";
        email = email.toLowerCase();
        password = genPassword(passDifficulty);

        showInfo();
    }

    public void changeStorage(int newStorage) {
        storage = newStorage;
    }

    private void showInfo() {
        System.out.println("**************************************");
        System.out.println("INFO ON ACCOUNT");
        System.out.println("**************************************\n");
        System.out.println("Full Name: " + firstName + " " + lastName);
        System.out.println("Department: " + dept);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        System.out.println("Mailbox Capacity: " + storage + " "
                + Const.DEF_SIZE_UNIT);
    }

    private String genPassword(int difficulty) {
        final int[] ASCII_SYMBOLS = ASCII_Symbols();
        final int[] ASCII_NUMS = IntStream.rangeClosed(48, 57).toArray();
        final int[] ASCII_UP_CASE = IntStream.rangeClosed(65, 90).toArray();
        final int[] ASCII_LOWER_CASE = IntStream.rangeClosed(97, 122).toArray();

        final int[] ASCII_ALPHANUM = new int[ASCII_NUMS.length
                + ASCII_LOWER_CASE.length + ASCII_UP_CASE.length];

        System.arraycopy(ASCII_NUMS, 0, ASCII_ALPHANUM, 0,
                ASCII_NUMS.length);
        System.arraycopy(ASCII_LOWER_CASE, 0, ASCII_ALPHANUM,
                ASCII_NUMS.length, ASCII_LOWER_CASE.length);
        System.arraycopy(ASCII_UP_CASE, 0, ASCII_ALPHANUM,
                ASCII_NUMS.length + ASCII_LOWER_CASE.length,
                ASCII_UP_CASE.length);
        
        switch(difficulty) {
            case 1 -> {
                return lowPassword(ASCII_ALPHANUM);
            }
            case 2 -> {
                return medHighPassword(ASCII_ALPHANUM, ASCII_SYMBOLS,
                        Const.PASS_SIZE_MED);
            }
            case  3 -> {
                return medHighPassword(ASCII_ALPHANUM, ASCII_SYMBOLS,
                        Const.PASS_SIZE_HIGH);
            }
            default -> {return null;}
        }
    }

    private String lowPassword(int[] ASCII_ALPHANUM) {
        StringBuilder pass = new StringBuilder();
        int len = ASCII_ALPHANUM.length;
        for (int i = 0; i < Const.PASS_SIZE_LOW; i++) {
            int randomDec = ASCII_ALPHANUM[(int) (Math.random() * len)];
            char c = (char) randomDec;
            pass.append(c);
        }
        return pass.toString();
    }

    private String medHighPassword(int[] ASCII_ALPHANUM, int[] ASCII_SYMBOLS,
            int passLen) {
        StringBuilder pass = new StringBuilder();
        final int[] ASCII_CHAR = new int[ASCII_ALPHANUM.length
                        + ASCII_SYMBOLS.length];
        System.arraycopy(ASCII_ALPHANUM, 0, ASCII_CHAR, 0,
                ASCII_ALPHANUM.length);
        System.arraycopy(ASCII_SYMBOLS, 0, ASCII_CHAR,
                ASCII_ALPHANUM.length, ASCII_SYMBOLS.length);
        int len = ASCII_CHAR.length;

        for (int i = 0; i < passLen; i++) {
            int randomDec = ASCII_CHAR[(int) (Math.random() * len)];
            char c = (char) randomDec;
            pass.append(c);
        }
        return pass.toString();
    }

    private int[] ASCII_Symbols() {
        final int[] symb_1 = IntStream.rangeClosed(33, 47).toArray();
        final int[] symb_2 = IntStream.rangeClosed(58, 64).toArray();
        final int[] symb_3 = IntStream.rangeClosed(91, 96).toArray();
        final int[] symb_4 = IntStream.rangeClosed(123, 126).toArray();
        final int[] result = new int[symb_1.length + symb_2.length
                + symb_3.length + symb_4.length];

        System.arraycopy(symb_1, 0, result, 0, symb_1.length);
        System.arraycopy(symb_2, 0, result, symb_1.length, symb_2.length);
        System.arraycopy(symb_3, 0, result, symb_1.length
                        + symb_2.length, symb_3.length);
        System.arraycopy(symb_4, 0, result,symb_1.length
                + symb_2.length + symb_3.length, symb_4.length);
        return result;
    }


}

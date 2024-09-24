package Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CheckInput {

    private static Scanner sc = new Scanner(System.in);

    public static int readInt(String prompt) {

        int value = 0;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            try {
                value = sc.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Erreur : La valeur doit être un nombre entier.");
                sc.next();
            }
        }
        return value;
    }

    public static String readString(String prompt) {
        System.out.print(prompt);
        return sc.next();
    }

    public static double readDouble(String prompt) {
        double value = 0.0;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            try {
                value = sc.nextDouble();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Erreur : La valeur doit être un nombre décimal.");
                sc.next();
            }
        }
        return value;
    }


    public static LocalDate readDate(String prompt) {
        LocalDate date = null;
        boolean valid = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (!valid) {
            System.out.print(prompt);
            String input = sc.next();
            try {
                date = LocalDate.parse(input, formatter);
                valid = true;
            } catch (DateTimeParseException e) {
                System.out.println("Erreur : La date doit être au format yyyy-mm-dd.");
            }
        }

        return date;
    }

}
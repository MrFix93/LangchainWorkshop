package nl.infosupport.swagshop;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Assistant assistant = Configuration.assistant();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String in = scanner.nextLine();

            String answer = assistant.chat(in);
            System.out.println(answer);
        }
    }
}
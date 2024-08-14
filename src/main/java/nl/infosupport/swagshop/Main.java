package nl.infosupport.swagshop;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Assistant assistant = Configuration.assistant();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Dit is het begin van jouw gesprek met de Info Support swag shop chatbot!");

        while (scanner.hasNext()) {
            String in = scanner.nextLine();

            String answer = assistant.chat(in);
            System.out.println(answer);
        }
    }
}
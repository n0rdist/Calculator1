package Calculator;

import java.util.Scanner;

public class Calculator {
  private static boolean its_an_arabic_numbers = true;

  private static String[] pars(String input) {
    String[] parsed_input = input.split(" ");
    if (parsed_input.length != 3) {
      Scanner input_a_value_again = new Scanner(System.in);
      System.out.println("Неверный формат ввода. Введите выражение в формате X [+,-,*,:] Y, разделяя каждый символ пробелом");
      input = input_a_value_again.nextLine();
      return pars(input);
    } else {
      return parsed_input;
    }
  }


  public static void main(String[] args) {

    Scanner input_a_value = new Scanner(System.in);
    System.out.print("Введите выражение: ");
    String input = input_a_value.nextLine();
    while (!input.isEmpty()) {
      String[] parsed_input = Calculator.pars(input);
      String operation = parsed_input[1];
      Number values;
      int value1 = 0;
      int value2 = 0;
      // Переводим в int. Если введены римские, выкинет исключение
      try {
        value1 = Integer.parseInt(parsed_input[0]);
        value2 = Integer.parseInt(parsed_input[2]);
        //values = new Arabic(value1, value2, 0);
      } catch (NumberFormatException e) {
        its_an_arabic_numbers = false;
        //System.out.println("Введены римские цифры");
        //values = new Romes(parsed_input[0], parsed_input[2], 0);
      }

      if (its_an_arabic_numbers) {
        values = new Arabic(value1, value2);
      } else {
        values = new Romes(parsed_input[0], parsed_input[2]);
      }

      if (operation.equals("+")) {
        values.sum();
      } else if (operation.equals("-")) {
        values.sub();
      } else if (operation.equals("/") || operation.equals(":")) {
        values.div();
      } else if (operation.equals("*") || operation.equals("x")) {
        values.mul();
      }

      if (its_an_arabic_numbers) {
        System.out.println("Результат: " + values.getResult());
      } else {
        System.out.println("Результат: " + values.getStringResult());
      }
      System.out.println();
      System.out.print("Введите выражение: ");
      input = input_a_value.nextLine();
    }
    System.out.println("Завершена работа приложения");

  }
}
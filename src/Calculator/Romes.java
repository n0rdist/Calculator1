package Calculator;

import static java.util.Collections.nCopies;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Romes extends Number {
  private String romes_value1;
  private String romes_value2;
  private int romes_value1_int;
  private int romes_value2_int;
  private int result_int;
  private String sign = "";
  private String result_string;
  private final String[] roman_letters_9 = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
  enum RomanNumeral {
    I(1), IV(4), V(5), IX(9), X(10),
    XL(40), L(50), XC(90), C(100),
    CD(400), D(500), CM(900), M(1000);

    private int value;

    RomanNumeral(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }

    public static List<RomanNumeral> getReverseSortedValues() {
      return Arrays.stream(values())
              .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
              .collect(Collectors.toList());
    }
  }

  public static String arabicToRoman(int number) {
    if ((number <= 0) || (number > 4000)) {
      throw new IllegalArgumentException(number + " is not in range (0,4000]");
    }

    List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

    int i = 0;
    StringBuilder sb = new StringBuilder();

    while ((number > 0) && (i < romanNumerals.size())) {
      RomanNumeral currentSymbol = romanNumerals.get(i);
      if (currentSymbol.getValue() <= number) {
        sb.append(currentSymbol.name());
        number -= currentSymbol.getValue();
      } else {
        i++;
      }
    }

    return sb.toString();
  }

  Romes(String value1, String value2) {
    this.romes_value1 = value1;
    this.romes_value2 = value2;
    this.romes_value1_int = convert_to_int(romes_value1);
    this.romes_value2_int = convert_to_int(romes_value2);
  }


  /*private String convert_result_to_Romes(int n, int ostatok) {
    ostatok = n % 10;
    if (ostatok != 0) {
      try {
        return convert_result_to_Romes(n - ostatok, 0) + roman_letters_9[ostatok - 1];
      } catch (ArrayIndexOutOfBoundsException e) {
        sign = "-";
        return convert_result_to_Romes(n - ostatok, 0) + roman_letters_9[(ostatok + 1) * -1];
      }
    }
    //Возможность вывести отрицательное римское число
    if (n > 0) {
      n = n - 10;
      return convert_result_to_Romes(n,0) + "X";
    } else if (n < 0) {
      n = n + 10;
      return convert_result_to_Romes(n,0) + "X";
    }   else {
      return sign;
    }
  }*/

  @Override
  public void sum() {
    result_int = romes_value1_int + romes_value2_int;
    result_string = arabicToRoman(result_int);
  }

  @Override
  public void sub() {
    result_int = romes_value1_int - romes_value2_int;
    result_string = arabicToRoman(result_int);
  }

  @Override
  public void div() {
    try {
      result_int = romes_value1_int / romes_value2_int;
      result_string = arabicToRoman(result_int);
    } catch (ArithmeticException e) {
      System.out.print("Проверьте правильность ввода римских цифр. Вероятно введены и арабские и римские одновременно. ");
      return;
    }

  }

  @Override
  public void mul() {
    result_int = romes_value1_int * romes_value2_int;
    result_string = arabicToRoman(result_int);
  }

  @Override
  public int getResult() {
    return result_int;
  }
  public String getStringResult() {
    return result_string;
  }

  private int convert_to_int(String romes_value){
    char[] value_char = romes_value.toCharArray();
    int[] values_int = new int[value_char.length];
    for (int i = 0; i < value_char.length; i++) {
      switch (value_char[i]) {
        case 'I':
          values_int[i] = 1;
          break;
        case 'V':
          values_int[i] = 5;
          break;
        case 'X':
          values_int[i] = 10;
          break;

        default:
          System.out.println("Содержится неверный символ. Проверьте правильность ввода римских цифр:" + "\n" +
                  "I = 1" + "\n" +
                  "V = 5" + "\n" +
                  "X = 10");
          break;
      }
    }
    int result = values_int[0];
    for (int i = 0; i < values_int.length && values_int.length > i + 1; i++) {
      if (values_int[i] >= values_int[i+1]) {
        result += values_int[i+1];
      } else if (values_int[i] < values_int[i+1]) {
        result = result + values_int[i+1] - 2;
      }
    }
    return result;
  }

  public String getRomes_value1() {
    return romes_value1;
  }

  public String getRomes_value2() {
    return romes_value2;
  }

  public void setRomes_value1(String romes_value1) {
    this.romes_value1 = romes_value1;
  }

  public void setRomes_value2(String romes_value2) {
    this.romes_value2 = romes_value2;
  }

  public int getRomes_value1_int() {
    return romes_value1_int;
  }

  public int getRomes_value2_int() {
    return romes_value2_int;
  }

  public void setRomes_value1_int(int romes_value1_int) {
    this.romes_value1_int = romes_value1_int;
  }

  public void setRomes_value2_int(int romes_value2_int) {
    this.romes_value2_int = romes_value2_int;
  }
}
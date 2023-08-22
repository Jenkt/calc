import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class calc {
    private static final Map<String, Integer> ROMAN_NUMERALS = new HashMap<>();
    static {
        ROMAN_NUMERALS.put("I", 1);
        ROMAN_NUMERALS.put("II", 2);
        ROMAN_NUMERALS.put("III", 3);
        ROMAN_NUMERALS.put("IV", 4);
        ROMAN_NUMERALS.put("V", 5);
        ROMAN_NUMERALS.put("VI", 6);
        ROMAN_NUMERALS.put("VII", 7);
        ROMAN_NUMERALS.put("VIII", 8);
        ROMAN_NUMERALS.put("IX", 9);
        ROMAN_NUMERALS.put("X", 10);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String result = calc(input);
        System.out.println(result);
        scanner.close();
    }

    public static String calc(String input) {
        try {
            String[] tokens = input.split("\\s");
            if (tokens.length != 3) {
                throw new IllegalArgumentException("Выражение введено некорректно!");
            }
            int a, b;
            try {
                a = Integer.parseInt(tokens[0]);
                b = Integer.parseInt(tokens[2]);
            } catch (NumberFormatException e) {
                a = parseRomanNumeral(tokens[0]);
                b = parseRomanNumeral(tokens[2]);
                if (a == -1 || b == -1) {
                    throw new IllegalArgumentException("Введите только целые числа или римские цифры (I, II, III, IV, V, ...)");
                }
            }
            if (a < 1 || a > 10 || b < 1 || b > 10) {
                throw new IllegalArgumentException("Числа должны быть от 1 до 10 включительно!");
            }

            int result;
            switch (tokens[1]) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                case "/":
                    result = a / b;
                    break;
                default:
                    throw new IllegalArgumentException("Неподдерживаемая операция! Введите '+', '-', '*' или '/'");
            }

            if (parseRomanNumeral(tokens[0]) != -1 || parseRomanNumeral(tokens[2]) != -1) {
                return convertToRomanNumerals(result);
            } else {
                return String.valueOf(result);
            }
        } catch (IllegalArgumentException e) {
            return "Ошибка: " + e.getMessage();
        }
    }

    private static int parseRomanNumeral(String s) {
        return ROMAN_NUMERALS.getOrDefault(s, -1);
    }

    private static String convertToRomanNumerals(int number) {
        if (number < 1) {
            throw new IllegalArgumentException("Римские числа должны быть только положительными!");
        }

        StringBuilder sb = new StringBuilder();
        int[] numbers = { 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String[] symbols = { "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

        for (int i = 0; i < numbers.length; i++) {
            while (number >= numbers[i]) {
                sb.append(symbols[i]);
                number -= numbers[i];
            }
        }
        return sb.toString();
    }
}
class Main {
    public String calc(String inputString) {
        CalculatorHelper calculatorHelper = new CalculatorHelper();

        String[] input = inputString.split(" ");

        if (input.length != 3) {
            throw new IllegalArgumentException("можно использовать только один формат: число - оператор - число");
        }

        String firstPart = input[0];
        String operator = input[1];
        String secondPart = input[2];

        boolean isFirstRoman = calculatorHelper.isRoman(firstPart);
        boolean isSecondRoman = calculatorHelper.isRoman(secondPart);

        if (isFirstRoman != isSecondRoman) {
            throw new IllegalArgumentException("нужно использовать только римские или арабские числа");
        } else if (isFirstRoman) {
            int a = calculatorHelper.romanToArabic(firstPart);
            int b = calculatorHelper.romanToArabic(secondPart);
            int result = calculatorHelper.mathArabic(a, b, operator);
            if (result < 1) {
                throw new IllegalArgumentException("Результат операции с римскими числами должен быть положительным");
            }
            return calculatorHelper.arabicToRoman(result);
        } else {
            int a = Integer.parseInt(firstPart);
            int b = Integer.parseInt(secondPart);
            if (a < 1 || a > 10 || b < 1 || b > 10) {
                throw new IllegalArgumentException("Арабские числа должны быть от 1 до 10");
            }
            return String.valueOf(calculatorHelper.mathArabic(a, b, operator));
        }
    }

    //Ты так же можешь добавлять свои классы (этот класс, если не потребуется, можно удалить)
    class CalculatorHelper {
        public int mathArabic(int a, int b, String operator) {
            switch (operator) {
                case "+":
                    return a + b;
                case "-":
                    return a - b;
                case "*":
                    return a * b;
                case "/":
                    if (b == 0) {
                        throw new ArithmeticException("на ноль делить нельзя");
                    }
                    return a / b;
                default:
                    throw new IllegalArgumentException("Недопустимый оператор" + operator);
            }
        }

        public String arabicToRoman(int number) {
            StringBuilder result = new StringBuilder();
            if (number < 1) {
                throw new IllegalArgumentException("Результат для римских цифр должен быть положительным");
            }
            while (number >= 10) {
                result.append("X");
                number -= 10;
            }
            while (number >= 9) {
                result.append("IX");
                number -= 9;
            }
            while (number >= 8) {
                result.append("VIII");
                number -= 8;
            }
            while (number >= 7) {
                result.append("VII");
                number -= 7;
            }
            while (number >= 6) {
                result.append("VI");
                number -= 6;
            }
            while (number >= 5) {
                result.append("V");
                number -= 5;
            }
            while (number >= 4) {
                result.append("IV");
                number -= 4;
            }
            while (number >= 3) {
                result.append("III");
                number -= 3;
            }
            while (number >= 2) {
                result.append("II");
                number -= 2;
            }
            while (number >= 1) {
                result.append("I");
                number -= 1;
            }
            return result.toString();
        }

        int romanToArabic(String roman) {
            int result = 0; //переменная result будет хранить итоговое значение
            int previousValue = 0; //переменная previousValue будет отслеживать значение предыдущего символа в римском числе

            for (char ch : roman.toCharArray()) {
                int currentValue = charToValue(ch);
                if (currentValue > previousValue) {
                    result += currentValue - 2 * previousValue;
                } else {
                    result += currentValue;
                }
                previousValue = currentValue;
            }
            return result;
        }

        public int charToValue(char ch) {
            switch (ch) {
                case 'I':
                    return 1;
                case 'V':
                    return 5;
                case 'X':
                    return 10;
                default:
                    throw new IllegalArgumentException("Неккоректный римский символ" + ch);
            }
        }

        boolean isRoman(String num) {
            return num.matches("^(I|V|X|L|C|D|M)+$");
        }

        boolean isArabic(String num) {
            try {
                int value = Integer.parseInt(num);
                return value >= 1 && value <= 10;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }
}

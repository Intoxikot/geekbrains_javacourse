public class Solution {
    public static void main(String[] args) {
        initializeValues();

        System.out.println(getValue(1, 2, 3, 4));
        System.out.println(getValue(2, 5, 1, 7));

        printSign(12);
        printSign(0);
        printSign(-3);

        System.out.println(isNegative(5));
        System.out.println(isNegative(0));
        System.out.println(isNegative(-3));

        printGreeting("nik koteev");

        for (int year = 2010; year <= 2020; year++)
            System.out.println(year + " - " + isLeapYear(year));
    }

    // Создаем переменные с разными типами данных
    public static void initializeValues() {
        byte b = 1;
        short s = 2;
        int i = 3;
        long l = 4L;
        float f = 0.01f;
        double d = 0.00001d;
        boolean flag = true;
        char c = 'c';
    }

    // Написать метод вычисляющий значение a * (b + (c / d))
    public static int getValue(int a, int b, int c, int d) {
        return a * (b + (c / d));
    }

    // Лежит ли сумма чисел в диапазоне [10, 20]
    public static boolean inRange(int a, int b) {
        int sum = a + b;
        return (sum >= 10) && (sum <= 20);
    }

    // Определяет тип числа (+ или -) и выводит информацию в консоль
    public static void printSign(int a) {
        String result;
        if (isNegative(a)) result = "Число отрицательное";
        else result = "Число положительное";
        System.out.println(result); // по условию задачи метод должен печатать в консоль
    }

    // Проверяет отрицательное ли число
    public static boolean isNegative(int a) {
        return a < 0; // согласно условию, ноль считаем положительным
    }

    // Выводит приветствие на экран
    public static void printGreeting(String name) {
        System.out.println("Hello, " + name + "!");
    }

    // Високосный год или нет
    public static boolean isLeapYear(int year) {
        return (year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0));
    }
}
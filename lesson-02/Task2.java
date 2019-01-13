/*      Арифметическая прогрессия

2. Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями​ ​0​ ​3​ ​6​ ​9​ ​12​ ​15​ ​18​ ​21;
*/

import java.util.Arrays;

public class Task2 {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        // Создать арифметическую последовательность с шагом 3
        System.out.println("№2");
        int[] c = initializeArray(8);
        System.out.println("initialize array: " + Arrays.toString(c));
        System.out.println();
    }

    // Инициализировать и заполнить массив значениями последовательности 0 3 6 9 .. в зависимости от size
    public static int[] initializeArray(int size) {
        int[] result = new int[size];
        for (int i = 0; i < size; i++)
            result[i] = i * 3; // 0 3 6 9 12 15 18 21;
        return result;
    }
}
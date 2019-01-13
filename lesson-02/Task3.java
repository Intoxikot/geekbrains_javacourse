/*      Проход по массиву

3. Задать массив [1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1] пройти по нему циклом, и числа меньшие 6 уножить ​на​ ​2;
*/

import java.util.Arrays;

public class Task3 {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        System.out.println("№3");
        int[] d = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println("input array:  " + Arrays.toString(d));
        // Пробуем изменить значения по определенному правилу
        int[] e = changeArray(d);
        System.out.println("result array: " + Arrays.toString(e));
        System.out.println();
    }

    // Меняем значения массива по заданному условию
    public static int[] changeArray(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] < 6) arr[i] *= 2; // удвоить все числа меньше 6
        return arr;
    }
}
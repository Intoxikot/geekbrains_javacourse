/*      Найти баланс

6. ** Написать метод, в который передается не пустой одномерный целочисленный массив,
метод должен вернуть true  если в массиве есть место, в котором сумма левой и правой части массива равны.

    Примеры:
checkBalance([1, 1, 1, || 2, 1]) → true,
checkBalance ([2,  1,  1, 2,  1]) → false,
checkBalance ([10, || 10]) → true,
(граница показана символами ||, эти символы в массив не входят)
 */

import java.util.Arrays;

public class Task6 {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        System.out.println("№6");
        int[][] input = {
            {1, 1, 1, 3}, // true
            {3, 3, 2, 4}, // true
            {1, 2, 1, 4}, // true
            {0, 1, 0, 2}, // false
            {1, 3, 2},    // false
            {2, 3},       // false
            {1, 1},       // true
            {2, 4, 2, 5}, // false
            {0, 0, 0, 0}, // true
        };

        for (int[] array: input)
            System.out.println(Arrays.toString(array) + " - " + checkBalance(array));
        System.out.println();
    }

    // Ищет "середину" в массиве и возвращает true, если она найдена
    public static boolean checkBalance(int[] a) {
        int leftSum = 0;
        int rightSum = sum(a);
        for (int i = 0; i < a.length; i++) {
            leftSum += a[i];
            rightSum -= a[i];
            // System.out.println(leftSum + " | " + rightSum); // Отладка
            if (leftSum == rightSum)
                return true;
        }
        return false;
    }

    // Вспомогательная функция - считает сумму элементов массива
    public static int sum(int[] a) {
        int sum = 0;
        for (int num : a)
            sum += num;
        return sum;
    }
}
/*      Инвертировать массив

1. Задать целочисленный массив, состоящий  из элементов 0 и 1.
Например: [1, 1, 0, 0, 1, 0, 1, 1, 0,​ ​0​ ​].​ ​С​ ​помощью​ ​цикла​ ​и​ ​условия​ ​заменить​ ​0​ ​на​ ​1,​ ​1​ ​на​ ​0;
*/

import java.util.Arrays;

public class Task1 {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        System.out.println("№1");
        int[] a = {1, 0, 1, 1, 1, 0, 1, 1, 1};
        System.out.println("input array:  " + Arrays.toString(a));
        int[] b = invertBinaryArray(a);
        System.out.println("result array: " + Arrays.toString(b));
        System.out.println();
    }

    // Меняет значения массива (состоящего из 1 и 0) на противоположные (1 и 0 на 0 и 1 соответственно)
    public static int[] invertBinaryArray(int[] data) {
        // вместо логического (через if), я решил воспользоваться математическим способом
        for (int i = 0; i < data.length; i++)
            data[i] = Math.abs(data[i] - 1); // дсстаточно вычесть единицу (по модулю)
        return data;
    }
}
/*          Найти минимум и максимум в массиве

5. ** Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи​ ​интернета)
P.S. Вот я помню какая-то штатная функция есть, но она похоже только для коллекций :c
 */

import java.util.Arrays;

public class Task5 {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        // Вообще можно найти оба значения через один проход, но я выделил в два отдельных метода
        System.out.println("№5");
        int[] a1 = {1, 3, 4, 5, 6, 7, 3, 0, 0, 3, 5, 3};
        System.out.println("array: " + Arrays.toString(a1));
        System.out.println("min:   " + min(a1));
        System.out.println("max:   " + max(a1));
        System.out.println();

        // Альтернативный вариант решения
        int[] a2 = {20, 17, 12, 34, 10, 84, 27, 13, 14};
        int[] extremum = getExtremum(a2);
        System.out.println("array: " + Arrays.toString(a2));
        System.out.println("min:   " + extremum[0]);
        System.out.println("max:   " + extremum[1]);
        System.out.println();
    }

    // Найти минимумальное значение в массиве
    public static int min(int[] array) {
        int min = array[0]; // в качестве инициализатора берем первое значение
        for (int item : array) // у нас будет один лишний проход, но такой цикл будет красивше
            if (item < min) min = item;
        return min;
    }

    // Найти максимальное значение в массиве
    public static int max(int[] array) {
        int max = array[0];
        for (int item : array)
            if (item > max) max = item;
        return max;
    }

    // Если производительность имеет значение - решение в один цикл. Находит крайние точки массива (минимум и максимум)
    // P.S. Постарался выбрать более лаконичное имя, чем банальное getMinMax
    public static int[] getExtremum(int[] array) {
        // Мы просто объединяем прерыдущие две функции
        int max = array[0];
        int min = array[0];
        for (int item : array) {
            if (item > max) max = item;
            if (item < min) min = item;
        }
        return new int[]{min, max};
    }
}
/*      Диагональная матрица

4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),​ ​
и​ ​с​ ​помощью​ ​цикла(-ов)​ ​заполнить​ ​его​ ​диагональные​ ​элементы​ ​единицами;
 */

import java.util.Arrays;

public class Task4 {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        // Создаем диагональную матрицу и выводим на экран
        System.out.println("№4");
        System.out.println(Arrays.deepToString(createDiagonalMatrix(5)));
        System.out.println();
    }

    public static int[][] createDiagonalMatrix(int size) {
        int[][] a = new int[size][size];
        for (int i = 0; i < size; i++) // строки и стобцы равны по диагонали, достаточно одного цикла
            a[i][i] = 1;
        return a;
    }
}
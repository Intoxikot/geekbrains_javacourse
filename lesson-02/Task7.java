/*      Сдвиг массива

7. **** Написать метод, которому на вход подается одномерный массив и число n (может быть положительным, или отрицательным),
при этом метод должен сместить все элементымассива на n позиций. Для усложнения задачи нельзя пользоваться вспомогательными массивами.

*/

import java.util.*;

public class Task7 {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        System.out.println("№7");

        int[] input = {0, 1, 2, 3, 4, 5, 6};

        // Выполняем проверку базовых методов (что интересно, что они перезаписывают исходный массив, поэтому выполняем копирование)
        System.out.println("input array: " + Arrays.toString(input));
        System.out.println("right shift: " + Arrays.toString(rightShift(Arrays.copyOf(input, input.length)))); // Сдвигаем вправо
        System.out.println();
        System.out.println("input array: " + Arrays.toString(input));
        System.out.println("left shift:  " + Arrays.toString(leftShift(Arrays.copyOf(input, input.length)))); // Сдвигаем влево
        System.out.println();

        // А теперь выполняем прогон тестов
        for (int t = 0; t < 10; t++) {
            int shift = (int)(Math.random() * 200) + -100; // В диапазоне от -100 до 100
            System.out.println("input: " + Arrays.toString(input));
            System.out.println("shift: " + Arrays.toString(getShifted(Arrays.copyOf(input, input.length), shift)) + " (shifted on " + shift + ")");
            System.out.println();
        }

        // Мне было скучно и я решил добавить еще несколько реализаций
        // Ясно, что обычный массив для таких операций не очень эффективен - нужно использовать очередь
        Deque<Integer> deque = new ArrayDeque<Integer>();
        int shiftValue;
        for (int i = 0; i < 10; i++)
            deque.addLast(i);

        shiftValue = 13;
        System.out.println("deque: " + deque.toString());
        System.out.println("shift: " + rightShift(deque, shiftValue).toString() + " (right shifted on " + shiftValue + ")");
        System.out.println();

        shiftValue = 7;
        System.out.println("deque: " + deque.toString());
        System.out.println("shift: " + leftShift(deque, shiftValue).toString() + " (left shifted on " + shiftValue + ")");
        // System.out.println();
    }

    public static int[] getShifted(int[] data, int shift) {
        shift %= data.length; // Циклическая оптимизация
        if (shift > 0) // Что важно, что ветвление выполняется снаружи, а не в цикле
            for (int s = 0; s < shift; s++) // Мы выполняем сдвиг указанное количество раз
                data = rightShift(data);
        else if (shift < 0)
            for (int s = 0; s < -shift; s++) // Если забыть поставить минус - цикл не выполнится ни разу
                data = leftShift(data);
        return data; // если shift нулевой - просто вернется исходый массив
    }

    // Правый сдвиг массива - сдвигает на один элемент вправо
    public static int[] rightShift(int[] input) {
        int last = input[input.length - 1]; // Сохраняем последний элемент, который будет перезаписан
        for (int i = input.length - 1; i > 0; i--) // Мы идем с конца, чтобы не терять данные
            input[i] = input[i - 1];
        input[0] = last; // Последний элемент становится первым
        return input;
    }

    // Левый сдвиг массива - то же, но в обратную сторону
    public static int[] leftShift(int[] input) {
        int first = input[0]; // Весь смысл мы меняем на противоположный
        for (int i = 0; i < input.length - 1; i++)
            input[i] = input[i + 1];
        input[input.length - 1] = first;
        return input;
    }

    // Для очередей все очень просто и эффективно - убрал/добавил
    public static Deque<Integer> rightShift(Deque<Integer> input, int shift) {
        shift %= input.size();
        for (int s = 0; s < shift; s++)
            input.addFirst(input.removeLast());
        return input;
    }

    public static Deque<Integer> leftShift(Deque<Integer> input, int shift) {
        shift %= input.size();
        for (int s = 0; s < shift; s++)
            input.addLast(input.removeFirst());
        return input;
    }
}
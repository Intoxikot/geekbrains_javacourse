/*      Угадай число

Написать программу, которая загадывает случайное число от 0 до 9, и пользователю дается 3 попытки угадать это число.
При каждой попытке компьютер должен сообщить больше ли указанное пользователем число чем загаданное, или меньше.
После победы или проигрыша выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).
Примечание: решение через while и return
*/

import java.util.Scanner;

public class NumberGame {
    // Настраиваемые параметры игры
    private static final int ATTEMPTS = 3;
    private static final int MIN = 0;
    private static final int MAX = 10;
    private static final int RANGE = MAX + Math.abs(MIN) + 1;

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        Scanner console = new Scanner(System.in);
        do {
            String message = "you're " + (singleGame(console) ? "win!": "lose!");
            System.out.println(message + " wants repeat? (1/0)");
        } while (console.nextInt() == 1); // продолжаем только если 1 (любой иной ввод расценивается как выход)
        System.out.println("googbye!");
    }

    // Одиночная игра - возвращает статус игры (победа или проигрыш)
    private static boolean singleGame(Scanner in) {
        // Начальные игровые установки
        int attempt = 0; // количество попыток
        int randNumber = (int)(Math.random() * RANGE) + MIN; // загадываем число

        // Игровой процесс
        System.out.println("try to guess my number! in range [" + MIN + "," + MAX + "]");
        while (true) { // такой цикл позволит сделать мгновенный выход при соблюдении условий
            attempt++;
            int userNumber = in.nextInt();
            if (userNumber == randNumber) // если число угадано, значит победа
                return true;
            if (attempt >= ATTEMPTS) // если превышено количество попыток, значит проигрыш
                return false;
            if (randNumber > userNumber) System.out.println("my number is greater"); // если ввести while с условием (или for) - этот блок будет выполнятся, даже если он не нужен
            else System.out.println("my number is less");                            // а вводить еще дополнительную проверку слишком избыточно
        }
    }
}
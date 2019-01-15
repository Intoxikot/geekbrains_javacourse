/*      Угадай число

Написать программу, которая загадывает случайное число от 0 до 9, и пользователю дается 3 попытки угадать это число.
При каждой попытке компьютер должен сообщить больше ли указанное пользователем число чем загаданное, или меньше.
После победы или проигрыша выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).
Примечание: решение через while и return
*/

import java.util.Scanner;

public class NumberGame {
    // Настраиваемые параметры игры
    private static int TOTAL_ATTEMPTS = 3;
    private static int MAX_NUMBER = 10;
    private static int MIN_NUMBER = 0;

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Scanner console = new Scanner(System.in);
        do {
            String message = single_game(console) ? "you're win!": "you're lose!";
            System.out.println(message + " wants repeat? (1/0)");
        } while (console.nextInt() == 1); // Продолжаем только если 1 (любой иной ввод расценивается как выход)
        System.out.println("googbye!");
    }

    // Одиночная игра - возвращает статус игры (победа или проигрыш)
    private static boolean single_game(Scanner in) {
        // Начальные игровые установки
        int max = MAX_NUMBER + Math.abs(MIN_NUMBER) + 1; // Не спрашивайте почему так, просто это работает
        int min = MIN_NUMBER;
        boolean win = false;
        int attempt = 0;
        int randNumber = (int)(Math.random() * max) + min;

        // Игровой процесс
        System.out.println("try to guess my number! in range [" + MIN_NUMBER + "," + MAX_NUMBER + "]");
        while (true) {
            attempt++;
            int userNumber = in.nextInt();
            if (userNumber == randNumber) // Если число угадано, значит победа
                return true;
            if (attempt >= TOTAL_ATTEMPTS) // Если превышено количество попыток, значит проигрыш
                return false;
            if (randNumber > userNumber) System.out.println("my number is greater");
            else System.out.println("my number is less");
        }
    }
}
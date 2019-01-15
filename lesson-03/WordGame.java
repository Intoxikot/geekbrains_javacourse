/*      Угадай слово

Создать массив из слов:
String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape",
"melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};

При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя, сравнивает его с загаданным словом и сообщает правильно
ли ответил пользователь. Если слово не угадано, компьютер показывает буквы которые стоят на своих местах. Играем до тех пор, пока игрок не отгадает слово. Используем только маленькие буквы. str.charAt(0) - метод, вернет char, который стоит в слове str на первой позиции

apple – загаданное
apricot - ответ игрока
ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
*/

import java.util.Scanner;

public class WordGame {
    private static String[] words = {
            "apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot",
            "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom",
            "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"
    };

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Scanner console = new Scanner(System.in);
        do {
            single_game(console, words);
            System.out.println("congratulations! you're guess a word.\nwants repeat? (y/n)");
        } while (console.nextLine().equals("y")); // продолжаем только если 'y' (любой иной ввод расценивается как выход)
        // если здесь использовать nextInt(), то при следующем nextLine() считается пустая строка
        System.out.println("googbye!");
    }

    // Одиночная игра - возвращает статус игры (победа или проигрыш)
    private static boolean single_game(Scanner in, String[] words) {
        int someIndex = (int)(Math.random() * words.length + 1);
        String myWord = words[someIndex];
        System.out.println("try to guess my word!");
        while (true) { // игра продолжается до тех пор, пока не будет угадано слово
            // System.out.println(myWord); // отладка
            String userWord = in.nextLine();
            if (userWord.equals(myWord)) // если слово угадано, значит победа
                return true;
            int matches = getNumOfMatch(myWord, userWord);
            if (matches == 0)
                System.out.println("no matches in your word!");
            else
                System.out.println(myWord.substring(0, matches) + "################" + " (" + matches + " matches in word!)");
        }
    }

    private static int getNumOfMatch(String basicWord, String inputWord) {
        int matches = 0;
        for (int i = 0; i < basicWord.length() && i < inputWord.length(); i++)
            if ((int) basicWord.charAt(i) == (int) inputWord.charAt(i))
                matches++;
        return matches;
    }
}
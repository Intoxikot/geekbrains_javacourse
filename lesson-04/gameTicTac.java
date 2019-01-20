
import java.util.Random;
import java.util.Scanner;

// Точка игрового поля (для возвращаемого значения)
class Point {
    public int x = 0;
    public int y = 0;
    public Point(int x, int y) { this.x = x; this.y = y; }
}

// В написании кода я конечно опирался на методичку, но просто переписать код мне недостаточно
// Отчасти я доработал представленное решение, отчасти реализовал свой взгляд на механизм/логику
// Опираться на готовое решение оказалось в разы проще, чем писать с нуля
public class gameTicTac {

    // Имена констант я сделал более удобными для себя, смысл тот же
    public static int SIZE = 5; // размер игрового поля (в моем случае, он квадратный)
    public static final int MAX_ROW = 4; // максимальный размер ряда фишек (собственно, это выигрыш)
    private static char[][] board = new char[SIZE][SIZE]; // игровое поле (почему бы не выделить его в отдельный объект?)

    // Некоторые общие инструменты
    private static Scanner sc = new Scanner(System.in);
    private static Random rand = new Random();

    // Если говорить о заполнении поля значениями, то здесь я пошел в сторону перечислений
    public enum fieldFill {
        EMPTY('•'),
        HUMAN('x'),
        COMP('o');

        private char fill;
        fieldFill(char fill) { this.fill = fill; }
        @Override public String toString() { return Character.toString(fill); }
        public char get() { return fill; }
    }

    // Статусы игры (все, что не PLAY является статусом завершения)
    // Можно было бы воспользоваться классическими 0, 1, -1, но все же так мне нравится больше
    public enum gameStatus {PLAY, WIN, DRAW, LOSE}

    public static void main(String[] args) {
        start();
    }

    public static gameStatus start() {
        gameStatus status = gameStatus.PLAY;
        createBoard();

        showBoard();
        while (true) {
            Point move1 = humanMove();
            status = checkStatus(move1);
            showBoard();
            if (status != gameStatus.PLAY) break;

            Point move2 = compMove();
            status = checkStatus(move2);
            showBoard();
            if (status != gameStatus.PLAY) break;
        }
        System.out.println(getGameMessage(status));
        return status;
    }

    // Создать пустую игровую доску
    private static void createBoard() {
        for (int y = 0; y < SIZE; y++)
            for (int x = 0; x < SIZE; x++)
                board[x][y] = fieldFill.EMPTY.get(); // может использовать String вместо char?
    }

    // Вывод игровой доски на экран
    private static void showBoard() {
        System.out.println();
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++)
                System.out.print("|" + board[x][y]);
            System.out.println("|");
        }
        System.out.println();
    }

    // Проверяет заполненность игровой доски
    private static boolean isFill() {
        for (int y = 0; y < SIZE; y++)
            for (int x = 0; x < SIZE; x++)
                if (board[x][y] == fieldFill.EMPTY.get())
                    return false;
        return true;
    }

    // Вспомогательные методы проверки полей
    private static boolean isEmpty(int x, int y) { return board[x][y] == fieldFill.EMPTY.get(); }
    private static boolean isCorrect(int x, int y) { return x >= 0 && x < SIZE && y >= 0 && y < SIZE; }
    private static boolean isMoveAvailable(int x, int y) { return isCorrect(x, y) && isEmpty(x, y); }

    // Проверяет победную комбинацию, на вход он принимает последний сделанный ход
    // Пошагово проходит по горизонталям, вертикалям и диагоналям и если мы достигаем победного значения - осуществляем выход
    // Возможно, он избыточен для игры с полем 3х3, но я постарался сделать этот метод универсальным - для любых размеров поля и количества фишек
    // Его ограничение только в том, что он работает только для квадратного поля
    private static boolean checkWin(int x, int y, char fill) {
        // Счетчики имеют значение -1 т.к. мы имеем 2 цикла проходящих через нашу точку
        // Смысл в том, что циклы выглядят проще и удобнее читаются
        int vertical = -1;
        for (int i = y; i < SIZE; i++)
            if (board[x][i] == fill) vertical++; else break;
        for (int i = y; i >= 0; i--)
            if (board[x][i] == fill) vertical++; else break;
        if (vertical == MAX_ROW) return true;

        int horizontal = -1;
        for (int i = x; i < SIZE; i++)
            if (board[i][y] == fill) horizontal++; else break;
        for (int i = x; i >= 0; i--)
            if (board[i][y] == fill) horizontal++; else break;
        if (horizontal == MAX_ROW) return true;

        int leftDiagonal = -1;
        for (int a = x, b = y; a >= 0 && b >= 0; a--, b--)
            if (board[a][b] == fill) leftDiagonal++; else break;
        for (int a = x, b = y; a < SIZE && b < SIZE; a++, b++)
            if (board[a][b] == fill) leftDiagonal++; else break;
        if (leftDiagonal == MAX_ROW) return true;

        int rightDiagonal = -1;
        for (int a = x, b = y; a < SIZE && b >= 0; a++, b--)
            if (board[a][b] == fill) rightDiagonal++; else break;
        for (int a = x, b = y; a >= 0 && b < SIZE; a--, b++)
            if (board[a][b] == fill) rightDiagonal++; else break;
        if (rightDiagonal == MAX_ROW) return true;
        return false; // если совпадений не найдено, значит не судьба :c
    }

    // Проверяет статус игры (по последнему ходу)
    private static gameStatus checkStatus(Point move) {
        char mark = board[move.x][move.y];
        if (checkWin(move.x, move.y, mark))
            if (mark == fieldFill.HUMAN.get()) return gameStatus.WIN; else return gameStatus.LOSE;
        else if (isFill())
            return gameStatus.DRAW;
        else
            return gameStatus.PLAY;
    }

    // По игровому статусу возвращает игровое сообщение
    private static String getGameMessage(gameStatus status) {
        switch (status) {
            case WIN: return "you win! nice game";
            case LOSE: return "you lose! dont worry";
            case DRAW: return "game draw! you had a good chance";
            default: return "something wrong";
        }
    }

    // Механизм хода игрока. Мне кажется, что для логики игры будет важно, чтобы этот метод имел возвращаемое значение (хотя можно обойтись и без этого)
    private static Point humanMove() {
        System.out.println("insert your move:");
        int x; int y;
        while (true) {
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
            if (isMoveAvailable(x, y)) break; else System.out.println("uncorrect input. try again");
        }
        System.out.println("your move accepted");
        board[x][y] = fieldFill.HUMAN.get();
        return new Point(x, y);
    }

    // Ход компьютера, до AI конечно ему далеко
    // Есть некоторые мысли как это можно реализовать, но я пока не готов представить свое решение
    // Над этим моментом нужно отдельно подумать
    private static Point compMove() {
        int x; int y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while(!isMoveAvailable(x, y));
        System.out.println("computer player make a move");
        board[x][y] = fieldFill.COMP.get();
        return new Point(x, y);
    }
}
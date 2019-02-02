import java.util.Random;
import java.util.Scanner;

public class MantyHall {
    public static Random random = new Random();
    public static int prize;

    public static void main(String[]args) {
        // console();
        graphic();
    }

    public static void graphic() {
        Form form = new Form();
    }

    // Вся игровая логика сосредоточена в руках ведущего
    public static void console() {
        Scanner sc = new Scanner(System.in);
        int choose;
        boolean again;
        System.out.println("Manty Hall Problem");
        do {
            prize = random.nextInt(3) + 1;
            // System.out.println("prize: " + prize); // debug
            DoorSet doors = new DoorSet(prize);
            doors.show();
            System.out.println("Leader: choose a door");
            choose = sc.nextInt();
            System.out.println("Leader: your choose is " + choose);
            int freeDoor = getAnyFreeDoor(choose, 1);
            doors.openDoor(freeDoor);
            System.out.println("Leader: are you sure? choose a door again");
            choose = sc.nextInt();
            doors.openDoor(choose);
            System.out.println("Leader: you " + ((choose == prize) ? "win!": "lose!") + " do you wants again? (1/0)");
            again = sc.nextInt() == 1;
        } while (again);
    }

    // Возвращается любая пустая дверь
    public static int getAnyFreeDoor(int choose, int indexBegins) {
        int[] free = getFreeDoors(indexBegins);
        // System.out.println("free doors: " + free[0] + " " + free[1]); // debug
        if (choose == prize)
            return free[random.nextInt(2)];
        return (choose == free[0]) ? free[1]: free[0];
    }

    // Из чисел 1, 2, 3 - вычеркивается одно, два других - возвращаются
    // Пришлось добавить ветвление, чтобы не дублировать логику
    public static int[] getFreeDoors(int indexBegins) {
        if (indexBegins == 0)
            return new int[]{(3 + prize - 1) % 3, (3 + prize + 1) % 3}; // начальный индекс с 0 (для графической версии)
        else
            return new int[]{(4 + prize - 1) % 3 + 1, (3 + prize + 1) % 3 + 1}; // начальный индекс с 1 (для консольной версии)
    }
}

// Под дверью подразумевается именно комната (пустая или непустая)
// Двери мы можем открывать, выводить на экран, прятать за ними приз
class Door {
    public enum DoorStatus {OPENED, CLOSED} // дверь может быть закрытой, а может быть открыта
    private DoorStatus status;
    private boolean prize;

    // Константы для консольного вывода
    private static final String CLOSED = "[CLOSED] ";
    private static final String EMPTY = "[EMPTY] ";
    private static final String WIN = "[WIN] ";

    public Door() {
        this.prize = false;
        status = DoorStatus.CLOSED;
    }

    public void addPrize() {
        prize = true;
    }

    public void open() {
        status = DoorStatus.OPENED;
    }
    public void show() {
        System.out.print(getPresentation());
    }

    private String getPresentation() {
        if (status == DoorStatus.OPENED)
            return (prize) ? WIN: EMPTY;
        else
            return CLOSED;
    }
}

// Набор дверей - мы можем делать с ними самые тривиальные действия
// Подразумевается, что осмысленность в эти действия будет добавлять именно ведущий
class DoorSet {
    private Door[] door;

    public DoorSet(int prize) {
        door = new Door[3];
        for (int d = 0; d < door.length; d++)
            door[d] = new Door();
        door[prize - 1].addPrize();
    }

    public void openDoor(int order) {
        if (order < 1) return;
        System.out.println("<door " + order + " is opened>");
        int index = order - 1;
        door[index].open();
        show();
    }

    public void show() {
        for (Door d : door)
            d.show();
        System.out.println();
    }
}